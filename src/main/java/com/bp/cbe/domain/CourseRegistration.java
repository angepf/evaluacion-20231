package com.bp.cbe.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "T_COURSE_REGISTRATION")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRegistration extends BaseEntity implements Serializable {

	private static final long serialVersionUIDLONG = 1L;

	@ManyToOne
	@JoinColumn(name = "register_id")
	Register register;

	String course;
	
	@Column(name = "start_date")
	Date startDate;
	
	@Column(name = "end_date")
	Date endDate;
}