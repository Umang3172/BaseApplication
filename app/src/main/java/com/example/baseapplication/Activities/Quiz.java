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

import com.example.baseapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class Quiz extends AppCompatActivity {

    private CheckBox a,b,c,d;
    private Button submit;
    private TextView q;
    private int start = 0;
    private int timepq=15;//get time from firebase
    private ProgressBar timeProgress;
    private Timer t;
    private int score =0;


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

        startTimer();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    }

}