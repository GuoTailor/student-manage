package com.student.manage.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * create by GYH on 2024/8/31
 */
@Data
public class OptionResp {
    private Integer id;

    /**
     * 选项
     */
    @Schema(description = "选项描述")
    private String optionDescription;

    /**
     * 编号
     */
    @Schema(description = "编号如A,B,C,D")
    private String no;
}
