package com.example.baseapplication.cloud;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FirebaseCloudStorage {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addQuestion(String userId, Map<String, String>queMap, String answer){
        Quizz q = new Quizz(userId,queMap,answer);
        db.collection("questions").add(q);
    }
}
