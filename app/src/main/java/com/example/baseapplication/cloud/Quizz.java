package com.example.baseapplication.cloud;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Quizz {
    final String userId;
    final Map<String, String> queMap;
    final String answer;


    public Quizz(String userId, Map<String, String> queMap, String answer) {
        this.userId = userId;
        this.queMap = queMap;
        this.answer = answer;
    }

}
