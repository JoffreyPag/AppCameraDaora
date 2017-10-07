package com.example.joffr.appcameradaora;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button butCam, butpegaimg;
    ImageView imagemTela;
    private static final int TIRAR_FOTO = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butCam = (Button) findViewById(R.id.butao);
        butpegaimg = (Button) findViewById(R.id.button2);

        imagemTela = (ImageView) findViewById(R.id.imagemTela);
    }

    public void teste(View view) {
        chamaCamera();
    }

    public void chamaCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TIRAR_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIRAR_FOTO) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "cam fechada", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
