package com.ryan.aicodegenerator.model.service;

import java.util.List;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.ryan.aicodegenerator.dto.request.AppQueryRequest;
import com.ryan.aicodegenerator.dto.response.AppVO;
import com.ryan.aicodegenerator.model.entity.App;
import com.ryan.aicodegenerator.model.entity.User;

import reactor.core.publisher.Flux;

/**
 * 应用 服务层。
 *
 * @author Jasonare
 */
public interface AppService extends IService<App> {

    /**
     * 获取应用视图对象。
     *
     * @param app 应用
     * @return 应用视图对象
     */
    AppVO getAppVO(App app);

    /**
     * 分页获取当前用户创建的应用列表。
     *
     * @param appQueryRequest 应用查询请求
     * @return 应用视图对象列表
     */
    Page<App> pageQuery(AppQueryRequest appQueryRequest);

    /**
     * 获取应用视图对象列表。
     *
     * @param appList 应用列表
     * @return 应用视图对象列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 对话生成代码应用
     *
     * @param appId
     * @param message
     * @param loginUser
     * @return
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 网站部署
     *
     * @param appId
     * @param loginUser
     * @return
     */
    String deployApp(Long appId, User loginUser);
}
