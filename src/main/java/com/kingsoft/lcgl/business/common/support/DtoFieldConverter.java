package com.kingsoft.lcgl.business.common.support;

/**
 * DTO转换器接口
 * @author Liu Sixian
 */
public interface DtoFieldConverter<D, E> {

    /**
     * 从DTO到实体
     * @param src
     */
    E fromDtoToEntity(D src);

    /**
     * 从实体到DTO
     * @param src
     */
    D fromEntityToDto(E src);

}
