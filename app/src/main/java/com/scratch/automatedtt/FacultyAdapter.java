package com.scratch.automatedtt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {
    private ArrayList<FacultyItem> fsublist;
    private FacultyAdapter.OnItemClickListenerF FListener;

    public interface OnItemClickListenerF {

        void onDeleteClickF(int position);
    }
    public void setOnItemClickListenerF(FacultyAdapter.OnItemClickListenerF listenerf) {
        this.FListener = listenerf;
    }
    public static class FacultyViewHolder extends RecyclerView.ViewHolder {
        public TextView ftv;
        public ImageView fdeleteimage;

        public FacultyViewHolder(View itemView, final FacultyAdapter.OnItemClickListenerF listenerf) {
            super(itemView);
            fdeleteimage = itemView.findViewById(R.id.deleteFaculty);
            ftv = itemView.findViewById(R.id.Facultytv);

            fdeleteimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerf != null) {
                        int position1 = getAdapterPosition();
                        if (position1 != RecyclerView.NO_POSITION) {
                            listenerf.onDeleteClickF(position1);
                        }
                    }
                }
            });
        }
    }
    public FacultyAdapter(ArrayList<FacultyItem> facultyList) {
        fsublist = facultyList;
    }
    @Override
    public FacultyAdapter.FacultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_item, parent, false);
        FacultyAdapter.FacultyViewHolder fvh = new FacultyAdapter.FacultyViewHolder(view, FListener);
        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
        FacultyItem currentItemf = fsublist.get(position);
        holder.ftv.setText(currentItemf.getfsubname());
    }



    @Override
    public int getItemCount() {
        return fsublist.size();
    }
}