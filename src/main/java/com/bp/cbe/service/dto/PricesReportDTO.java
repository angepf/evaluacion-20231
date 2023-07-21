package com.bp.cbe.service.dto;

import com.bp.cbe.external.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricesReportDTO extends BaseEntityDTO {

	UserDTO user;

	BigDecimal price;

	List<CourseRegistrationDTO> registrations;
}