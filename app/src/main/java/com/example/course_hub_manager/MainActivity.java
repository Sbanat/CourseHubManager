package com.example.course_hub_manager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.course_hub_manager.R;
import com.example.course_hub_manager.adapter.CourseAdapter;
import com.example.course_hub_manager.viewmodel.CourseViewModel;
import com.example.course_hub_manager.view.MyCoursesActivity;
import com.example.course_hub_manager.view.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter(course -> {
            // Handle course click (optional)
        });

        recyclerView.setAdapter(courseAdapter);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseViewModel.getCoursesByCategory(1).observe(this, courses -> {
            if (courses != null && !courses.isEmpty()) {
                courseAdapter.submitList(courses);
            }
        });

        // BottomNavigationView item selection listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                // Stay on MainActivity
                return true;
            } else if (item.getItemId() == R.id.navigation_my_courses) {
                // Navigate to My Courses Activity
                Intent coursesIntent = new Intent(MainActivity.this, MyCoursesActivity.class);
                startActivity(coursesIntent);
                return true;
            } else if (item.getItemId() == R.id.navigation_profile) {
                // Navigate to Profile Activity
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
            } else {
                return false;
            }
        });

        // Set OnClickListener for buttons
        findViewById(R.id.btn_programming).setOnClickListener(v -> {
            // Navigate to Programming Activity
            Intent intent = new Intent(MainActivity.this, ProgrammingActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_mathematics).setOnClickListener(v -> {
            // Navigate to Mathematics Activity
            Intent intent = new Intent(MainActivity.this, MathematicsActivity.class);
            startActivity(intent);
        });

    }
}
