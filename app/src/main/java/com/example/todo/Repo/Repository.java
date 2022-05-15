package com.example.todo.Repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todo.Database.TodoRoomDatabase;
import com.example.todo.Model.Task;

import java.util.List;

public class Repository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public Repository(Application application)
    {
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getTask();
    }

    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }
    public void insert(Task task)
    {
        TodoRoomDatabase.databaseWriterExecutor.execute(() ->taskDao.insertTask(task));
    }
    public LiveData<Task> get(long id)
    {
        return taskDao.get(id);
    }
    public void update(Task task)
    {
        TodoRoomDatabase.databaseWriterExecutor.execute(() ->taskDao.update(task));
    }
    public void delete(Task task)
    {
        TodoRoomDatabase.databaseWriterExecutor.execute(() ->taskDao.delete(task));
    }
}
