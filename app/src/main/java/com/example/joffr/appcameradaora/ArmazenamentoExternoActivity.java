package com.example.joffr.appcameradaora;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class ArmazenamentoExternoActivity extends AppCompatActivity {

    String FILENAME = "phtp_external.jpg";
    private String pictureImagePath = "";
    ImageView imageView;
    Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazenamento_externo);

        imageView =(ImageView)findViewById(R.id.imageView2);

    }

    private File createImageFile() throws IOException{
        //cria um nome do arquivo da imagem
        String cam = "Camera";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        pictureImagePath = storageDir.getPath()+"/"+FILENAME;
        File image = new File(pictureImagePath);
        /*Isso não foi necessario sendo que eu precisava apenas consertar o nome para a imagem e o
        metodo crateTempFile add o integer aleatorio no fim do nome do arquivo*/

        //File image = File.createTempFile(
        //  FILENAME, /* prefix */
        //  ".jpg", /*suffix*/
        //  storageDir /*directory*/
        //);

        return image;
    }

    public  void tirarFotoESalvarExternamente(View view) throws IOException{
        if (checkStorage() == false){
            return;
        }else{
            File file = createImageFile();
            Uri outputFileUri = FileProvider.getUriForFile(ArmazenamentoExternoActivity.this, BuildConfig.APPLICATION_ID + "provider",file);
            //Log.i("Salvando", file.getAbsolutePath());
            Intent cameraIn = new Intent();
        }
    }

    public boolean checkStorage(){
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()){
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageReadOnly(){
        String extStorageState = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable(){
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)){
            return true;
        }
        return false;
    }
}
