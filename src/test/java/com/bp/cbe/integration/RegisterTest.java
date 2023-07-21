package com.bp.cbe.integration;

import com.bp.cbe.repository.RegisterRepository;
import com.bp.cbe.service.CourseRegistrationService;
import com.bp.cbe.service.mockdata.CourseRegistrationMock;
import com.bp.cbe.service.mockdata.RegisterMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RegisterTest {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    CourseRegistrationService courseRegistrationService;

    @DisplayName("whenSaveCourseMoreLessFive - OK")
    @Test
    void whenSaveCourseMoreLessFive() {
        registerRepository.save( RegisterMock.getRegisterMock() );

        courseRegistrationService.save( CourseRegistrationMock.getCourseRegistrationDTOMockNull() );
    }
}