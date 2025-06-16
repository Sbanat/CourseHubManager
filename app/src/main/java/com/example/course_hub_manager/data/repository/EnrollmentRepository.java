package com.example.course_hub_manager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.EnrollmentDao;
import com.example.course_hub_manager.data.entities.Enrollment;
import com.example.course_hub_manager.data.entities.Course;  // استيراد فئة Course

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EnrollmentRepository {
    private final EnrollmentDao enrollmentDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public EnrollmentRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        enrollmentDao = db.enrollmentDao();
    }

    public void insert(Enrollment enrollment) {
        executor.execute(() -> enrollmentDao.enroll(enrollment));
    }

    public void delete(Enrollment enrollment) {
        executor.execute(() -> enrollmentDao.delete(enrollment));
    }

    public LiveData<Enrollment> getEnrollmentLive(int userId, int courseId) {
        return enrollmentDao.getEnrollmentLive(userId, courseId);
    }

    public LiveData<List<Course>> getEnrolledCoursesByStatus(int userId, String status) {
        return enrollmentDao.getEnrolledCoursesByStatus(userId, status);
    }
}
