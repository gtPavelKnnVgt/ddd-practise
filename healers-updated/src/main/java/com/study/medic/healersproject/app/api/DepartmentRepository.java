package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Department;

public interface DepartmentRepository {
    /**
     * Найти департамент по идентификатору врача
     * @param doctorId идентификатор врача
     * @return найденный департамент
     */
    Department getDepartmentByDoctorId(Long doctorId);

    /**
     * Сохранить информацию по департаменту
     * @param department департамент для сохранения
     * @return сохраненный департамент
     */
    Department saveAndFlush(Department department);
}
