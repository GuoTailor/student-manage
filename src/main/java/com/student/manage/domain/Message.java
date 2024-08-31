package com.student.manage.domain;

import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class Message {
    private Integer id;

    /**
     * 标签
     */
    private String label;

    /**
     * 话术
     */
    private String verbalTrick;

    /**
     * 描述
     */
    private String describe;
}