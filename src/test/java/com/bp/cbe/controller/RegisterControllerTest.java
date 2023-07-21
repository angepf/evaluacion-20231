package com.bp.cbe.controller;

import com.bp.cbe.exception.ResourceNotFoundException;
import com.bp.cbe.service.impl.RegisterServiceImpl;
import com.bp.cbe.service.mockdata.RegisterMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class)
@TestPropertySource(locations = "classpath:application.properties")
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterServiceImpl registerService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString( obj );
    }

    @DisplayName("GetRegister - OK")
    @Test
    void whenCallUrlRegisterWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( registerService.getById( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMock( ) );

        mockMvc.perform( get( "/registers/1" ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("GetRegister - Exception")
    @Test
    void whenCallUrlRegisterWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( registerService.getById( Mockito.anyInt() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( get( "/registers/1" ) )
                .andExpect( status().isNotFound() );
    }

    @DisplayName("CreateRegister - OK")
    @Test
    void whenCallUrlRegisterSaveWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( registerService.getById( Mockito.anyInt() ) )
                .thenReturn( RegisterMock.getRegisterDTOMock( ) );

        String inputJson = mapToJson( RegisterMock.getRegisterDTOMock( ) );

        mockMvc.perform( post( "/registers" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isOk() );
    }
}