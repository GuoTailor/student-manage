package com.student.manage.sevice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.student.manage.domain.Role;
import com.student.manage.dto.PageInfo;
import com.student.manage.dto.req.AddRole;
import com.student.manage.dto.req.RolePageReq;
import com.student.manage.exception.BusinessException;
import com.student.manage.mapper.RoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public PageInfo<Role> getRoleByPage(RolePageReq pageReq) {
        try (Page<Role> page = PageHelper.startPage(pageReq.getPage(), pageReq.getPageSize())) {
            List<Role> byReq = roleMapper.getByReq(pageReq);
            return PageInfo.ok(page.getTotal(), pageReq, byReq);
        }
    }

    public Role addRole(AddRole addRole) {
        if (addRole.getSortNum() != null) {
            int count = roleMapper.countBySort(addRole.getSortNum());
            if (count >= 1) {
                throw new BusinessException("排序重复");
            }
        }
        if (!addRole.getAuthority().startsWith("ROLE_")) {
            addRole.setAuthority("ROLE_" + addRole.getAuthority());
        }
        Role role = new Role();
        BeanUtils.copyProperties(addRole, role);
        roleMapper.insertSelective(role);
        return role;
    }

    public Integer updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
