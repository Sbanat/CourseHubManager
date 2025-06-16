package com.example.course_hub_manager.data.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.course_hub_manager.data.AppDatabase;
import com.example.course_hub_manager.data.dao.UserDao;
import com.example.course_hub_manager.data.entities.User;

public class UserRepository {

    private final UserDao userDao;
    private final SharedPreferences prefs;

    private static final String PREFS_NAME = "coursehub_prefs";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void addSampleUsers() {
        new Thread(() -> {
            User user1 = new User("saleh", "saleh@hopeschool.com", "123456");
            User user2 = new User("mohammed", "mohammed@hopeschool.com", "123456");
            User user3 = new User("omar", "omar@hopeschool.com", "123456");

            userDao.insert(user1);
            userDao.insert(user2);
            userDao.insert(user3);
        }).start();
    }

    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public LiveData<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    public LiveData<User> getCurrentUser() {
        int currentUserId = prefs.getInt(KEY_CURRENT_USER_ID, -1);
        if (currentUserId == -1) {
            return new MutableLiveData<>(null);
        }
        return userDao.getUserById(currentUserId);
    }

    public void insert(User user) {
        new Thread(() -> userDao.insert(user)).start();
    }
}
