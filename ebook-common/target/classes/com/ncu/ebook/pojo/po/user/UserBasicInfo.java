package com.ncu.ebook.pojo.po.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName : UserBasicInfo
 * @Description : 表名: user_basic_info_tb
 * @Author : xfakir
 * @Date :2019-01-21 03:53
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class UserBasicInfo implements Serializable {
    /**
     * <pre>
     * 用户Id
     * 表字段: java.lang.Long.user_id
     * </pre>
     *
     */
    private Long userId;

    /**
     * <pre>
     * 用户名
     * 表字段: java.lang.String.username
     * </pre>
     *
     */
    private String username;

    /**
     * <pre>
     * 邮箱
     * 表字段: java.lang.String.email
     * </pre>
     *
     */
    private String email;

    /**
     * <pre>
     * 手机号
     * 表字段: java.lang.String.telephone
     * </pre>
     *
     */
    private String telephone;

    /**
     * <pre>
     * 昵称
     * 表字段: java.lang.String.nickname
     * </pre>
     *
     */
    private String nickname;

    /**
     * <pre>
     * 账户状态
     * 表字段: java.lang.Integer.status
     * </pre>
     *
     */
    private Integer status;

    /**
     * <pre>
     * 注册日期
     * 表字段: java.util.Date.register_date
     * </pre>
     *
     */
    private Date registerDate;

    /**
     * <pre>
     * 更新日期
     * 表字段: java.util.Date.update_date
     * </pre>
     *
     */
    private Date updateDate;

    /**
     * <pre>
     * 最后登录日期
     * 表字段: java.util.Date.last_login_date
     * </pre>
     *
     */
    private Date lastLoginDate;

    /**
     * <pre>
     * 最后登录IP
     * 表字段: java.lang.String.last_login_ip
     * </pre>
     *
     */
    private String lastLoginIp;

    private static final long serialVersionUID = 1L;
}