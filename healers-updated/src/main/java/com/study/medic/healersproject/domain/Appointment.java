package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.PatientAcceptedException;
import com.study.medic.healersproject.app.api.UnexpectedTimeSlotReserve;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Builder
@Getter
public class Appointment extends DomainObject {
    @OneToOne
    @JoinColumn(name = "working_slot_id")
    private WorkingSlot workingSlot;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Setter
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    protected Appointment() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public static Appointment create(Patient patient, Doctor doctor, WorkingSlot targetWorkingSlot) {
        WorkingSlot workingSlot = doctor.getWorkingSlot();
        TimeSlot doctorsTimeSlot = workingSlot.getTimeSlot();
        if (targetWorkingSlot.getTimeSlot().getStartTime().isBefore(doctorsTimeSlot.getStartTime()) ||
                targetWorkingSlot.getTimeSlot().getEndTime().isAfter(doctorsTimeSlot.getEndTime())) {
            throw new UnexpectedTimeSlotReserve(targetWorkingSlot.getTimeSlot().getId(), doctor.getId());
        }
        if (!patient.isAccepted() || patient.getMedicalRecordNumber() == null) {
            throw new PatientAcceptedException();
        }
        return Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .workingSlot(targetWorkingSlot)
                .build();
    }

    public void reserve(Patient patient, WorkingSlot workingSlot) {
        if (patient.hasAppointment(workingSlot.getTimeSlot())) {
            throw new IllegalStateException("Patient already has an appointment reservation for this time slot.");
        }
        this.patient = patient;
        this.workingSlot = workingSlot;
    }
}
