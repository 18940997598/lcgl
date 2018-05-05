package com.kingsoft.lcgl.business.common.support;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * DTO转换器
 * @author Liu Sixian
 */
public class DtoConverter {

    private static final Logger logger = LoggerFactory.getLogger(DtoConverter.class);

    /**
     * 从DTO转换到实体
     * @param dto
     * @param entity
     * @param <T>
     * @param <S>
     */
    public static <T extends Object, S extends Object> void fromDto(S dto, T entity) {
        try {
            for (Field field : dto.getClass().getDeclaredFields()) {
                DtoMapping mapping = field.getAnnotation(DtoMapping.class);
                if (mapping == null) {
                    continue;
                }
                String dtoField = mapping.dtoField(), entityField = mapping.entityFiled();
                if (StringUtils.isEmpty(dtoField)) {
                    dtoField = field.getName();
                }
                if (StringUtils.isEmpty(entityField)) {
                    entityField = dtoField;
                }
                PropertyDescriptor entityDescriptor = PropertyUtils.getPropertyDescriptor(entity, entityField);
                if (entityDescriptor == null) {
                    continue;
                }
                Object dtoValue = PropertyUtils.getPropertyDescriptor(dto, dtoField).getReadMethod().invoke(dto);
                if(!mapping.converter().isInterface()) {
                    DtoFieldConverter converter = mapping.converter().newInstance();
                    dtoValue = converter.fromDtoToEntity(dtoValue);
                }
                entityDescriptor.getWriteMethod().invoke(entity, dtoValue);
            }
        } catch (Exception ex) {
            logger.warn("Convert DTO to entity failed.", ex);
            throw new RuntimeException("Convert DTO to entity failed.", ex);
        }
    }

    /**
     * 从实体转换到DTO
     * @param entity
     * @param dto
     * @param <T>
     * @param <S>
     */
    public static <T extends Object, S extends Object> void toDto(S entity, T dto) {
        try {
            for (Field field : dto.getClass().getDeclaredFields()) {
                DtoMapping mapping = field.getAnnotation(DtoMapping.class);
                if (mapping == null) {
                    continue;
                }
                String dtoField = mapping.dtoField(), entityField = mapping.entityFiled();
                if (StringUtils.isEmpty(dtoField)) {
                    dtoField = field.getName();
                }
                if (StringUtils.isEmpty(entityField)) {
                    entityField = dtoField;
                }
                PropertyDescriptor entityDescriptor = PropertyUtils.getPropertyDescriptor(entity, entityField);
                if (entityDescriptor == null) {
                    continue;
                }
                Object entityValue = entityDescriptor.getReadMethod().invoke(entity);
                if(!mapping.converter().isInterface()) {
                    DtoFieldConverter converter = mapping.converter().newInstance();
                    entityValue = converter.fromEntityToDto(entityValue);
                }
                PropertyUtils.getPropertyDescriptor(dto, dtoField).getWriteMethod().invoke(dto, entityValue);
            }
        } catch (Exception ex) {
            logger.warn("Convert entity to DTO failed.", ex);
            throw new RuntimeException("Convert entity to DTO failed.", ex);
        }
    }

}
