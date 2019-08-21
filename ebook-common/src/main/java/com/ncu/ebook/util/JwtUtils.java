package com.ncu.ebook.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ncu.ebook.constant.JwtConstant;
import javafx.beans.binding.BooleanExpression;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : JwtUtils
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-06 13:08
 * @Version : 1.0
 */
public class JwtUtils {


    /**
     * 创建jwt
     * @return jwt
     */
    public static String createJwt(Map<String, String> claims) {
        LocalDateTime localDateTime = DateUtils.nowWithLocalDateTime();

        JWTCreator.Builder builder = JWT.create().withIssuer(JwtConstant.ISSUER)
                .withIssuedAt(DateUtils.asDate(localDateTime))
                .withSubject(JwtConstant.SUBJECT)
                .withAudience(JwtConstant.AUDIENCE)
                .withJWTId(String.valueOf(IdUtils.nextId()))
                .withExpiresAt(DateUtils.asDate(localDateTime.plusSeconds(JwtConstant.DEFAULT_EXPIRE_SECOND)));


        claims.forEach(builder::withClaim);

        return builder.sign(JwtConstant.ALGORITHM);

    }

   /* private static void handleExpire(JWTCreator.Builder builder, Map<String, String> claims, LocalDateTime localDateTime) {
        String claimsExp = claims.get("exp");
        long exp = !StringUtils.isEmptyOrNull(claimsExp) ? Long.parseLong(claimsExp) : DEFAULT_EXPIRE_SECOND;

        builder.withExpiresAt(DateUtils.asDate(localDateTime.plusSeconds(exp)));

        claims.remove("exp");


    }*/

    /*public static void refreshJWT(String token) {
        LocalDateTime localDateTime = DateUtils.nowWithLocalDateTime();
        //计算差值
        DecodedJWT decodedJWT = decodeJwt(token);

        JWTCreator.Builder builder = JWT.create().withIssuer(decodedJWT.getIssuer())
                .withIssuedAt(DateUtils.asDate(localDateTime))
                .withJWTId(String.valueOf(IdUtils.nextId()))
                .withExpiresAt(DateUtils.asDate(localDateTime.plusSeconds(getExpire(token))));
        decodedJWT.getClaims().forEach(builder::withClaim);

    }*/

    /*public static Long getExpire(String token) {
        DecodedJWT decodedJWT = decodeJwt(token);
        return DateUtils.asTimestamp(decodedJWT.getExpiresAt()) - DateUtils.asTimestamp(decodedJWT.getIssuedAt());
    }*/

    public static Boolean verifyJwt(String token) {
        DecodedJWT decodedJWT = decodeJwt(token);
        JWTVerifier jwtVerifier = JWT.require(JwtConstant.ALGORITHM)
                .withIssuer(JwtConstant.ISSUER)
                .withSubject(JwtConstant.SUBJECT)
                .withAudience(JwtConstant.AUDIENCE)
                .build();
        try {
            jwtVerifier.verify(token);
            return Boolean.TRUE;
        } catch (JWTVerificationException verificationException) {
            throw new JWTVerificationException("token can not be verified");
        }

    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public static DecodedJWT decodeJwt(String jwt) {
        return JWT.decode(jwt);
    }

    /**
     * 解析并返回Claim
     * @param jwt
     * @return
     */
    public static Map<String, Claim> getClaims(String jwt) {
        return decodeJwt(jwt).getClaims();
    }

    /**
     * 解析并获取特定key的Claim
     * @param jwt
     * @param key
     * @return
     */
    public static Claim getClaimWithKey(String jwt, String key) {
        return getClaims(jwt).get(key);
    }


}
