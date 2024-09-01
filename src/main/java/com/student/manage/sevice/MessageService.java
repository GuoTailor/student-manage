package com.student.manage.sevice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.student.manage.domain.Message;
import com.student.manage.dto.PageInfo;
import com.student.manage.dto.req.AddMessageReq;
import com.student.manage.dto.req.MessagePageReq;
import com.student.manage.dto.req.UpdateMessageReq;
import com.student.manage.mapper.MessageMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * create by GYH on 2024/9/1
 */
@Service
public class MessageService {
    @Resource
    private MessageMapper messageMapper;

    /**
     * 添加话术
     */
    public Message addMessage(AddMessageReq addMessageReq) {
        Message message = new Message();
        BeanUtils.copyProperties(addMessageReq, message);
        messageMapper.insert(message);
        return message;
    }

    /**
     * 更新话术
     */
    public Message updateMessage(UpdateMessageReq addMessageReq) {
        Message message = new Message();
        BeanUtils.copyProperties(addMessageReq, message);
        messageMapper.updateByPrimaryKey(message);
        return message;
    }

    /**
     * 分页获取话术
     */
    public PageInfo<Message> getByPage(MessagePageReq req) {
        try(Page<Message> page = PageHelper.startPage(req.getPage(), req.getPageSize())) {
            return PageInfo.ok(page.getTotal(), req, messageMapper.getAll());
        }
    }

    /**
     * 删除话术
     */
    public void deleteMessage(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id获取话术
     */
    public Message getMessageById(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }
}
