package com.example.joffr.appcameradaora;

/**
 * Created by joffr on 12/10/2017.
 */

public class Foto {
    private Long id;
    private byte[] imagem;

    public Foto(Long id, byte[] imagem) {
        this.id = id;
        this.imagem = imagem;
    }

    public Foto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
