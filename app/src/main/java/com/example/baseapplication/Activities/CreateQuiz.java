package com.example.baseapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.baseapplication.Adapter.CreateQuestionDialog;
import com.example.baseapplication.Adapter.QuestionAdapter;
import com.example.baseapplication.R;
import com.example.baseapplication.cloud.FirebaseCloudStorage;
import com.example.baseapplication.cloud.Questions;
import com.example.baseapplication.cloud.Quizz;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class CreateQuiz extends AppCompatActivity implements CreateQuestionDialog.DialogListener, QuestionAdapter.onItemListener {

    private Button submit,accept,cancel;
    private ArrayList<Quizz> list;
    private TextView emptyrv;
    private EditText title;
    private Spinner time;
    private RecyclerView recyclerView;
    private ArrayList<Questions> temporary;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String uid;
    String quizTitle;
    String timePerQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        emptyrv = findViewById(R.id.no_item_in_rv);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.q_title);
        time = findViewById(R.id.spinner);

        // Get timer
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);

        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timePerQuestion = (String) adapterView.getItemAtPosition(i);
                Log.d("time",timePerQuestion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
//        quizTitle = title.getText().toString();
//        Log.d("title",quizTitle);
        list = new ArrayList<>();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add quiz to firebase
                for(Quizz q: list){
                    FirebaseCloudStorage cloudStorage = new FirebaseCloudStorage();
                    cloudStorage.addQuiz(q.getUserId(),q.getQueMap(),q.getAnswer(),q.getTitle(), q.getTime());
                }

            }
        });


        // Populate recycler view

        recyclerView = findViewById(R.id.recyclerView);
        temporary = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new QuestionAdapter(temporary, this));




    }

    public void addQuestions(View view) {
        CreateQuestionDialog dialog = new CreateQuestionDialog();
        dialog.show(getSupportFragmentManager(),"Add Question");

    }

    @Override
    public void addQuestion( Map<String, String> queMap, String answer) {
        quizTitle = title.getText().toString();
        Log.d("title",quizTitle);
        Quizz q = new Quizz(uid,queMap,answer,quizTitle, timePerQuestion);
        list.add(q);
        FirebaseCloudStorage cloudStorage = new FirebaseCloudStorage();
        cloudStorage.addQuiz(q.getUserId(),q.getQueMap(),q.getAnswer(),q.getTitle(), q.getTime());

    }
    // For after clicking on recycler view
    @Override
    public void onClicked(int position) {

    }
}

