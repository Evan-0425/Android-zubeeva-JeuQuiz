package com.zubeeva.jeuquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Initialisation des composants
    private EditText Player1;
    private EditText Player2;
    private Button bt_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialisation des composants avec les id
        bt_start = findViewById(R.id.main_button_start);
        Player1 = findViewById(R.id.Joueur1);
        Player2 = findViewById(R.id.Joueur2);
    }
    @Override
    protected void onStart(){
        super.onStart();
        //Dès que l'user appuie sur start ça lui ouvre la page de quiz
        bt_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent Activity_jeux = new Intent(MainActivity.this, Jeux.class);
                Activity_jeux.putExtra("Player1", Player1.getText().toString());
                Activity_jeux.putExtra("Player2", Player2.getText().toString());
                startActivity(Activity_jeux);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Liste des menus déroulants
        switch  (item.getItemId()){
            case R.id.menu_paramètre:
                return true;
            case R.id.menu_about:
                return true;
            case R.id.menu_effacer:
                Player1.setText("");
                Player2.setText("");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
}
}

