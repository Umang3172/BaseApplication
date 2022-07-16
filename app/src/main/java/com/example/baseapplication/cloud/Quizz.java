package com.example.baseapplication.cloud;

import java.util.Map;

public class Quizz {
    final String userId;
    final Map<String, String> queMap;
    final String answer;
    final String title;
    final String time;


    public Quizz(String userId, Map<String, String> queMap, String answer, String title, String time) {
        this.userId = userId;
        this.queMap = queMap;
        this.answer = answer;
        this.title = title;
        this.time = time;
    }

    public String getTime() {
        return time;
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
