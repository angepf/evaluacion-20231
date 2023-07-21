package com.bp.cbe.controller;

import com.bp.cbe.external.dto.UserDTO;
import com.bp.cbe.service.CourseRegistrationService;
import com.bp.cbe.service.RegisterService;
import com.bp.cbe.service.dto.CourseRegistrationDTO;
import com.bp.cbe.service.dto.PricesReportDTO;
import com.bp.cbe.service.dto.RegisterReportDTO;
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

@Tag(name = "CourseRegistration", description = "CourseRegistration administration service.")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/courseRegistrations")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseRegistrationController {

    CourseRegistrationService courseRegistrationService;

    RegisterService registerService;


    @Operation(summary = "Get courseRegistration by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CourseRegistrationDTO> getById(@PathVariable Integer id) {
        log.info( "getTotalScore caseId={}", id );

        return ResponseEntity.ok( courseRegistrationService.getById( id ) );
    }

    @Operation(summary = "Get all register courseRegistrations.")
    @GetMapping
    public ResponseEntity<List<CourseRegistrationDTO>> getAll() {
        return ResponseEntity.ok( courseRegistrationService.getAll() );
    }

    @Operation(summary = "Create new courseRegistration.")
    @PostMapping
    public ResponseEntity<CourseRegistrationDTO> create(@RequestBody @Valid CourseRegistrationDTO data) {
        return ResponseEntity.ok( courseRegistrationService.save( data ) );
    }

    @Operation(summary = "Update existing courseRegistration by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<CourseRegistrationDTO> update(@PathVariable Integer id, @RequestBody @Valid CourseRegistrationDTO data) {
        return ResponseEntity.ok( courseRegistrationService.update( id, data ) );
    }

    @Operation(summary = "Delete courseRegistration by ID.")
    @GetMapping("/report/{user}")
    public ResponseEntity<RegisterReportDTO> getReport(@PathVariable Integer id) {
        return ResponseEntity.ok( courseRegistrationService.getReport( id ) );
    }

    @Operation(summary = "Delete courseRegistration by ID.")
    @GetMapping("/reportTotal/{user}")
    public ResponseEntity<PricesReportDTO> getReportTotals(@PathVariable Integer id) {
        return ResponseEntity.ok( courseRegistrationService.getReportTotals( id ) );
    }

    @Operation(summary = "Delete courseRegistration by ID.")
    @GetMapping("/reportUsers")
    public ResponseEntity<List<UserDTO>> getReportUsers() {
        return ResponseEntity.ok( courseRegistrationService.getReportUsers() );
    }
}