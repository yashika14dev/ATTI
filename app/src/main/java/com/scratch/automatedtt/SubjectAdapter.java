package com.scratch.automatedtt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private ArrayList<SubjectItem> mSubjectList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
       this.mListener = listener;
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        public ImageView mDeleteImage;
        public TextView mTextViewLine3;

        public SubjectViewHolder(View itemView ,final OnItemClickListener listener) {
            super(itemView);
            mDeleteImage = itemView.findViewById(R.id.delete);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
            mTextViewLine3 = itemView.findViewById(R.id.textview_line_3);


            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


        }
    }
    public SubjectAdapter(ArrayList<SubjectItem> subjectList) {
        mSubjectList = subjectList;
    }
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false);
        SubjectViewHolder svh = new SubjectViewHolder(v, mListener);
        return svh;
    }
    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        SubjectItem currentItem = mSubjectList.get(position);
        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
        holder.mTextViewLine3.setText(currentItem.getLine3());
    }
    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }
}
