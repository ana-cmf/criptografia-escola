package com.criptografia;

import com.criptografia.criptografia.CriptografiaAssimetrica;
import com.criptografia.criptografia.CriptografiaSimetrica;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Responsavel {


    private byte[] chavePublicaCriptografada;
    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SecretKey decritaChave(byte [] chavePublicaCriptografada, PrivateKey privateKey) throws Exception {

        String string = CriptografiaAssimetrica.decripta(chavePublicaCriptografada, privateKey);

        byte[] bytes = new byte[32];
        bytes = string.getBytes();

        SecretKey chave = new SecretKeySpec(bytes,"AES");
        return chave;
    }

    public String decriptaBoletim(Boletim boletim) throws Exception {
        SecretKey chave =  decritaChave(chavePublicaCriptografada, boletim.getChavePrivadaSimetrica());
      String string = CriptografiaSimetrica.decripta(boletim.getConteudo(),chave, boletim.getIv());
      return string;
    }

    public byte[] getChavePublicaCriptografada() {
        return chavePublicaCriptografada;
    }

    public void setChavePublicaCriptografada(byte[] chavePublicaCriptografada) {
        this.chavePublicaCriptografada = chavePublicaCriptografada;
    }
}
