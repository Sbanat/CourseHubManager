package com.example.course_hub_manager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.course_hub_manager.data.entities.Lesson;

import java.util.List;

@Dao
public interface LessonDao {
    @Insert
    void insert(Lesson lesson);

    @Update
    void update(Lesson lesson);

    @Delete
    void delete(Lesson lesson);

    @Query("SELECT * FROM lessons WHERE courseId = :courseId")
    LiveData<List<Lesson>> getLessonsByCourse(int courseId);
}
