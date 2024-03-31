package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.DoctorNotValidException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "working_slot")
@Builder
@Getter
@Setter
public class WorkingSlot extends DomainObject {
    private static final String VALID_DAY_OF_WEEK = "^(MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY|SATURDAY|SUNDAY)$";

    @Column(name = "day_of_week", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;
    @ManyToOne
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private List<Doctor> doctors;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    protected WorkingSlot() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public static WorkingSlot create(String dayOfWeek, TimeSlot timeSlot) {
        if (!isValidDayOfWeek(dayOfWeek)) {
            throw new DoctorNotValidException(dayOfWeek);
        }
        return WorkingSlot.builder()
                .dayOfWeek(DayOfWeek.valueOf(dayOfWeek))
                .timeSlot(timeSlot)
                .doctors(new ArrayList<>())
                .build();
    }

    public static void updateWorkingSlot(WorkingSlot oldWorkingSlot, LocalDateTime startAt, DayOfWeek nextDay) {
        TimeSlot oldTimeSlot = oldWorkingSlot.getTimeSlot();
        Duration duration = Duration.between(oldTimeSlot.getStartTime(), oldTimeSlot.getEndTime());
        oldWorkingSlot.setDayOfWeek(nextDay);
        oldWorkingSlot.getTimeSlot().setStartTime(startAt);
        oldWorkingSlot.getTimeSlot().setEndTime(startAt.plus(duration));
    }

    private static boolean isValidDayOfWeek(String dayOfWeek) {
        Pattern pattern = Pattern.compile(VALID_DAY_OF_WEEK);
        Matcher matcher = pattern.matcher(dayOfWeek);
        return matcher.matches();
    }


    public static DayOfWeek getNextWorkDayOfWeek(DayOfWeek dayOfWeek) {
        DayOfWeek nextDay = dayOfWeek.plus(1);
        if (DayOfWeek.SATURDAY.equals(nextDay) || DayOfWeek.SUNDAY.equals(nextDay)) {
            nextDay = DayOfWeek.MONDAY;
        }
        return nextDay;
    }
}
