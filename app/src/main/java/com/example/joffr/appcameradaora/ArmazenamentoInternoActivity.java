package com.example.joffr.appcameradaora;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArmazenamentoInternoActivity extends AppCompatActivity {

    String FILENAME = "photo_internal.jpg";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazenamento_interno);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void tirarFotoESalvar(View view) {
        //import da biblioteca android.provider.MediaStore lá em cima
        Intent cameraInt = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraInt, 1);
    }

    public void lerFotoSalva(View view) {
        FileInputStream fin;
        try {
            //abre o arquivo chamado FILENAME para LEITURA
            fin = openFileInput(FILENAME);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap imagem = BitmapFactory.decodeStream(fin, null, options);

            imageView.setImageBitmap(imagem);

            fin.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                FileOutputStream fOut = null;

                try {
                    fOut = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                try {
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "imagem salva com sucesso", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
