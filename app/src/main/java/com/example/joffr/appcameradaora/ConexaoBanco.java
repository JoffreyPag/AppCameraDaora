package com.example.joffr.appcameradaora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by joffr on 12/10/2017.
 */

public class ConexaoBanco extends SQLiteOpenHelper {
    //String auxiliares
    private static final String TAG = "sql";
    private static final String IMAGE_TYPE = " BLOB";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String VIRGULA = ",";

    private static final String DATABASE_NAME = "bancoImagem.sqlite";

    //versao do banco
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE = (
            "CREATE TABÇE " + FotoEntry.TABLE_NAME +
                    "(" +
                    FotoEntry._ID + NUMBER_TYPE + " PRIMARY KEY" + VIRGULA +
                    FotoEntry.IMAGEM + IMAGE_TYPE + ");"
    );

    public ConexaoBanco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //INSERE UM NOVO LIVRO, OU ATUALIZA SE JA EXISTE
    public long save(Foto foto) {
        long id = foto.getId();
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues valores = new ContentValues();
            valores.put(FotoEntry.IMAGEM, foto.getImagem());

            if (id != 0) {
                String selection = FotoEntry._ID + "= ?";
                String[] whereArgs = new String[]{String.valueOf(id)};

                //update foto set values = ... where _id=?
                int count = db.update(FotoEntry.TABLE_NAME, valores, selection, whereArgs);

                return count;
            } else {
                //insert into foto values (...)
                id = db.insert(FotoEntry.TABLE_NAME, null, valores);

                return id;
            }
        } finally {
            db.close();
        }
    }

    //consulta um livro pelo id
    public Foto findFoto(int id) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            String selection = FotoEntry._ID + "= ?";
            String[] whereArgs = new String[]{String.valueOf(id)};
            Cursor c = db.query(FotoEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null);

            if (c.moveToFirst()) {
                Foto foto = new Foto();

                //recupera os atributos da foto
                foto.setId(c.getLong(c.getColumnIndex(FotoEntry._ID)));
                foto.setImagem(c.getBlob(c.getColumnIndex(FotoEntry.IMAGEM)));

                return foto;
            } else {
                return null;
            }
        } finally {
            db.close();
        }
    }

    public static class FotoEntry implements BaseColumns {
        public static final String TABLE_NAME = "Album";
        public static final String IMAGEM = "Imagem";
    }
}


