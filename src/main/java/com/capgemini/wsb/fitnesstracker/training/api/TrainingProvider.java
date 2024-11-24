package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for managing operations on {@link Training} entities.
 * Implementing classes are responsible for executing changes within a database transaction,
 * whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training based on its ID.
     * If the training with the given ID is not found, {@link Optional#empty()} will be returned.
     *
     * @param trainingId The ID of the training to be searched.
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found.
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings in the system.
     *
     * @return A {@link List} containing all trainings.
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all trainings for a specific user based on their user ID.
     *
     * @param userId The ID of the user whose trainings are to be retrieved.
     * @return A {@link List} containing trainings associated with the given user ID.
     */
    List<Training> getAllTrainingsByUserId(Long userId);

    /**
     * Retrieves all trainings that ended after the specified date.
     *
     * @param date The threshold date.
     * @return A {@link List} containing trainings that ended after the given date.
     */
    List<Training> getAllTrainingsByEndTimeAfter(Date date);

    /**
     * Retrieves all trainings filtered by a specific activity type.
     *
     * @param activityType The activity type to filter trainings by.
     * @return A {@link List} containing trainings of the given activity type.
     */
    List<Training> getAllTrainingsByActivityType(ActivityType activityType);
}
