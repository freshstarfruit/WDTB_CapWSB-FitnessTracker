package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {
    private final UserMapper userMapper;
    private final UserProvider userProvider;

    @Autowired
    public TrainingMapper(UserMapper userMapper, UserProvider userProvider) {
        this.userMapper = userMapper;
        this.userProvider = userProvider;
    }

    public TrainingDto toDto(Training training) {
        if (training == null) {
            return null;
        }

        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    public Training toEntity(TrainingDto trainingDto) {
        if (trainingDto == null) {
            return null;
        }

        return new Training(
                userMapper.toEntity(trainingDto.getUser()),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
    }

    public Training toEntity(TrainingShallowDto trainingShallowDto) throws UserNotFoundException {
        if (trainingShallowDto == null) {
            return null;
        }

        User user = userProvider.getUser(trainingShallowDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(trainingShallowDto.getUserId()));

        return new Training(
                user,
                trainingShallowDto.getStartTime(),
                trainingShallowDto.getEndTime(),
                trainingShallowDto.getActivityType(),
                trainingShallowDto.getDistance(),
                trainingShallowDto.getAverageSpeed()
        );
    }
}
