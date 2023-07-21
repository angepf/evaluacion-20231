package com.bp.cbe.service;

import com.bp.cbe.domain.enums.StatusEnum;
import com.bp.cbe.exception.RegisterException;
import com.bp.cbe.exception.ResourceNotFoundException;
import com.bp.cbe.external.service.CourseService;
import com.bp.cbe.external.service.UserService;
import com.bp.cbe.repository.CourseRegistrarionRepository;
import com.bp.cbe.service.impl.CourseRegistrationServiceImpl;
import com.bp.cbe.service.mockdata.CourseMock;
import com.bp.cbe.service.mockdata.CourseRegistrationMock;
import com.bp.cbe.service.mockdata.RegisterMock;
import com.bp.cbe.service.mockdata.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CourseRegistrationServiceTest {

    @Mock
    CourseRegistrarionRepository courseRegistrationRepository;

    @Mock
    RegisterService registerService;

    @Mock
    UserService userService;

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseRegistrationServiceImpl courseRegistrationService;

    @DisplayName("GetAllCourseRegistration - OK")
    @Test
    void whenGetAllCourseRegistrationsThenOkResponse() {
        Mockito.when( courseRegistrationRepository.findAll() )
                .thenReturn( Collections.singletonList( CourseRegistrationMock.getCourseRegistrationMock() ) );

        Assertions.assertEquals( 1, courseRegistrationService.getAll().size() );

    }

    @DisplayName("GetCourseRegistrationById - OK")
    @Test
    void whenGetCourseRegistrationByIdThenOkResponse() {
        Mockito.when( courseRegistrationRepository.findById( 1 ) )
                .thenReturn( Optional.of( CourseRegistrationMock.getCourseRegistrationMock() ) );

        Assertions.assertEquals( 1, courseRegistrationService.getById( 1 ).getId() );

    }

    @DisplayName("GetCourseRegistrationById - Exception")
    @Test
    void whenGetCourseRegistrationByIdThenExceptionResponse() {
        Mockito.when( courseRegistrationRepository.findById( Mockito.anyInt() ) )
                .thenThrow( new ResourceNotFoundException( "CourseRegistration", "id", "3" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> courseRegistrationService.getById( 3 ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("whenSaveCourseUserRegisterExist - OK")
    @Test
    void whenSaveCourseUserRegisterExist() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMock() );

        Mockito.when( courseRegistrationRepository.save( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMock() );

        Assertions.assertEquals( 1, courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMock() ).getId() );
    }

    @DisplayName("whenSaveCourseUserRegisterNotExist - OK")
    @Test
    void whenSaveCourseUserRegisterNotExist() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( null );

        Mockito.when( courseRegistrationRepository.save( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMock() );

        Assertions.assertEquals( 1, courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMock() ).getId() );
    }

    @DisplayName("whenSaveCourseWithCourse - OK")
    @Test
    void whenSaveCourseWithCourse() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( null );

        Mockito.when( courseRegistrationRepository.save( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMock() );

        Assertions.assertEquals( "CR-002", courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMock() ).getCourse() );
    }

    @DisplayName("whenSaveCourseWithoutCourse - OK")
    @Test
    void whenSaveCourseWithoutCourse() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMock() );

        Mockito.when( courseRegistrationRepository.save( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMockNull() );

        Assertions.assertEquals( "CR-001", courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMockNull() ).getCourse() );
    }

    @DisplayName("whenSaveCourseMoreLessFive - OK")
    @Test
    void whenSaveCourseMoreLessFive() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMock() );

        Mockito.when( courseRegistrationRepository.findByRegister( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMockList() );

        Mockito.when( courseRegistrationRepository.save( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMock() );

        Assertions.assertEquals( "CR-002", courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMock() ).getCourse() );
    }

    @DisplayName("whenSaveCourseMoreMoreFive - OK")
    @Test
    void whenReport() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMock() );

        Mockito.when( courseRegistrationRepository.findByRegister( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMockList5() );

        Mockito.when( courseRegistrationRepository.save( Mockito.any() ) )
                .thenReturn( CourseRegistrationMock.getCourseRegistrationMock() );

        Exception exception = Assertions.assertThrows(
                RegisterException.class, () -> courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMock() ) );

        Assertions.assertEquals( RegisterException.class, exception.getClass() );
    }

    @DisplayName("whenSaveCourseMoreMoreFive - OK")
    @Test
    void whenGetReport() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMockWithList() );

        Mockito.when( userService.getAll() )
                .thenReturn( Collections.singletonList( UserMock.getUserDTOMock() ) );

        Mockito.when( courseService.getAll() )
                .thenReturn( Collections.singletonList( CourseMock.getCourseDTOMock( StatusEnum.ACT ) ) );

        Assertions.assertNotNull( courseRegistrationService.getReport( 1 ).getRegistrations() );
    }

    @DisplayName("whenSaveCourseMoreMoreFive - OK")
    @Test
    void whenGetReportPrices() {
        Mockito.when( registerService.getByUser( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMockWithList() );

        Mockito.when( userService.getAll() )
                .thenReturn( Collections.singletonList( UserMock.getUserDTOMock() ) );

        Mockito.when( courseService.getAll() )
                .thenReturn( Collections.singletonList( CourseMock.getCourseDTOMock( StatusEnum.ACT ) ) );

        Assertions.assertEquals( BigDecimal.TEN, courseRegistrationService.getReportTotals( 1 ).getPrice() );
    }

    @DisplayName("whenSaveCourseMoreMoreFive - OK")
    @Test
    void whenGetReportUsers() {
        Mockito.when( userService.getAll() )
                .thenReturn( Collections.singletonList( UserMock.getUserDTOMock() ) );

        Assertions.assertEquals( 1, courseRegistrationService.getReportUsers().size() );
    }

}