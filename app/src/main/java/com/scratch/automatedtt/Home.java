package com.scratch.automatedtt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView courseCard , subjectCard , facultyCard , timetableCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //definig cards
        courseCard = (CardView)findViewById(R.id.course_card);
        subjectCard = (CardView)findViewById(R.id.subject_card);
        facultyCard = (CardView)findViewById(R.id.faculty_card);
        timetableCard = (CardView)findViewById(R.id.timetable_card);
        //adding onclick
        courseCard.setOnClickListener(this);
        subjectCard.setOnClickListener(this);
        facultyCard.setOnClickListener(this);
        timetableCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.course_card : i = new Intent(this, Course.class);startActivity(i);break;
            case R.id.subject_card : i = new Intent(this , Subject.class);startActivity(i);break;
            case R.id.faculty_card : i = new Intent(this, Faculty.class);startActivity(i);break;
            case R.id.timetable_card : i = new Intent(this, Timetable.class);startActivity(i);break;
            default:break;


        }
    }
}
