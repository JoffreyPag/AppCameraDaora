package com.example.joffr.appcameradaora;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.io.ByteArrayOutputStream;

public class ArmazenamentoBancoActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazenamento_banco);

        imageView = (ImageView)findViewById(R.id.imageView3);

        SugarContext.init(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    public void tirarFotoESalvarBanco(View view){
        Intent camIn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camIn, 1);
    }

    public void lerFotodoBanco(View view){
        ImageRecord imr =  ImageRecord.last(ImageRecord.class);

        if (imr != null){
            Bitmap image = BitmapFactory.decodeByteArray(imr.getImage(),0,imr.getImage().length);
            imageView.setImageBitmap(image);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            if (data!=null){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap)extras.get("data");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] photo = baos.toByteArray();

                ImageRecord imageBanco = new ImageRecord(photo);

                imageBanco.save();

                Toast.makeText(this, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
