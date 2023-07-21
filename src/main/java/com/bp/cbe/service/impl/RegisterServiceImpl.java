package com.bp.cbe.service.impl;

import com.bp.cbe.domain.Register;
import com.bp.cbe.exception.ResourceNotFoundException;
import com.bp.cbe.repository.RegisterRepository;
import com.bp.cbe.service.RegisterService;
import com.bp.cbe.service.dto.RegisterDTO;
import com.bp.cbe.service.mapper.RegisterMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterServiceImpl implements RegisterService {
    
    RegisterRepository registerRepository;


    @Override
    public List<RegisterDTO> getAll() {
        return registerRepository.findAll()
                .stream()
                .map(RegisterMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public RegisterDTO getById(Integer id) {
        Register entity = getRegister(id);
        return RegisterMapper.INSTANCE.toDto(entity);
    }

    @Override
    public RegisterDTO getByUser(Integer user) {
        return RegisterMapper.INSTANCE.toDto(registerRepository.findByUser( user ));

    }
    
    @Override
    public RegisterDTO save(RegisterDTO data) {
        Register entity = RegisterMapper.INSTANCE.toEntity(data);

        return RegisterMapper.INSTANCE.toDto(registerRepository.save(entity));
            }

    @Override
    public RegisterDTO update(Integer id, RegisterDTO data) {
        Register entity = getRegister(id);
        entity.setStatus(data.getStatus());
        return RegisterMapper.INSTANCE.toDto(registerRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        registerRepository.deleteById(id);
    }

    private Register getRegister(Integer id) {
        return registerRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException( "Register", "id", id.toString() ) );
    }

}
