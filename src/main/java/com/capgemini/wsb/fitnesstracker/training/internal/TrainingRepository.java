package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByUserId(Long userId);
    List<Training> findAllByEndTimeAfter(Date time);
    List<Training> findAllByActivityType(ActivityType activityType);
}
