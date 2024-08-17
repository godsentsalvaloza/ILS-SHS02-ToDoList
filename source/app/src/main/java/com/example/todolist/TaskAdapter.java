package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private OnItemClickListener mListener;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    // Interface for click listener
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    // Method to set click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        Task currentTask = taskList.get(position);
        holder.textViewTaskTitle.setText(currentTask.getTitle());

        // Set OnClickListener for the delete button
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition(); // Retrieve position using holder
                if (mListener != null && adapterPosition != RecyclerView.NO_POSITION) {
                    mListener.onDeleteClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView textViewTaskTitle;
        ImageButton buttonDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            textViewTaskTitle = itemView.findViewById(R.id.textViewTask);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
