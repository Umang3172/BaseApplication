package com.example.baseapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseapplication.Activities.CreateQuiz;
import com.example.baseapplication.Adapter.HomeQuizAdapter;
import com.example.baseapplication.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeQuizAdapter.onItemListener {
    private RecyclerView recyclerView;
    private ArrayList<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_quiz_rv);

        list = new ArrayList<>();

        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");




        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new HomeQuizAdapter(list,this));

        return view;
    }


    @Override
    public void onClicked(int position) {

    }
}