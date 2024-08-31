package com.student.manage.controller;

import com.student.manage.domain.ExamQuestion;
import com.student.manage.domain.Option;
import com.student.manage.domain.UserOption;
import com.student.manage.dto.PageInfo;
import com.student.manage.dto.ResponseInfo;
import com.student.manage.dto.req.*;
import com.student.manage.dto.resp.TestPaperResp;
import com.student.manage.sevice.ExamQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * create by GYH on 2024/8/31
 */
@Tag(name = "试卷")
@RestController
@RequestMapping("/question")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;

    /**
     * 添加题目
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "添加题目", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<ExamQuestion> addQuestion(@RequestBody @Valid AddExamQuestionReq addExamQuestionReq) {
        return ResponseInfo.ok(examQuestionService.addQuestion(addExamQuestionReq));
    }

    /**
     * 修改题目
     */
    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "修改题目", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<ExamQuestion> updateQuestion(@RequestBody @Valid UpdateExamQuestionReq updateExamQuestionReq) {
        return ResponseInfo.ok(examQuestionService.updateQuestion(updateExamQuestionReq));
    }

    /**
     * 删除题目
     */
    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "删除题目", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Boolean> deleteQuestion(@RequestParam("id") Integer id) {
        examQuestionService.deleteQuestion(id);
        return ResponseInfo.ok(true);
    }

    /**
     * 分页获取题目列表
     */
    @PostMapping("/find/page")
    @Operation(summary = "分页获取题目列表", security = {@SecurityRequirement(name = "Authorization")})
    public PageInfo<ExamQuestion> findQuestionList(@RequestBody QuestionPageReq pageReq) {
        return examQuestionService.getQuestionList(pageReq);
    }

    /**
     * 添加问题选项
     */
    @PostMapping("/option/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "添加问题选项", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Option> addOption(@RequestBody @Valid AddOptionReq addOptionReq) {
        return ResponseInfo.ok(examQuestionService.addOption(addOptionReq));
    }

    /**
     * 修改选项
     */
    @PostMapping("/option/update")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "修改选项", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Option> updateOption(@RequestBody @Valid UpdateOptionReq addOptionReq) {
        return ResponseInfo.ok(examQuestionService.updateOption(addOptionReq));
    }

    /**
     * 获取题目的所以选项
     */
    @GetMapping("/option/find")
    @Operation(summary = "获取题目的所以选项", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<List<Option>> findOptionList(@RequestParam("questionId") Integer questionId) {
        return ResponseInfo.ok(examQuestionService.getOptionListByQuestionId(questionId));
    }

    /**
     * 删除选项
     */
    @GetMapping("/option/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "删除选项", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<?> deleteOption(@RequestParam("id") Integer id) {
        examQuestionService.deleteOption(id);
        return ResponseInfo.ok(true);
    }

    /**
     * 通过问题编号获取试卷
     */
    @GetMapping("/find/testPaper")
    @Operation(summary = "通过问题编号获取试卷", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<List<TestPaperResp>> findTestPaperByNo(@RequestParam("no") String no) {
        return ResponseInfo.ok(examQuestionService.getTestPaperByNo(no));
    }

    /**
     * 提交答案
     */
    @PostMapping("/answer")
    @Operation(summary = "提交答案", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<UserOption> answer(@RequestBody @Valid AnswerReq answerReq) {
        return ResponseInfo.ok(examQuestionService.answer(answerReq));
    }

    /**
     * 获取用户某套试卷的得分
     */
    @GetMapping("/score")
    @Operation(summary = "获取用户某套试卷的得分", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<BigDecimal> getScore(@RequestParam("no") String no) {
        return ResponseInfo.ok(examQuestionService.getScore(no));
    }

    /**
     * 重做某套试卷，清空已选择的答案
     */
    @GetMapping("/clean")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "重做某套试卷，清空已选择的答案", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Boolean> cleanOption(@RequestParam("no") String no) {
        examQuestionService.cleanOption(no);
        return ResponseInfo.ok(true);
    }
}
