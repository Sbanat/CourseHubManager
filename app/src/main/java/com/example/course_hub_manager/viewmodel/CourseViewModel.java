package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.Course;
import com.example.course_hub_manager.data.repository.CourseRepository;
import com.example.course_hub_manager.data.entities.Category;
import com.example.course_hub_manager.data.repository.CategoryRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
        categoryRepository = new CategoryRepository(application);
    }

    public LiveData<List<Course>> getCoursesByCategory(int categoryId) {
        return courseRepository.getCoursesByCategory(categoryId);
    }

    public LiveData<Course> getCourseById(int id) {
        return courseRepository.getCourseById(id);
    }

    // إضافة الدالة getCategories للحصول على التصنيفات
    public LiveData<List<Category>> getCategories() {
        return categoryRepository.getAllCategories();
    }
}
