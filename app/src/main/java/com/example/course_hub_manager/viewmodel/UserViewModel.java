package com.example.course_hub_manager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.course_hub_manager.data.entities.User;
import com.example.course_hub_manager.data.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<User> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    public LiveData<User> getUserById(int id) {
        return repository.getUserById(id);
    }

    public LiveData<User> getCurrentUser() {
        return repository.getCurrentUser();
    }

    public void insertUser(User user) {
        repository.insert(user);
    }
}
