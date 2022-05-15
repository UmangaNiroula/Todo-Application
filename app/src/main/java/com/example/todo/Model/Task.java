package com.example.todo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")

public class Task {

    @ColumnInfo(name = "task_id")
    @PrimaryKey (autoGenerate = true)
    public long taskId;

    @ColumnInfo(name = "task")
    public String task;

    @ColumnInfo(name = "desc")
    public String description;

    @ColumnInfo(name = "priority")
    public Priority priority;

    @ColumnInfo(name = "due_date")
    public Date dueDate;

    @ColumnInfo(name = "created_at")
    public Date dateCreated;


    @ColumnInfo(name = "is_done")
    public boolean isDone;

    public Task(String task, String description ,Priority priority, Date dueDate, Date dateCreated, boolean isDone) {
        this.task = task;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.dateCreated = dateCreated;
        this.isDone = isDone;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
