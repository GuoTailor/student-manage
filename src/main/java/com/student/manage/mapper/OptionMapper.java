package com.student.manage.mapper;

import com.student.manage.domain.Option;

import java.util.List;

/**
 * create by GYH on 2024/8/31
 */
public interface OptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Option record);

    int insertOrUpdate(Option record);

    int insertOrUpdateSelective(Option record);

    int insertSelective(Option record);

    Option selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Option record);

    int updateByPrimaryKey(Option record);

    List<Option> selectByQuestionId(Integer questionId);
}