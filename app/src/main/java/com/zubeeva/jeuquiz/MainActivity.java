package com.zubeeva.jeuquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Créateurs : Evan Zuber \nDate : 17.02.2023 \nVersion : 1.0");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
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

