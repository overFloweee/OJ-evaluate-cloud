package com.hjw.api.service;


import com.hjw.common.common.ErrorCode;
import com.hjw.common.exception.BusinessException;
import com.hjw.model.entity.User;
import com.hjw.model.enums.UserRoleEnum;
import com.hjw.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.hjw.common.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务
 */
// @FeignClient(name = "evaluate-user-service", url = "localhost:8102", path = "/api/inner/user")
// openfeign只能从nacos拿到 host和端口，拿不到content-path，要自己补 /api
@FeignClient(name = "evaluate-user-service", path = "/api/user/inner")
public interface UserFeignClient
{
    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get/id")
    User getById(@RequestParam("id") long id);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    default User getLoginUser(HttpServletRequest request)
    {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null)
        {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    default boolean isAdmin(HttpServletRequest request)
    {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return isAdmin(user);
    }

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user)
    {
        if (user == null)
        {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    default UserVO getUserVO(User user)
    {
        if (user == null)
        {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
