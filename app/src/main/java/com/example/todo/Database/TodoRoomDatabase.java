package com.example.todo.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todo.Repo.TaskDao;
import com.example.todo.Model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class TodoRoomDatabase extends RoomDatabase {

    public static final int NUMBER_OF_THREADS = 4;
    public static final String DATABASE_NAME = "todo_database";
    private static volatile TodoRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);

            databaseWriterExecutor.execute(() ->
            {
                TaskDao taskDao = INSTANCE.taskDao();
                taskDao.deleteAll();

                //writing to out table
            });
        }
    };

    public static TodoRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (TodoRoomDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodoRoomDatabase.class, DATABASE_NAME)
                    .addCallback(sRoomDatabaseCallback)
                    .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TaskDao taskDao();
}
