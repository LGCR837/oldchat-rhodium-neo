package org.spongycastle.util.test;

import java.math.BigInteger;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.test.FixedSecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class TestRandomBigInteger extends FixedSecureRandom {
    public TestRandomBigInteger(String str) {
        this(str, 10);
    }

    public TestRandomBigInteger(String str, int i2) {
        super(new FixedSecureRandom.Source[]{new FixedSecureRandom.BigInteger(BigIntegers.asUnsignedByteArray(new BigInteger(str, i2)))});
    }

    public TestRandomBigInteger(byte[] bArr) {
        super(new FixedSecureRandom.Source[]{new FixedSecureRandom.BigInteger(bArr)});
    }

    public TestRandomBigInteger(int i2, byte[] bArr) {
        super(new FixedSecureRandom.Source[]{new FixedSecureRandom.BigInteger(i2, bArr)});
    }
}
