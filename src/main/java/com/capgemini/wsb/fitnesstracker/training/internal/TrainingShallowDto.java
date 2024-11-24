package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ActivityType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.Nullable;

import java.util.Date;

@Getter
@RequiredArgsConstructor
class TrainingShallowDto {
    @Nullable
    private Long id;
    @Nullable
    private final Long userId;
    private final Date startTime;
    private final Date endTime;
    private final ActivityType activityType;
    private final double distance;
    private final double averageSpeed;
}
