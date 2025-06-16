package com.example.course_hub_manager.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.adapter.LessonAdapter;
import com.example.course_hub_manager.data.entities.Lesson;
import com.example.course_hub_manager.databinding.ActivityCourseContentBinding;
import com.example.course_hub_manager.viewmodel.CourseViewModel;
import com.example.course_hub_manager.viewmodel.LessonViewModel;

public class CourseContentActivity extends AppCompatActivity implements LessonAdapter.OnLessonClickListener {

    private ActivityCourseContentBinding binding;
    private LessonViewModel lessonViewModel;
    private CourseViewModel courseViewModel;
    private LessonAdapter lessonAdapter;
    private int currentCourseId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentCourseId = getIntent().getIntExtra("course_id", -1);
        if (currentCourseId == -1) {
            Toast.makeText(this, "Error: Course ID not found", Toast.LENGTH_SHORT).show();
            finish(); // Close activity if no course ID
            return;
        }

        // Initialize ViewModels
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Setup RecyclerView
        binding.rvLessons.setLayoutManager(new LinearLayoutManager(this));
        lessonAdapter = new LessonAdapter(this); // Pass 'this' as the listener
        binding.rvLessons.setAdapter(lessonAdapter);

        // Get and display course title
        courseViewModel.getCourseById(currentCourseId).observe(this, course -> {
            if (course != null) {
                binding.tvCourseContentTitle.setText(course.getTitle() + " - Lessons");
            } else {
                binding.tvCourseContentTitle.setText("Course Lessons");
            }
        });

        // Observe lessons for the current course
        lessonViewModel.getLessonsByCourseId(currentCourseId).observe(this, lessons -> {
            if (lessons != null) {
                lessonAdapter.submitList(lessons);
            }
        });
    }

    @Override
    public void onLessonClick(Lesson lesson) {
        // Handle lesson click: Show description and provide YouTube link
        // Option 1: Show in a Dialog
        // new AlertDialog.Builder(this)
        //         .setTitle(lesson.getTitle())
        //         .setMessage(lesson.getDescription() + "\n\nWatch on YouTube?")
        //         .setPositiveButton("Watch", (dialog, which) -> openYouTubeLink(lesson.getYoutubeLink()))
        //         .setNegativeButton("Cancel", null)
        //         .show();

        // Option 2: Navigate to a new LessonDetailActivity (if complex details needed)

        // Option 3: Directly try to open YouTube link (as per description)
        Toast.makeText(this, "Opening lesson: " + lesson.getTitle(), Toast.LENGTH_SHORT).show();
        openYouTubeLink(lesson.getYoutubeLink());

        // TODO: Implement marking lesson as completed
    }

    private void openYouTubeLink(String youtubeLink) {
        if (youtubeLink == null || youtubeLink.trim().isEmpty()) {
            Toast.makeText(this, "No video link available for this lesson.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
        // Try to open in YouTube app
        // Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId)); // Need to extract videoId
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));

        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            // YouTube app not installed, open in browser
            try {
                startActivity(webIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "Could not open video link. No suitable app found.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

