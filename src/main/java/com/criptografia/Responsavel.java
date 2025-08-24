package com.criptografia;

public class Responsavel {

    private byte[] chavePublicaCriptografada;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public byte[] getChavePublicaCriptografada() {
        return chavePublicaCriptografada;
    }

    public void setChavePublicaCriptografada(byte[] chavePublicaCriptografada) {
        this.chavePublicaCriptografada = chavePublicaCriptografada;
    }
}
