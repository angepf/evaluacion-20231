package com.bp.cbe.service.mapper;

import com.bp.cbe.domain.Register;
import com.bp.cbe.service.dto.RegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterMapper {
    RegisterMapper INSTANCE = Mappers.getMapper( RegisterMapper.class);

    Register toEntity(RegisterDTO dto);

    RegisterDTO toDto(Register entity);
}