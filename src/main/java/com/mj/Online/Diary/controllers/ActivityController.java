package com.mj.Online.Diary.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.Online.Diary.models.Activity;
import com.mj.Online.Diary.services.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();

        return ResponseEntity.ok(activities);
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> activityOptional = activityService.getActivityById(id);

        return activityOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateEntity(@PathVariable Long id, @RequestBody Activity updatedActivity) {
        Activity activity = activityService.updateActivity(id, updatedActivity);

        return activity != null ? ResponseEntity.ok(activity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable Long id) {
        boolean deleted = activityService.deleteActivity(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
