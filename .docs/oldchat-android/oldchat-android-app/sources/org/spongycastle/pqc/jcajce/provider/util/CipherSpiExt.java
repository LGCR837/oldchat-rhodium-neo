package org.spongycastle.pqc.jcajce.provider.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.CipherSpi;

/* JADX INFO: loaded from: classes.dex */
public abstract class CipherSpiExt extends CipherSpi {
    public static final int DECRYPT_MODE = 2;
    public static final int ENCRYPT_MODE = 1;
    protected int opMode;

    public abstract int doFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    public final byte[] doFinal() {
        return doFinal(null, 0, 0);
    }

    public abstract byte[] doFinal(byte[] bArr, int i2, int i3);

    @Override // javax.crypto.CipherSpi
    public final byte[] engineDoFinal(byte[] bArr, int i2, int i3) {
        return doFinal(bArr, i2, i3);
    }

    @Override // javax.crypto.CipherSpi
    public final int engineGetBlockSize() {
        return getBlockSize();
    }

    @Override // javax.crypto.CipherSpi
    public final byte[] engineGetIV() {
        return getIV();
    }

    @Override // javax.crypto.CipherSpi
    public final int engineGetKeySize(Key key) throws InvalidKeyException {
        if (key instanceof Key) {
            return getKeySize(key);
        }
        throw new InvalidKeyException("Unsupported key.");
    }

    @Override // javax.crypto.CipherSpi
    public final int engineGetOutputSize(int i2) {
        return getOutputSize(i2);
    }

    @Override // javax.crypto.CipherSpi
    public final AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public final void engineInit(int i2, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i2, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new InvalidParameterException(e2.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    public final void engineSetMode(String str) {
        setMode(str);
    }

    @Override // javax.crypto.CipherSpi
    public final void engineSetPadding(String str) {
        setPadding(str);
    }

    @Override // javax.crypto.CipherSpi
    public final byte[] engineUpdate(byte[] bArr, int i2, int i3) {
        return update(bArr, i2, i3);
    }

    public abstract int getBlockSize();

    public abstract byte[] getIV();

    public abstract int getKeySize(Key key);

    public abstract String getName();

    public abstract int getOutputSize(int i2);

    public abstract AlgorithmParameterSpec getParameters();

    public abstract void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    public abstract void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    public abstract void setMode(String str);

    public abstract void setPadding(String str);

    public abstract int update(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    public final byte[] update(byte[] bArr) {
        return update(bArr, 0, bArr.length);
    }

    public abstract byte[] update(byte[] bArr, int i2, int i3);

    public final byte[] doFinal(byte[] bArr) {
        return doFinal(bArr, 0, bArr.length);
    }

    @Override // javax.crypto.CipherSpi
    public final int engineDoFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        return doFinal(bArr, i2, i3, bArr2, i4);
    }

    @Override // javax.crypto.CipherSpi
    public final int engineUpdate(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        return update(bArr, i2, i3, bArr2, i4);
    }

    @Override // javax.crypto.CipherSpi
    public final void engineInit(int i2, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException {
        if (algorithmParameters == null) {
            engineInit(i2, key, secureRandom);
        } else {
            engineInit(i2, key, (AlgorithmParameterSpec) null, secureRandom);
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException {
        if (key != null) {
            this.opMode = i2;
            if (i2 == 1) {
                initEncrypt(key, algorithmParameterSpec, secureRandom);
                return;
            } else {
                if (i2 == 2) {
                    initDecrypt(key, algorithmParameterSpec);
                    return;
                }
                return;
            }
        }
        throw new InvalidKeyException();
    }
}
