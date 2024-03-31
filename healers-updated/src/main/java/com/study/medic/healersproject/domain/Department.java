package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.AggregationRoot;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@Builder
@Getter
public class Department extends DomainObject implements AggregationRoot {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doctor> doctors ;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingSlot> workingSlots;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    protected Department() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public Department create(String name, String description, List<Doctor> doctors,
                             List<WorkingSlot> workingSlots, List<Appointment> appointments) {
        return Department.builder()
                .name(name)
                .description(description)
                .workingSlots(workingSlots)
                .doctors(doctors)
                .appointments(appointments)
                .build();
    }
}
