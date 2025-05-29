package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.Enrollment;
import com.example.course_hub_manager.data.repository.EnrollmentRepository;

import java.util.List;

public class EnrollmentViewModel extends AndroidViewModel {
    private final EnrollmentRepository repository;

    public EnrollmentViewModel(@NonNull Application application) {
        super(application);
        repository = new EnrollmentRepository(application);
    }

    public void enroll(Enrollment enrollment) {
        repository.enroll(enrollment);
    }

    public void update(Enrollment enrollment) {
        repository.update(enrollment);
    }

    public void delete(Enrollment enrollment) {
        repository.delete(enrollment);
    }

    public LiveData<List<Enrollment>> getEnrolledCourses(int userId) {
        return repository.getEnrolledCourses(userId);
    }

    public LiveData<Enrollment> getEnrollment(int userId, int courseId) {
        return repository.getEnrollment(userId, courseId);
    }
}

