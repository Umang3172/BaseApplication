package com.example.baseapplication.cloud;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class FirebaseCloudStorage {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addQuestion(String userId, Map<String, String>queMap, String answer){
        Quizz q = new Quizz(userId,queMap,answer);
        db.collection("questions").add(q);
    }
}
