package com.example.joffr.appcameradaora;

import com.orm.SugarRecord;

/**
 * Created by joffr on 12/10/2017.
 */

public class ImageRecord extends SugarRecord {

    private byte[] image;

    public  ImageRecord(){

    }

    public ImageRecord(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
