package com.student.manage.sevice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.student.manage.domain.ExamQuestion;
import com.student.manage.domain.Option;
import com.student.manage.domain.User;
import com.student.manage.domain.UserOption;
import com.student.manage.dto.PageInfo;
import com.student.manage.dto.req.*;
import com.student.manage.dto.resp.OptionResp;
import com.student.manage.dto.resp.TestPaperResp;
import com.student.manage.exception.BusinessException;
import com.student.manage.mapper.ExamQuestionMapper;
import com.student.manage.mapper.OptionMapper;
import com.student.manage.mapper.UserOptionMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create by GYH on 2024/8/31
 */
@Service
public class ExamQuestionService {
    @Resource
    private ExamQuestionMapper examQuestionMapper;
    @Resource
    private OptionMapper optionMapper;
    @Resource
    private UserOptionMapper userOptionMapper;

    /**
     * 添加题目
     */
    public ExamQuestion addQuestion(AddExamQuestionReq addExamQuestionReq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExamQuestion examQuestion = new ExamQuestion();
        BeanUtils.copyProperties(addExamQuestionReq, examQuestion);
        examQuestion.setCreateAt(user.getName());
        examQuestion.setCreateTime(LocalDateTime.now());
        examQuestionMapper.insertSelective(examQuestion);
        return examQuestion;
    }

    /**
     * 修改题目
     */
    public ExamQuestion updateQuestion(UpdateExamQuestionReq updateExamQuestionReq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExamQuestion examQuestion = new ExamQuestion();
        BeanUtils.copyProperties(updateExamQuestionReq, examQuestion);
        examQuestion.setCreateAt(user.getName());
        examQuestion.setCreateTime(LocalDateTime.now());
        examQuestionMapper.updateByPrimaryKeySelective(examQuestion);
        return examQuestion;
    }

    /**
     * 删除题目
     */
    public void deleteQuestion(Integer id) {
        examQuestionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页获取题目列表
     */
    public PageInfo<ExamQuestion> getQuestionList(QuestionPageReq pageReq) {
        try (Page<ExamQuestion> page = PageHelper.startPage(pageReq.getPage(), pageReq.getPageSize())) {
            List<ExamQuestion> examQuestions = examQuestionMapper.selectAll();
            return PageInfo.ok(page.getTotal(), pageReq, examQuestions);
        }
    }

    /**
     * 添加问题选项
     */
    public Option addOption(AddOptionReq addOptionReq) {
        Option option = new Option();
        BeanUtils.copyProperties(addOptionReq, option);
        optionMapper.insertSelective(option);
        return option;
    }

    /**
     * 修改选项
     */
    public Option updateOption(UpdateOptionReq updateOptionReq) {
        Option option = new Option();
        BeanUtils.copyProperties(updateOptionReq, option);
        optionMapper.updateByPrimaryKeySelective(option);
        return option;
    }

    /**
     * 获取题目的所以选项
     */
    public List<Option> getOptionListByQuestionId(Integer questionId) {
        return optionMapper.selectByQuestionId(questionId);
    }

    /**
     * 删除选项
     */
    public void deleteOption(Integer id) {
        optionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过问题编号获取试卷
     */
    public List<TestPaperResp> getTestPaperByNo(String no) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExamQuestion> examQuestions = examQuestionMapper.selectByNo(no);
        return examQuestions.parallelStream().map(it -> {
            TestPaperResp testPaperResp = new TestPaperResp();
            BeanUtils.copyProperties(it, testPaperResp);
            List<Option> options = optionMapper.selectByQuestionId(it.getId());
            testPaperResp.setOptions(options.stream().map(option -> {
                OptionResp req = new OptionResp();
                BeanUtils.copyProperties(option, req);
                return req;
            }).sorted(Comparator.comparing(OptionResp::getNo)).toList());
            testPaperResp.setAnswer(userOptionMapper.selectByQuestionId(it.getId(), user.getId()).stream().map(option -> {
                OptionResp req = new OptionResp();
                BeanUtils.copyProperties(option, req);
                return req;
            }).toList());
            return testPaperResp;
        }).toList();
    }

    /**
     * 提交答案
     */
    public UserOption answer(AnswerReq answerReq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExamQuestion examQuestion = examQuestionMapper.selectByPrimaryKey(answerReq.getQuestionId());
        List<UserOption> userOptions = userOptionMapper.selectByUserAndQuestion(user.getId(), examQuestion.getId());
        if (!examQuestion.getMultipleChoice() && !userOptions.isEmpty()) {
            throw new BusinessException("此题目为单选题");
        }
        UserOption userOption1 = userOptionMapper.selectByUserAndQuestionAndOption(user.getId(), answerReq.getQuestionId(), answerReq.getOptionId());
        UserOption userOption = new UserOption();
        if (userOption1 != null) {
            userOption.setId(userOption1.getId());
        }
        userOption.setUserId(user.getId());
        userOption.setOptionId(answerReq.getOptionId());
        userOption.setQuestionId(answerReq.getQuestionId());
        userOption.setCreateTime(LocalDateTime.now());
        userOptionMapper.insertOrUpdate(userOption);
        return userOption;
    }

    /**
     * 获取用户某套试卷的得分
     */
    public BigDecimal getScore(String no) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Option> options = userOptionMapper.selectOptionByUserId(user.getId(), no);
        Map<Integer, List<Option>> collect = options.stream().collect(Collectors.groupingBy(Option::getQuestionId));
        BigDecimal score = BigDecimal.ZERO;
        for (Map.Entry<Integer, List<Option>> integerListEntry : collect.entrySet()) {
            Integer questionId = integerListEntry.getKey();
            List<Option> optionList = integerListEntry.getValue();
            ExamQuestion examQuestion = examQuestionMapper.selectByPrimaryKey(questionId);
            Option option = optionList.getFirst();
            if (!examQuestion.getMultipleChoice() && option.getAnswers()) {
                score = score.add(BigDecimal.valueOf(examQuestion.getScore()));
            }
            if (examQuestion.getMultipleChoice() && optionList.stream().allMatch(Option::getAnswers)) {
                score = score.add(BigDecimal.valueOf(examQuestion.getScore()));
            }
        }
        return score;
    }

    /**
     * 重做某套试卷，清空已选择的答案
     */
    public void cleanOption(String no) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userOptionMapper.cleanOption(user.getId(), no);
    }
}
