package com.bp.cbe.service.dto;

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
public class RegisterDTO extends BaseEntityDTO {

	Integer user;

	Date registerDate;

	List<CourseRegistrationDTO> registrations;
}