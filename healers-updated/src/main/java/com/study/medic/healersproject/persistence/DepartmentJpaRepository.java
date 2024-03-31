package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDoctorsIn(List<Long> doctorsId);
}
