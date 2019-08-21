package com.ncu.ebook.gateway.auth.core.realm;

import com.ncu.ebook.gateway.auth.service.impl.AuthService;
import com.ncu.ebook.gateway.auth.token.JwtToken;
import com.ncu.ebook.util.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : JwtRealm
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-07 21:00
 * @Version : 1.0
 */
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private AuthService authService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        String token = ((JwtToken) authenticationToken).getJwt();





        if (!JwtUtils.verifyJwt(token)) {
            throw new AuthenticationException("token can not be verified");
        }

        String principal = JwtUtils.getClaimWithKey(token, "principal").asString();

        String newToken = authService.generateJwt(principal);

        System.out.println("newToken: " + newToken);

        return new SimpleAuthenticationInfo(newToken, newToken, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();

        List<String> roleLists = Arrays.asList(JwtUtils.getClaimWithKey(token, "roles").asString().split(","));
        System.out.println("jwtRealm: " + roleLists);

        simpleAuthorizationInfo.setRoles(new HashSet<>(roleLists));
        return simpleAuthorizationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
