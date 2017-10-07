package com.example.joffr.appcameradaora;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joffr on 07/10/2017.
 */

public class PermissionUtils {

    public static boolean validate(Activity activity, int requestCode, String... permissions){
        List<String> permissionToRequest = new ArrayList<>();

        for(String permission : permissions){
            //verifica se tem a permissao
            if(ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
                //verifica se alguma vez o usuario já negou permissao e precisa-se dar outra explicação
                if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
                    Toast.makeText(activity, "é Preciso que seja aceito", Toast.LENGTH_SHORT).show();
                    permissionToRequest.add(permission);
                }
            }
        }

        if (permissionToRequest.isEmpty()){
            //tudo ok retorna true
            return true;
        }

        //lista de permissoes que falta acesso
        String[] newPermissions = new String[permissionToRequest.size()];
        permissionToRequest.toArray(newPermissions);

        //solicita permissao
        ActivityCompat.requestPermissions(activity, newPermissions, 1);
        return false;
    }
}
