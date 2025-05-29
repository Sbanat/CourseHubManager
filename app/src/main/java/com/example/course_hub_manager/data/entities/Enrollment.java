package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;

@Entity(tableName = "enrollments", primaryKeys = {"userId", "courseId"})
public class Enrollment {
    public int userId;
    public int courseId;
    public boolean completed;
}
