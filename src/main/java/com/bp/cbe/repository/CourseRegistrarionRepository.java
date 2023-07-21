package com.bp.cbe.repository;

import com.bp.cbe.domain.CourseRegistration;
import com.bp.cbe.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrarionRepository extends JpaRepository<CourseRegistration, Integer> {

    List<CourseRegistration> findByRegister(Register register);

}