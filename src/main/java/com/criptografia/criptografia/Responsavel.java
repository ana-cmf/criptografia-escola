package com.criptografia.criptografia;

public class Responsavel {

    byte[] chavePublicaCriptografada;
    String nome;

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
