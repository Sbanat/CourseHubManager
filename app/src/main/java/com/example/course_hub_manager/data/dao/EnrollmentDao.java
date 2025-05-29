package com.example.course_hub_manager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.course_hub_manager.data.entities.Enrollment;

import java.util.List;

@Dao
public interface EnrollmentDao {
    @Insert
    void enroll(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Query("SELECT * FROM enrollments WHERE userId = :userId")
    LiveData<List<Enrollment>> getEnrollmentsByUser(int userId);

    @Query("SELECT * FROM enrollments WHERE userId = :userId AND courseId = :courseId LIMIT 1")
    Enrollment getEnrollment(int userId, int courseId);
}
