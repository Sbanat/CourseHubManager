package com.example.course_hub_manager.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.data.entities.Course;
import com.example.course_hub_manager.viewmodel.CourseViewModel;

public class CourseDetailsActivity extends AppCompatActivity {

    private int currentCourseId = -1;
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        // Get the course ID from the Intent
        currentCourseId = getIntent().getIntExtra("course_id", -1);

        if (currentCourseId == -1) {
            Toast.makeText(this, "Error: Course ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize ViewModel
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Fetch course details using the course ID
        courseViewModel.getCourseById(currentCourseId).observe(this, course -> {
            if (course != null) {
                // Update UI with course details
                TextView textCourseName = findViewById(R.id.text_course_name);
                TextView textInstructor = findViewById(R.id.text_instructor);
                TextView textPrice = findViewById(R.id.text_price);
                TextView textStudents = findViewById(R.id.text_students);
                TextView textHours = findViewById(R.id.text_hours);
                TextView textDescription = findViewById(R.id.text_description);

                textCourseName.setText(course.getTitle());
                textInstructor.setText("Instructor: " + course.getInstructor());
                textPrice.setText("Price: " + course.getPrice() + " $");
                textStudents.setText("Students: " + course.getStudentsCount());
                textHours.setText("Hours: " + course.getHours());
                textDescription.setText("Description: " + course.getDescription());
            } else {
                Toast.makeText(this, "Error: Course details not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
