package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;

@Entity(tableName = "bookmarks", primaryKeys = {"userId", "courseId"})
public class Bookmark {
    public int userId;
    public int courseId;

    public Bookmark(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }
}
