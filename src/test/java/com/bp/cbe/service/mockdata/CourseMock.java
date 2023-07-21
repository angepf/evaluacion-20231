package com.bp.cbe.service.mockdata;

import com.bp.cbe.domain.enums.StatusEnum;
import com.bp.cbe.external.dto.CourseDTO;

import java.math.BigDecimal;

public class CourseMock {

    public static CourseDTO getCourseDTOMock(StatusEnum statusEnum) {
        return CourseDTO.builder()
                .name( "PYTHON" )
                .code( "CR-002" )
                .price( BigDecimal.TEN )
                .description( "PYTHON 3 COURSE" )
                .byDefault( true )
                .status( statusEnum )
                .build();
    }
}