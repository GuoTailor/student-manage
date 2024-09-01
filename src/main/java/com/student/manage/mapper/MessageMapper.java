package com.student.manage.mapper;

import com.student.manage.domain.Message;

import java.util.List;

/**
 * create by GYH on 2024/8/31
 */
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertOrUpdate(Message record);

    int insertOrUpdateSelective(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> getAll();

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}
