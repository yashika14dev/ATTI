package com.scratch.automatedtt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Subject extends AppCompatActivity {

        ArrayList<SubjectItem> mSubjectList;
        private RecyclerView mRecyclerView;
        private SubjectAdapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_subject);
            loadData();
            buildRecyclerView();
            setInsertButton();
            Button buttonSave = findViewById(R.id.button_save);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveData();
                }
            });
        }

        private void saveData() {
            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(mSubjectList);
            editor.putString("task list", json);
            editor.apply();
            Toast.makeText(this, " Saved Successfully ", Toast.LENGTH_SHORT).show();
        }

        private void loadData() {
            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("task list", null);
            Type type = new TypeToken<ArrayList<SubjectItem>>() {
            }.getType();
            mSubjectList = gson.fromJson(json, type);
            if (mSubjectList == null) {
                mSubjectList = new ArrayList<>();
            }
        }

        private void buildRecyclerView() {
            mRecyclerView = findViewById(R.id.recyclerview);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new SubjectAdapter(mSubjectList);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
                @Override
                public void onDeleteClick(int position) {
                    removeItem(position);
                }
            });
        }

        private void setInsertButton() {
            Button buttonInsert = findViewById(R.id.button_insert);
            buttonInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText line1 = findViewById(R.id.edittext_line_1);
                    EditText line2 = findViewById(R.id.edittext_line_2);
                    EditText line3 = findViewById(R.id.edittext_line_3);
                    insertItem(line1.getText().toString(), line2.getText().toString() , line3.getText().toString());
                }
            });
        }

    public void removeItem(int position) {
        mSubjectList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

        private void insertItem(String line1, String line2 , String line3) {
            mSubjectList.add(new SubjectItem(line1, line2 , line3));
            mAdapter.notifyItemInserted(mSubjectList.size());
        }
    }


