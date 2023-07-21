package com.bp.cbe.external.service;

import com.bp.cbe.external.dto.UserDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "userService", url = "https://3d8973d4-6325-4c04-872c-095d662f0c4f.mock.pstmn.io/users")
@Headers("Content-Type: application/json")
public interface UserService {

    @GetMapping("")
    List<UserDTO> getAll();
}