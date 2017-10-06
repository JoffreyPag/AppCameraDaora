package com.example.joffr.appcameradaora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button camBanco, camExt, camInt, pegaBanco, pegaExt, pegaInt;
    ImageView imagemTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camBanco = (Button)findViewById(R.id.button1);
        pegaBanco = (Button)findViewById(R.id.button2);
        camExt = (Button)findViewById(R.id.button3);
        pegaExt = (Button)findViewById(R.id.button4);
        camInt = (Button)findViewById(R.id.button5);
        pegaInt = (Button)findViewById(R.id.button6);

        imagemTela = (ImageView)findViewById(R.id.ultimaimagem);
    }
}
