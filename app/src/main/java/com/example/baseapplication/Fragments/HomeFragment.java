package com.example.baseapplication.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baseapplication.Activities.CreateQuiz;
import com.example.baseapplication.Activities.Quiz;
import com.example.baseapplication.Adapter.HomeQuizAdapter;
import com.example.baseapplication.Adapter.ShareDialogAdapter;
import com.example.baseapplication.Model.QuizzCollection;
import com.example.baseapplication.R;
import com.example.baseapplication.cloud.Quizz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements HomeQuizAdapter.onItemListener {
    private RecyclerView recyclerView;
//    private ArrayList<Quizz> list;
    public List<Quizz>mlist;
    FirebaseFirestore firebaseFirestore;
    String title;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_quiz_rv);
        mlist = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
//        firebaseFirestore.collection("hii").get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            mlist.addAll(task.getResult().toObjects(Quizz.class));
//                        }
//                    }
//                });

        Map<String, String> map = new HashMap<>();
        map.put("question","add");
        map.put("opt_a","1");
        map.put("opt_b","2");
        map.put("opt_c","3");
        map.put("opt_d","4");

        Map<String, String> map1 = new HashMap<>();
        map.put("question","hello");
        map.put("opt_a","1");
        map.put("opt_b","2");
        map.put("opt_c","3");
        map.put("opt_d","4");

        Map<String, String> map2 = new HashMap<>();
        map.put("question","world");
        map.put("opt_a","1");
        map.put("opt_b","2");
        map.put("opt_c","3");
        map.put("opt_d","4");

        Map<String, String> map3 = new HashMap<>();
        map.put("question","java");
        map.put("opt_a","1");
        map.put("opt_b","2");
        map.put("opt_c","3");
        map.put("opt_d","4");

        Map<String, String> map4 = new HashMap<>();
        map.put("question","python");
        map.put("opt_a","1");
        map.put("opt_b","2");
        map.put("opt_c","3");
        map.put("opt_d","4");

        mlist.add(new Quizz("User1",map,"a","collection","10"));
        mlist.add(new Quizz("User1",map1,"ac","collection","10"));
        mlist.add(new Quizz("User1",map2,"c","collection","10"));
        mlist.add(new Quizz("User1",map3,"d","collection","10"));
        mlist.add(new Quizz("User1",map4,"cd","collection","10"));

//        firebaseFirestore.collection("hii").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.d("done","1");
//                        if (queryDocumentSnapshots.isEmpty()) {
//                            Log.d("doc", "onSuccess: LIST EMPTY");
//                            return;
//                        } else {
//                            // Convert the whole Query Snapshot to a list
//                            // of objects directly! No need to fetch each
//                            // document.
//                            List<Quizz> types = queryDocumentSnapshots.toObjects(Quizz.class);
//                            Log.d("sizelist",types.size()+"");
//
//                            for(Quizz q : types){
//                                mlist.add(q);
//
//                                Log.d("count","worked");
//                            }
//                            notify();
//
//                            // Add all to your list
//
//                            Log.d("query", "onSuccess: " + mlist);
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("failed",e.toString());
//                    }
//                });



//        list = new ArrayList<>();
//        for(Quizz q : mlist){
//            list.add(new QuizzCollection(q,title));
//        }

//        list.add(new Quizz("hello"));
//        list.add("hello");
//        list.add("hello");
//        list.add("hello");
//        list.add("hello");
//        list.add("hello");

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new HomeQuizAdapter(mlist,this));

        return view;
    }


    @Override
    public void onClicked(int position) {
        // Add url in constructor .
        title = mlist.get(position).getTitle();


        Uri uri = Uri.parse("http://www.quizz.com/v1");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

        ShareDialogAdapter dFragment = new ShareDialogAdapter().newInstance(uri.toString());
        dFragment.show(getActivity().getSupportFragmentManager(), "Frag");
    }
}