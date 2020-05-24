package com.scratch.automatedtt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Faculty extends AppCompatActivity {
    ArrayList<FacultyItem> fsubList;
    private RecyclerView frecyclerView;
    private FacultyAdapter fAdapter;
    private RecyclerView.LayoutManager flayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        loadDataF();
        buildRecyclerViewF();
        setInsertButtonF();
        Button buttonSaveF = findViewById(R.id.button_saveF);
        buttonSaveF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataF();
            }
        });
    }

    private void saveDataF() {
        SharedPreferences sharedPreferencesF = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editorF = sharedPreferencesF.edit();
        Gson gsonF = new Gson();
        String jsonF = gsonF.toJson(fsubList);
        editorF.putString("Faculty subject list", jsonF);
        editorF.apply();
        Toast.makeText(this, " Saved Successfully ", Toast.LENGTH_SHORT).show();
    }
    private void loadDataF() {
        SharedPreferences sharedPreferencesF = getSharedPreferences("shared preferences faculty subject", MODE_PRIVATE);
        Gson gsonF = new Gson();
        String jsonF = sharedPreferencesF.getString("Faculty subject list", null);
        Type typeF = new TypeToken<ArrayList<FacultyItem>>() {
        }.getType();
        fsubList = gsonF.fromJson(jsonF, typeF);
        if (fsubList == null) {
            fsubList = new ArrayList<>();
        }
    }
    private void buildRecyclerViewF() {
        frecyclerView = findViewById(R.id.recylerviewF);
        frecyclerView.setHasFixedSize(true);
        flayoutManager = new LinearLayoutManager(this);
        fAdapter = new FacultyAdapter(fsubList);
        frecyclerView.setLayoutManager(flayoutManager);
        frecyclerView.setAdapter(fAdapter);
        fAdapter.setOnItemClickListenerF(new FacultyAdapter.OnItemClickListenerF() {
            @Override
            public void onDeleteClickF(int position) {
                removeItemF(position);
            }
        });
    }
    private void setInsertButtonF() {
        Button buttonInsertf = findViewById(R.id.fadd_btn);
        buttonInsertf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText lineF = findViewById(R.id.fsub_et);
                insertItemF(lineF.getText().toString());
                lineF.setText("");
            }
        });
    }
    public void removeItemF(int position) {
        fsubList.remove(position);
        fAdapter.notifyItemRemoved(position);
    }

    private void insertItemF(String linef) {
        fsubList.add(new FacultyItem(linef));
        fAdapter.notifyItemInserted(fsubList.size());
    }
}




