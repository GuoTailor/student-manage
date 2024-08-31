package com.student.manage.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class AnswerReq {
    @Schema(description = "题目id")
    @NotNull
    private Integer questionId;
    @Schema(description = "选项id")
    @NotNull
    private Integer optionId;
}
