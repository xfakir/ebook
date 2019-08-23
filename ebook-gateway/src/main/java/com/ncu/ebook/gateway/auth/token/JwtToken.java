package com.ncu.ebook.gateway.auth.token;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * @ClassName : JwtToken
 * @Description : 用户认证JWT
 * @Author : xfakir
 * @Date : 2019-08-05 13:55
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class JwtToken implements HostAuthenticationToken {

    private String jwt;

    private String host;

    public JwtToken(String jwt) {
        this(jwt, null);
    }

    public JwtToken(String jwt, String host) {
        this.jwt = jwt;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

    @Override
    public String getHost() {
        return host;
    }
}
