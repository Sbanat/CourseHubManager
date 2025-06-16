package com.example.course_hub_manager.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.adapter.CourseAdapter; // Re-use CourseAdapter
import com.example.course_hub_manager.data.entities.Bookmark;
import com.example.course_hub_manager.databinding.FragmentBookmarkBinding;
import com.example.course_hub_manager.viewmodel.BookmarkViewModel;
import com.example.course_hub_manager.viewmodel.UserViewModel;

public class BookmarkFragment extends Fragment {

    private FragmentBookmarkBinding binding;
    private BookmarkViewModel bookmarkViewModel;
    private UserViewModel userViewModel;
    private CourseAdapter courseAdapter;
    private int currentUserId = -1;

    public BookmarkFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Get the current logged-in user's ID.
        currentUserId = 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookmarkViewModel = new ViewModelProvider(this).get(BookmarkViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.rvBookmarkedCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        courseAdapter = new CourseAdapter(course -> {
            Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
            intent.putExtra("course_id", course.getId());
            startActivity(intent);
        });
        binding.rvBookmarkedCourses.setAdapter(courseAdapter);

        if (currentUserId != -1) {
            bookmarkViewModel.getBookmarkedCourses(currentUserId).observe(getViewLifecycleOwner(), courses -> {
                if (courses != null && !courses.isEmpty()) {
                    courseAdapter.submitList(courses);
                    binding.tvNoBookmarks.setVisibility(View.GONE);
                    binding.rvBookmarkedCourses.setVisibility(View.VISIBLE);
                } else {
                    courseAdapter.submitList(null); // Clear the list
                    binding.tvNoBookmarks.setVisibility(View.VISIBLE);
                    binding.rvBookmarkedCourses.setVisibility(View.GONE);
                }
            });
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // Not interested in move events
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    int courseId = courseAdapter.getCourseAt(position).getId();
                    showDeleteConfirmationDialog(currentUserId, courseId);
                }
            }
        }).attachToRecyclerView(binding.rvBookmarkedCourses);
    }

    private void showDeleteConfirmationDialog(int userId, int courseId) {
        new AlertDialog.Builder(getContext())
                .setTitle("Remove Bookmark")
                .setMessage("Are you sure you want to remove this course from your bookmarks?")
                .setPositiveButton("Remove", (dialog, which) -> {
                    Bookmark bookmarkToRemove = new Bookmark(userId, courseId);
                    bookmarkViewModel.deleteBookmark(bookmarkToRemove);
                    Toast.makeText(getContext(), "Bookmark removed", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    courseAdapter.notifyDataSetChanged();
                })
                .setOnCancelListener(dialog -> {
                     courseAdapter.notifyDataSetChanged();
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

