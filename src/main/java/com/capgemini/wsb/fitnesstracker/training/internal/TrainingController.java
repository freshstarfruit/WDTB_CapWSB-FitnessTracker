package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingUpdateDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingProviderImpl trainingProvider;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingProvider.getAllTrainings()
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingShallowDto trainingShallowDto) throws UserNotFoundException {
        Training training = trainingMapper.toEntity(trainingShallowDto);
        Training persistedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(persistedTraining);
    }

    @PutMapping("{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingUpdateDto trainingUpdateDto) {
        Training updatedTraining = trainingService.updateTraining(trainingId, trainingUpdateDto);
        return trainingMapper.toDto(updatedTraining);
    }

    @GetMapping("{userId}")
    public List<TrainingDto> getAllTrainingsByUserId(@PathVariable long userId) {
        return trainingProvider.getAllTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    private Date parseDate(String input) throws ParseException {
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");

        try {
            return fullFormat.parse(input);
        } catch (ParseException ignored) {
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateOnlyFormat.parse(input);
            return new Date(date.getTime());
        }
    }

    @GetMapping("finished/{afterTime}")
    public List<TrainingDto> getAllByEndTimeAfter(@PathVariable String afterTime) throws ParseException {
        Date parsedDate = parseDate(afterTime);

        return trainingProvider.getAllTrainingsByEndTimeAfter(parsedDate)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("activityType")
    public List<TrainingDto> getAllByActivityType(@RequestParam ActivityType activityType) {
        return trainingProvider.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
