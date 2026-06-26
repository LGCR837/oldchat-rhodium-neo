package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.TweakableBlockCipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class ThreefishEngine implements BlockCipher {
    public static final int BLOCKSIZE_1024 = 1024;
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    private static final long C_240 = 2004413935125273122L;
    private static final int MAX_ROUNDS = 80;
    private static int[] MOD17 = null;
    private static int[] MOD3 = null;
    private static int[] MOD5 = null;
    private static int[] MOD9 = null;
    private static final int ROUNDS_1024 = 80;
    private static final int ROUNDS_256 = 72;
    private static final int ROUNDS_512 = 72;
    private static final int TWEAK_SIZE_BYTES = 16;
    private static final int TWEAK_SIZE_WORDS = 2;
    private int blocksizeBytes;
    private int blocksizeWords;
    private ThreefishCipher cipher;
    private long[] currentBlock;
    private boolean forEncryption;
    private long[] kw;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private long[] f7335t;

    public static final class Threefish1024Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 24;
        private static final int ROTATION_0_1 = 13;
        private static final int ROTATION_0_2 = 8;
        private static final int ROTATION_0_3 = 47;
        private static final int ROTATION_0_4 = 8;
        private static final int ROTATION_0_5 = 17;
        private static final int ROTATION_0_6 = 22;
        private static final int ROTATION_0_7 = 37;
        private static final int ROTATION_1_0 = 38;
        private static final int ROTATION_1_1 = 19;
        private static final int ROTATION_1_2 = 10;
        private static final int ROTATION_1_3 = 55;
        private static final int ROTATION_1_4 = 49;
        private static final int ROTATION_1_5 = 18;
        private static final int ROTATION_1_6 = 23;
        private static final int ROTATION_1_7 = 52;
        private static final int ROTATION_2_0 = 33;
        private static final int ROTATION_2_1 = 4;
        private static final int ROTATION_2_2 = 51;
        private static final int ROTATION_2_3 = 13;
        private static final int ROTATION_2_4 = 34;
        private static final int ROTATION_2_5 = 41;
        private static final int ROTATION_2_6 = 59;
        private static final int ROTATION_2_7 = 17;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 20;
        private static final int ROTATION_3_2 = 48;
        private static final int ROTATION_3_3 = 41;
        private static final int ROTATION_3_4 = 47;
        private static final int ROTATION_3_5 = 28;
        private static final int ROTATION_3_6 = 16;
        private static final int ROTATION_3_7 = 25;
        private static final int ROTATION_4_0 = 41;
        private static final int ROTATION_4_1 = 9;
        private static final int ROTATION_4_2 = 37;
        private static final int ROTATION_4_3 = 31;
        private static final int ROTATION_4_4 = 12;
        private static final int ROTATION_4_5 = 47;
        private static final int ROTATION_4_6 = 44;
        private static final int ROTATION_4_7 = 30;
        private static final int ROTATION_5_0 = 16;
        private static final int ROTATION_5_1 = 34;
        private static final int ROTATION_5_2 = 56;
        private static final int ROTATION_5_3 = 51;
        private static final int ROTATION_5_4 = 4;
        private static final int ROTATION_5_5 = 53;
        private static final int ROTATION_5_6 = 42;
        private static final int ROTATION_5_7 = 41;
        private static final int ROTATION_6_0 = 31;
        private static final int ROTATION_6_1 = 44;
        private static final int ROTATION_6_2 = 47;
        private static final int ROTATION_6_3 = 46;
        private static final int ROTATION_6_4 = 19;
        private static final int ROTATION_6_5 = 42;
        private static final int ROTATION_6_6 = 44;
        private static final int ROTATION_6_7 = 25;
        private static final int ROTATION_7_0 = 9;
        private static final int ROTATION_7_1 = 48;
        private static final int ROTATION_7_2 = 35;
        private static final int ROTATION_7_3 = 52;
        private static final int ROTATION_7_4 = 23;
        private static final int ROTATION_7_5 = 31;
        private static final int ROTATION_7_6 = 37;
        private static final int ROTATION_7_7 = 20;

        public Threefish1024Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.spongycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.f7336t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            int i2 = 1;
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            long j10 = jArr[8];
            long j11 = jArr[9];
            long j12 = jArr[10];
            long j13 = jArr[11];
            long j14 = jArr[12];
            long j15 = jArr[13];
            long j16 = jArr[14];
            long jXorRotr = jArr[15];
            int i3 = 19;
            while (i3 >= i2) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                int i6 = i4 + 1;
                long j17 = j2 - jArr3[i6];
                int i7 = i4 + 2;
                long j18 = j3 - jArr3[i7];
                int i8 = i4 + 3;
                long j19 = j4 - jArr3[i8];
                int i9 = i4 + 4;
                long j20 = j5 - jArr3[i9];
                int i10 = i4 + 5;
                long j21 = j6 - jArr3[i10];
                int i11 = i4 + 6;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j22 = j7 - jArr3[i11];
                int i12 = i4 + 7;
                long j23 = j8 - jArr3[i12];
                int i13 = i4 + 8;
                long j24 = j9 - jArr3[i13];
                int i14 = i4 + 9;
                long j25 = j10 - jArr3[i14];
                int i15 = i4 + 10;
                long j26 = j11 - jArr3[i15];
                int i16 = i4 + 11;
                long j27 = j12 - jArr3[i16];
                int i17 = i4 + 12;
                long j28 = j13 - jArr3[i17];
                int i18 = i4 + 13;
                long j29 = j14 - jArr3[i18];
                int i19 = i4 + 14;
                int i20 = i5 + 1;
                long j30 = j15 - (jArr3[i19] + jArr4[i20]);
                int i21 = i4 + 15;
                long j31 = j16 - (jArr3[i21] + jArr4[i5 + 2]);
                long[] jArr5 = jArr3;
                long j32 = i3;
                long jXorRotr2 = ThreefishEngine.xorRotr(jXorRotr - ((jArr3[i4 + 16] + j32) + 1), 9, j17);
                long j33 = j17 - jXorRotr2;
                long jXorRotr3 = ThreefishEngine.xorRotr(j28, 48, j19);
                long j34 = j19 - jXorRotr3;
                long jXorRotr4 = ThreefishEngine.xorRotr(j30, 35, j23);
                long j35 = j23 - jXorRotr4;
                long[] jArr6 = jArr4;
                long jXorRotr5 = ThreefishEngine.xorRotr(j26, 52, j21);
                long j36 = j21 - jXorRotr5;
                long jXorRotr6 = ThreefishEngine.xorRotr(j18, 23, j31);
                long j37 = j31 - jXorRotr6;
                long jXorRotr7 = ThreefishEngine.xorRotr(j22, 31, j25);
                long j38 = j25 - jXorRotr7;
                long jXorRotr8 = ThreefishEngine.xorRotr(j20, 37, j27);
                long j39 = j27 - jXorRotr8;
                long jXorRotr9 = ThreefishEngine.xorRotr(j24, 20, j29);
                long j40 = j29 - jXorRotr9;
                long jXorRotr10 = ThreefishEngine.xorRotr(jXorRotr9, 31, j33);
                long j41 = j33 - jXorRotr10;
                long jXorRotr11 = ThreefishEngine.xorRotr(jXorRotr7, 44, j34);
                long j42 = j34 - jXorRotr11;
                long jXorRotr12 = ThreefishEngine.xorRotr(jXorRotr8, 47, j36);
                long j43 = j36 - jXorRotr12;
                long jXorRotr13 = ThreefishEngine.xorRotr(jXorRotr6, 46, j35);
                long j44 = j35 - jXorRotr13;
                long jXorRotr14 = ThreefishEngine.xorRotr(jXorRotr2, 19, j40);
                long j45 = j40 - jXorRotr14;
                long jXorRotr15 = ThreefishEngine.xorRotr(jXorRotr4, 42, j37);
                long j46 = j37 - jXorRotr15;
                long jXorRotr16 = ThreefishEngine.xorRotr(jXorRotr3, 44, j38);
                long j47 = j38 - jXorRotr16;
                long jXorRotr17 = ThreefishEngine.xorRotr(jXorRotr5, 25, j39);
                long j48 = j39 - jXorRotr17;
                long jXorRotr18 = ThreefishEngine.xorRotr(jXorRotr17, 16, j41);
                long j49 = j41 - jXorRotr18;
                long jXorRotr19 = ThreefishEngine.xorRotr(jXorRotr15, 34, j42);
                long j50 = j42 - jXorRotr19;
                long jXorRotr20 = ThreefishEngine.xorRotr(jXorRotr16, 56, j44);
                long j51 = j44 - jXorRotr20;
                long jXorRotr21 = ThreefishEngine.xorRotr(jXorRotr14, 51, j43);
                long j52 = j43 - jXorRotr21;
                long jXorRotr22 = ThreefishEngine.xorRotr(jXorRotr10, 4, j48);
                long j53 = j48 - jXorRotr22;
                long jXorRotr23 = ThreefishEngine.xorRotr(jXorRotr12, 53, j45);
                long j54 = j45 - jXorRotr23;
                long jXorRotr24 = ThreefishEngine.xorRotr(jXorRotr11, 42, j46);
                long j55 = j46 - jXorRotr24;
                long jXorRotr25 = ThreefishEngine.xorRotr(jXorRotr13, 41, j47);
                long j56 = j47 - jXorRotr25;
                long jXorRotr26 = ThreefishEngine.xorRotr(jXorRotr25, 41, j49);
                long jXorRotr27 = ThreefishEngine.xorRotr(jXorRotr23, 9, j50);
                long jXorRotr28 = ThreefishEngine.xorRotr(jXorRotr24, 37, j52);
                long j57 = j52 - jXorRotr28;
                long jXorRotr29 = ThreefishEngine.xorRotr(jXorRotr22, 31, j51);
                long j58 = j51 - jXorRotr29;
                long jXorRotr30 = ThreefishEngine.xorRotr(jXorRotr18, 12, j56);
                long j59 = j56 - jXorRotr30;
                long jXorRotr31 = ThreefishEngine.xorRotr(jXorRotr20, 47, j53);
                long j60 = j53 - jXorRotr31;
                long jXorRotr32 = ThreefishEngine.xorRotr(jXorRotr19, 44, j54);
                long j61 = j54 - jXorRotr32;
                long jXorRotr33 = ThreefishEngine.xorRotr(jXorRotr21, 30, j55);
                long j62 = j55 - jXorRotr33;
                long j63 = (j49 - jXorRotr26) - jArr5[i4];
                long j64 = jXorRotr26 - jArr5[i6];
                long j65 = (j50 - jXorRotr27) - jArr5[i7];
                long j66 = jXorRotr27 - jArr5[i8];
                long j67 = j57 - jArr5[i9];
                long j68 = jXorRotr28 - jArr5[i10];
                long j69 = j58 - jArr5[i11];
                long j70 = jXorRotr29 - jArr5[i12];
                long j71 = j59 - jArr5[i13];
                long j72 = jXorRotr30 - jArr5[i14];
                long j73 = j60 - jArr5[i15];
                long j74 = jXorRotr31 - jArr5[i16];
                long j75 = j61 - jArr5[i17];
                long j76 = jXorRotr32 - (jArr5[i18] + jArr6[i5]);
                long j77 = j62 - (jArr5[i19] + jArr6[i20]);
                long jXorRotr34 = ThreefishEngine.xorRotr(jXorRotr33 - (jArr5[i21] + j32), 5, j63);
                long j78 = j63 - jXorRotr34;
                long jXorRotr35 = ThreefishEngine.xorRotr(j74, 20, j65);
                long j79 = j65 - jXorRotr35;
                long jXorRotr36 = ThreefishEngine.xorRotr(j76, 48, j69);
                long j80 = j69 - jXorRotr36;
                long jXorRotr37 = ThreefishEngine.xorRotr(j72, 41, j67);
                long j81 = j67 - jXorRotr37;
                long jXorRotr38 = ThreefishEngine.xorRotr(j64, 47, j77);
                long j82 = j77 - jXorRotr38;
                long jXorRotr39 = ThreefishEngine.xorRotr(j68, 28, j71);
                long j83 = j71 - jXorRotr39;
                long jXorRotr40 = ThreefishEngine.xorRotr(j66, 16, j73);
                long j84 = j73 - jXorRotr40;
                long jXorRotr41 = ThreefishEngine.xorRotr(j70, 25, j75);
                long j85 = j75 - jXorRotr41;
                long jXorRotr42 = ThreefishEngine.xorRotr(jXorRotr41, 33, j78);
                long j86 = j78 - jXorRotr42;
                long jXorRotr43 = ThreefishEngine.xorRotr(jXorRotr39, 4, j79);
                long j87 = j79 - jXorRotr43;
                long jXorRotr44 = ThreefishEngine.xorRotr(jXorRotr40, 51, j81);
                long j88 = j81 - jXorRotr44;
                long jXorRotr45 = ThreefishEngine.xorRotr(jXorRotr38, 13, j80);
                long j89 = j80 - jXorRotr45;
                long jXorRotr46 = ThreefishEngine.xorRotr(jXorRotr34, 34, j85);
                long j90 = j85 - jXorRotr46;
                long jXorRotr47 = ThreefishEngine.xorRotr(jXorRotr36, 41, j82);
                long j91 = j82 - jXorRotr47;
                long jXorRotr48 = ThreefishEngine.xorRotr(jXorRotr35, 59, j83);
                long j92 = j83 - jXorRotr48;
                long jXorRotr49 = ThreefishEngine.xorRotr(jXorRotr37, 17, j84);
                long j93 = j84 - jXorRotr49;
                long jXorRotr50 = ThreefishEngine.xorRotr(jXorRotr49, 38, j86);
                long j94 = j86 - jXorRotr50;
                long jXorRotr51 = ThreefishEngine.xorRotr(jXorRotr47, 19, j87);
                long j95 = j87 - jXorRotr51;
                long jXorRotr52 = ThreefishEngine.xorRotr(jXorRotr48, 10, j89);
                long j96 = j89 - jXorRotr52;
                long jXorRotr53 = ThreefishEngine.xorRotr(jXorRotr46, 55, j88);
                long j97 = j88 - jXorRotr53;
                long jXorRotr54 = ThreefishEngine.xorRotr(jXorRotr42, 49, j93);
                long j98 = j93 - jXorRotr54;
                long jXorRotr55 = ThreefishEngine.xorRotr(jXorRotr44, 18, j90);
                long j99 = j90 - jXorRotr55;
                long jXorRotr56 = ThreefishEngine.xorRotr(jXorRotr43, 23, j91);
                long j100 = j91 - jXorRotr56;
                long jXorRotr57 = ThreefishEngine.xorRotr(jXorRotr45, 52, j92);
                long j101 = j92 - jXorRotr57;
                long jXorRotr58 = ThreefishEngine.xorRotr(jXorRotr57, 24, j94);
                long j102 = j94 - jXorRotr58;
                long jXorRotr59 = ThreefishEngine.xorRotr(jXorRotr55, 13, j95);
                j4 = j95 - jXorRotr59;
                long jXorRotr60 = ThreefishEngine.xorRotr(jXorRotr56, 8, j97);
                long j103 = j97 - jXorRotr60;
                long jXorRotr61 = ThreefishEngine.xorRotr(jXorRotr54, 47, j96);
                long j104 = j96 - jXorRotr61;
                long jXorRotr62 = ThreefishEngine.xorRotr(jXorRotr50, 8, j101);
                long j105 = j101 - jXorRotr62;
                long jXorRotr63 = ThreefishEngine.xorRotr(jXorRotr52, 17, j98);
                long j106 = j98 - jXorRotr63;
                long jXorRotr64 = ThreefishEngine.xorRotr(jXorRotr51, 22, j99);
                j14 = j99 - jXorRotr64;
                jXorRotr = ThreefishEngine.xorRotr(jXorRotr53, 37, j100);
                j16 = j100 - jXorRotr;
                j13 = jXorRotr63;
                j11 = jXorRotr62;
                iArr = iArr3;
                iArr2 = iArr4;
                jArr4 = jArr6;
                jArr3 = jArr5;
                j15 = jXorRotr64;
                j10 = j105;
                i2 = 1;
                i3 -= 2;
                j3 = jXorRotr58;
                j5 = jXorRotr59;
                j12 = j106;
                j9 = jXorRotr61;
                j6 = j103;
                j7 = jXorRotr60;
                j8 = j104;
                j2 = j102;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j107 = j2 - jArr7[0];
            long j108 = j3 - jArr7[1];
            long j109 = j4 - jArr7[2];
            long j110 = j5 - jArr7[3];
            long j111 = j6 - jArr7[4];
            long j112 = j7 - jArr7[5];
            long j113 = j8 - jArr7[6];
            long j114 = j9 - jArr7[7];
            long j115 = j10 - jArr7[8];
            long j116 = j11 - jArr7[9];
            long j117 = j12 - jArr7[10];
            long j118 = j13 - jArr7[11];
            long j119 = j14 - jArr7[12];
            long j120 = j15 - (jArr7[13] + jArr8[0]);
            long j121 = j16 - (jArr7[14] + jArr8[1]);
            long j122 = jXorRotr - jArr7[15];
            jArr2[0] = j107;
            jArr2[1] = j108;
            jArr2[2] = j109;
            jArr2[3] = j110;
            jArr2[4] = j111;
            jArr2[5] = j112;
            jArr2[6] = j113;
            jArr2[7] = j114;
            jArr2[8] = j115;
            jArr2[9] = j116;
            jArr2[10] = j117;
            jArr2[11] = j118;
            jArr2[12] = j119;
            jArr2[13] = j120;
            jArr2[14] = j121;
            jArr2[15] = j122;
        }

        @Override // org.spongycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.f7336t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            int i2 = 1;
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            long j10 = jArr[8];
            long j11 = jArr[9];
            long j12 = jArr[10];
            long j13 = jArr[11];
            long j14 = jArr[12];
            long j15 = jArr[13];
            long j16 = jArr[14];
            long j17 = jArr[15];
            long j18 = j2 + jArr3[0];
            long j19 = j3 + jArr3[1];
            long j20 = j4 + jArr3[2];
            long j21 = j5 + jArr3[3];
            long j22 = j6 + jArr3[4];
            long j23 = j7 + jArr3[5];
            long j24 = j8 + jArr3[6];
            long j25 = j9 + jArr3[7];
            long j26 = j10 + jArr3[8];
            long j27 = j11 + jArr3[9];
            long j28 = j12 + jArr3[10];
            long j29 = j13 + jArr3[11];
            long j30 = j14 + jArr3[12];
            long j31 = j15 + jArr3[13] + jArr4[0];
            long j32 = j16 + jArr3[14] + jArr4[1];
            long j33 = j21;
            long j34 = j23;
            long j35 = j25;
            long j36 = j27;
            long j37 = j29;
            long j38 = j31;
            long j39 = j17 + jArr3[15];
            while (i2 < 20) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j40 = j18 + j19;
                long jRotlXor = ThreefishEngine.rotlXor(j19, 24, j40);
                long j41 = j20 + j33;
                long jRotlXor2 = ThreefishEngine.rotlXor(j33, 13, j41);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j42 = j34;
                long j43 = j22 + j42;
                long jRotlXor3 = ThreefishEngine.rotlXor(j42, 8, j43);
                int i5 = i2;
                long j44 = j35;
                long j45 = j24 + j44;
                long jRotlXor4 = ThreefishEngine.rotlXor(j44, 47, j45);
                long[] jArr6 = jArr4;
                long j46 = j36;
                long j47 = j26 + j46;
                long jRotlXor5 = ThreefishEngine.rotlXor(j46, 8, j47);
                long j48 = j37;
                long j49 = j28 + j48;
                long jRotlXor6 = ThreefishEngine.rotlXor(j48, 17, j49);
                long j50 = j38;
                long j51 = j30 + j50;
                long jRotlXor7 = ThreefishEngine.rotlXor(j50, 22, j51);
                long j52 = j39;
                long j53 = j32 + j52;
                long jRotlXor8 = ThreefishEngine.rotlXor(j52, 37, j53);
                long j54 = j40 + jRotlXor5;
                long jRotlXor9 = ThreefishEngine.rotlXor(jRotlXor5, 38, j54);
                long j55 = j41 + jRotlXor7;
                long jRotlXor10 = ThreefishEngine.rotlXor(jRotlXor7, 19, j55);
                long j56 = j45 + jRotlXor6;
                long jRotlXor11 = ThreefishEngine.rotlXor(jRotlXor6, 10, j56);
                long j57 = j43 + jRotlXor8;
                long jRotlXor12 = ThreefishEngine.rotlXor(jRotlXor8, 55, j57);
                long j58 = j49 + jRotlXor4;
                long jRotlXor13 = ThreefishEngine.rotlXor(jRotlXor4, 49, j58);
                long j59 = j51 + jRotlXor2;
                long jRotlXor14 = ThreefishEngine.rotlXor(jRotlXor2, 18, j59);
                long j60 = j53 + jRotlXor3;
                long jRotlXor15 = ThreefishEngine.rotlXor(jRotlXor3, 23, j60);
                long j61 = j47 + jRotlXor;
                long jRotlXor16 = ThreefishEngine.rotlXor(jRotlXor, 52, j61);
                long j62 = j54 + jRotlXor13;
                long jRotlXor17 = ThreefishEngine.rotlXor(jRotlXor13, 33, j62);
                long j63 = j55 + jRotlXor15;
                long jRotlXor18 = ThreefishEngine.rotlXor(jRotlXor15, 4, j63);
                long j64 = j57 + jRotlXor14;
                long jRotlXor19 = ThreefishEngine.rotlXor(jRotlXor14, 51, j64);
                long j65 = j56 + jRotlXor16;
                long jRotlXor20 = ThreefishEngine.rotlXor(jRotlXor16, 13, j65);
                long j66 = j59 + jRotlXor12;
                long jRotlXor21 = ThreefishEngine.rotlXor(jRotlXor12, 34, j66);
                long j67 = j60 + jRotlXor10;
                long jRotlXor22 = ThreefishEngine.rotlXor(jRotlXor10, 41, j67);
                long j68 = j61 + jRotlXor11;
                long jRotlXor23 = ThreefishEngine.rotlXor(jRotlXor11, 59, j68);
                long j69 = j58 + jRotlXor9;
                long jRotlXor24 = ThreefishEngine.rotlXor(jRotlXor9, 17, j69);
                long j70 = j62 + jRotlXor21;
                long jRotlXor25 = ThreefishEngine.rotlXor(jRotlXor21, 5, j70);
                long j71 = j63 + jRotlXor23;
                long jRotlXor26 = ThreefishEngine.rotlXor(jRotlXor23, 20, j71);
                long j72 = j65 + jRotlXor22;
                long jRotlXor27 = ThreefishEngine.rotlXor(jRotlXor22, 48, j72);
                long j73 = j64 + jRotlXor24;
                long jRotlXor28 = ThreefishEngine.rotlXor(jRotlXor24, 41, j73);
                long j74 = j67 + jRotlXor20;
                long jRotlXor29 = ThreefishEngine.rotlXor(jRotlXor20, 47, j74);
                long j75 = j68 + jRotlXor18;
                long jRotlXor30 = ThreefishEngine.rotlXor(jRotlXor18, 28, j75);
                long j76 = j69 + jRotlXor19;
                long jRotlXor31 = ThreefishEngine.rotlXor(jRotlXor19, 16, j76);
                long j77 = j66 + jRotlXor17;
                long jRotlXor32 = ThreefishEngine.rotlXor(jRotlXor17, 25, j77);
                long j78 = j70 + jArr5[i3];
                int i6 = i3 + 1;
                long j79 = jRotlXor29 + jArr5[i6];
                int i7 = i3 + 2;
                long j80 = j71 + jArr5[i7];
                int i8 = i3 + 3;
                long j81 = jRotlXor31 + jArr5[i8];
                int i9 = i3 + 4;
                long j82 = j73 + jArr5[i9];
                int i10 = i3 + 5;
                long j83 = jRotlXor30 + jArr5[i10];
                int i11 = i3 + 6;
                long j84 = j72 + jArr5[i11];
                int i12 = i3 + 7;
                long j85 = jRotlXor32 + jArr5[i12];
                int i13 = i3 + 8;
                long j86 = j75 + jArr5[i13];
                int i14 = i3 + 9;
                long j87 = jRotlXor28 + jArr5[i14];
                int i15 = i3 + 10;
                long j88 = j76 + jArr5[i15];
                int i16 = i3 + 11;
                long j89 = jRotlXor26 + jArr5[i16];
                int i17 = i3 + 12;
                long j90 = j77 + jArr5[i17];
                int i18 = i3 + 13;
                long j91 = jRotlXor27 + jArr5[i18] + jArr6[i4];
                int i19 = i3 + 14;
                int i20 = i4 + 1;
                long j92 = j74 + jArr5[i19] + jArr6[i20];
                int i21 = i3 + 15;
                long j93 = i5;
                long j94 = jRotlXor25 + jArr5[i21] + j93;
                long j95 = j78 + j79;
                long jRotlXor33 = ThreefishEngine.rotlXor(j79, 41, j95);
                long j96 = j80 + j81;
                long jRotlXor34 = ThreefishEngine.rotlXor(j81, 9, j96);
                long j97 = j82 + j83;
                long jRotlXor35 = ThreefishEngine.rotlXor(j83, 37, j97);
                long j98 = j84 + j85;
                long jRotlXor36 = ThreefishEngine.rotlXor(j85, 31, j98);
                long j99 = j86 + j87;
                long jRotlXor37 = ThreefishEngine.rotlXor(j87, 12, j99);
                long j100 = j88 + j89;
                long jRotlXor38 = ThreefishEngine.rotlXor(j89, 47, j100);
                long j101 = j90 + j91;
                long jRotlXor39 = ThreefishEngine.rotlXor(j91, 44, j101);
                long j102 = j92 + j94;
                long jRotlXor40 = ThreefishEngine.rotlXor(j94, 30, j102);
                long j103 = j95 + jRotlXor37;
                long jRotlXor41 = ThreefishEngine.rotlXor(jRotlXor37, 16, j103);
                long j104 = j96 + jRotlXor39;
                long jRotlXor42 = ThreefishEngine.rotlXor(jRotlXor39, 34, j104);
                long j105 = j98 + jRotlXor38;
                long jRotlXor43 = ThreefishEngine.rotlXor(jRotlXor38, 56, j105);
                long j106 = j97 + jRotlXor40;
                long jRotlXor44 = ThreefishEngine.rotlXor(jRotlXor40, 51, j106);
                long j107 = j100 + jRotlXor36;
                long jRotlXor45 = ThreefishEngine.rotlXor(jRotlXor36, 4, j107);
                long j108 = j101 + jRotlXor34;
                long jRotlXor46 = ThreefishEngine.rotlXor(jRotlXor34, 53, j108);
                long j109 = j102 + jRotlXor35;
                long jRotlXor47 = ThreefishEngine.rotlXor(jRotlXor35, 42, j109);
                long j110 = j99 + jRotlXor33;
                long jRotlXor48 = ThreefishEngine.rotlXor(jRotlXor33, 41, j110);
                long j111 = j103 + jRotlXor45;
                long jRotlXor49 = ThreefishEngine.rotlXor(jRotlXor45, 31, j111);
                long j112 = j104 + jRotlXor47;
                long jRotlXor50 = ThreefishEngine.rotlXor(jRotlXor47, 44, j112);
                long j113 = j106 + jRotlXor46;
                long jRotlXor51 = ThreefishEngine.rotlXor(jRotlXor46, 47, j113);
                long j114 = j105 + jRotlXor48;
                long jRotlXor52 = ThreefishEngine.rotlXor(jRotlXor48, 46, j114);
                long j115 = j108 + jRotlXor44;
                long jRotlXor53 = ThreefishEngine.rotlXor(jRotlXor44, 19, j115);
                long j116 = j109 + jRotlXor42;
                long jRotlXor54 = ThreefishEngine.rotlXor(jRotlXor42, 42, j116);
                long j117 = j110 + jRotlXor43;
                long jRotlXor55 = ThreefishEngine.rotlXor(jRotlXor43, 44, j117);
                long j118 = j107 + jRotlXor41;
                long jRotlXor56 = ThreefishEngine.rotlXor(jRotlXor41, 25, j118);
                long j119 = j111 + jRotlXor53;
                long jRotlXor57 = ThreefishEngine.rotlXor(jRotlXor53, 9, j119);
                long j120 = j112 + jRotlXor55;
                long jRotlXor58 = ThreefishEngine.rotlXor(jRotlXor55, 48, j120);
                long j121 = j114 + jRotlXor54;
                long jRotlXor59 = ThreefishEngine.rotlXor(jRotlXor54, 35, j121);
                long j122 = j113 + jRotlXor56;
                long jRotlXor60 = ThreefishEngine.rotlXor(jRotlXor56, 52, j122);
                long j123 = j116 + jRotlXor52;
                long jRotlXor61 = ThreefishEngine.rotlXor(jRotlXor52, 23, j123);
                long j124 = j117 + jRotlXor50;
                long jRotlXor62 = ThreefishEngine.rotlXor(jRotlXor50, 31, j124);
                long j125 = j118 + jRotlXor51;
                long jRotlXor63 = ThreefishEngine.rotlXor(jRotlXor51, 37, j125);
                long j126 = j115 + jRotlXor49;
                long jRotlXor64 = ThreefishEngine.rotlXor(jRotlXor49, 20, j126);
                long j127 = j119 + jArr5[i6];
                long j128 = jRotlXor61 + jArr5[i7];
                long j129 = j120 + jArr5[i8];
                long j130 = jRotlXor63 + jArr5[i9];
                long j131 = j122 + jArr5[i10];
                j34 = jRotlXor62 + jArr5[i11];
                long j132 = j121 + jArr5[i12];
                j35 = jRotlXor64 + jArr5[i13];
                j26 = j124 + jArr5[i14];
                long j133 = jRotlXor60 + jArr5[i15];
                j28 = j125 + jArr5[i16];
                long j134 = jRotlXor58 + jArr5[i17];
                j30 = j126 + jArr5[i18];
                j38 = jRotlXor59 + jArr5[i19] + jArr6[i20];
                j32 = j123 + jArr5[i21] + jArr6[i4 + 2];
                j39 = jRotlXor57 + jArr5[i3 + 16] + j93 + 1;
                j36 = j133;
                j24 = j132;
                j37 = j134;
                iArr = iArr3;
                jArr4 = jArr6;
                j22 = j131;
                j33 = j130;
                iArr2 = iArr4;
                jArr3 = jArr5;
                i2 = i5 + 2;
                j19 = j128;
                j18 = j127;
                j20 = j129;
            }
            jArr2[0] = j18;
            jArr2[1] = j19;
            jArr2[2] = j20;
            jArr2[3] = j33;
            jArr2[4] = j22;
            jArr2[5] = j34;
            jArr2[6] = j24;
            jArr2[7] = j35;
            jArr2[8] = j26;
            jArr2[9] = j36;
            jArr2[10] = j28;
            jArr2[11] = j37;
            jArr2[12] = j30;
            jArr2[13] = j38;
            jArr2[14] = j32;
            jArr2[15] = j39;
        }
    }

    public static final class Threefish256Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 14;
        private static final int ROTATION_0_1 = 16;
        private static final int ROTATION_1_0 = 52;
        private static final int ROTATION_1_1 = 57;
        private static final int ROTATION_2_0 = 23;
        private static final int ROTATION_2_1 = 40;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 37;
        private static final int ROTATION_4_0 = 25;
        private static final int ROTATION_4_1 = 33;
        private static final int ROTATION_5_0 = 46;
        private static final int ROTATION_5_1 = 12;
        private static final int ROTATION_6_0 = 58;
        private static final int ROTATION_6_1 = 22;
        private static final int ROTATION_7_0 = 32;
        private static final int ROTATION_7_1 = 32;

        public Threefish256Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.spongycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.f7336t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long jXorRotr = jArr[3];
            int i2 = 17;
            for (int i3 = 1; i2 >= i3; i3 = 1) {
                int i4 = iArr[i2];
                int i5 = iArr2[i2];
                int i6 = i4 + 1;
                long j5 = j2 - jArr3[i6];
                int i7 = i4 + 2;
                int i8 = i5 + 1;
                long j6 = j3 - (jArr3[i7] + jArr4[i8]);
                int i9 = i4 + 3;
                long j7 = j4 - (jArr3[i9] + jArr4[i5 + 2]);
                long j8 = i2;
                long jXorRotr2 = ThreefishEngine.xorRotr(jXorRotr - ((jArr3[i4 + 4] + j8) + 1), 32, j5);
                long j9 = j5 - jXorRotr2;
                int[] iArr3 = iArr;
                long jXorRotr3 = ThreefishEngine.xorRotr(j6, 32, j7);
                long j10 = j7 - jXorRotr3;
                long jXorRotr4 = ThreefishEngine.xorRotr(jXorRotr3, 58, j9);
                long j11 = j9 - jXorRotr4;
                long jXorRotr5 = ThreefishEngine.xorRotr(jXorRotr2, 22, j10);
                long j12 = j10 - jXorRotr5;
                long jXorRotr6 = ThreefishEngine.xorRotr(jXorRotr5, 46, j11);
                long j13 = j11 - jXorRotr6;
                long jXorRotr7 = ThreefishEngine.xorRotr(jXorRotr4, 12, j12);
                long j14 = j12 - jXorRotr7;
                long jXorRotr8 = ThreefishEngine.xorRotr(jXorRotr7, 25, j13);
                long jXorRotr9 = ThreefishEngine.xorRotr(jXorRotr6, 33, j14);
                long j15 = (j13 - jXorRotr8) - jArr3[i4];
                long j16 = jXorRotr8 - (jArr3[i6] + jArr4[i5]);
                long j17 = (j14 - jXorRotr9) - (jArr3[i7] + jArr4[i8]);
                long jXorRotr10 = ThreefishEngine.xorRotr(jXorRotr9 - (jArr3[i9] + j8), 5, j15);
                long j18 = j15 - jXorRotr10;
                long jXorRotr11 = ThreefishEngine.xorRotr(j16, 37, j17);
                long j19 = j17 - jXorRotr11;
                long jXorRotr12 = ThreefishEngine.xorRotr(jXorRotr11, 23, j18);
                long j20 = j18 - jXorRotr12;
                long jXorRotr13 = ThreefishEngine.xorRotr(jXorRotr10, 40, j19);
                long j21 = j19 - jXorRotr13;
                long jXorRotr14 = ThreefishEngine.xorRotr(jXorRotr13, 52, j20);
                long j22 = j20 - jXorRotr14;
                long jXorRotr15 = ThreefishEngine.xorRotr(jXorRotr12, 57, j21);
                long j23 = j21 - jXorRotr15;
                long jXorRotr16 = ThreefishEngine.xorRotr(jXorRotr15, 14, j22);
                j2 = j22 - jXorRotr16;
                jXorRotr = ThreefishEngine.xorRotr(jXorRotr14, 16, j23);
                j4 = j23 - jXorRotr;
                i2 -= 2;
                j3 = jXorRotr16;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            long j24 = j2 - jArr3[0];
            long j25 = j3 - (jArr3[1] + jArr4[0]);
            long j26 = j4 - (jArr3[2] + jArr4[1]);
            long j27 = jXorRotr - jArr3[3];
            jArr2[0] = j24;
            jArr2[1] = j25;
            jArr2[2] = j26;
            jArr2[3] = j27;
        }

        @Override // org.spongycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.f7336t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = j2 + jArr3[0];
            long j7 = j3 + jArr3[1] + jArr4[0];
            long j8 = j4 + jArr3[2] + jArr4[1];
            long j9 = j5 + jArr3[3];
            int i2 = 1;
            while (i2 < 18) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j10 = j6 + j7;
                long jRotlXor = ThreefishEngine.rotlXor(j7, 14, j10);
                long j11 = j8 + j9;
                long jRotlXor2 = ThreefishEngine.rotlXor(j9, 16, j11);
                long j12 = j10 + jRotlXor2;
                long jRotlXor3 = ThreefishEngine.rotlXor(jRotlXor2, 52, j12);
                long j13 = j11 + jRotlXor;
                long jRotlXor4 = ThreefishEngine.rotlXor(jRotlXor, 57, j13);
                long j14 = j12 + jRotlXor4;
                long jRotlXor5 = ThreefishEngine.rotlXor(jRotlXor4, 23, j14);
                long j15 = j13 + jRotlXor3;
                long jRotlXor6 = ThreefishEngine.rotlXor(jRotlXor3, 40, j15);
                long j16 = j14 + jRotlXor6;
                long jRotlXor7 = ThreefishEngine.rotlXor(jRotlXor6, 5, j16);
                long j17 = j15 + jRotlXor5;
                long jRotlXor8 = ThreefishEngine.rotlXor(jRotlXor5, 37, j17);
                long j18 = j16 + jArr3[i3];
                int i5 = i3 + 1;
                long j19 = jRotlXor8 + jArr3[i5] + jArr4[i4];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j20 = j17 + jArr3[i6] + jArr4[i7];
                int i8 = i3 + 3;
                int[] iArr3 = iArr;
                long j21 = i2;
                long j22 = jRotlXor7 + jArr3[i8] + j21;
                long j23 = j18 + j19;
                long jRotlXor9 = ThreefishEngine.rotlXor(j19, 25, j23);
                long j24 = j20 + j22;
                long jRotlXor10 = ThreefishEngine.rotlXor(j22, 33, j24);
                long j25 = j23 + jRotlXor10;
                long jRotlXor11 = ThreefishEngine.rotlXor(jRotlXor10, 46, j25);
                long j26 = j24 + jRotlXor9;
                long jRotlXor12 = ThreefishEngine.rotlXor(jRotlXor9, 12, j26);
                long j27 = j25 + jRotlXor12;
                long jRotlXor13 = ThreefishEngine.rotlXor(jRotlXor12, 58, j27);
                long j28 = j26 + jRotlXor11;
                long jRotlXor14 = ThreefishEngine.rotlXor(jRotlXor11, 22, j28);
                long j29 = j27 + jRotlXor14;
                long jRotlXor15 = ThreefishEngine.rotlXor(jRotlXor14, 32, j29);
                long j30 = j28 + jRotlXor13;
                long jRotlXor16 = ThreefishEngine.rotlXor(jRotlXor13, 32, j30);
                j6 = j29 + jArr3[i5];
                j7 = jRotlXor16 + jArr3[i6] + jArr4[i7];
                j8 = j30 + jArr3[i8] + jArr4[i4 + 2];
                j9 = jRotlXor15 + jArr3[i3 + 4] + j21 + 1;
                i2 += 2;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            jArr2[0] = j6;
            jArr2[1] = j7;
            jArr2[2] = j8;
            jArr2[3] = j9;
        }
    }

    public static final class Threefish512Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 46;
        private static final int ROTATION_0_1 = 36;
        private static final int ROTATION_0_2 = 19;
        private static final int ROTATION_0_3 = 37;
        private static final int ROTATION_1_0 = 33;
        private static final int ROTATION_1_1 = 27;
        private static final int ROTATION_1_2 = 14;
        private static final int ROTATION_1_3 = 42;
        private static final int ROTATION_2_0 = 17;
        private static final int ROTATION_2_1 = 49;
        private static final int ROTATION_2_2 = 36;
        private static final int ROTATION_2_3 = 39;
        private static final int ROTATION_3_0 = 44;
        private static final int ROTATION_3_1 = 9;
        private static final int ROTATION_3_2 = 54;
        private static final int ROTATION_3_3 = 56;
        private static final int ROTATION_4_0 = 39;
        private static final int ROTATION_4_1 = 30;
        private static final int ROTATION_4_2 = 34;
        private static final int ROTATION_4_3 = 24;
        private static final int ROTATION_5_0 = 13;
        private static final int ROTATION_5_1 = 50;
        private static final int ROTATION_5_2 = 10;
        private static final int ROTATION_5_3 = 17;
        private static final int ROTATION_6_0 = 25;
        private static final int ROTATION_6_1 = 29;
        private static final int ROTATION_6_2 = 39;
        private static final int ROTATION_6_3 = 43;
        private static final int ROTATION_7_0 = 8;
        private static final int ROTATION_7_1 = 35;
        private static final int ROTATION_7_2 = 56;
        private static final int ROTATION_7_3 = 22;

        public Threefish512Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.spongycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.f7336t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long jXorRotr = jArr[3];
            long j5 = jArr[4];
            long jXorRotr2 = jArr[5];
            long j6 = jArr[6];
            long jXorRotr3 = jArr[7];
            int i2 = 17;
            for (int i3 = 1; i2 >= i3; i3 = 1) {
                int i4 = iArr[i2];
                int i5 = iArr2[i2];
                int i6 = i4 + 1;
                long j7 = j2 - jArr3[i6];
                int i7 = i4 + 2;
                long j8 = j3 - jArr3[i7];
                int i8 = i4 + 3;
                long j9 = j4 - jArr3[i8];
                int i9 = i4 + 4;
                long j10 = jXorRotr - jArr3[i9];
                int i10 = i4 + 5;
                long j11 = j5 - jArr3[i10];
                int i11 = i4 + 6;
                int i12 = i5 + 1;
                long j12 = jXorRotr2 - (jArr3[i11] + jArr4[i12]);
                int i13 = i4 + 7;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j13 = j6 - (jArr3[i13] + jArr4[i5 + 2]);
                long[] jArr5 = jArr3;
                long j14 = i2;
                long j15 = jXorRotr3 - ((jArr3[i4 + 8] + j14) + 1);
                long jXorRotr4 = ThreefishEngine.xorRotr(j8, 8, j13);
                long j16 = j13 - jXorRotr4;
                long jXorRotr5 = ThreefishEngine.xorRotr(j15, 35, j7);
                long j17 = j7 - jXorRotr5;
                long jXorRotr6 = ThreefishEngine.xorRotr(j12, 56, j9);
                long j18 = j9 - jXorRotr6;
                long jXorRotr7 = ThreefishEngine.xorRotr(j10, 22, j11);
                long j19 = j11 - jXorRotr7;
                long jXorRotr8 = ThreefishEngine.xorRotr(jXorRotr4, 25, j19);
                long j20 = j19 - jXorRotr8;
                long jXorRotr9 = ThreefishEngine.xorRotr(jXorRotr7, 29, j16);
                long j21 = j16 - jXorRotr9;
                long jXorRotr10 = ThreefishEngine.xorRotr(jXorRotr6, 39, j17);
                long j22 = j17 - jXorRotr10;
                long jXorRotr11 = ThreefishEngine.xorRotr(jXorRotr5, 43, j18);
                long j23 = j18 - jXorRotr11;
                long jXorRotr12 = ThreefishEngine.xorRotr(jXorRotr8, 13, j23);
                long j24 = j23 - jXorRotr12;
                long jXorRotr13 = ThreefishEngine.xorRotr(jXorRotr11, 50, j20);
                long j25 = j20 - jXorRotr13;
                long jXorRotr14 = ThreefishEngine.xorRotr(jXorRotr10, 10, j21);
                long j26 = j21 - jXorRotr14;
                long[] jArr6 = jArr4;
                long jXorRotr15 = ThreefishEngine.xorRotr(jXorRotr9, 17, j22);
                long j27 = j22 - jXorRotr15;
                long jXorRotr16 = ThreefishEngine.xorRotr(jXorRotr12, 39, j27);
                long jXorRotr17 = ThreefishEngine.xorRotr(jXorRotr15, 30, j24);
                long jXorRotr18 = ThreefishEngine.xorRotr(jXorRotr14, 34, j25);
                long j28 = j25 - jXorRotr18;
                long jXorRotr19 = ThreefishEngine.xorRotr(jXorRotr13, 24, j26);
                long j29 = (j27 - jXorRotr16) - jArr5[i4];
                long j30 = jXorRotr16 - jArr5[i6];
                long j31 = (j24 - jXorRotr17) - jArr5[i7];
                long j32 = jXorRotr17 - jArr5[i8];
                long j33 = j28 - jArr5[i9];
                long j34 = jXorRotr18 - (jArr5[i10] + jArr6[i5]);
                long j35 = (j26 - jXorRotr19) - (jArr5[i11] + jArr6[i12]);
                long j36 = jXorRotr19 - (jArr5[i13] + j14);
                long jXorRotr20 = ThreefishEngine.xorRotr(j30, 44, j35);
                long j37 = j35 - jXorRotr20;
                long jXorRotr21 = ThreefishEngine.xorRotr(j36, 9, j29);
                long j38 = j29 - jXorRotr21;
                long jXorRotr22 = ThreefishEngine.xorRotr(j34, 54, j31);
                long j39 = j31 - jXorRotr22;
                long jXorRotr23 = ThreefishEngine.xorRotr(j32, 56, j33);
                long j40 = j33 - jXorRotr23;
                long jXorRotr24 = ThreefishEngine.xorRotr(jXorRotr20, 17, j40);
                long j41 = j40 - jXorRotr24;
                long jXorRotr25 = ThreefishEngine.xorRotr(jXorRotr23, 49, j37);
                long j42 = j37 - jXorRotr25;
                long jXorRotr26 = ThreefishEngine.xorRotr(jXorRotr22, 36, j38);
                long j43 = j38 - jXorRotr26;
                long jXorRotr27 = ThreefishEngine.xorRotr(jXorRotr21, 39, j39);
                long j44 = j39 - jXorRotr27;
                long jXorRotr28 = ThreefishEngine.xorRotr(jXorRotr24, 33, j44);
                long j45 = j44 - jXorRotr28;
                long jXorRotr29 = ThreefishEngine.xorRotr(jXorRotr27, 27, j41);
                long j46 = j41 - jXorRotr29;
                long jXorRotr30 = ThreefishEngine.xorRotr(jXorRotr26, 14, j42);
                long j47 = j42 - jXorRotr30;
                long jXorRotr31 = ThreefishEngine.xorRotr(jXorRotr25, 42, j43);
                long j48 = j43 - jXorRotr31;
                long jXorRotr32 = ThreefishEngine.xorRotr(jXorRotr28, 46, j48);
                jXorRotr = ThreefishEngine.xorRotr(jXorRotr31, 36, j45);
                j4 = j45 - jXorRotr;
                jXorRotr2 = ThreefishEngine.xorRotr(jXorRotr30, 19, j46);
                jXorRotr3 = ThreefishEngine.xorRotr(jXorRotr29, 37, j47);
                j6 = j47 - jXorRotr3;
                i2 -= 2;
                j5 = j46 - jXorRotr2;
                j2 = j48 - jXorRotr32;
                j3 = jXorRotr32;
                iArr = iArr3;
                jArr4 = jArr6;
                iArr2 = iArr4;
                jArr3 = jArr5;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j49 = j2 - jArr7[0];
            long j50 = j3 - jArr7[1];
            long j51 = j4 - jArr7[2];
            long j52 = jXorRotr - jArr7[3];
            long j53 = j5 - jArr7[4];
            long j54 = jXorRotr2 - (jArr7[5] + jArr8[0]);
            long j55 = j6 - (jArr7[6] + jArr8[1]);
            long j56 = jXorRotr3 - jArr7[7];
            jArr2[0] = j49;
            jArr2[1] = j50;
            jArr2[2] = j51;
            jArr2[3] = j52;
            jArr2[4] = j53;
            jArr2[5] = j54;
            jArr2[6] = j55;
            jArr2[7] = j56;
        }

        @Override // org.spongycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.f7336t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            long j10 = j2 + jArr3[0];
            long j11 = j3 + jArr3[1];
            long j12 = j4 + jArr3[2];
            long j13 = j5 + jArr3[3];
            long j14 = j6 + jArr3[4];
            long j15 = j7 + jArr3[5] + jArr4[0];
            long j16 = j8 + jArr3[6] + jArr4[1];
            long j17 = j13;
            long j18 = j15;
            long j19 = j9 + jArr3[7];
            int i2 = 1;
            while (i2 < 18) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j20 = j10 + j11;
                long jRotlXor = ThreefishEngine.rotlXor(j11, 46, j20);
                long j21 = j12 + j17;
                long jRotlXor2 = ThreefishEngine.rotlXor(j17, 36, j21);
                long j22 = j18;
                int[] iArr3 = iArr;
                long j23 = j14 + j22;
                long jRotlXor3 = ThreefishEngine.rotlXor(j22, 19, j23);
                long[] jArr5 = jArr3;
                long j24 = j19;
                long j25 = j16 + j24;
                long jRotlXor4 = ThreefishEngine.rotlXor(j24, 37, j25);
                int i5 = i2;
                long j26 = j21 + jRotlXor;
                long jRotlXor5 = ThreefishEngine.rotlXor(jRotlXor, 33, j26);
                long j27 = j23 + jRotlXor4;
                long jRotlXor6 = ThreefishEngine.rotlXor(jRotlXor4, 27, j27);
                long j28 = j25 + jRotlXor3;
                long jRotlXor7 = ThreefishEngine.rotlXor(jRotlXor3, 14, j28);
                long j29 = j20 + jRotlXor2;
                long jRotlXor8 = ThreefishEngine.rotlXor(jRotlXor2, 42, j29);
                long j30 = j27 + jRotlXor5;
                long jRotlXor9 = ThreefishEngine.rotlXor(jRotlXor5, 17, j30);
                long j31 = j28 + jRotlXor8;
                long jRotlXor10 = ThreefishEngine.rotlXor(jRotlXor8, 49, j31);
                long j32 = j29 + jRotlXor7;
                long jRotlXor11 = ThreefishEngine.rotlXor(jRotlXor7, 36, j32);
                long j33 = j26 + jRotlXor6;
                long jRotlXor12 = ThreefishEngine.rotlXor(jRotlXor6, 39, j33);
                long j34 = j31 + jRotlXor9;
                long jRotlXor13 = ThreefishEngine.rotlXor(jRotlXor9, 44, j34);
                long j35 = j32 + jRotlXor12;
                long jRotlXor14 = ThreefishEngine.rotlXor(jRotlXor12, 9, j35);
                long j36 = j33 + jRotlXor11;
                long jRotlXor15 = ThreefishEngine.rotlXor(jRotlXor11, 54, j36);
                long j37 = j30 + jRotlXor10;
                long jRotlXor16 = ThreefishEngine.rotlXor(jRotlXor10, 56, j37);
                long j38 = j35 + jArr5[i3];
                int i6 = i3 + 1;
                long j39 = jRotlXor13 + jArr5[i6];
                int i7 = i3 + 2;
                long j40 = j36 + jArr5[i7];
                int i8 = i3 + 3;
                long j41 = jRotlXor16 + jArr5[i8];
                int i9 = i3 + 4;
                long j42 = j37 + jArr5[i9];
                int i10 = i3 + 5;
                long j43 = jRotlXor15 + jArr5[i10] + jArr4[i4];
                int i11 = i3 + 6;
                int i12 = i4 + 1;
                long j44 = j34 + jArr5[i11] + jArr4[i12];
                int i13 = i3 + 7;
                long j45 = i5;
                long j46 = jRotlXor14 + jArr5[i13] + j45;
                long j47 = j38 + j39;
                long jRotlXor17 = ThreefishEngine.rotlXor(j39, 39, j47);
                long j48 = j40 + j41;
                long jRotlXor18 = ThreefishEngine.rotlXor(j41, 30, j48);
                long j49 = j42 + j43;
                long jRotlXor19 = ThreefishEngine.rotlXor(j43, 34, j49);
                long j50 = j44 + j46;
                long jRotlXor20 = ThreefishEngine.rotlXor(j46, 24, j50);
                long j51 = j48 + jRotlXor17;
                long jRotlXor21 = ThreefishEngine.rotlXor(jRotlXor17, 13, j51);
                long j52 = j49 + jRotlXor20;
                long jRotlXor22 = ThreefishEngine.rotlXor(jRotlXor20, 50, j52);
                long j53 = j50 + jRotlXor19;
                long jRotlXor23 = ThreefishEngine.rotlXor(jRotlXor19, 10, j53);
                long j54 = j47 + jRotlXor18;
                long jRotlXor24 = ThreefishEngine.rotlXor(jRotlXor18, 17, j54);
                long j55 = j52 + jRotlXor21;
                long jRotlXor25 = ThreefishEngine.rotlXor(jRotlXor21, 25, j55);
                long j56 = j53 + jRotlXor24;
                long jRotlXor26 = ThreefishEngine.rotlXor(jRotlXor24, 29, j56);
                long j57 = j54 + jRotlXor23;
                long jRotlXor27 = ThreefishEngine.rotlXor(jRotlXor23, 39, j57);
                long j58 = j51 + jRotlXor22;
                long[] jArr6 = jArr4;
                long jRotlXor28 = ThreefishEngine.rotlXor(jRotlXor22, 43, j58);
                long j59 = j56 + jRotlXor25;
                long jRotlXor29 = ThreefishEngine.rotlXor(jRotlXor25, 8, j59);
                long j60 = j57 + jRotlXor28;
                long jRotlXor30 = ThreefishEngine.rotlXor(jRotlXor28, 35, j60);
                long j61 = j58 + jRotlXor27;
                long jRotlXor31 = ThreefishEngine.rotlXor(jRotlXor27, 56, j61);
                long j62 = j55 + jRotlXor26;
                long jRotlXor32 = ThreefishEngine.rotlXor(jRotlXor26, 22, j62);
                long j63 = j60 + jArr5[i6];
                j11 = jRotlXor29 + jArr5[i7];
                long j64 = j61 + jArr5[i8];
                j17 = jRotlXor32 + jArr5[i9];
                long j65 = j62 + jArr5[i10];
                j18 = jRotlXor31 + jArr5[i11] + jArr6[i12];
                long j66 = j59 + jArr5[i13] + jArr6[i4 + 2];
                long j67 = jRotlXor30 + jArr5[i3 + 8] + j45 + 1;
                j16 = j66;
                j14 = j65;
                jArr3 = jArr5;
                iArr = iArr3;
                jArr4 = jArr6;
                j19 = j67;
                j12 = j64;
                j10 = j63;
                i2 = i5 + 2;
                iArr2 = iArr2;
            }
            jArr2[0] = j10;
            jArr2[1] = j11;
            jArr2[2] = j12;
            jArr2[3] = j17;
            jArr2[4] = j14;
            jArr2[5] = j18;
            jArr2[6] = j16;
            jArr2[7] = j19;
        }
    }

    public static abstract class ThreefishCipher {
        protected final long[] kw;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        protected final long[] f7336t;

        public ThreefishCipher(long[] jArr, long[] jArr2) {
            this.kw = jArr;
            this.f7336t = jArr2;
        }

        public abstract void decryptBlock(long[] jArr, long[] jArr2);

        public abstract void encryptBlock(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = new int[80];
        MOD9 = iArr;
        MOD17 = new int[iArr.length];
        MOD5 = new int[iArr.length];
        MOD3 = new int[iArr.length];
        int i2 = 0;
        while (true) {
            int[] iArr2 = MOD9;
            if (i2 >= iArr2.length) {
                return;
            }
            MOD17[i2] = i2 % 17;
            iArr2[i2] = i2 % 9;
            MOD5[i2] = i2 % 5;
            MOD3[i2] = i2 % 3;
            i2++;
        }
    }

    public ThreefishEngine(int i2) {
        long[] jArr = new long[5];
        this.f7335t = jArr;
        int i3 = i2 / 8;
        this.blocksizeBytes = i3;
        int i4 = i3 / 8;
        this.blocksizeWords = i4;
        this.currentBlock = new long[i4];
        long[] jArr2 = new long[(i4 * 2) + 1];
        this.kw = jArr2;
        if (i2 == 256) {
            this.cipher = new Threefish256Cipher(jArr2, jArr);
        } else if (i2 == 512) {
            this.cipher = new Threefish512Cipher(jArr2, jArr);
        } else {
            if (i2 != 1024) {
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
            }
            this.cipher = new Threefish1024Cipher(jArr2, jArr);
        }
    }

    public static long bytesToWord(byte[] bArr, int i2) {
        if (i2 + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        long j2 = (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40);
        return ((((long) bArr[i2 + 7]) & 255) << 56) | j2 | ((((long) bArr[i2 + 6]) & 255) << 48);
    }

    public static long rotlXor(long j2, int i2, long j3) {
        return ((j2 >>> (-i2)) | (j2 << i2)) ^ j3;
    }

    private void setKey(long[] jArr) {
        if (jArr.length != this.blocksizeWords) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeWords + " words)");
        }
        long j2 = C_240;
        int i2 = 0;
        while (true) {
            int i3 = this.blocksizeWords;
            if (i2 >= i3) {
                long[] jArr2 = this.kw;
                jArr2[i3] = j2;
                System.arraycopy(jArr2, 0, jArr2, i3 + 1, i3);
                return;
            } else {
                long[] jArr3 = this.kw;
                long j3 = jArr[i2];
                jArr3[i2] = j3;
                j2 ^= j3;
                i2++;
            }
        }
    }

    private void setTweak(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.f7335t;
        long j2 = jArr[0];
        jArr2[0] = j2;
        long j3 = jArr[1];
        jArr2[1] = j3;
        jArr2[2] = j2 ^ j3;
        jArr2[3] = j2;
        jArr2[4] = j3;
    }

    public static void wordToBytes(long j2, byte[] bArr, int i2) {
        if (i2 + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        bArr[i2] = (byte) j2;
        bArr[i2 + 1] = (byte) (j2 >> 8);
        bArr[i2 + 2] = (byte) (j2 >> 16);
        bArr[i2 + 3] = (byte) (j2 >> 24);
        bArr[i2 + 4] = (byte) (j2 >> 32);
        bArr[i2 + 5] = (byte) (j2 >> 40);
        bArr[i2 + 6] = (byte) (j2 >> 48);
        bArr[i2 + 7] = (byte) (j2 >> 56);
    }

    public static long xorRotr(long j2, int i2, long j3) {
        long j4 = j2 ^ j3;
        return (j4 << (-i2)) | (j4 >>> i2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Threefish-" + (this.blocksizeBytes * 8);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blocksizeBytes;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        byte[] key;
        byte[] tweak;
        long[] jArr;
        long[] jArr2 = null;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            key = tweakableBlockCipherParameters.getKey().getKey();
            tweak = tweakableBlockCipherParameters.getTweak();
        } else {
            if (!(cipherParameters instanceof KeyParameter)) {
                throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
            }
            key = ((KeyParameter) cipherParameters).getKey();
            tweak = null;
        }
        if (key == null) {
            jArr = null;
        } else {
            if (key.length != this.blocksizeBytes) {
                throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeBytes + " bytes)");
            }
            int i2 = this.blocksizeWords;
            jArr = new long[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                jArr[i3] = bytesToWord(key, i3 * 8);
            }
        }
        if (tweak != null) {
            if (tweak.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[]{bytesToWord(tweak, 0), bytesToWord(tweak, 8)};
        }
        init(z2, jArr, jArr2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = this.blocksizeBytes;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.blocksizeBytes; i6 += 8) {
            this.currentBlock[i6 >> 3] = bytesToWord(bArr, i2 + i6);
        }
        long[] jArr = this.currentBlock;
        processBlock(jArr, jArr);
        while (true) {
            int i7 = this.blocksizeBytes;
            if (i5 >= i7) {
                return i7;
            }
            wordToBytes(this.currentBlock[i5 >> 3], bArr2, i3 + i5);
            i5 += 8;
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    public int processBlock(long[] jArr, long[] jArr2) {
        long[] jArr3 = this.kw;
        int i2 = this.blocksizeWords;
        if (jArr3[i2] != 0) {
            if (jArr.length == i2) {
                if (jArr2.length == i2) {
                    if (this.forEncryption) {
                        this.cipher.encryptBlock(jArr, jArr2);
                    } else {
                        this.cipher.decryptBlock(jArr, jArr2);
                    }
                    return this.blocksizeWords;
                }
                throw new OutputLengthException("Output buffer too short");
            }
            throw new DataLengthException("Input buffer too short");
        }
        throw new IllegalStateException("Threefish engine not initialised");
    }

    public void init(boolean z2, long[] jArr, long[] jArr2) {
        this.forEncryption = z2;
        if (jArr != null) {
            setKey(jArr);
        }
        if (jArr2 != null) {
            setTweak(jArr2);
        }
    }
}
