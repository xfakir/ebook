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
 * @Date :2019-01-21 03:53
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class UserAuthorization implements Serializable {
    /**
     * <pre>
     * 
     * 表字段: java.lang.Integer.user_authorization_id
     * </pre>
     *
     */
    private Integer userAuthorizationId;

    /**
     * <pre>
     * 
     * 表字段: java.lang.Long.user_id
     * </pre>
     *
     */
    private Long userId;

    /**
     * <pre>
     * 
     * 表字段: java.lang.Boolean.identify_type
     * </pre>
     *
     */
    private Boolean identifyType;

    /**
     * <pre>
     * 
     * 表字段: java.lang.String.identifier
     * </pre>
     *
     */
    private String identifier;

    /**
     * <pre>
     * 
     * 表字段: java.lang.String.credential
     * </pre>
     *
     */
    private String credential;

    /**
     * <pre>
     * 
     * 表字段: java.lang.Integer.is_third_party
     * </pre>
     *
     */
    private Integer isThirdParty;

    /**
     * <pre>
     * 
     * 表字段: java.util.Date.expired_time
     * </pre>
     *
     */
    private Date expiredTime;

    /**
     * <pre>
     * 
     * 表字段: java.lang.String.last_credential
     * </pre>
     *
     */
    private String lastCredential;

    /**
     * <pre>
     * 
     * 表字段: java.util.Date.update_date
     * </pre>
     *
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}