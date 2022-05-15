package com.example.todo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.todo.Model.Priority;
import com.example.todo.Model.SharedViewModel;
import com.example.todo.Model.Task;
import com.example.todo.Model.TaskViewModel;
import com.example.todo.Database.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;
import java.util.Date;

public class AddNewTasks extends BottomSheetDialogFragment {

    private EditText enterTodo, enterDesc;
    private RadioGroup priorityRadioGroup, selectedRadioButton;
    private int selectButtonId;
    private Button saveButton, calenderButton;
    private CalendarView calendarView;
    private Group calenderGroup;
    private Date dueDate;
    Calendar calendar = Calendar.getInstance();
    private SharedViewModel sharedViewModel;
    private boolean isEdit;

    public AddNewTasks() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        calenderGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);
        calenderButton = view.findViewById(R.id.today_calendar_button);
        enterTodo = view.findViewById(R.id.enter_todo_et);
        enterDesc = view.findViewById(R.id.desc);
        saveButton = view.findViewById(R.id.save_todo_button);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (sharedViewModel.getSelectedItem().getValue() != null)
        {
            isEdit = sharedViewModel.getIsEdit();
            Task task = sharedViewModel.getSelectedItem().getValue();
            enterTodo.setText(task.getTask());
            enterDesc.setText(task.getDescription());
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
     {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);



        calenderButton.setOnClickListener(view12 -> {
            calenderGroup.setVisibility(calenderGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            Utils.hideSoftKeyboard(view12);
        });
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) ->
        {
            calendar.clear();
            calendar.set(year, month, dayOfMonth);

            dueDate = calendar.getTime();
        });

        saveButton.setOnClickListener(view1 ->
        {
            String task = enterTodo.getText().toString().trim();
            String desc = enterDesc.getText().toString().trim();

            Log.d("isEdit", "isEdit "+ isEdit);
            if(!TextUtils.isEmpty(task) && dueDate != null)
            {
                Task myTask = new Task(task, desc, Priority.HIGH, dueDate, Calendar.getInstance().getTime(), false);


                if(isEdit)
                {
                    Task updateTask = sharedViewModel.getSelectedItem().getValue();
                    updateTask.setTask(task);
                    updateTask.setDescription(desc);
                    updateTask.setDateCreated(Calendar.getInstance().getTime());
                    updateTask.setDueDate(dueDate);

                    TaskViewModel.update(updateTask);

                    sharedViewModel.setIsEdit(false);
                    enterTodo.setText("");
                    enterDesc.setText("");
                }

                else
                {
                    TaskViewModel.insert(myTask);
                    enterTodo.setText("");
                    enterDesc.setText("");
                }


                if(this.isVisible())
                {
                    this.dismiss();
                }

            }
            else
            {
                Snackbar.make(saveButton, R.string.empty_field, Snackbar.LENGTH_LONG).show();
            }
        });

    }
}