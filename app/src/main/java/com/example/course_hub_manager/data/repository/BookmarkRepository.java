package com.example.course_hub_manager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.BookmarkDao;
import com.example.course_hub_manager.data.entities.Bookmark;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookmarkRepository {
    private final BookmarkDao bookmarkDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public BookmarkRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        bookmarkDao = db.bookmarkDao();
    }

    public void addBookmark(Bookmark bookmark) {
        executor.execute(() -> bookmarkDao.insert(bookmark));
    }

    public void removeBookmark(Bookmark bookmark) {
        executor.execute(() -> bookmarkDao.delete(bookmark));
    }

    public LiveData<List<Bookmark>> getBookmarksByUser(int userId) {
        return bookmarkDao.getBookmarksByUser(userId);
    }

    public LiveData<Bookmark> getBookmark(int userId, int courseId) {
        return bookmarkDao.getBookmark(userId, courseId);
    }
}
