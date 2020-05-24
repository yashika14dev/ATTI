package com.scratch.automatedtt;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Course extends AppCompatActivity {


    ArrayList<CourseItem> cCourseList;
    private RecyclerView cRecyclerview;
    private CourseAdapter cAdapter;
    private RecyclerView.LayoutManager cLayoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        loadDatac();
        buildRecyclerViewc();
        setInsertButtonc();
        Button buttonSavec = findViewById(R.id.button_savec);
        buttonSavec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatac();
            }
        });
    }
    private void saveDatac() {
        SharedPreferences sharedPreferencesC = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editorC = sharedPreferencesC.edit();
        Gson gsonC = new Gson();
        String jsonC = gsonC.toJson(cCourseList);
        editorC.putString("course list", jsonC);
        editorC.apply();
        Toast.makeText(this, " Saved Successfully ", Toast.LENGTH_SHORT).show();
    }

    private void loadDatac() {
        SharedPreferences sharedPreferencesC = getSharedPreferences("shared preferences courses", MODE_PRIVATE);
        Gson gsonC = new Gson();
        String jsonC = sharedPreferencesC.getString("Course list", null);
        Type typeC = new TypeToken<ArrayList<CourseItem>>() {
        }.getType();
        cCourseList = gsonC.fromJson(jsonC, typeC);
        if (cCourseList == null) {
            cCourseList = new ArrayList<>();
        }
    }
    private void buildRecyclerViewc() {
        cRecyclerview = findViewById(R.id.recyclerviewC);
        cRecyclerview.setHasFixedSize(true);
        cLayoutmanager = new LinearLayoutManager(this);
        cAdapter = new CourseAdapter(cCourseList);
        cRecyclerview.setLayoutManager(cLayoutmanager);
        cRecyclerview.setAdapter(cAdapter);
        cAdapter.setOnItemClickListenerc(new CourseAdapter.OnItemClickListenerc() {
            @Override
            public void onCDeleteClick(int position) {
                removeItemc(position);
            }
        });
    }

    private void setInsertButtonc() {
        Button buttonInsertc = findViewById(R.id.addCourse_btn);
        buttonInsertc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText linec = findViewById(R.id.addCourse_ed);
                insertItemc(linec.getText().toString());
                linec.setText("");
            }
        });
    }

    public void removeItemc(int position) {
        cCourseList.remove(position);
        cAdapter.notifyItemRemoved(position);
    }

    private void insertItemc(String linec) {
        cCourseList.add(new CourseItem(linec));
        cAdapter.notifyItemInserted(cCourseList.size());
    }
}


