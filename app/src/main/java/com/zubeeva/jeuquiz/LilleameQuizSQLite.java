package com.zubeeva.jeuquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LilleameQuizSQLite extends SQLiteOpenHelper {

    static String DB_NAME="LilleameQuiz.db";
    static int DB_VERSION= 1;

    public LilleameQuizSQLite(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatabase = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT,reponse INTEGER);";
        db.execSQL(sqlCreateDatabase);
        db.execSQL("INSERT INTO quiz VALUES(1,\"La capitale de l'Australie est Sidney\",0)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Est-ce que Evan est plus beau que Colin ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Est-ce que Evan est plus beau que Liam ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Est-ce que Pinto est plus charismatique que Evan ?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"Est-ce que Tobias est plus grand que Evan ?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"Est-ce que Rayan voit-il mieux que Evan ?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(7,\"Est-ce que Evan ressemble à Alexis ?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(8,\"Est-ce que Evan à le même pied gauche que Messi ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(9,\"Est-ce que Evan est le GOAT ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(10,\"Est-ce que Evan est le GOAT ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(11,\"Est-ce que le bras d'Evan mesure exactement 72cm ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(12,\"Est-ce que Evan mange une pizza jambon,champignons,oeuf,origan ?\",1)");
        db.execSQL("INSERT INTO quiz VALUES(13,\"Est-ce que Evan aime le melon ?\",0)");
        db.execSQL("INSERT INTO quiz VALUES(14,\"Est-ce que Evan aime marcher ?\",0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
