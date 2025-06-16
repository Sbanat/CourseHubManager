package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String youtubeLink;

    private int courseId;

    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
