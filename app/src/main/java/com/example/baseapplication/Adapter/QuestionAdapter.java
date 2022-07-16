package com.example.baseapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseapplication.R;
import com.example.baseapplication.cloud.Questions;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolderquestion> {

    private ArrayList<Questions> list;
    private onItemListener mOnItemListener;

    public QuestionAdapter(ArrayList<Questions> list, onItemListener OnItemListener) {

        this.list = list;
        this.mOnItemListener=OnItemListener;

    }

    public class ViewHolderquestion extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView q,a,b,c,d,ans;
        onItemListener onItemListener;

        public ViewHolderquestion(View view, onItemListener onItemListener) {
            super(view);

            q = (TextView) view.findViewById(R.id.question);
            a = (TextView) view.findViewById(R.id.opA);
            b = (TextView) view.findViewById(R.id.opB);
            c = (TextView) view.findViewById(R.id.opC);
            d = (TextView) view.findViewById(R.id.opD);


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
    public QuestionAdapter.ViewHolderquestion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_question,parent,false);

        return new ViewHolderquestion(itemView,mOnItemListener);
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolderquestion holder,final int position) {
        //Log.d("TAG", Integer.toString(stlist.size()));
        holder.q.setText(list.get(position).getQ());
        holder.a.setText(list.get(position).getA());
        holder.b.setText(list.get(position).getB());
        holder.c.setText(list.get(position).getC());
        holder.d.setText(list.get(position).getD());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}