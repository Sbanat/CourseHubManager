package com.example.course_hub_manager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.data.entities.Lesson;

public class LessonAdapter extends ListAdapter<Lesson, LessonAdapter.LessonViewHolder> {

    public interface OnLessonClickListener {
        void onLessonClick(Lesson lesson);
    }

    private final OnLessonClickListener listener;

    public LessonAdapter(OnLessonClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<Lesson> DIFF_CALLBACK = new DiffUtil.ItemCallback<Lesson>() {
        @Override
        public boolean areItemsTheSame(@NonNull Lesson oldItem, @NonNull Lesson newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Lesson oldItem, @NonNull Lesson newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getYoutubeLink().equals(newItem.getYoutubeLink());
        }
    };

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson currentLesson = getItem(position);
        holder.bind(currentLesson, listener);
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        private final TextView lessonTitleTextView;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonTitleTextView = itemView.findViewById(R.id.tv_lesson_title);
        }

        public void bind(final Lesson lesson, final OnLessonClickListener listener) {
            lessonTitleTextView.setText(lesson.getTitle());
            itemView.setOnClickListener(v -> listener.onLessonClick(lesson));
        }
    }
}
