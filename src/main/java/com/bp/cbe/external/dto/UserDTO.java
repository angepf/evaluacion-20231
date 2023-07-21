package com.bp.cbe.external.dto;

import com.bp.cbe.domain.enums.StatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

	Integer id;
	String identificationNumber;

	String name;

	String lastname;

	String phoneNumber;

	StatusEnum status;

}