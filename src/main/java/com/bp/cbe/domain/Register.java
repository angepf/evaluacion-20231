package com.bp.cbe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "T_REGISTER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Register extends BaseEntity implements Serializable {

	private static final long serialVersionUIDLONG = 1L;

	Integer user;

	@Column(name = "register_date", nullable = false)
	Date registerDate;
	
	@OneToMany(mappedBy = "register")
    List<CourseRegistration> registrations;
}