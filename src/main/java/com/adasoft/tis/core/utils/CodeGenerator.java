package com.adasoft.tis.core.utils;

import java.security.SecureRandom;

public final class CodeGenerator {

    private SecureRandom rnd = new SecureRandom();

    public String generateLetterCode(int size, int interval, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0 && i % interval == 0)
                sb.append(separator);
            sb.append((char) (rnd.nextInt(26) + 97));
        }
        return sb.toString();
    }
}
