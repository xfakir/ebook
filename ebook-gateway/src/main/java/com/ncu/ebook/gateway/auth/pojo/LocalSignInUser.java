package com.ncu.ebook.gateway.auth.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : LocalSignUser
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-11 13:38
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class LocalSignInUser {
    private String username;

    private String password;

}
