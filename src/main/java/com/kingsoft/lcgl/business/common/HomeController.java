package com.kingsoft.lcgl.business.common;

import com.kingsoft.lcgl.business.common.support.CommonResponse;
import com.kingsoft.lcgl.core.webservice.support.ExceptionMsg;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyongkui on 2015/12/3.
 */
@RestController
@RequestMapping("/")
public class HomeController implements ErrorController{

    /**
     * 系统错误消息
     * @return
     */
    @RequestMapping(value = "error")
    public CommonResponse doError(HttpServletRequest request) {
        Object a = request.getAttribute("commonResponse");
        if(a == null) {
            return new CommonResponse().put("errorCode", ExceptionMsg.SYSTEM_ERROR).
                    put("errorMsg",ExceptionMsg.getRestExceptionResponse(ExceptionMsg.SYSTEM_ERROR).getErrorMsg());
        }
        return (CommonResponse)a;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
