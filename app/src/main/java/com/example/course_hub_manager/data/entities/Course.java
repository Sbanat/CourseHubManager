package com.example.course_hub_manager.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public String instructor;
    public String imageUrl;
    public double price;
    public int hours;
    public int studentsCount;
    public int categoryId;  // Foreign key to Category (يمكنك لاحقًا إضافة علاقة في DAO)
}
