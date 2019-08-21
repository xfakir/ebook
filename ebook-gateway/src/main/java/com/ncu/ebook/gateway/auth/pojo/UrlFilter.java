package com.ncu.ebook.gateway.auth.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : UrlFilter
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-15 13:44
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public class UrlFilter {
    private Long urlFilterId;

    private String filterName;

    private String url;

    private String roles;

    private String permissions;

    private Integer activited;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;*/
}
