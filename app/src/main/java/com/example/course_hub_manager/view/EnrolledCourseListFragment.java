package com.example.course_hub_manager.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.adapter.CourseAdapter;
import com.example.course_hub_manager.data.entities.Course;
import com.example.course_hub_manager.viewmodel.EnrollmentViewModel;
import com.example.course_hub_manager.viewmodel.UserViewModel;

public class EnrolledCourseListFragment extends Fragment {

    private static final String ARG_STATUS = "status";
    private String courseStatus;

    private EnrollmentViewModel enrollmentViewModel;
    private UserViewModel userViewModel;
    private CourseAdapter courseAdapter;
    private RecyclerView recyclerView;

    private int currentUserId = -1;

    public static EnrolledCourseListFragment newInstance(String status) {
        EnrolledCourseListFragment fragment = new EnrolledCourseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseStatus = getArguments().getString(ARG_STATUS);
        }

        enrollmentViewModel = new ViewModelProvider(this).get(EnrollmentViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                currentUserId = user.getId();
                loadEnrolledCourses();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        courseAdapter = new CourseAdapter(course -> {
            Intent intent = new Intent(getActivity(), CourseContentActivity.class);
            intent.putExtra("course_id", course.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(courseAdapter);
        return view;
    }

    private void loadEnrolledCourses() {
        if (currentUserId != -1 && courseStatus != null) {
            enrollmentViewModel.getEnrolledCoursesByStatus(currentUserId, courseStatus)
                    .observe(getViewLifecycleOwner(), courses -> courseAdapter.submitList(courses));
        }
    }
}
