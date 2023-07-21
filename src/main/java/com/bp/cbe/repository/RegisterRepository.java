package com.bp.cbe.repository;

import com.bp.cbe.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {

    Register findByUser(Integer user);

}