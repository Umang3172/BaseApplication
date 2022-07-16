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
import com.example.baseapplication.cloud.FirebaseCloudStorage;
import com.example.baseapplication.cloud.Quizz;
import com.example.baseapplication.cloud.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class CreateQuiz extends AppCompatActivity implements CreateQuestionDialog.DialogListener {

    private Button submit,accept,cancel;
    private ArrayList<Quizz> list;
    private TextView emptyrv;
    private EditText title;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String uid;
    String quizTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        emptyrv = findViewById(R.id.no_item_in_rv);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.q_title);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
        quizTitle = title.getText().toString();
        list = new ArrayList<>();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add quiz to firebase
                for(Quizz q: list){
                    FirebaseCloudStorage cloudStorage = new FirebaseCloudStorage();
                    cloudStorage.addQuiz(q.getUserId(),q.getQueMap(),q.getAnswer(),q.getTitle());
                }

            }
        });




    }

    public void addQuestions(View view) {
        CreateQuestionDialog dialog = new CreateQuestionDialog();
        dialog.show(getSupportFragmentManager(),"Add Question");

    }

    @Override
    public void addQuestion( Map<String, String> queMap, String answer) {
        Quizz q = new Quizz(uid,queMap,answer,quizTitle);
        list.add(q);
        FirebaseCloudStorage cloudStorage = new FirebaseCloudStorage();
        cloudStorage.addQuiz(q.getUserId(),q.getQueMap(),q.getAnswer(),q.getTitle());

    }
}

