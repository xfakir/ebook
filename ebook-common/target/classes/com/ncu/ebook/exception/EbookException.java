package com.ncu.ebook.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : EbookException
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-30 20:03
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class EbookException extends RuntimeException{
    private Integer code;

    private String msg;

    public EbookException() {
    }

    public EbookException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public EbookException(Integer code, String msg, Throwable throwable) {
        super(msg, throwable);
        this.code = code;
        this.msg = msg;
    }
}
