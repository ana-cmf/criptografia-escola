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

public class Boletim {

    private byte[] conteudo;
    private String assinaturaDigital;

    // Chave que verifica a assinatura digital
    private PublicKey chaveAssinaturaDigital;
    
    // Chave que descriptografa o conteudo do boletim
    private byte[] chaveSimetricaCriptografada;
    
    public byte[] criptografarConteudo(String conteudo) throws Exception {
        SecretKey chaveSimetrica = CriptografiaSimetrica.geraChave();
        IvParameterSpec ivParameterSpec = CriptografiaSimetrica.geraIv();
        
        // A chaveSimetricaCriptografada descriptografa o conteúdo do boletim
        this.chaveSimetricaCriptografada = criptografarChaveSimetrica(chaveSimetrica);
        return CriptografiaSimetrica.encripta(conteudo, chaveSimetrica, ivParameterSpec);
    }
    
    public byte[] criptografarChaveSimetrica(SecretKey chaveSimetrica) throws Exception {
        KeyPair keyPair = CriptografiaAssimetrica.geraRSAKeyPair();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        return cipher.doFinal(chaveSimetrica.getEncoded());
    }
    
    // Recebe o conteudo descriptografado e salva criptografado
    public void setConteudo(String conteudoDescriptografado) throws Exception {
        this.conteudo = criptografarConteudo(conteudoDescriptografado);
        assinaturaDigital = assinarDigitalmente();
    }

    
    public String assinarDigitalmente() throws Exception{
        KeyPair keyPair = CriptografiaAssimetrica.geraRSAKeyPair();
        PrivateKey chavePrivada = keyPair.getPrivate();
        this.chaveAssinaturaDigital = keyPair.getPublic();
        return AssinaturaDigital.assina(conteudo, chavePrivada);
    }
    
    public void enviarChavePublicaParaResponsavel(Responsavel responsavel) throws Exception {
        responsavel.setChavePublicaCriptografada(chaveSimetricaCriptografada);
    }
    
    public byte[] getConteudo() {
        return conteudo;
    }
    
    public String getAssinaturaDigital() {
        return assinaturaDigital;
    }
    
    public PublicKey getChaveAssinaturaDigital() {
        return chaveAssinaturaDigital;
    }
    
    public byte[] getChaveSimetricaCriptografada() {
        return chaveSimetricaCriptografada;
    }
}