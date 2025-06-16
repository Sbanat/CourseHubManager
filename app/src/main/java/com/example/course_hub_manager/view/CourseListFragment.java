package com.example.course_hub_manager.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.adapter.CourseAdapter;
import com.example.course_hub_manager.data.entities.Course;
import com.example.course_hub_manager.viewmodel.CourseViewModel;

import java.util.List;

public class CourseListFragment extends Fragment {

    private CourseViewModel courseViewModel;
    private CourseAdapter courseAdapter;
    private RecyclerView recyclerView;

    public CourseListFragment() {
        // Required empty public constructor
    }

    public static CourseListFragment newInstance(int categoryId) {
        CourseListFragment fragment = new CourseListFragment();
        Bundle args = new Bundle();
        args.putInt("category_id", categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_list, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        courseAdapter = new CourseAdapter(course -> {

        });
        recyclerView.setAdapter(courseAdapter);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        if (getArguments() != null) {
            int categoryId = getArguments().getInt("category_id", -1);
            if (categoryId != -1) {
                courseViewModel.getCoursesByCategory(categoryId).observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
                    @Override
                    public void onChanged(List<Course> courses) {
                        if (courses != null && !courses.isEmpty()) {
                            courseAdapter.submitList(courses);
                        }
                    }
                });
            }
        }

        return rootView;
    }
}
