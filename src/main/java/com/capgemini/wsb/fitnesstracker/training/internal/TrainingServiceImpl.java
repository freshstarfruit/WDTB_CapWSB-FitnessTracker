package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingUpdateDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training createTraining(Training training) throws IllegalArgumentException, UserNotFoundException {
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training already has a DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(final Long trainingId, final TrainingUpdateDto trainingUpdateDto)
            throws TrainingNotFoundException {
        Training existingTraining = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));

        existingTraining.setStartTime(trainingUpdateDto.getStartTime());
        existingTraining.setEndTime(trainingUpdateDto.getEndTime());
        existingTraining.setActivityType(trainingUpdateDto.getActivityType());
        existingTraining.setDistance(trainingUpdateDto.getDistance());
        existingTraining.setAverageSpeed(trainingUpdateDto.getAverageSpeed());

        return trainingRepository.save(existingTraining);
    }
}
