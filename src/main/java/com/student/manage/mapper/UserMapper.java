package com.student.manage.mapper;

import com.student.manage.domain.Role;
import com.student.manage.domain.User;
import com.student.manage.dto.req.UserPageReq;
import com.student.manage.dto.resp.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by GYH on 2024/5/24
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteAllRole(@Param("userId") Integer userId);

    User findUserByUserName(String username);

    List<Role> findRolesByUserId(Integer userId);

    List<UserInfo> findAll();

    List<UserInfo> findByPage(UserPageReq req);

    List<Integer> findAllId();
}
