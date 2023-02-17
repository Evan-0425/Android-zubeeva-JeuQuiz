package com.zubeeva.jeuquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {

private ArrayList<Question> QuestionList = new ArrayList<>();
private int index = -1;

    public  QuestionManager(Context context)  {
        QuestionList = initQuestionList(context);
    }

    public Question getQuestion() {
        return QuestionList.get(index);
    }
    public ArrayList<Question> getQuestionListe() {
        return QuestionList;
    }
    public void indexIncremente () {
        System.out.println("index:"+index+1);
        this.index++;
    }
    public void resetIndex () {
        this.index = -1;
    }

    public int getIndex() {
        return index;
    }

    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> initQuestion = new ArrayList<>();
        LilleameQuizSQLite helper = new LilleameQuizSQLite(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true,"quiz", new String[]{"idQuiz","question","reponse"},null,null, null,null,"idQuiz", null);

        while(cursor.moveToNext()){
            initQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return initQuestion;
    }
 }


