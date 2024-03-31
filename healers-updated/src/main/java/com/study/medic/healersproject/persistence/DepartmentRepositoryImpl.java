package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.app.api.DepartmentNotFoundException;
import com.study.medic.healersproject.app.api.DepartmentRepository;
import com.study.medic.healersproject.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final DepartmentJpaRepository departmentJpaRepository;

    @Override
    public Department getDepartmentByDoctorId(Long doctorId) {
        return departmentJpaRepository.findByDoctorsIn(List.of(doctorId))
                .orElseThrow(() -> new DepartmentNotFoundException(doctorId));
    }
}
