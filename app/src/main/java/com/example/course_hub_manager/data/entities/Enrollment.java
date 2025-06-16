package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity(tableName = "enrollments")
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public int courseId;

    @NonNull
    public String status;



    public Enrollment() {
        // Required by Room
    }

    public Enrollment(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
