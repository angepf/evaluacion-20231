package com.bp.cbe.controller;

import com.bp.cbe.service.RegisterService;
import com.bp.cbe.service.dto.RegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Register", description = "Register administration service.")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/registers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterController {

    RegisterService registerService;

    @Operation(summary = "Get register by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<RegisterDTO> getById(@PathVariable Integer id) {
        log.info("getTotalScore caseId={}", id);
        return ResponseEntity.ok(registerService.getById(id));
    }

    @Operation(summary = "Get all register registers.")
    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAll() {
        return ResponseEntity.ok(registerService.getAll());
    }

    @Operation(summary = "Create new register.")
    @PostMapping
    public ResponseEntity<RegisterDTO> create(@RequestBody @Valid RegisterDTO data) {
        return ResponseEntity.ok(registerService.save(data));
    }

    @Operation(summary = "Update existing register by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<RegisterDTO> update(@PathVariable Integer id, @RequestBody @Valid RegisterDTO data) {
        return ResponseEntity.ok(registerService.update(id, data));
    }

    @Operation(summary = "Delete register by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        log.info("getTotalScore caseId={}", id);
        registerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}