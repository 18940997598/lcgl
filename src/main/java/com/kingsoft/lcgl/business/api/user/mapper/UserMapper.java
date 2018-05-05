package com.kingsoft.lcgl.business.api.user.mapper;

import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.dto.UserLoginRquest;
import com.kingsoft.lcgl.business.api.user.dto.UserRegisterRequest;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface UserMapper {

    Long getInfo(@Param("mail")String mail);

    void addUser(UserRegisterRequest request);

    UserDto getUserInfo(UserLoginRquest request);
}
