package com.ryan.aicodegenerator.model.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.ryan.aicodegenerator.dto.request.ChatHistoryAdminQueryRequest;
import com.ryan.aicodegenerator.dto.request.ChatHistoryQueryRequest;
import com.ryan.aicodegenerator.dto.response.ChatHistoryVO;
import com.ryan.aicodegenerator.model.entity.ChatHistory;
import com.ryan.aicodegenerator.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 对话历史 服务层
 *
 * @author Jasonare
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);

    /**
     * 管理员分页查询全部对话历史（按时间倒序）
     *
     * @param queryRequest 查询参数
     * @return 分页结果
     */
    Page<ChatHistory> pageQueryByAdmin(ChatHistoryAdminQueryRequest queryRequest);

    /**
     * 保存用户消息
     *
     * @param appId   应用 id
     * @param userId  用户 id
     * @param message 消息
     */
    void saveUserMessage(Long appId, Long userId, String message);

    /**
     * 保存 AI 成功消息
     *
     * @param appId   应用 id
     * @param userId  用户 id
     * @param message 消息
     */
    void saveAiMessage(Long appId, Long userId, String message);

    /**
     * 保存 AI 错误消息
     *
     * @param appId        应用 id
     * @param userId       用户 id
     * @param errorMessage 错误消息
     */
    void saveAiErrorMessage(Long appId, Long userId, String errorMessage);

    /**
     * 按应用删除历史消息
     *
     * @param appId 应用 id
     * @return 删除结果
     */
    boolean removeByAppId(Long appId);

    /**
     * 获取对话历史 VO
     *
     * @param chatHistory 对话历史实体
     * @return VO
     */
    ChatHistoryVO getChatHistoryVO(ChatHistory chatHistory);

    /**
     * 获取对话历史 VO 列表
     *
     * @param chatHistoryList 对话历史实体列表
     * @return VO 列表
     */
    List<ChatHistoryVO> getChatHistoryVOList(List<ChatHistory> chatHistoryList);
}
