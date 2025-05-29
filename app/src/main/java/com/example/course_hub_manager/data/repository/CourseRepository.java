package com.example.course_hub_manager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.CourseDao;
import com.example.course_hub_manager.data.entities.Course;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {
    private final CourseDao courseDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public CourseRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        courseDao = db.courseDao();
    }

    public void insert(Course course) {
        executor.execute(() -> courseDao.insert(course));
    }

    public void update(Course course) {
        executor.execute(() -> courseDao.update(course));
    }

    public void delete(Course course) {
        executor.execute(() -> courseDao.delete(course));
    }

    public LiveData<List<Course>> getCoursesByCategory(int categoryId) {
        return courseDao.getCoursesByCategory(categoryId);
    }

    public LiveData<Course> getCourseById(int id) {
        return courseDao.getCourseById(id);
    }
}
