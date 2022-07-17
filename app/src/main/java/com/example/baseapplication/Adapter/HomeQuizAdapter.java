package com.example.baseapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseapplication.R;

import java.util.ArrayList;

public class HomeQuizAdapter extends RecyclerView.Adapter<HomeQuizAdapter.ViewHolderHome> {

    private ArrayList<String> quiz_titile;
    private onItemListener mOnItemListener;

    public HomeQuizAdapter(ArrayList<String> quiz_titile, onItemListener OnItemListener) {

        this.quiz_titile = quiz_titile;
        this.mOnItemListener=OnItemListener;
    }
    public class ViewHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        onItemListener onItemListener;

        public ViewHolderHome(View view, onItemListener onItemListener) {
            super(view);

            title = (TextView) view.findViewById(R.id.quiz_title);


            this.onItemListener=onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onItemListener.onClicked(getAdapterPosition());
        }

    }

    // For onClickListener for item in recyclerview
    public interface onItemListener{
        void onClicked(int position);
    }

    @NonNull
    @Override
    public HomeQuizAdapter.ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_quiz,parent,false);

        return new ViewHolderHome(itemView,mOnItemListener);
    }

    @Override
    public void onBindViewHolder(HomeQuizAdapter.ViewHolderHome holder,final int position) {
        //Log.d("TAG", Integer.toString(stlist.size()));
        holder.title.setText(quiz_titile.get(position));
    }

    @Override
    public int getItemCount() {
        return quiz_titile.size();
    }
}