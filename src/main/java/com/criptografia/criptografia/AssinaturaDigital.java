package com.criptografia.criptografia;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class AssinaturaDigital {

    public static String assina(byte[] dados, PrivateKey chavePrivada) throws Exception {
        Signature assinatura = Signature.getInstance("SHA256withRSA");
        assinatura.initSign(chavePrivada);
        assinatura.update(dados);
        byte[] assinaturaBytes = assinatura.sign();
        return Base64.getEncoder().encodeToString(assinaturaBytes);
    }

    public static boolean verificaSHA256RSA(byte[] dados, String assinaturaBase64, PublicKey chavePublica) throws Exception {
        Signature assinatura = Signature.getInstance("SHA256withRSA");
        assinatura.initVerify(chavePublica);
        assinatura.update(dados);
        byte[] assinaturaBytes = Base64.getDecoder().decode(assinaturaBase64);
        return assinatura.verify(assinaturaBytes);
    }
}
