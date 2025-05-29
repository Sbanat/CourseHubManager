package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String content;
    public String videoUrl;
    public int courseId;  // Foreign key to Course
}
