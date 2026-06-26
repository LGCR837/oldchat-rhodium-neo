package org.spongycastle.util.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Provider;
import java.security.SecureRandom;
import org.spongycastle.util.Pack;
import org.spongycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes.dex */
public class FixedSecureRandom extends SecureRandom {
    private static final boolean isAndroidStyle;
    private static final boolean isClasspathStyle;
    private static final boolean isRegularStyle;
    private byte[] _data;
    private int _index;
    private static java.math.BigInteger REGULAR = new java.math.BigInteger("01020304ffffffff0506070811111111", 16);
    private static java.math.BigInteger ANDROID = new java.math.BigInteger("1111111105060708ffffffff01020304", 16);
    private static java.math.BigInteger CLASSPATH = new java.math.BigInteger("3020104ffffffff05060708111111", 16);

    public static class BigInteger extends Source {
        public BigInteger(byte[] bArr) {
            super(bArr);
        }

        public BigInteger(int i2, byte[] bArr) {
            super(FixedSecureRandom.expandToBitLength(i2, bArr));
        }

        public BigInteger(String str) {
            this(Hex.decode(str));
        }

        public BigInteger(int i2, String str) {
            super(FixedSecureRandom.expandToBitLength(i2, Hex.decode(str)));
        }
    }

    public static class Data extends Source {
        public Data(byte[] bArr) {
            super(bArr);
        }
    }

    public static class DummyProvider extends Provider {
        public DummyProvider() {
            super("BCFIPS_FIXED_RNG", 1.0d, "BCFIPS Fixed Secure Random Provider");
        }
    }

    public static class RandomChecker extends SecureRandom {
        byte[] data;
        int index;

        public RandomChecker() {
            super(null, new DummyProvider());
            this.data = Hex.decode("01020304ffffffff0506070811111111");
            this.index = 0;
        }

        @Override // java.security.SecureRandom, java.util.Random
        public void nextBytes(byte[] bArr) {
            System.arraycopy(this.data, this.index, bArr, 0, bArr.length);
            this.index += bArr.length;
        }
    }

    public static class Source {
        byte[] data;

        public Source(byte[] bArr) {
            this.data = bArr;
        }
    }

    static {
        java.math.BigInteger bigInteger = new java.math.BigInteger(128, new RandomChecker());
        java.math.BigInteger bigInteger2 = new java.math.BigInteger(120, new RandomChecker());
        isAndroidStyle = bigInteger.equals(ANDROID);
        isRegularStyle = bigInteger.equals(REGULAR);
        isClasspathStyle = bigInteger2.equals(CLASSPATH);
    }

    public FixedSecureRandom(byte[] bArr) {
        this(new Source[]{new Data(bArr)});
    }

    private static Data[] buildDataArray(byte[][] bArr) {
        Data[] dataArr = new Data[bArr.length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            dataArr[i2] = new Data(bArr[i2]);
        }
        return dataArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] expandToBitLength(int i2, byte[] bArr) {
        int i3;
        int i4;
        int i5 = (i2 + 7) / 8;
        if (i5 <= bArr.length) {
            if (isAndroidStyle && i2 < bArr.length * 8 && (i3 = i2 % 8) != 0) {
                Pack.intToBigEndian(Pack.bigEndianToInt(bArr, 0) << (8 - i3), bArr, 0);
            }
            return bArr;
        }
        byte[] bArr2 = new byte[i5];
        System.arraycopy(bArr, 0, bArr2, i5 - bArr.length, bArr.length);
        if (isAndroidStyle && (i4 = i2 % 8) != 0) {
            Pack.intToBigEndian(Pack.bigEndianToInt(bArr2, 0) << (8 - i4), bArr2, 0);
        }
        return bArr2;
    }

    private int nextValue() {
        byte[] bArr = this._data;
        int i2 = this._index;
        this._index = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i2) {
        byte[] bArr = new byte[i2];
        nextBytes(bArr);
        return bArr;
    }

    public boolean isExhausted() {
        return this._index == this._data.length;
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        System.arraycopy(this._data, this._index, bArr, 0, bArr.length);
        this._index += bArr.length;
    }

    @Override // java.util.Random
    public int nextInt() {
        return (nextValue() << 24) | (nextValue() << 16) | (nextValue() << 8) | nextValue();
    }

    @Override // java.util.Random
    public long nextLong() {
        return (((long) nextValue()) << 56) | (((long) nextValue()) << 48) | (((long) nextValue()) << 40) | (((long) nextValue()) << 32) | (((long) nextValue()) << 24) | (((long) nextValue()) << 16) | (((long) nextValue()) << 8) | ((long) nextValue());
    }

    public FixedSecureRandom(byte[][] bArr) {
        this(buildDataArray(bArr));
    }

    public FixedSecureRandom(Source[] sourceArr) {
        super(null, new DummyProvider());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        if (isRegularStyle) {
            if (isClasspathStyle) {
                while (i2 != sourceArr.length) {
                    try {
                        Source source = sourceArr[i2];
                        if (source instanceof BigInteger) {
                            byte[] bArr = source.data;
                            int length = bArr.length - (bArr.length % 4);
                            for (int length2 = (bArr.length - length) - 1; length2 >= 0; length2--) {
                                byteArrayOutputStream.write(bArr[length2]);
                            }
                            for (int length3 = bArr.length - length; length3 < bArr.length; length3 += 4) {
                                byteArrayOutputStream.write(bArr, length3, 4);
                            }
                        } else {
                            byteArrayOutputStream.write(source.data);
                        }
                        i2++;
                    } catch (IOException unused) {
                        throw new IllegalArgumentException("can't save value source.");
                    }
                }
            } else {
                while (i2 != sourceArr.length) {
                    try {
                        byteArrayOutputStream.write(sourceArr[i2].data);
                        i2++;
                    } catch (IOException unused2) {
                        throw new IllegalArgumentException("can't save value source.");
                    }
                }
            }
        } else if (isAndroidStyle) {
            for (int i3 = 0; i3 != sourceArr.length; i3++) {
                try {
                    Source source2 = sourceArr[i3];
                    if (source2 instanceof BigInteger) {
                        byte[] bArr2 = source2.data;
                        int length4 = bArr2.length - (bArr2.length % 4);
                        int i4 = 0;
                        while (i4 < length4) {
                            i4 += 4;
                            byteArrayOutputStream.write(bArr2, bArr2.length - i4, 4);
                        }
                        if (bArr2.length - length4 != 0) {
                            for (int i5 = 0; i5 != 4 - (bArr2.length - length4); i5++) {
                                byteArrayOutputStream.write(0);
                            }
                        }
                        for (int i6 = 0; i6 != bArr2.length - length4; i6++) {
                            byteArrayOutputStream.write(bArr2[length4 + i6]);
                        }
                    } else {
                        byteArrayOutputStream.write(source2.data);
                    }
                } catch (IOException unused3) {
                    throw new IllegalArgumentException("can't save value source.");
                }
            }
        } else {
            throw new IllegalStateException("Unrecognized BigInteger implementation");
        }
        this._data = byteArrayOutputStream.toByteArray();
    }
}
