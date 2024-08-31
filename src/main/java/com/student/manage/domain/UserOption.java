package com.student.manage.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class UserOption {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 选项id
     */
    private Integer optionId;

    /**
     * 做题时间
     */
    private LocalDateTime createTime;

    /**
     * 题目id
     */
    private Integer questionId;
}
