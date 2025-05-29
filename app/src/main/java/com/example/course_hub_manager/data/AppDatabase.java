package com.example.course_hub_manager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.course_hub_manager.data.dao.*;
import com.example.course_hub_manager.data.entities.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
                User.class,
                Category.class,
                Course.class,
                Lesson.class,
                Enrollment.class,
                Bookmark.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract CourseDao courseDao();
    public abstract LessonDao lessonDao();
    public abstract EnrollmentDao enrollmentDao();
    public abstract BookmarkDao bookmarkDao();
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "course_hub_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;


    }
}
