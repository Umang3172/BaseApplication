package com.example.baseapplication.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baseapplication.Adapter.CreateQuestionDialog;
import com.example.baseapplication.Model.Questions;
import com.example.baseapplication.R;

import java.util.ArrayList;

public class CreateQuiz extends AppCompatActivity implements CreateQuestionDialog.DialogListener {

    private Button submit,accept,cancel;
    private ArrayList<Questions> list;
    private TextView emptyrv;
    private EditText title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        emptyrv = findViewById(R.id.no_item_in_rv);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.q_title);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add quiz to firebase


            }
        });




    }

    public void addQuestions(View view) {
        CreateQuestionDialog dialog = new CreateQuestionDialog();
        dialog.show(getSupportFragmentManager(),"Add Question");

    }

    @Override
    public void addQuestion(String title, String opA, String opB, String opC, String opD, String ans) {
        list.add(new Questions(title,opA,opB,opC,opD,ans));
    }
}

