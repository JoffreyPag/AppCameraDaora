package com.example.joffr.appcameradaora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joffr on 06/10/2017.
 */

public class ConexaoBanco extends SQLiteOpenHelper{

    //String auxiliares
    private static final String TAG = "sql";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String VIRGULA = ",";

    private static final String DATABASE_NAME = "bancoFotos.sqlite";

    //versão do banco
    private static final int DATABASE_VERSION = 1;
/*
    private static final String SQL_CREATE_TABLE =
            ("CREATE TABLE " + LivroContrato.LivroEntry.TABLE_NAME +
                    "(" +
                    LivroContrato.LivroEntry._ID + NUMBER_TYPE + " PRIMARY KEY " + VIRGULA +
                    LivroContrato.LivroEntry.NOME + TEXT_TYPE + VIRGULA +
                    LivroContrato.LivroEntry.AUTOR + TEXT_TYPE + VIRGULA +
                    LivroContrato.LivroEntry.ANO + TEXT_TYPE + VIRGULA +
                    LivroContrato.LivroEntry.NOTA + FLOAT_TYPE +
                    ");");

    private static final String SQL_DROP_TABLE =
            ("DROP TABLE " + LivroContrato.LivroEntry.TABLE_NAME + ";"
            );

*/

    public ConexaoBanco(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VERSAOANTIGA, int NOVAVERSAO) {

    }

    //INSERE A IMAGEM
    /*public long save(){

    }

    //consulta pela id
    */


    //executa uma sql
    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }

    //executa um sql
    public void execSQL(String sql, Object[] args){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql, args);
        }finally {
            db.close();
        }
    }

}
