package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.Course;
import com.example.course_hub_manager.data.entities.Enrollment;
import com.example.course_hub_manager.data.repository.EnrollmentRepository;

import java.util.List;

public class EnrollmentViewModel extends AndroidViewModel {

    private final EnrollmentRepository repository;

    public EnrollmentViewModel(@NonNull Application application) {
        super(application);
        repository = new EnrollmentRepository(application);
    }

    public void insertEnrollment(Enrollment enrollment) {
        repository.insert(enrollment);
    }

    public void deleteEnrollment(Enrollment enrollment) {
        repository.delete(enrollment);
    }

    public LiveData<Enrollment> isEnrolled(int userId, int courseId) {
        return repository.getEnrollmentLive(userId, courseId);
    }

    public LiveData<List<Course>> getEnrolledCoursesByStatus(int userId, String status) {
        return repository.getEnrolledCoursesByStatus(userId, status); // استرجاع الدورات بدلاً من التسجيلات
    }
}
