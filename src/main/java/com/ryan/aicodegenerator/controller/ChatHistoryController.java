package com.ryan.aicodegenerator.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybatisflex.core.paginate.Page;
import com.ryan.aicodegenerator.annotation.AuthCheck;
import com.ryan.aicodegenerator.common.BaseResponse;
import com.ryan.aicodegenerator.common.ResultUtils;
import com.ryan.aicodegenerator.consts.UserConstant;
import com.ryan.aicodegenerator.dto.request.ChatHistoryAdminQueryRequest;
import com.ryan.aicodegenerator.dto.response.ChatHistoryVO;
import com.ryan.aicodegenerator.exception.ErrorCode;
import com.ryan.aicodegenerator.exception.ThrowUtils;
import com.ryan.aicodegenerator.model.entity.ChatHistory;
import com.ryan.aicodegenerator.model.entity.User;
import com.ryan.aicodegenerator.model.service.ChatHistoryService;
import com.ryan.aicodegenerator.model.service.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 对话历史 控制层
 *
 * @author Jasonare
 */
@RestController
@RequestMapping("/chatHistory")
public class ChatHistoryController {

    @Resource
    private ChatHistoryService chatHistoryService;

    @Resource
    private UserService userService;

    /**
     * 分页查询某个应用的对话历史（游标查询）
     *
     * @param appId          应用ID
     * @param pageSize       页面大小
     * @param lastCreateTime 最后一条记录的创建时间
     * @param request        请求
     * @return 对话历史分页
     */
    @GetMapping("/app/{appId}")
    public BaseResponse<Page<ChatHistory>> listAppChatHistory(@PathVariable Long appId,
                                                              @RequestParam(defaultValue = "10") int pageSize,
                                                              @RequestParam(required = false) LocalDateTime lastCreateTime,
                                                              HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Page<ChatHistory> result = chatHistoryService.listAppChatHistoryByPage(appId, pageSize, lastCreateTime, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 管理员分页查看全部应用的对话历史（按时间倒序）
     */
    @PostMapping("/admin/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<ChatHistoryVO>> listChatHistoryByPageByAdmin(
            @RequestBody ChatHistoryAdminQueryRequest queryRequest) {
        ThrowUtils.throwIf(queryRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(queryRequest.getPageSize() > 100, ErrorCode.PARAMS_ERROR, "每页最多查询 100 条消息");
        Page<ChatHistory> historyPage = chatHistoryService.pageQueryByAdmin(queryRequest);
        Page<ChatHistoryVO> voPage = new Page<>(queryRequest.getPageNum(),
                queryRequest.getPageSize(), historyPage.getTotalRow());
        List<ChatHistoryVO> historyVOList = chatHistoryService.getChatHistoryVOList(historyPage.getRecords());
        voPage.setRecords(historyVOList);
        return ResultUtils.success(voPage);
    }
}
