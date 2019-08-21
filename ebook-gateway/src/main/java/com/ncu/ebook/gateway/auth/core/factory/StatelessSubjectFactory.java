package com.ncu.ebook.gateway.auth.core.factory;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;


/**
 * @ClassName : EbookSubjectFactory
 * @Description : 不生成session
 * @Author : xfakir
 * @Date : 2019-08-06 20:37
 * @Version : 1.0
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    //private final SessionStorageEvaluator storageEvaluator;

    /**
     * 是否持久化SESSION
     */
    /*public StatelessSubjectFactory(SessionStorageEvaluator storageEvaluator){
        this.storageEvaluator = storageEvaluator;
    }*/

    @Override
    public Subject createSubject(SubjectContext context) {
        //this.storageEvaluator.setSessionStorageEnabled(Boolean.TRUE);
        AuthenticationToken token = context.getAuthenticationToken();
        //if(token instanceof JwtToken){
            // 不创建 session
            context.setSessionCreationEnabled(false);
            // 不持久化session
            //this.storageEvaluator.setSessionStorageEnabled(Boolean.FALSE);
        //}
        return super.createSubject(context);
    }
}
