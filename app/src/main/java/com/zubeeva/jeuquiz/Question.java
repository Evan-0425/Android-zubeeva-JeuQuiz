package com.zubeeva.jeuquiz;

import android.database.Cursor;

public class Question {
    private String Question;
    private int reponse;

    public Question(Cursor cursor){
        Question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    public String getQuestion() {
        return Question;
    }

    public int isReponse() {
        System.out.println("reponse: "+ reponse);
        return reponse;
    }

}