package com.bp.cbe.service.mockdata;

import com.bp.cbe.domain.CourseRegistration;
import com.bp.cbe.domain.enums.StatusEnum;
import com.bp.cbe.service.dto.CourseRegistrationDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseRegistrationMock {

    public static CourseRegistration getCourseRegistrationMock() {
        return CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build();
    }

    public static List<CourseRegistration> getCourseRegistrationMockList5() {
        List<CourseRegistration> list = new ArrayList<>();

        list.add( CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build() );
        list.add( CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build() );
        list.add( CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build() );
        list.add( CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build() );
        list.add( CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build() );
        list.add( CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build() );

        return list;
    }

    public static CourseRegistration getCourseRegistrationMockNull() {
        return CourseRegistration.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterMock() )
                .course( "CR-001" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build();
    }

    public static List<CourseRegistration> getCourseRegistrationMockList() {
        List<CourseRegistration> courseRegistrations = new ArrayList<>();
        courseRegistrations.add( getCourseRegistrationMock() );
        return courseRegistrations;
    }

    public static CourseRegistrationDTO getCourseRegistrationDTOMock() {
        return CourseRegistrationDTO.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterDTOMock() )
                .course( "CR-002" )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build();
    }

    public static CourseRegistrationDTO getCourseRegistrationDTOMockNull() {
        return CourseRegistrationDTO.builder()
                .id( 1 )
                .register( RegisterMock.getRegisterDTOMock() )
                .course( null )
                .startDate( new Date() )
                .endDate( new Date() )
                .status( StatusEnum.ACT )
                .build();
    }

    public static List<CourseRegistrationDTO> getCourseRegistrationDTOMockList() {
        List<CourseRegistrationDTO> courseRegistrations = new ArrayList<>();
        courseRegistrations.add( getCourseRegistrationDTOMock() );
        return courseRegistrations;
    }
}