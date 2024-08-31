package com.student.manage.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class UpdateExamQuestionReq {
    @NotNull
    private Integer id;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String describe;

    /**
     * 得分
     */
    @Schema(description = "得分")
    private Double score;

    /**
     * 是否多选
     */
    @Schema(description = "是否多选")
    private Boolean multipleChoice;

    /**
     * 题目编号
     */
    @Schema(description = "题目编号")
    private String no;

}
