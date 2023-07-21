package com.bp.cbe.service.mapper;

import com.bp.cbe.domain.CourseRegistration;
import com.bp.cbe.service.dto.CourseRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseRegistrationMapper {
    CourseRegistrationMapper INSTANCE = Mappers.getMapper( CourseRegistrationMapper.class);

    CourseRegistration toEntity(CourseRegistrationDTO dto);

    CourseRegistrationDTO toDto(CourseRegistration entity);
}