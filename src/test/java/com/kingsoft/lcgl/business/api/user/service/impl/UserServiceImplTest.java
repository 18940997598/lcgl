package com.kingsoft.lcgl.business.api.user.service.impl;

import com.kingsoft.lcgl.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by yangdiankang on 2018/1/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceImplTest {
    @Test
    public void login() throws Exception {
        for(int i=0;i<4;i++){
            System.out.println(String.valueOf((int)Math.floor(Math.random() * (1000000 - 100000) + 100000)));
        }

    }
}