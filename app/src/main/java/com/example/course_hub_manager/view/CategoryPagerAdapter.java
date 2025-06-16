package com.example.course_hub_manager.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.course_hub_manager.data.entities.Category;

import java.util.List;

public class CategoryPagerAdapter extends FragmentStateAdapter {
    private List<Category> categoryList;

    public CategoryPagerAdapter(FragmentActivity fragmentActivity, List<Category> categories) {
        super(fragmentActivity);
        this.categoryList = categories;
    }

    @Override
    public Fragment createFragment(int position) {
        return CourseListFragment.newInstance(categoryList.get(position).getId());  // تمرير الـ categoryId
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
