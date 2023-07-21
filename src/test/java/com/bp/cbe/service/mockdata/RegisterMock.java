package com.bp.cbe.service.mockdata;

import com.bp.cbe.domain.Register;
import com.bp.cbe.service.dto.RegisterDTO;

import java.util.Date;

public class RegisterMock {

    public static Register getRegisterMock() {
        return Register.builder()
                .id( 1 )
                .user( 1 )
                .registerDate( new Date() )
                .build();
    }

    public static RegisterDTO getRegisterDTOMock() {
        return RegisterDTO.builder()
                .id( 1 )
                .user( 1 )
                .registerDate( new Date() )
                .build();
    }

    public static RegisterDTO getRegisterDTOMockWithList() {
        return RegisterDTO.builder()
                .id( 1 )
                .user( 1 )
                .registerDate( new Date() )
                .registrations( CourseRegistrationMock.getCourseRegistrationDTOMockList() )
                .build();
    }

}