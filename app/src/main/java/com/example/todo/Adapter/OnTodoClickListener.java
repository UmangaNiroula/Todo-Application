package com.example.todo.Adapter;

import com.example.todo.Model.Task;

public interface OnTodoClickListener
{
    void onTodoClick(Task task);
    void onTodoRadioButtonClick(Task task);
}
