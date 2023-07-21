package com.bp.cbe.external.dto;

import com.bp.cbe.domain.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString(includeFieldNames = false, callSuper = true)
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {

	String code;

	String name;

	BigDecimal price;

	String description;

	@JsonProperty("default")
	Boolean byDefault;

	StatusEnum status;
}