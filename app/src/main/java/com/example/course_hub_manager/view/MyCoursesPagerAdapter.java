package com.example.course_hub_manager.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyCoursesPagerAdapter extends FragmentStateAdapter {

    private static final int NUM_TABS = 2;

    public MyCoursesPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return EnrolledCourseListFragment.newInstance("ongoing"); // Assuming "ongoing" is the status string
            case 1:
                return EnrolledCourseListFragment.newInstance("completed"); // Assuming "completed" is the status string
            default:
                return null; // Should not happen
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}

