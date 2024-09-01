package com.student.manage.controller;

import com.student.manage.domain.Message;
import com.student.manage.dto.PageInfo;
import com.student.manage.dto.ResponseInfo;
import com.student.manage.dto.req.AddMessageReq;
import com.student.manage.dto.req.MessagePageReq;
import com.student.manage.dto.req.UpdateMessageReq;
import com.student.manage.sevice.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * create by GYH on 2024/9/1
 */
@Tag(name = "消息")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 添加话术
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "添加话术", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Message> addMessage(@RequestBody AddMessageReq addMessageReq) {
        return ResponseInfo.ok(messageService.addMessage(addMessageReq));
    }

    /**
     * 更新话术
     */
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "更新话术", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Message> updateMessage(@RequestBody @Valid UpdateMessageReq addMessageReq) {
        return ResponseInfo.ok(messageService.updateMessage(addMessageReq));
    }

    /**
     * 分页获取话术
     */
    @PostMapping("/find/page")
    @Operation(summary = "分页获取话术", security = {@SecurityRequirement(name = "Authorization")})
    public PageInfo<Message> findMessage(@RequestBody MessagePageReq pageReq) {
        return messageService.getByPage(pageReq);
    }

    /**
     * 删除话术
     */
    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "删除话术", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<?> deleteMessage(@RequestParam("id") Integer id) {
        messageService.deleteMessage(id);
        return ResponseInfo.ok();
    }

    /**
     * 根据id获取话术
     */
    @GetMapping("/find")
    @Operation(summary = "根据id获取话术", security = {@SecurityRequirement(name = "Authorization")})
    public ResponseInfo<Message> findMessage(@RequestParam("id") Integer id) {
        return ResponseInfo.ok(messageService.getMessageById(id));
    }
}
