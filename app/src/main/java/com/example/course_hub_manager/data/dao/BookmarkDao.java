package com.example.course_hub_manager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.course_hub_manager.data.entities.Bookmark;
import com.example.course_hub_manager.data.entities.Course;

import java.util.List;

@Dao
public interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Bookmark bookmark);

    @Delete
    void delete(Bookmark bookmark);

    @Query("SELECT * FROM bookmarks WHERE userId = :userId")
    LiveData<List<Bookmark>> getBookmarksByUser(int userId);

    @Query("SELECT * FROM bookmarks WHERE userId = :userId AND courseId = :courseId LIMIT 1")
    LiveData<Bookmark> getBookmark(int userId, int courseId);

    @Query("SELECT c.* FROM courses c INNER JOIN bookmarks b ON c.id = b.courseId WHERE b.userId = :userId")
    LiveData<List<Course>> getBookmarkedCourses(int userId);
}
