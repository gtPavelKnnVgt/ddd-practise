package com.study.medic.healersproject.app.api;

import java.time.LocalDateTime;

public class UnexpectedTimeSlotReserve extends RuntimeException {
    private static final String ERROR_MESSAGE = "Cannot reserve timeslot %s. There is no matches in working slot within doctor id %s";
    private static final String ERROR_MESSAGE_WITH_TIME = "Trying to reserve slot by past datetime: %s. Cannot resolve.";
    private static final String ERROR_MESSAGE_WITH_START_END_TIME = "Trying to reserve slot by start date %s after end date: %s. Cannot resolve.";

    public UnexpectedTimeSlotReserve(Long timeSlotId, Long doctorId) {
        super(String.format(ERROR_MESSAGE, timeSlotId, doctorId));
    }

    public UnexpectedTimeSlotReserve(LocalDateTime startTime) {
        super(String.format(ERROR_MESSAGE_WITH_TIME, startTime));
    }

    public UnexpectedTimeSlotReserve(LocalDateTime startTime, LocalDateTime endTime) {
        super(String.format(ERROR_MESSAGE_WITH_START_END_TIME, startTime, endTime));
    }
}
