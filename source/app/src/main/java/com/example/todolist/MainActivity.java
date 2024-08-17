package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private EditText editTextTask;
    private ImageButton buttonAddTask;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        recyclerView = findViewById(R.id.recyclerView);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle = editTextTask.getText().toString();
                if (!taskTitle.isEmpty()) {
                    if (taskList.size() < 5) {
                        taskList.add(new Task(taskTitle));
                        taskAdapter.notifyDataSetChanged();
                        editTextTask.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "You can only add up to 5 tasks.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set OnClickListener for the delete button in the TaskAdapter
        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                taskList.remove(position);
                taskAdapter.notifyItemRemoved(position);
                Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
