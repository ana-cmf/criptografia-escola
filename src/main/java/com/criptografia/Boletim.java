package com.criptografia;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.criptografia.criptografia.AssinaturaDigital;
import com.criptografia.criptografia.CriptografiaAssimetrica;
import com.criptografia.criptografia.CriptografiaSimetrica;
import com.criptografia.criptografia.Responsavel;

public class Boletim {

    byte[] conteudo;
    String assinaturaDigital;
    PrivateKey chavePrivada;
    byte[] chaveSimetricaCriptografada;

    public byte[] criptografarConteudo(String conteudo) throws Exception {
        SecretKey chaveSimetrica = CriptografiaSimetrica.geraChave();
        IvParameterSpec ivParameterSpec = CriptografiaSimetrica.geraIv();
        this.chaveSimetricaCriptografada = criptografarChaveSimetrica(chaveSimetrica);
        return CriptografiaSimetrica.encripta(conteudo, chaveSimetrica, ivParameterSpec);
    }
    
    public byte[] criptografarChaveSimetrica(SecretKey chaveSimetrica) throws Exception {
        KeyPair keyPair = CriptografiaAssimetrica.geraRSAKeyPair();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        
        // Salva a chave privada para assinar o conteudo com ela
        this.chavePrivada = keyPair.getPrivate();

        return cipher.doFinal(chaveSimetrica.getEncoded());
    }

    // Recebe o conteudo descriptografado e salva criptografado
    public void setConteudo(String conteudoDescriptografado) throws Exception {
        criptografarConteudo(conteudoDescriptografado);
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    // Assina digitalmente o conteudo do boletim com a mesma chave privada da criptografia da chave simetrica
    // para que o responsavel possa ler o conteudo
    // e verificar a assinatura com uma chave só
    public String assinarDigitalmente() throws Exception{
        return AssinaturaDigital.assina(conteudo, chavePrivada);
    }

    public void enviarChavePublicaParaResponsavel(Responsavel responsavel) throws Exception {
        responsavel.setChavePublicaCriptografada(chaveSimetricaCriptografada);
    }
}