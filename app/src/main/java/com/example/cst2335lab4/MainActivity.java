package com.example.cst2335lab4;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<TodoItem> items;
    private TodoAdapter adapter;
    private EditText todoInput;
    private Switch urgentSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.todoList);
        todoInput = findViewById(R.id.todoInput);
        urgentSwitch = findViewById(R.id.urgentSwitch);
        Button addButton = findViewById(R.id.addButton);

        items = new ArrayList<>();
        adapter = new TodoAdapter(this, items);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(view -> {
            String text = todoInput.getText().toString().trim();
            if (!text.isEmpty()) {
                items.add(new TodoItem(text, urgentSwitch.isChecked()));
                adapter.notifyDataSetChanged();
                todoInput.setText("");
                urgentSwitch.setChecked(false);
            }
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.delete_title);
            builder.setMessage(getString(R.string.selected_row, position));
            builder.setPositiveButton(R.string.yes, (dialog, which) -> {
                items.remove(position);
                adapter.notifyDataSetChanged();
            });
            builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
            builder.show();
            return true;
        });
    }
}
