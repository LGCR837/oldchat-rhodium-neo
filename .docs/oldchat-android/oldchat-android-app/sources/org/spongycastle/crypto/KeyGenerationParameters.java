package org.spongycastle.crypto;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class KeyGenerationParameters {
    private SecureRandom random;
    private int strength;

    public KeyGenerationParameters(SecureRandom secureRandom, int i2) {
        this.random = secureRandom;
        this.strength = i2;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getStrength() {
        return this.strength;
    }
}
