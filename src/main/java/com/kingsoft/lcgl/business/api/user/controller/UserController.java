package com.kingsoft.lcgl.business.api.user.controller;

import com.kingsoft.lcgl.business.api.user.dto.UserLoginRespone;
import com.kingsoft.lcgl.business.api.user.dto.UserLoginRquest;
import com.kingsoft.lcgl.business.api.user.dto.UserRegisterRequest;
import com.kingsoft.lcgl.business.api.user.service.UserService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="login", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLoginRespone login(@RequestBody UserLoginRquest request) {
        return  userService.login(request);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="register", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse register(@RequestBody UserRegisterRequest request) {
        return  userService.addProject(request);
    }
    /**
     * 通过邮箱发送验证码
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="mailSendIdentifyCode", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse mailSendIdentifyCode(@RequestBody String  mail) {
        return  userService.mailSendIdentifyCode(mail);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="test", method= RequestMethod.GET)
    public Long test() {

        Long time = new Date().getTime();
        logger.info("时间戳："+time);
        if(time<1525276800000L && time>1525190400000L){
            return 5L ;
        }else {
            return 1L ;
        }

    }

}
