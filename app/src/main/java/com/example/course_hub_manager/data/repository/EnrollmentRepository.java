package com.example.course_hub_manager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.EnrollmentDao;
import com.example.course_hub_manager.data.entities.Enrollment;

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

    public void enroll(Enrollment enrollment) {
        executor.execute(() -> enrollmentDao.insert(enrollment));
    }

    public void update(Enrollment enrollment) {
        executor.execute(() -> enrollmentDao.update(enrollment));
    }

    public void delete(Enrollment enrollment) {
        executor.execute(() -> enrollmentDao.delete(enrollment));
    }

    public LiveData<List<Enrollment>> getEnrolledCourses(int userId) {
        return enrollmentDao.getEnrollmentsByUser(userId);
    }

    public LiveData<Enrollment> getEnrollment(int userId, int courseId) {
        return enrollmentDao.getEnrollment(userId, courseId);
    }
}
