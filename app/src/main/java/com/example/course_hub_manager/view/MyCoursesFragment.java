package com.example.course_hub_manager.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.databinding.FragmentMyCoursesBinding; // Use ViewBinding
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MyCoursesFragment extends Fragment {

    private FragmentMyCoursesBinding binding;
    private MyCoursesPagerAdapter myCoursesPagerAdapter;

    public MyCoursesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyCoursesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myCoursesPagerAdapter = new MyCoursesPagerAdapter(this);
        binding.viewPagerMyCourses.setAdapter(myCoursesPagerAdapter);

        new TabLayoutMediator(binding.tabLayoutMyCourses, binding.viewPagerMyCourses,
                (tab, position) -> {
                    // Set tab titles based on position
                    if (position == 0) {
                        tab.setText("Ongoing");
                    } else if (position == 1) {
                        tab.setText("Completed");
                    }
                }
        ).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

