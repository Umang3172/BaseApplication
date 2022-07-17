package com.example.baseapplication.cloud;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseCloudStorage {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addQuiz(String userId, Map<String, String>queMap, String answer, String title, String time){
        Quizz q = new Quizz(userId,queMap,answer,title, time);
        Map<String, Object> doc = new HashMap<>();
        doc.put("user_id",q.getUserId());
        doc.put("que_map", q.getQueMap());
        doc.put("answer", q.getAnswer());
        doc.put("title", q.getTitle());
        doc.put("time", q.getTime());
        db.collection(title).document().set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }
}
