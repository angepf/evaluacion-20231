package com.bp.cbe.service;

import com.bp.cbe.service.dto.RegisterDTO;

public interface RegisterService extends GenericService<Integer, RegisterDTO> {

    RegisterDTO getByUser(Integer user);

}