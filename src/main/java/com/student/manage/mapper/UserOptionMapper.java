package com.student.manage.mapper;

import com.student.manage.domain.Option;
import com.student.manage.domain.User;
import com.student.manage.domain.UserOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by GYH on 2024/8/31
 */
public interface UserOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOption record);

    int insertOrUpdate(UserOption record);

    int insertOrUpdateSelective(UserOption record);

    int insertSelective(UserOption record);

    UserOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOption record);

    int updateByPrimaryKey(UserOption record);

    List<Option> selectByQuestionId(@Param("questionId") Integer questionId, @Param("userId") Integer userId);

    UserOption selectByUserAndQuestionAndOption(@Param("userId") Integer userId, @Param("questionId") Integer questionId, @Param("optionId") Integer optionId);

    List<UserOption> selectByUserAndQuestion(@Param("userId") Integer userId, @Param("questionId") Integer questionId);

    List<Option> selectOptionByUserId(@Param("userId") Integer userId, @Param("no") String no);

    int cleanOption(@Param("userId") Integer userId, @Param("no") String no);
}
