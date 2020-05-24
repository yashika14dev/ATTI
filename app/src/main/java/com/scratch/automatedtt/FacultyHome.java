package com.scratch.automatedtt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FacultyHome extends AppCompatActivity implements View.OnClickListener {
    private CardView ViewTTCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        ViewTTCard = (CardView) findViewById(R.id.ViewTT_card);
        ViewTTCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.ViewTT_card:
                i = new Intent(this, Timetable.class);
                startActivity(i);
                break;
            default:
                break;


        }
    }
}