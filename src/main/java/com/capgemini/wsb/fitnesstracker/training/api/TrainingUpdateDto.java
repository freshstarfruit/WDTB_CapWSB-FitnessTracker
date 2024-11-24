package com.capgemini.wsb.fitnesstracker.training.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class TrainingUpdateDto {
    private final Date startTime;
    private final Date endTime;
    private final ActivityType activityType;
    private final double distance;
    private final double averageSpeed;
}
