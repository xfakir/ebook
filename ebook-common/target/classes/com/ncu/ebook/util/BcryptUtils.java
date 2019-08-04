package com.ncu.ebook.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @ClassName : BcryptUtils
 * @Description : Bcrypt加密解密工具类
 * @Author : xfakir
 * @Date : 2019-03-19 10:04
 * @Version : 1.0
 */
public class BcryptUtils {
    /**
     * 对明文密码进行加密
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    /**
     * 将输入的密码与数据库验证
     * @param password
     * @param hashed
     * @return
     */
    public static boolean decrypt(String password, String hashed) {
        return BCrypt.checkpw(password,hashed);
    }

}
