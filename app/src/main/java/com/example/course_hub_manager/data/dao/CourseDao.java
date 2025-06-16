package com.example.course_hub_manager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.course_hub_manager.data.entities.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM courses WHERE categoryId = :categoryId")
    LiveData<List<Course>> getCoursesByCategory(int categoryId);

    @Query("SELECT * FROM courses WHERE id = :id")
    LiveData<Course> getCourseById(int id);
}
