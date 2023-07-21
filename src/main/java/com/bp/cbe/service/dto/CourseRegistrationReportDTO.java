package com.bp.cbe.service.dto;

import com.bp.cbe.external.dto.CourseDTO;
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
public class CourseRegistrationReportDTO extends BaseEntityDTO {

	RegisterDTO register;

	CourseDTO course;

	Date startDate;

	Date endDate;
}