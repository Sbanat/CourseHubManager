package com.example.course_hub_manager.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.course_hub_manager.databinding.FragmentHomeBinding;
import com.example.course_hub_manager.viewmodel.CategoryViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CategoryViewModel categoryViewModel;
    private CategoryPagerAdapter categoryPagerAdapter;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryPagerAdapter = new CategoryPagerAdapter(getActivity(), categories);
            binding.viewPager.setAdapter(categoryPagerAdapter);

            new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                    (tab, position) -> tab.setText(categories.get(position).getName())
            ).attach();
        });

        return binding.getRoot();
    }
}
