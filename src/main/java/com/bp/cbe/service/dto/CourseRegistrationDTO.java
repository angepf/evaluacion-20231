package com.bp.cbe.service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRegistrationDTO extends BaseEntityDTO {

	RegisterDTO register;

	String course;

	Date startDate;

	Date endDate;
}