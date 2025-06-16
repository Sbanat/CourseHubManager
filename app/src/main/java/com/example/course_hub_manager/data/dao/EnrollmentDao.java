package com.example.course_hub_manager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.course_hub_manager.data.entities.Enrollment;
import com.example.course_hub_manager.data.entities.Course;

import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert
    void enroll(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);

    @Query("SELECT * FROM enrollments WHERE userId = :userId")
    LiveData<List<Enrollment>> getEnrollmentsByUser(int userId);

    @Query("SELECT * FROM enrollments WHERE userId = :userId AND courseId = :courseId LIMIT 1")
    Enrollment getEnrollment(int userId, int courseId);

    @Query("SELECT * FROM enrollments WHERE userId = :userId AND courseId = :courseId LIMIT 1")
    LiveData<Enrollment> getEnrollmentLive(int userId, int courseId);

    @Query("SELECT courses.* FROM courses " +
            "INNER JOIN enrollments ON courses.id = enrollments.courseId " +
            "WHERE enrollments.userId = :userId AND enrollments.status = :status")
    LiveData<List<Course>> getEnrolledCoursesByStatus(int userId, String status);
}
