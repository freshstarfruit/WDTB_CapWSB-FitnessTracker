package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;

/**
 * Interface (API) for modifying operations on {@link Training} entities.
 * Responsible for executing changes within a database transaction,
 * whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    /**
     * Creates a new training.
     * If the training already exists (i.e., has a DB ID), {@link IllegalArgumentException} will be thrown.
     * If the user associated with the training doesn't exist, {@link UserNotFoundException} will be thrown.
     *
     * @param training The training to be created.
     * @return The created training.
     * @throws IllegalArgumentException if the training already has a DB ID.
     * @throws UserNotFoundException if the user associated with the training is not found.
     */
    Training createTraining(Training training) throws IllegalArgumentException, UserNotFoundException;

    /**
     * Updates an existing training.
     * If the training with the specified ID doesn't exist, {@link TrainingNotFoundException} will be thrown.
     *
     * @param trainingId The ID of the training to be updated.
     * @param trainingUpdateDto The data to update the training with.
     * @return The updated training.
     * @throws TrainingNotFoundException if the training with the specified ID is not found.
     */
    Training updateTraining(Long trainingId, TrainingUpdateDto trainingUpdateDto) throws TrainingNotFoundException;
}
