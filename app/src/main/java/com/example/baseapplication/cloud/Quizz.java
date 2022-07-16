package com.example.baseapplication.cloud;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Quizz {
    final String userId;
    final Map<String, String> queMap;
    final String answer;
    final String title;


    public Quizz(String userId, Map<String, String> queMap, String answer, String title) {
        this.userId = userId;
        this.queMap = queMap;
        this.answer = answer;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, String> getQueMap() {
        return queMap;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTitle() {
        return title;
    }
}
