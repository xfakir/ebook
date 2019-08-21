package com.ncu.ebook.pojo.po.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName : UserAuthorization
 * @Description : 表名: user_authorization_tb
 * @Author : xfakir
 * @Date :2019-01-21 13:53
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class LocalAuthorization implements Serializable {

    private Long localAuthorizationId;

    private UserBasicInfo userBasicInfo;

    /**
     * 账号
     */
    private String principal;

    /**
     * 密码
     */
    private String credential;

    private String roles;

    //private String perms;

    private Integer status;

    private static final long serialVersionUID = 1L;
}