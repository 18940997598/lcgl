package com.kingsoft.lcgl.business.api.user.service;

import com.kingsoft.lcgl.business.api.user.dto.UserLoginRespone;
import com.kingsoft.lcgl.business.api.user.dto.UserLoginRquest;
import com.kingsoft.lcgl.business.api.user.dto.UserRegistRequest;
import com.kingsoft.lcgl.business.api.user.dto.UserRegisterRequest;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface UserService {
    CommonApiResponse addProject(UserRegisterRequest request);
    CommonApiResponse mailSendIdentifyCode(String  mail);
    UserLoginRespone login(UserLoginRquest request);
}
