package com.ncu.ebook.user.controller;

import com.ncu.ebook.config.RedisManager;
import com.ncu.ebook.pojo.vo.EbookResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-30 22:43
 * @Version : 1.0
 */
@Api(tags = "框架集成测试接口", description = "框架集成测试")
@RestController
@RequestMapping(value = "/user")
public class UserController {


    /*@Autowired
    private RedisManager redisManager;*/

    @ApiOperation(
            value = "test", //接口说明
            notes = "test", //接口发布说明。
            response = EbookResult.class)
    @RequestMapping(value = "/test")
    public EbookResult test() {
        /*String test = "test";

        redisManager.set("aaa", "bbb");*/


        /**
         * service
         */

        /**
         * 封装EbookResult
         */



        return new EbookResult(200, "ok", null);
    }

}
