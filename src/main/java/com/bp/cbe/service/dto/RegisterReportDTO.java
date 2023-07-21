package com.bp.cbe.service.dto;

import com.bp.cbe.external.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterReportDTO extends BaseEntityDTO {

	UserDTO user;

	Date registerDate;

	List<CourseRegistrationReportDTO> registrations;
}