package com.example.course_hub_manager.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.course_hub_manager.R;

public class MyCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        // Find the "View Course Details" button
        Button btnEnroll = findViewById(R.id.btn_enroll);

        // Set an OnClickListener to handle the button click
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the correct course ID
                Intent intent = new Intent(MyCoursesActivity.this, CourseDetailsActivity.class);
                intent.putExtra("course_id", 1); // Replace 1 with the actual course ID you want to pass
                startActivity(intent);
            }
        });

    }
}
