package com.student.manage.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class AddMessageReq {

    /**
     * 标签
     */
    @Schema(description = "标签")
    private String label;

    /**
     * 话术
     */
    @Schema(description = "话术")
    private String verbalTrick;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String describe;
}
