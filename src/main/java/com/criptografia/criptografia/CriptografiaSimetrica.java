package com.criptografia.criptografia;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CriptografiaSimetrica {
    
    //Gera chave AES
    public static SecretKey geraChave() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    // vetor de inicialização
    public static IvParameterSpec geraIv() {
        byte[] vetorInicializacao = new byte[16]; //128 bits
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(vetorInicializacao);
        return new IvParameterSpec(vetorInicializacao);
    }
    public static byte[] encripta(String mensagem, SecretKey chave, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, chave, iv);
        return cipher.doFinal(mensagem.getBytes(StandardCharsets.UTF_8));
    }


    public static String decripta(byte[] textoCifrado, SecretKey chave, IvParameterSpec iv) throws Exception{

        Cipher decipher = Cipher.getInstance("AES/CFB8/NoPadding");
        decipher.init(Cipher.DECRYPT_MODE, chave, iv);
        byte[] textoAberto = decipher.doFinal(textoCifrado);

        return new String(textoAberto);
    }
}
