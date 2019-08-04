package com.ncu.ebook.exception;

import com.ncu.ebook.pojo.vo.EbookResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : GlobalExceptionHandler
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-31 14:08
 * @Version : 1.0
 */
@ControllerAdvice(annotations = RestController.class)
@Component
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = EbookException.class)
    public EbookResult ebookExceptionHandler(EbookException e) {
        // e.printStackTrace();
        if (e instanceof ExampleException) {
            //
            //
            return EbookResult.build(500,"error",null);

        }
        return EbookResult.build(500, "unknown error", null);
    }
}
