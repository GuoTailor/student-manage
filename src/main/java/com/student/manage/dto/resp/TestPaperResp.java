package com.student.manage.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * create by GYH on 2024/8/31
 */
@Data
@Schema(description = "试卷")
public class TestPaperResp {
    @Schema(description = "题目id")
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

    @Schema(description = "题目选项")
    private List<OptionResp> options;

    @Schema(description = "用户选择的答案")
    private List<OptionResp> answer;
}
