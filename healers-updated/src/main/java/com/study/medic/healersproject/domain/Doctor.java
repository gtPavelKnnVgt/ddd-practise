package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.DoctorNotValidException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "doctor")
@Builder
@Getter
public class Doctor extends DomainObject {
    private static final String NAME_REGEX = "^[A-Za-z\\s]+$";

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "specialization", nullable = false)
    @Enumerated(EnumType.STRING)
    private DoctorSpecialization specialization;
    @Column(name = "license_number")
    private String licenseNumber;
    @ManyToOne
    @JoinColumn(name = "working_slot_id")
    private WorkingSlot workingSlot;

    @Setter
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    protected Doctor() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public void updateAvailability(WorkingSlot newWorkingSlot, List<Appointment> appointmentsForCheck) {
        List<Appointment> conflictingAppointments = appointmentsForCheck.stream()
                .filter(appointment -> !appointment.getWorkingSlot().getTimeSlot().overlaps(newWorkingSlot.getTimeSlot()))
                .toList();

        for (Appointment appointment : conflictingAppointments) {
            updateSlotAndNotifyPatient(newWorkingSlot, appointment, conflictingAppointments);
        }

        this.workingSlot = newWorkingSlot;
    }

    public static Doctor create(WorkingSlot workingSlot, String firstName, String lastName,
                                DoctorSpecialization specialization, String licenseNumber) {
        if (!isValidName(firstName) || !isValidName(lastName)) {
            throw new DoctorNotValidException(firstName, lastName);
        }
        return Doctor.builder()
                .firstName(firstName)
                .lastName(lastName)
                .specialization(specialization)
                .licenseNumber(licenseNumber)
                .workingSlot(workingSlot)
                .build();
    }

    private static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private void updateSlotAndNotifyPatient(WorkingSlot newWorkingSlot, Appointment appointment, List<Appointment> appointments) {
        // Implement the logic to find the first available time slot based on the working slot and the current time slot
        // ...
        WorkingSlot oldWorkingSlot = appointment.getWorkingSlot();
        if (LocalDate.now().isBefore(newWorkingSlot.getTimeSlot().getStartTime().toLocalDate())
                || LocalDateTime.now().isBefore(newWorkingSlot.getTimeSlot().getStartTime())
                && LocalDateTime.now().isAfter(newWorkingSlot.getTimeSlot().getEndTime())) {
            List<Appointment> appointmentsWithOverlappingWorkingSlots = appointments.stream()
                    .filter(app -> app.getWorkingSlot().getTimeSlot().overlaps(newWorkingSlot.getTimeSlot()))
                    .toList();
            Appointment lastAppointmentWithOverlappingSlot = appointmentsWithOverlappingWorkingSlots
                    .get(appointmentsWithOverlappingWorkingSlots.size() - 1);
            DayOfWeek nextDayOfWeek = WorkingSlot.getNextWorkDayOfWeek(oldWorkingSlot.getDayOfWeek());
            WorkingSlot.updateWorkingSlot(oldWorkingSlot, lastAppointmentWithOverlappingSlot.getWorkingSlot().getTimeSlot().getStartTime(),
                    nextDayOfWeek);
            Patient.notifyAppointmentTimeSlotChanged(appointment);
        }
    }
}
