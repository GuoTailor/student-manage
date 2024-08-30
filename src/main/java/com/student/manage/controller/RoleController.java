package com.student.manage.controller;

import com.student.manage.domain.Role;
import com.student.manage.dto.PageInfo;
import com.student.manage.dto.ResponseInfo;
import com.student.manage.dto.req.AddRole;
import com.student.manage.dto.req.RolePageReq;
import com.student.manage.sevice.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Tag(name = "角色")
@RestController
@RequestMapping("/system/permission")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "获取所有角色", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<List<Role>> getAllRoles() {
        return ResponseInfo.ok(roleService.getAllRoles());
    }

    @PostMapping("/role/page")
    @Operation(summary = "分页获取角色", security = {@SecurityRequirement(name = "Authorization")})
    public PageInfo<Role> getRoleByPage(@RequestBody RolePageReq req) {
        return roleService.getRoleByPage(req);
    }

    @Operation(summary = "添加角色", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseInfo<Role> addRole(@RequestBody AddRole role) {
        return ResponseInfo.ok(roleService.addRole(role));
    }

    @Operation(summary = "删除角色", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/role/{rid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseInfo<?> deleteRoleById(@PathVariable Integer rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return ResponseInfo.ok("删除成功!");
        }
        return ResponseInfo.failed("删除失败!");
    }

    @Operation(summary = "修改角色", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/role/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseInfo<?> updateRole(@RequestBody Role role) {
        if (roleService.updateRole(role) == 1) {
            return ResponseInfo.ok("修改成功!");
        }
        return ResponseInfo.failed("修改失败!");
    }

}
