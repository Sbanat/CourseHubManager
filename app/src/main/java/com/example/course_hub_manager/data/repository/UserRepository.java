package com.example.course_hub_manager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.UserDao;
import com.example.course_hub_manager.data.entities.User;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
    }

    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public User login(String email, String password) {
        return userDao.login(email, password);
    }

    public LiveData<User> getUserById(int id) {
        return userDao.getUserById(id);
    }
}
