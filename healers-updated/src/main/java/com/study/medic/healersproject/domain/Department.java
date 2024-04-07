package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.Aggregator;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Builder
@Getter
public class Department extends DomainObject implements Aggregator {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doctor> doctors;

    protected Department() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public Department create(String name, String description, List<Doctor> doctors) {
        return Department.builder()
                .name(name)
                .description(description)
                .doctors(doctors)
                .build();
    }

    public void addDoctor(Doctor doctor) {
        if (doctors == null) {
            doctors = new ArrayList<>();
            doctors.add(doctor);
        } else {
            doctors.add(doctor);
        }
    }
}
