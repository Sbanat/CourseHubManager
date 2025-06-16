package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.Lesson;
import com.example.course_hub_manager.data.repository.LessonRepository;

import java.util.List;

public class LessonViewModel extends AndroidViewModel {
    private final LessonRepository repository;

    public LessonViewModel(@NonNull Application application) {
        super(application);
        repository = new LessonRepository(application);
    }

    public void insert(Lesson lesson) {
        repository.insert(lesson);
    }

    public void update(Lesson lesson) {
        repository.update(lesson);
    }

    public void delete(Lesson lesson) {
        repository.delete(lesson);
    }

    public LiveData<List<Lesson>> getLessonsByCourseId(int courseId) {
        return repository.getLessonsByCourse(courseId);
    }
    }

