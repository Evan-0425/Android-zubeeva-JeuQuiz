package com.zubeeva.jeuquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class Jeux extends AppCompatActivity {
    private TextView TextViewPlayer1;
    private TextView TextViewPlayer2;
    private TextView Score1;
    private TextView Score2;
    private TextView TextViewPlayer1Score;
    private TextView TextViewPlayer2Score;
    private Button ButtonRejouer;
    private Button ButtonMenu;
    private Button ButtonReponseP1;
    private Button ButtonReponseP2;
    Runnable questionRunnable = null;
    Handler handler;
    private QuestionManager qManager;
    private int score1 = 0;
    private int score2 = 0;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        Intent Activity_jeux = getIntent();
        //Initialisation des composant avec les id
        TextViewPlayer1Score = findViewById(R.id.GoPlayer1);
        TextViewPlayer2Score = findViewById(R.id.GoPlayer2);
        TextViewPlayer1 = findViewById(R.id.TFplayer1);
        TextViewPlayer2 = findViewById(R.id.TFplayer2);
        ButtonRejouer = findViewById(R.id.BoutonRejouer);
        ButtonMenu = findViewById(R.id.BoutonMenu);
        ButtonReponseP1 = findViewById(R.id.BoutonPlayer1);
        ButtonReponseP2 = findViewById(R.id.BoutonPlayer2);
        Score1 = findViewById(R.id.ScorePlayer1);
        Score2 = findViewById(R.id.ScorePlayer2);
        //Pseudo Joueur
        String player1 = String.valueOf(Activity_jeux.getStringExtra("Player1"));
        String player2 = String.valueOf(Activity_jeux.getStringExtra("Player2"));
        TextViewPlayer1.setText(player1);
        TextViewPlayer2.setText(player2);

        //Cache Bouton Rejouer et Menu
        ButtonMenu.setVisibility(View.INVISIBLE);
        ButtonRejouer.setVisibility(View.INVISIBLE);

        qManager = new QuestionManager(this);

    }
    protected void onStart() {
        super.onStart();
        startCountDownTimer();
        //Regarde si le user appuie sur le bouton
        ButtonReponseP1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Regarde si la reponse est vrai  si oui + 1 point sinon ça en enlève 1
               if(qManager.getQuestion().isReponse() == 1){
                   score1+=1;
               }
               else{
                   score1-=1;
               }
               ButtonReponseP1.setEnabled(false);
               ButtonReponseP2.setEnabled(false);
                String scoreString1 = ""+score1;
                Score1.setText(scoreString1);
            }
        });
        //Regarde si le user appuie sur le bouton
        ButtonReponseP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Regarde si la reponse est vrai  si oui + 1 point sinon ça en enlève 1
                if(qManager.getQuestion().isReponse() == 1){
                    score2+=1;
                }
                else{
                    score2-=1;
                }
                ButtonReponseP1.setEnabled(false);
                ButtonReponseP2.setEnabled(false);
                String scoreString2 = ""+score2;
                Score2.setText(scoreString2);
            }
        });
        ButtonRejouer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //rend visible est invisible les composants qu'il faut et réinitialisation des scores
                ButtonMenu.setVisibility(View.INVISIBLE);
                ButtonRejouer.setVisibility(View.INVISIBLE);
                TextViewPlayer1Score.setVisibility(View.VISIBLE);
                TextViewPlayer2Score.setVisibility(View.VISIBLE);
                Score1.setVisibility(View.VISIBLE);
                Score2.setVisibility(View.VISIBLE);
                Score1.setText("0");
                Score2.setText("0");
                score1 = 0;
                score2 = 0;
                startCountDownTimer();
            }
        });
        ButtonMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent Activity_menu = new Intent(Jeux.this, MainActivity.class);
                startActivity(Activity_menu);
            }
        });
    }

    public void startQuestionIterative(){
        qManager.resetIndex();
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                //rend les boutons utilisables
                ButtonReponseP1.setEnabled(true);
                ButtonReponseP2.setEnabled(true);
                //incrémente l'index pour ensuite sortir une question
                qManager.indexIncremente();
                Question question = qManager.getQuestion();
                if(qManager.getQuestionListe().size() -1 == qManager.getIndex()){
                    //arrete la boucle et rend invisible les choses qui faut
                    handler.removeCallbacks(this);
                    SetVisibleInvisible();
                }else {
                    //met la question dans le textview
                    TextViewPlayer1Score.setText(question.getQuestion());
                    TextViewPlayer2Score.setText(question.getQuestion());
                    handler.postDelayed(this, 2000);
                }
            }
        };

        handler.postDelayed(questionRunnable,1000);
    }
    private void startCountDownTimer(){
        //lance un compte a rebour de 6 secondes
        new CountDownTimer(6000,1000){
            public void onTick(long milliUntilFinished){
                //rend les boutons inutilisables et note le temps dans les textview
                ButtonReponseP1.setEnabled(false);
                ButtonReponseP2.setEnabled(false);
                String temps = String.valueOf(milliUntilFinished / 1000);
                TextViewPlayer1Score.setText(temps);
                TextViewPlayer2Score.setText(temps);
            }
            public void onFinish() {
                //Afficher le départ à l'utilisateur
                startQuestionIterative();
            }
        }.start();
    }
    private void SetVisibleInvisible() {
        //rend visble les boutons menu et rejouer et rend invisible le score
        ButtonMenu.setVisibility(View.VISIBLE);
        ButtonRejouer.setVisibility(View.VISIBLE);
        TextViewPlayer1Score.setVisibility(View.INVISIBLE);
        TextViewPlayer2Score.setVisibility(View.INVISIBLE);
    }
}