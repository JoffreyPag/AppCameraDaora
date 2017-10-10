package com.example.joffr.appcameradaora;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ArmazenamentoExternoActivity extends AppCompatActivity {

    String FILENAME = "photo_external.jpg";
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
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        pictureImagePath = storageDir.getAbsolutePath()+"/"+FILENAME;
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
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
            return;
        }else{
            File file = createImageFile();
            //um erro aocntece aqui
            Uri outputFileUri = FileProvider.getUriForFile(ArmazenamentoExternoActivity.this, BuildConfig.APPLICATION_ID + ".provider",file);
            //Log.i("Salvando", file.getAbsolutePath());
            Intent cameraIn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIn.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(cameraIn, 1);

            /*
            Esse codigo nao funciona no nougat já que os arquivos uri nao podem mais serem expostos. Use
            um ile provider para android N
             */

            /*
            File storageDir = Enviroment.getExternalPublishDirectory(Environment.DIRECTORY_DCIM);
            pictureImagePath = storageDir.getAbsolutePath() + "/Camera" + FILENAME;
            File file = new File(pictureImagePath);
            Uri outputFileUri = Uri.fromFile(file);
            Intent cameraIn = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIn.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(cameraIn, 1);
             */
        }
    }

    public void lerFotoExterna(View view) throws IOException{
        if (checkStorage() == false){
            Toast.makeText(this, "Storage is busy", Toast.LENGTH_SHORT).show();
            return;
        } else{
            File imgFile = createImageFile();

            //Log.i("Imagem, imgFile.getAbsolutePath());
            if (imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                //Log.i("Testes"," Arquivo: "+imgFile);
                imageView.setImageBitmap(myBitmap);
            }
        }
    }

    public boolean checkStorage(){
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()){
            return false;
        }
        return true;
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
