package com.scratch.automatedtt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<CourseItem> cCourseList;
    private CourseAdapter.OnItemClickListenerc cListener;


    public interface OnItemClickListenerc {

        void onCDeleteClick(int position);
    }

    public void setOnItemClickListenerc(CourseAdapter.OnItemClickListenerc listenerc) {
        this.cListener = listenerc;
    }
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView cTextViewLine1;
        public ImageView cDeleteImage;


        public CourseViewHolder(View itemView , final CourseAdapter.OnItemClickListenerc listenerc) {
            super(itemView);
            cDeleteImage = itemView.findViewById(R.id.deleteCourse);
            cTextViewLine1 = itemView.findViewById(R.id.Coursetv);

            cDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listenerc != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listenerc.onCDeleteClick(position);
                        }
                    }
                }
            });


        }
    }
    public CourseAdapter(ArrayList<CourseItem> courseList) {
        cCourseList = courseList;
    }
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        CourseAdapter.CourseViewHolder cvh = new CourseAdapter.CourseViewHolder(v1, cListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseItem currentItemc = cCourseList.get(position);
        holder.cTextViewLine1.setText(currentItemc.getCLine());
    }


    @Override
    public int getItemCount() {
        return cCourseList.size();
    }
}


