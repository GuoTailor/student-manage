package com.student.manage.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class ExamQuestion {
    private Integer id;

    /**
     * 描述
     */
    private String describe;

    /**
     * 得分
     */
    private Double score;

    /**
     * 创建者
     */
    private String createAt;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否多选
     */
    private Boolean multipleChoice;

    /**
     * 题目编号
     */
    private String no;
}
