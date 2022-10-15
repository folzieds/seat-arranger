package com.phos.seatarrangement.core.security.config;


import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class RsaKeyProperties {

    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;

    public RsaKeyProperties() {
        initialize();
    }

    private void initialize(){
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            KeyPair pair = keyGen.generateKeyPair();
            this.rsaPrivateKey = (RSAPrivateKey) pair.getPrivate();
            this.rsaPublicKey = (RSAPublicKey) pair.getPublic();
        }catch(Exception ex){

        }
    }

    public RSAPublicKey getRsaPublicKey() {
        return rsaPublicKey;
    }

    public RSAPrivateKey getRsaPrivateKey() {
        return rsaPrivateKey;
    }
}
