package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
class TrainingProviderImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingProviderImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getAllTrainingsByUserId(final Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getAllTrainingsByEndTimeAfter(final Date date) {
        return trainingRepository.findAllByEndTimeAfter(date);
    }

    @Override
    public List<Training> getAllTrainingsByActivityType(final ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType);
    }
}