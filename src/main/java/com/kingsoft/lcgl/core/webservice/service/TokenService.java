package com.kingsoft.lcgl.core.webservice.service;

import com.kingsoft.lcgl.business.api.common.dto.TokenRespone;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;

/**
 * Created by yangdiankang on 2018/1/3.
 */
public interface TokenService {
    /**
     * 获取token
     * @return
     */
    TokenRespone commonGetToken(String mail);
    /**
     * 获取regreshToken
     * @return
     */
    String commonGetRegreshToken(String mail);
}
