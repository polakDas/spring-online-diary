package com.mj.Online.Diary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mj.Online.Diary.models.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
