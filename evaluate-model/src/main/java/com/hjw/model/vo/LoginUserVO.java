package com.hjw.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 已登录用户视图（脱敏）
 **/
@Data
public class LoginUserVO implements Serializable
{

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    /**
     * 用户标签
     */
    private String tag;

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
    /**
     * 0 - 正常，1 - 注销，2 - 封号
     */
    private Integer userState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
