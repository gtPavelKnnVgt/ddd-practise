package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.AggregationRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Department extends DomainObject implements AggregationRoot {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doctor> doctors = new ArrayList<>();
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingSlot> workingSlots = new ArrayList<>();
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();
}
