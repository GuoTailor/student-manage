package com.student.manage.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class AddOptionReq {

    /**
    * 题目id
    */
    @Schema(description = "题目id")
    @NotNull
    private Integer questionId;

    /**
    * 选项
    */
    @Schema(description = "选项描述")
    @NotNull
    private String optionDescription;

    /**
    * 是否是正确答案
    */
    @Schema(description = "是否是正确答案")
    @NotNull
    private Boolean answers;

    /**
     * 编号
     */
    @Schema(description = "编号如A,B,C,D")
    @NotNull
    private String no;
}
