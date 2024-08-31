package com.student.manage.domain;

import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class Option {
    private Integer id;

    /**
     * 题目id
     */
    private Integer questionId;

    /**
     * 选项
     */
    private String optionDescription;

    /**
     * 是否是正确答案
     */
    private Boolean answers;

    /**
     * 编号
     */
    private String no;
}
