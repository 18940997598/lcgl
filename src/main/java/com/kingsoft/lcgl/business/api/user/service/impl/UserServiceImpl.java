package com.kingsoft.lcgl.business.api.user.service.impl;

import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.dto.UserLoginRespone;
import com.kingsoft.lcgl.business.api.user.dto.UserLoginRquest;
import com.kingsoft.lcgl.core.redis.util.Jedis;
import com.kingsoft.lcgl.core.redis.util.JedisClientTemplate;
import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.business.api.common.service.impl.EmailServiceImpl;
import com.kingsoft.lcgl.business.api.user.dto.UserRegisterRequest;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.api.user.service.UserService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.business.common.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JedisClientTemplate jedisClientTemplate;
    @Value("${timeout.identifyCodeTimeout}")
    private int identifyCodeTimeout;
    @Value("${timeout.tokenTimeout}")
    private int tokenTimeout;
    @Value("${timeout.refreshTokenTimeout}")
    private int refreshTokenTimeout;

    @Override
    public UserLoginRespone login(UserLoginRquest request) {
        UserLoginRespone respone = new UserLoginRespone();
        UserDto userDto = userMapper.getUserInfo(request);
        if(userDto!=null&&request.getPass().equals(userDto.getPass())){
            String token = RandomUtil.getToken();
            String refreshToken = RandomUtil.getRefreshToken(request.getMail());
            HashMap<String,String> map = new HashMap<>();
            map.put("token",token);
            map.put("refreshToken",refreshToken);

            /**
             * redis 操作：
             * 1.清除原先的 token,refreshToken
             * 2.插入新的token,refreshToken
             * 3.更新mail
             */
            String oldToken = jedisClientTemplate.hmget(Jedis.MAIL.getName()+request.getMail(),"token").get(0);
            String oldRefresh =  jedisClientTemplate.hmget(Jedis.MAIL.getName()+request.getMail(),"refreshToken").get(0);
            jedisClientTemplate.del(Jedis.TOKEN.getName()+oldToken);
            jedisClientTemplate.del(Jedis.REFRESHTOKEN.getName()+oldRefresh);

            jedisClientTemplate.set(Jedis.TOKEN.getName()+token,request.getMail());
            jedisClientTemplate.set(Jedis.REFRESHTOKEN.getName()+refreshToken,request.getMail());
            jedisClientTemplate.hmset(Jedis.MAIL.getName()+request.getMail(),map);
            jedisClientTemplate.timeout(Jedis.TOKEN.getName()+token,tokenTimeout);
            jedisClientTemplate.timeout(Jedis.REFRESHTOKEN.getName()+refreshToken,refreshTokenTimeout);
            respone.setCode(0);
            respone.setMsg(request.getMail());
            respone.setToken(token);
            respone.setRefreshToken(refreshToken);
        }else {
            respone.setCode(500);
            respone.setMsg("用户名不存在或者密码错误");
        }
        return respone;
    }

    @Override
    public CommonApiResponse addProject(UserRegisterRequest request) {
        int code  =  0;
        String msg =  "";
        CommonApiResponse commonApiResponse = new CommonApiResponse();
        String identifyCode = request.getIdentifyCode();
        String redisIdentifyCode = jedisClientTemplate.get(Jedis.IDENTIFYCODE.getName()+request.getMail());
        if(redisIdentifyCode!=null){
            if(redisIdentifyCode.equals(identifyCode)){
                if(userMapper.getInfo(request.getMail())==null){
                    userMapper.addUser(request);
                    commonApiResponse.setCode(0);
                }else {
                    code = 500;
                    msg = "该邮箱已经注册";
                }
            }else {
                code = 500;
                msg = "验证码不匹配";
            }
        }else {
            code = 500;
            msg = "验证码不匹配";
        }
        commonApiResponse.setCode(code);
        commonApiResponse.setMsg(msg);

        return commonApiResponse;
    }

    @Override
    public CommonApiResponse mailSendIdentifyCode(String  mail) {
        String Mail=  mail.replaceAll("\"","");
        CommonApiResponse response = new CommonApiResponse();
        String reciver[] = {Mail};
        String identifyCode = RandomUtil.getRandomSix();
        String content = "尊敬的用户你好，您正在注册西山居任务管理系统，（如果非本人注册请忽略此消息），您的验证码为："+identifyCode;
        try{
            emailService.sendMail(reciver,"REGIST",content);
            jedisClientTemplate.set(Jedis.IDENTIFYCODE.getName()+Mail,identifyCode);
            jedisClientTemplate.timeout(Jedis.IDENTIFYCODE.getName()+Mail,identifyCodeTimeout);
            response.setCode(0);
            response.setMsg(identifyCode);
        }catch (Exception e){
            response.setCode(500);
        }
        return response;
    }

}
