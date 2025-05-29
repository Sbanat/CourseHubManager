package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.Course;
import com.example.course_hub_manager.data.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private final CourseRepository repository;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
    }

    public void insert(Course course) {
        repository.insert(course);
    }

    public void update(Course course) {
        repository.update(course);
    }

    public void delete(Course course) {
        repository.delete(course);
    }

    public LiveData<List<Course>> getCoursesByCategory(int categoryId) {
        return repository.getCoursesByCategory(categoryId);
    }

    public LiveData<Course> getCourseById(int id) {
        return repository.getCourseById(id);
    }
}
