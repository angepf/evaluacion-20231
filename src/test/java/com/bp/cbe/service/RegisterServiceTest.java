package com.bp.cbe.service;

import com.bp.cbe.exception.ResourceNotFoundException;
import com.bp.cbe.repository.RegisterRepository;
import com.bp.cbe.service.impl.RegisterServiceImpl;
import com.bp.cbe.service.mockdata.RegisterMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class RegisterServiceTest {

    @Mock
    RegisterRepository registerRepository;

    @InjectMocks
    RegisterServiceImpl registerService;

    @DisplayName("GetAllRegister - OK")
    @Test
    void whenGetAllRegistersThenOkResponse() {
        Mockito.when( registerRepository.findAll() )
                .thenReturn( Collections.singletonList( RegisterMock.getRegisterMock() ) );

        Assertions.assertEquals(1, registerService.getAll().size());

    }

    @DisplayName("GetRegisterById - OK")
    @Test
    void whenGetRegisterByIdThenOkResponse() {
        Mockito.when( registerRepository.findById(1) )
                .thenReturn( Optional.of( RegisterMock.getRegisterMock() ) );

        Assertions.assertEquals(1, registerService.getById( 1 ).getId() );

    }
    @DisplayName("GetRegisterById - Exception")
    @Test
    void whenGetRegisterByIdThenExceptionResponse() {
        Mockito.when( registerRepository.findById(Mockito.anyInt()) )
                .thenThrow( new ResourceNotFoundException( "Register", "id", "3" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () ->registerService.getById( 3 ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("SaveRegister - OK")
    @Test
    void whenSaveRegisterThenOkResponse() {
        Mockito.when( registerRepository.save( Mockito.any() ) )
                .thenReturn( RegisterMock.getRegisterMock() );

        Assertions.assertEquals(1, registerService.save( RegisterMock.getRegisterDTOMock() ).getId() );
    }
}