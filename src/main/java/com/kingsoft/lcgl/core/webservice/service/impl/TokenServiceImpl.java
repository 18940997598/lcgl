package com.kingsoft.lcgl.core.webservice.service.impl;

import com.kingsoft.lcgl.business.api.common.dto.TokenRespone;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.business.common.util.RandomUtil;
import com.kingsoft.lcgl.core.redis.util.Jedis;
import com.kingsoft.lcgl.core.redis.util.JedisClientTemplate;
import com.kingsoft.lcgl.core.webservice.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangdiankang on 2017/12/29.
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Autowired
    private JedisClientTemplate jedisClientTemplate;
    @Value("${timeout.tokenTimeout}")
    private int tokenTimeout;
    @Value("${timeout.refreshTokenTimeout}")
    private int refreshTokenTimeout;

    @Override
    public TokenRespone commonGetToken(String mail) {
        TokenRespone respone = new TokenRespone();

        String token = RandomUtil.getToken();
        String refreshToken = RandomUtil.getRefreshToken(mail);
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("refreshToken",refreshToken);

        /**
         * redis 操作：
         * 1.清除原先的 token,refreshToken
         * 2.插入新的token,refreshToken
         * 3.更新mail
         */
        String oldToken = jedisClientTemplate.hmget(Jedis.MAIL.getName()+mail,"token").get(0);
        String oldRefresh =  jedisClientTemplate.hmget(Jedis.MAIL.getName()+mail,"refreshToken").get(0);
        jedisClientTemplate.del(Jedis.TOKEN.getName()+oldToken);
        jedisClientTemplate.del(Jedis.REFRESHTOKEN.getName()+oldRefresh);

        jedisClientTemplate.set(Jedis.TOKEN.getName()+token,mail);
        jedisClientTemplate.set(Jedis.REFRESHTOKEN.getName()+refreshToken,mail);
        jedisClientTemplate.hmset(Jedis.MAIL.getName()+mail,map);
        jedisClientTemplate.timeout(Jedis.TOKEN.getName()+token,tokenTimeout);
        jedisClientTemplate.timeout(Jedis.REFRESHTOKEN.getName()+refreshToken,refreshTokenTimeout);
        respone.setCode(0);
        respone.setMail(mail);
        respone.setToken(token);
        respone.setRefreshToken(refreshToken);
        return respone;
    }

    @Override
    public String commonGetRegreshToken(String mail) {
        return null;
    }
}
