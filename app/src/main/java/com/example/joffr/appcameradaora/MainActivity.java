package com.example.joffr.appcameradaora;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private static String[] permissoes = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PermissionUtils.validate(MainActivity.this, 0, permissoes);
    }

    public void vaiProInterno(View view) {
        startActivity(new Intent(this, ArmazenamentoInternoActivity.class));
    }

    public void vaiProExterno(View view){
        startActivity(new Intent(this, ArmazenamentoExternoActivity.class));
    }

    public void vaiProBanco(View view){
        startActivity(new Intent(this, ArmazenamentoBancoActivity.class));
    }
//=======================================TRATAMENTO DE PERMISSOES===================================

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                //aLGUMA PERMISSAO FOI NEGADA
                alertAndFinish();
                return;
            }
        }
        //se chegou aqui ta ok
    }

    private void alertAndFinish() {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões");
            //add the buttons
            builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //nao fazer nada as vezes é melhor
                }
            });
            builder.setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

//==================================================================================================
}
