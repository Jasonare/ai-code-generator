package com.ryan.aicodegenerator.model.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ryan.aicodegenerator.consts.UserConstant;
import com.ryan.aicodegenerator.dto.request.ChatHistoryAdminQueryRequest;
import com.ryan.aicodegenerator.dto.request.ChatHistoryQueryRequest;
import com.ryan.aicodegenerator.dto.response.ChatHistoryVO;
import com.ryan.aicodegenerator.enums.MessageTypeEnum;
import com.ryan.aicodegenerator.exception.BizException;
import com.ryan.aicodegenerator.exception.ErrorCode;
import com.ryan.aicodegenerator.exception.ThrowUtils;
import com.ryan.aicodegenerator.model.entity.App;
import com.ryan.aicodegenerator.model.entity.ChatHistory;
import com.ryan.aicodegenerator.model.entity.User;
import com.ryan.aicodegenerator.model.mapper.ChatHistoryMapper;
import com.ryan.aicodegenerator.model.service.AppService;
import com.ryan.aicodegenerator.model.service.ChatHistoryService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对话历史 服务层实现
 *
 * @author Jasonare
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements ChatHistoryService {

    @Resource
    @Lazy
    private AppService appService;

    @Override
    public Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                                      LocalDateTime lastCreateTime,
                                                      User loginUser) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR, "应用ID不能为空");
        ThrowUtils.throwIf(pageSize <= 0 || pageSize > 50, ErrorCode.PARAMS_ERROR, "页面大小必须在1-50之间");
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        // 验证权限：只有应用创建者和管理员可以查看
        App app = appService.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR, "应用不存在");
        boolean isAdmin = UserConstant.ADMIN_ROLE.equals(loginUser.getUserRole());
        boolean isCreator = app.getUserId().equals(loginUser.getId());
        ThrowUtils.throwIf(!isAdmin && !isCreator, ErrorCode.NO_AUTH_ERROR, "无权查看该应用的对话历史");
        // 构建查询条件
        ChatHistoryQueryRequest queryRequest = new ChatHistoryQueryRequest();
        queryRequest.setAppId(appId);
        queryRequest.setLastCreateTime(lastCreateTime);
        QueryWrapper queryWrapper = this.getQueryWrapper(queryRequest);
        // 查询数据
        return this.page(Page.of(1, pageSize), queryWrapper);
    }

    @Override
    public Page<ChatHistory> pageQueryByAdmin(ChatHistoryAdminQueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new BizException(ErrorCode.PARAMS_ERROR, "请求参数不能为空");
        }
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("appId", queryRequest.getAppId())
                .eq("userId", queryRequest.getUserId())
                .eq("messageType", queryRequest.getMessageType())
                .like("message", queryRequest.getKeyword())
                .orderBy("createTime", false)
                .orderBy("id", false);
        return page(Page.of(queryRequest.getPageNum(), queryRequest.getPageSize()), queryWrapper);
    }

    @Override
    public void saveUserMessage(Long appId, Long userId, String message) {
        saveMessage(appId, userId, message, MessageTypeEnum.USER);
    }

    @Override
    public void saveAiMessage(Long appId, Long userId, String message) {
        saveMessage(appId, userId, message, MessageTypeEnum.AI);
    }

    @Override
    public void saveAiErrorMessage(Long appId, Long userId, String errorMessage) {
        String safeErrorMessage = StrUtil.blankToDefault(errorMessage, "AI 回复失败，错误信息为空");
        saveMessage(appId, userId, safeErrorMessage, MessageTypeEnum.AI_ERROR);
    }

    @Override
    public boolean removeByAppId(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR, "应用 id 无效");
        QueryWrapper queryWrapper = QueryWrapper.create().eq("appId", appId);
        return remove(queryWrapper);
    }

    @Override
    public ChatHistoryVO getChatHistoryVO(ChatHistory chatHistory) {
        if (chatHistory == null) {
            return null;
        }
        ChatHistoryVO chatHistoryVO = new ChatHistoryVO();
        BeanUtil.copyProperties(chatHistory, chatHistoryVO);
        return chatHistoryVO;
    }

    @Override
    public List<ChatHistoryVO> getChatHistoryVOList(List<ChatHistory> chatHistoryList) {
        if (CollUtil.isEmpty(chatHistoryList)) {
            return new ArrayList<>();
        }
        return chatHistoryList.stream().map(this::getChatHistoryVO).collect(Collectors.toList());
    }

    /**
     * 通用消息保存逻辑
     */
    private void saveMessage(Long appId, Long userId, String message, MessageTypeEnum messageTypeEnum) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR, "应用 id 无效");
        ThrowUtils.throwIf(userId == null || userId <= 0, ErrorCode.PARAMS_ERROR, "用户 id 无效");
        ThrowUtils.throwIf(messageTypeEnum == null, ErrorCode.PARAMS_ERROR, "消息类型不能为空");
        ThrowUtils.throwIf(StrUtil.isBlank(message), ErrorCode.PARAMS_ERROR, "消息不能为空");
        ChatHistory chatHistory = ChatHistory.builder()
                .appId(appId)
                .userId(userId)
                .message(message)
                .messageType(messageTypeEnum.getValue())
                .build();
        boolean result = save(chatHistory);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "保存对话历史失败");
    }

    public QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        if (chatHistoryQueryRequest == null) {
            return queryWrapper;
        }
        Long id = chatHistoryQueryRequest.getId();
        String message = chatHistoryQueryRequest.getMessage();
        String messageType = chatHistoryQueryRequest.getMessageType();
        Long appId = chatHistoryQueryRequest.getAppId();
        Long userId = chatHistoryQueryRequest.getUserId();
        LocalDateTime lastCreateTime = chatHistoryQueryRequest.getLastCreateTime();
        String sortField = chatHistoryQueryRequest.getSortField();
        String sortOrder = chatHistoryQueryRequest.getSortOrder();
        // 拼接查询条件
        queryWrapper.eq("id", id)
                .like("message", message)
                .eq("messageType", messageType)
                .eq("appId", appId)
                .eq("userId", userId);
        // 游标查询逻辑 - 只使用 createTime 作为游标
        if (lastCreateTime != null) {
            queryWrapper.lt("createTime", lastCreateTime);
        }
        // 排序
        if (StrUtil.isNotBlank(sortField)) {
            queryWrapper.orderBy(sortField, "ascend".equals(sortOrder));
        } else {
            // 默认按创建时间降序排列
            queryWrapper.orderBy("createTime", false);
        }
        return queryWrapper;
    }
}
