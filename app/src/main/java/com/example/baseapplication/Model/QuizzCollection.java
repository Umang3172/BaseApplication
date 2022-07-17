package com.example.baseapplication.Model;

import com.example.baseapplication.cloud.Quizz;

import java.util.ArrayList;

public class QuizzCollection {
    ArrayList<Quizz> quiz;
    String title;

    public QuizzCollection(ArrayList<Quizz> quiz, String title) {
        this.quiz = quiz;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Quizz> getQuiz() {
        return quiz;
    }
}
