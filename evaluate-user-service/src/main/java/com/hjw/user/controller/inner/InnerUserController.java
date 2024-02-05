package com.hjw.user.controller.inner;

import com.hjw.api.service.UserFeignClient;
import com.hjw.common.common.ErrorCode;
import com.hjw.common.exception.BusinessException;
import com.hjw.common.exception.ThrowUtils;
import com.hjw.model.entity.User;
import com.hjw.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/inner")
public class InnerUserController
{
    @Resource
    private UserService userService;

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get/id")
    public User getById(@RequestParam("id") long id, HttpServletRequest request)
    {
        if (id <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return user;
    }
}
