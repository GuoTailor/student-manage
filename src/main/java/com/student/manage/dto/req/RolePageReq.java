package com.student.manage.dto.req;

import com.student.manage.dto.PageReq;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * create by GYH on 2024/5/14
 */
@Data
public class RolePageReq extends PageReq {

    private String roleName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
