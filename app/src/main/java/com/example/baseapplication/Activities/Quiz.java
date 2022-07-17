package com.example.baseapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseapplication.Adapter.QuizEndDialog;
import com.example.baseapplication.Adapter.ShareDialogAdapter;
import com.example.baseapplication.R;
import com.example.baseapplication.cloud.Quizz;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz extends AppCompatActivity {

    private CheckBox a,b,c,d;
    private Button submit;
    private TextView q;
    private int start = 0;
    private ProgressBar timeProgress;
    private Timer t;
    private int score =0;
    private int curQue=-1;

    // To be taken from firebase
    private String ans="ad";
    private int timepq=15;
    private int totalQue=5;
    private ArrayList<Quizz> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        a = findViewById(R.id.optionA);
        b = findViewById(R.id.optionB);
        c = findViewById(R.id.optionC);
        d = findViewById(R.id.optionD);
        submit = findViewById(R.id.submit);
        q = findViewById(R.id.question);
        timeProgress = findViewById(R.id.timer);
        t = new Timer();

        list=new ArrayList<>();
        //timepq=Integer.parseInt(list.get(0).getTime().substring(0,2));

        startTimer();
        displayNextQ();

//
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userAns=0;
                if(a.isChecked()){
                    userAns+=1;
                }
                if(b.isChecked()){
                    userAns+=10;
                }
                if(c.isChecked()){
                    userAns+=100;
                }
                if(d.isChecked()){
                    userAns+=1000;
                }

                for(char option : ans.toCharArray()){
                    if(option=='a'){
                        userAns-=1;
                    }
                    else if(option=='b'){
                        userAns-=10;
                    }
                    else if(option=='c'){
                        userAns-=100;
                    }
                    else if(option=='d'){
                        userAns-=1000;
                    }
                }

                if(userAns==0){
                    //Correct ans
                    score += timepq-start;
                    Toast.makeText(Quiz.this, "score " + score, Toast.LENGTH_SHORT).show();
                }
                else{
                    //Wrong ans
                }
                start=0;
                displayNextQ();
            }
        });

    }


    public void startTimer(){
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(start==timepq+1){
                            start=0;
                            displayNextQ();
                        }
                        else{
                            start++;
                        }

                        timeProgress.setProgress((int)(start/15.0*100.0));
                    }
                });
            }
        },0,1000);
    }


    public void displayNextQ(){
        curQue++;
        if(curQue>=totalQue){
            //Quiz over
            QuizEndDialog dFragment = new QuizEndDialog().newInstance(score+"/"+(timepq*totalQue));
            dFragment.setCancelable(false);
            dFragment.show(getSupportFragmentManager(), "Frag");


        }
        else{
           // q = list.get(curQue).getQueMap();
        }
    }

}