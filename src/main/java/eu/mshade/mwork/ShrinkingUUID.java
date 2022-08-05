package eu.mshade.mwork;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class ShrinkingUUID {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String randomUUID(int size){

        byte[] bytes = new byte[size];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String randomUUID(){
        return randomUUID(16);
    }
}
