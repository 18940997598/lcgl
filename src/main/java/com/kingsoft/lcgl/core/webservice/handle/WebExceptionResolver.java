package com.kingsoft.lcgl.core.webservice.handle;

import com.kingsoft.lcgl.business.common.support.CommonResponse;
import com.kingsoft.lcgl.core.webservice.exception.*;
import com.kingsoft.lcgl.core.webservice.support.ExceptionMsg;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;

/**
 * 认证异常处理器
 * @author wangyonkgui
 */
@Component
public class WebExceptionResolver extends AbstractHandlerExceptionResolver {

	private static final Logger log = Logger.getLogger(WebExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        CommonResponse commonResponse = new CommonResponse();
        Integer httpStatus = 400;
        if(ex instanceof RestParamsException || ex instanceof UnexpectedTypeException) {
            response.setStatus(httpStatus);
            commonResponse.put("errorCode", ExceptionMsg.PARAM_ERROR);
            commonResponse.put("errorMsg", ExceptionMsg.getRestExceptionResponse(ExceptionMsg.PARAM_ERROR).getErrorMsg());
        } else if(ex instanceof RestCommonException) {
            response.setStatus(httpStatus);
            commonResponse.put("errorCode", ((RestCommonException) ex).getResponse().getErrorCode());
            commonResponse.put("errorMsg", ((RestCommonException) ex).getResponse().getErrorMsg());
        } else if(ex instanceof RestCheckException) {
            httpStatus = 403;
            response.setStatus(httpStatus);
            commonResponse.put("errorCode", ((RestCheckException) ex).getResponse().getErrorCode());
            commonResponse.put("errorMsg", ((RestCheckException) ex).getResponse().getErrorMsg());
        } else if(ex != null) {
            httpStatus = 500;
            response.setStatus(httpStatus);
            commonResponse.put("errorCode", ExceptionMsg.SYSTEM_ERROR);
            commonResponse.put("errorMsg", ExceptionMsg.getRestExceptionResponse(ExceptionMsg.SYSTEM_ERROR).getErrorMsg());
        }
        if(ex != null) {
            logger.info("ID:"+request.getAttribute("currentId")+" out");
            log.info("Payload: {errorCode:"+commonResponse.get("errorCode")+",errorMsg:"+commonResponse.get("errorMsg")+"}");
            log.info("-------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------");
            log.error("REST exception", ex);
            request.setAttribute("commonResponse", commonResponse);
            request.setAttribute("exception", ex);
            return new ModelAndView("forward:/error");
        }
		return null;
	}

}
