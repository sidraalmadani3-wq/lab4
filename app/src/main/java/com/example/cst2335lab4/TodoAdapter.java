package com.example.cst2335lab4;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TodoItem> items;

    public TodoAdapter(Context context, ArrayList<TodoItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public TodoItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.todo_row, parent, false);
        }

        TextView textView = (TextView) view;
        TodoItem item = getItem(position);
        textView.setText(item.getText());

        if (item.isUrgent()) {
            view.setBackgroundColor(Color.RED);
            textView.setTextColor(Color.WHITE);
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
        }

        return view;
    }
}
