package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.Bookmark;
import com.example.course_hub_manager.data.repository.BookmarkRepository;

import java.util.List;

public class BookmarkViewModel extends AndroidViewModel {
    private final BookmarkRepository repository;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        repository = new BookmarkRepository(application);
    }

    public void addBookmark(Bookmark bookmark) {
        repository.addBookmark(bookmark);
    }

    public void removeBookmark(Bookmark bookmark) {
        repository.removeBookmark(bookmark);
    }

    public LiveData<List<Bookmark>> getBookmarksByUser(int userId) {
        return repository.getBookmarksByUser(userId);
    }

    public LiveData<Bookmark> getBookmark(int userId, int courseId) {
        return repository.getBookmark(userId, courseId);
    }
}
