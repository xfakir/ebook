package com.ncu.ebook.constant;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * @ClassName : JwtConstant
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-21 16:20
 * @Version : 1.0
 */
public class JwtConstant {
    public static final String SECRET_KEY = "ncu_ebook_auth";

    public static final String ISSUER = "ebook_auth_center";

    public static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    /**
     * 过期时间 24小时
     */
    public static final long DEFAULT_EXPIRE_SECOND = 24 * 60 * 60L;

    public static final String SUBJECT ="ebook_auth";

    public static final String AUDIENCE = "ebook_user";
}
