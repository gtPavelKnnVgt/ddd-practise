package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.UnexpectedTimeSlotReserve;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timeslot")
@Builder
@Getter
@Setter
public class TimeSlot extends DomainObject {
    private static final int ONE_HOUR = 1;

    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "endTime", nullable = false)
    private LocalDateTime endTime;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private List<Appointment> appointments;

    protected TimeSlot() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public static TimeSlot create(LocalDateTime startTime, LocalDateTime endTime) {
        if (LocalDateTime.now().isAfter(startTime) ||
                Duration.between(startTime, endTime).toHours() > ONE_HOUR) {
            throw new UnexpectedTimeSlotReserve(startTime);
        }
        if (startTime.isAfter(endTime)) {
            throw new UnexpectedTimeSlotReserve(startTime, endTime);
        }
        return TimeSlot.builder()
                .startTime(startTime)
                .endTime(endTime)
                .appointments(new ArrayList<>())
                .build();
    }

    public boolean overlaps(TimeSlot other) {
        // Implement the logic to check if the two time slots overlap
        return this.startTime.isBefore(other.getEndTime()) && this.endTime.isAfter(other.getStartTime());
    }
}
