package com.ncu.ebook.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : EbookResult
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-30 19:59
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class EbookResult {
    /**
     * 响应状态码
     */
    private Integer status;


    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    public static EbookResult build(Integer status, String msg, Object data) {
        return new EbookResult(status, msg, data);
    }

    public EbookResult() {
    }

    public EbookResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
