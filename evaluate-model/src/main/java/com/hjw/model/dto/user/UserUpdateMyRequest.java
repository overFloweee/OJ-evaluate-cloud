package com.hjw.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新个人信息请求
 *
 *
 *
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0 - 女，1 - 男
     */
    private Integer gender;

    private static final long serialVersionUID = 1L;
}
