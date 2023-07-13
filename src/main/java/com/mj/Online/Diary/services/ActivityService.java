package com.mj.Online.Diary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mj.Online.Diary.models.Activity;
import com.mj.Online.Diary.repositories.ActivityRepository;

public class ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // Get all the activities
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // Create a new activity
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    // Get activity by it's id
    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    // Update an existing activity
    public Activity updateActivity(Long id, Activity updatedActivity) {
        Optional<Activity> existingActivityOptional = activityRepository.findById(id);
        if (existingActivityOptional.isPresent()) {
            Activity existingActivity = existingActivityOptional.get();
            existingActivity.setTitle(updatedActivity.getTitle());
            existingActivity.setDescription(updatedActivity.getDescription());

            return activityRepository.save(existingActivity);
        }
        return null;
    }

    // Delete an existing activity
    public boolean deleteActivity(Long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
