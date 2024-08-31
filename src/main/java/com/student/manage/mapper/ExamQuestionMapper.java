package com.student.manage.mapper;

import com.student.manage.domain.ExamQuestion;

import java.util.List;

/**
 * create by GYH on 2024/8/31
 */
public interface ExamQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamQuestion record);

    int insertOrUpdate(ExamQuestion record);

    int insertOrUpdateSelective(ExamQuestion record);

    int insertSelective(ExamQuestion record);

    ExamQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamQuestion record);

    int updateByPrimaryKey(ExamQuestion record);

    List<ExamQuestion> selectAll();

    List<ExamQuestion> selectByNo(String no);
}
