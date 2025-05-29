package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;

@Entity(tableName = "bookmarks", primaryKeys = {"userId", "courseId"})
public class Bookmark {
    public int userId;
    public int courseId;
}
