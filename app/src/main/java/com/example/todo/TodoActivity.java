package com.example.todo;

import android.os.Bundle;

import com.example.todo.Adapter.OnTodoClickListener;
import com.example.todo.Adapter.RecyclerViewAdapter;
import com.example.todo.Model.SharedViewModel;
import com.example.todo.Model.Task;
import com.example.todo.Model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class TodoActivity extends AppCompatActivity implements OnTodoClickListener {

    private static final String TAG = "ITEM";
    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    AddNewTasks bottomSheetFragment;
    private SharedViewModel sharedViewModel;
    private EditText enter_todo_et, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomSheetFragment = new AddNewTasks();
        ConstraintLayout constraintLayout = findViewById(R.id.bottomSheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        recyclerView = findViewById(R.id.recycler_view);
        enter_todo_et = findViewById(R.id.enter_todo_et);
        desc = findViewById(R.id.desc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(TodoActivity.this.getApplication()).create(TaskViewModel.class);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


        taskViewModel.getAllTasks().observe(this, tasks ->
        {
            recyclerViewAdapter = new RecyclerViewAdapter(tasks, this);
            recyclerView.setAdapter(recyclerViewAdapter);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
        {
            enter_todo_et.setText("");
            desc.setText("");
            showBottomSheetDialog();
        });
    }

    private void showBottomSheetDialog()
    {
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTodoClick(Task task)
    {
        sharedViewModel.selectItem(task);
        sharedViewModel.setIsEdit(true);
        showBottomSheetDialog();
    }

    @Override
    public void onTodoRadioButtonClick(Task task)
    {
        TaskViewModel.delete(task);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}