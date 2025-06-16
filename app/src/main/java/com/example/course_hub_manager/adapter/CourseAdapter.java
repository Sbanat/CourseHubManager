package com.example.course_hub_manager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.data.entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    private List<Course> courses;
    private final OnCourseClickListener listener;

    public CourseAdapter(OnCourseClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Course> courseList) {
        this.courses = courseList;
        notifyDataSetChanged();
    }

    public Course getCourseAt(int position) {
        if (courses != null && position >= 0 && position < courses.size()) {
            return courses.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.title.setText(course.title);
        holder.description.setText(course.description);
        holder.instructor.setText(course.instructor);

        holder.itemView.setOnClickListener(v -> listener.onCourseClick(course));
    }

    @Override
    public int getItemCount() {
        return courses == null ? 0 : courses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, instructor;

        CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            description = itemView.findViewById(R.id.text_description);
            instructor = itemView.findViewById(R.id.text_instructor);
        }
    }
}
