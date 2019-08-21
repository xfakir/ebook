package com.ncu.ebook.gateway.auth.core.realm;
import com.ncu.ebook.gateway.auth.service.impl.AuthService;
import com.ncu.ebook.pojo.po.user.LocalAuthorization;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @ClassName : PasswordRealm
 * @Description : JWT
 * @Author : xfakir
 * @Date : 2019-08-05 14:08
 * @Version : 1.0
 */
@Slf4j
public class PasswordRealm extends AuthorizingRealm {

    @Autowired
    private AuthService authService;

    /**
     * 认证
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = (String) usernamePasswordToken.getPrincipal();
        log.info("authenticating user [{}] ...", username);

        LocalAuthorization storedUser = authService.getByUsername(username);

        System.out.println(storedUser);

        if (storedUser == null) {
            throw new AuthenticationException("No such user or wrong password");
        }

        if (storedUser.getStatus() != 1) {
            throw new LockedAccountException("account has been locked");
        }





        return new SimpleAuthenticationInfo(authService.generateJwt(storedUser), storedUser.getCredential(), getName());
    }


    /**
     * 授权
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();
        log.info("authorizing user [{}] ...", principal);
        return new SimpleAuthorizationInfo();
    }



    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
