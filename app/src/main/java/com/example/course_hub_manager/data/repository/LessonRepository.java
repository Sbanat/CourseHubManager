package com.example.course_hub_manager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.LessonDao;
import com.example.course_hub_manager.data.entities.Lesson;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LessonRepository {
    private final LessonDao lessonDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public LessonRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        lessonDao = db.lessonDao();
    }

    public void insert(Lesson lesson) {
        executor.execute(() -> lessonDao.insert(lesson));
    }

    public void update(Lesson lesson) {
        executor.execute(() -> lessonDao.update(lesson));
    }

    public void delete(Lesson lesson) {
        executor.execute(() -> lessonDao.delete(lesson));
    }

    public LiveData<List<Lesson>> getLessonsByCourse(int courseId) {
        return lessonDao.getLessonsByCourse(courseId);
    }
}
