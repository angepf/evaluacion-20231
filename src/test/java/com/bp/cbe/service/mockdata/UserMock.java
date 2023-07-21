package com.bp.cbe.service.mockdata;

import com.bp.cbe.domain.enums.StatusEnum;
import com.bp.cbe.external.dto.UserDTO;

public class UserMock {

    public static UserDTO getUserDTOMock() {
        return UserDTO.builder()
                .id( 1 )
                .identificationNumber( "0109382473" )
                .name( "ANA CECILIA" )
                .lastname( "VEGA PRADO" )
                .phoneNumber( "0983449388" )
                .status( StatusEnum.ACT )
                .build();
    }
}