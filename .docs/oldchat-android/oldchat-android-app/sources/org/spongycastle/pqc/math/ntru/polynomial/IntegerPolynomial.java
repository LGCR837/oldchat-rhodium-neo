package org.spongycastle.pqc.math.ntru.polynomial;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.util.ArrayEncoder;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class IntegerPolynomial implements Polynomial {
    private static final int NUM_EQUAL_RESULTANTS = 3;
    public int[] coeffs;
    private static final int[] PRIMES = {4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693, 5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207, 7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017, 8039, 8053, 8059, 8069, 8081, 8087, 8089, 8093, 8101, 8111, 8117, 8123, 8147, 8161, 8167, 8171, 8179, 8191, 8209, 8219, 8221, 8231, 8233, 8237, 8243, 8263, 8269, 8273, 8287, 8291, 8293, 8297, 8311, 8317, 8329, 8353, 8363, 8369, 8377, 8387, 8389, 8419, 8423, 8429, 8431, 8443, 8447, 8461, 8467, 8501, 8513, 8521, 8527, 8537, 8539, 8543, 8563, 8573, 8581, 8597, 8599, 8609, 8623, 8627, 8629, 8641, 8647, 8663, 8669, 8677, 8681, 8689, 8693, 8699, 8707, 8713, 8719, 8731, 8737, 8741, 8747, 8753, 8761, 8779, 8783, 8803, 8807, 8819, 8821, 8831, 8837, 8839, 8849, 8861, 8863, 8867, 8887, 8893, 8923, 8929, 8933, 8941, 8951, 8963, 8969, 8971, 8999, 9001, 9007, 9011, 9013, 9029, 9041, 9043, 9049, 9059, 9067, 9091, 9103, 9109, 9127, 9133, 9137, 9151, 9157, 9161, 9173, 9181, 9187, 9199, 9203, 9209, 9221, 9227, 9239, 9241, 9257, 9277, 9281, 9283, 9293, 9311, 9319, 9323, 9337, 9341, 9343, 9349, 9371, 9377, 9391, 9397, 9403, 9413, 9419, 9421, 9431, 9433, 9437, 9439, 9461, 9463, 9467, 9473, 9479, 9491, 9497, 9511, 9521, 9533, 9539, 9547, 9551, 9587, 9601, 9613, 9619, 9623, 9629, 9631, 9643, 9649, 9661, 9677, 9679, 9689, 9697, 9719, 9721, 9733, 9739, 9743, 9749, 9767, 9769, 9781, 9787, 9791, 9803, 9811, 9817, 9829, 9833, 9839, 9851, 9857, 9859, 9871, 9883, 9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973};
    private static final List BIGINT_PRIMES = new ArrayList();

    public class CombineTask implements Callable<ModularResultant> {
        private ModularResultant modRes1;
        private ModularResultant modRes2;

        private CombineTask(ModularResultant modularResultant, ModularResultant modularResultant2) {
            this.modRes1 = modularResultant;
            this.modRes2 = modularResultant2;
        }

        @Override // java.util.concurrent.Callable
        public ModularResultant call() {
            return ModularResultant.combineRho(this.modRes1, this.modRes2);
        }
    }

    public class ModResultantTask implements Callable<ModularResultant> {
        private int modulus;

        private ModResultantTask(int i2) {
            this.modulus = i2;
        }

        @Override // java.util.concurrent.Callable
        public ModularResultant call() {
            return IntegerPolynomial.this.resultant(this.modulus);
        }
    }

    public class PrimeGenerator {
        private int index;
        private BigInteger prime;

        private PrimeGenerator() {
            this.index = 0;
        }

        public BigInteger nextPrime() {
            if (this.index < IntegerPolynomial.BIGINT_PRIMES.size()) {
                List list = IntegerPolynomial.BIGINT_PRIMES;
                int i2 = this.index;
                this.index = i2 + 1;
                this.prime = (BigInteger) list.get(i2);
            } else {
                this.prime = this.prime.nextProbablePrime();
            }
            return this.prime;
        }
    }

    static {
        int i2 = 0;
        while (true) {
            if (i2 == PRIMES.length) {
                return;
            }
            BIGINT_PRIMES.add(BigInteger.valueOf(r1[i2]));
            i2++;
        }
    }

    public IntegerPolynomial(int i2) {
        this.coeffs = new int[i2];
    }

    private boolean equalsAbsOne() {
        int i2 = 1;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return Math.abs(iArr[0]) == 1;
            }
            if (iArr[i2] != 0) {
                return false;
            }
            i2++;
        }
    }

    private boolean equalsZero() {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return true;
            }
            if (iArr[i2] != 0) {
                return false;
            }
            i2++;
        }
    }

    public static IntegerPolynomial fromBinary(byte[] bArr, int i2, int i3) {
        return new IntegerPolynomial(ArrayEncoder.decodeModQ(bArr, i2, i3));
    }

    public static IntegerPolynomial fromBinary3Sves(byte[] bArr, int i2) {
        return new IntegerPolynomial(ArrayEncoder.decodeMod3Sves(bArr, i2));
    }

    public static IntegerPolynomial fromBinary3Tight(byte[] bArr, int i2) {
        return new IntegerPolynomial(ArrayEncoder.decodeMod3Tight(bArr, i2));
    }

    private IntegerPolynomial mod2ToModq(IntegerPolynomial integerPolynomial, int i2) {
        int i3 = 2;
        if (!Util.is64BitJVM() || i2 != 2048) {
            while (i3 < i2) {
                i3 *= 2;
                int[] iArr = integerPolynomial.coeffs;
                IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(Arrays.copyOf(iArr, iArr.length));
                integerPolynomial2.mult2(i3);
                integerPolynomial2.sub(mult(integerPolynomial, i3).mult(integerPolynomial, i3), i3);
                integerPolynomial = integerPolynomial2;
            }
            return integerPolynomial;
        }
        LongPolynomial2 longPolynomial2 = new LongPolynomial2(this);
        LongPolynomial2 longPolynomial22 = new LongPolynomial2(integerPolynomial);
        while (i3 < i2) {
            i3 *= 2;
            LongPolynomial2 longPolynomial23 = (LongPolynomial2) longPolynomial22.clone();
            int i4 = i3 - 1;
            longPolynomial23.mult2And(i4);
            longPolynomial23.subAnd(longPolynomial2.mult(longPolynomial22).mult(longPolynomial22), i4);
            longPolynomial22 = longPolynomial23;
        }
        return longPolynomial22.toIntegerPolynomial();
    }

    private void mult2(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 >= iArr.length) {
                return;
            }
            int i4 = iArr[i3] * 2;
            iArr[i3] = i4;
            iArr[i3] = i4 % i2;
            i3++;
        }
    }

    private IntegerPolynomial multRecursive(IntegerPolynomial integerPolynomial) {
        int[] iArr = this.coeffs;
        int[] iArr2 = integerPolynomial.coeffs;
        int length = iArr2.length;
        int i2 = 0;
        if (length <= 32) {
            int i3 = (length * 2) - 1;
            IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(new int[i3]);
            for (int i4 = 0; i4 < i3; i4++) {
                for (int iMax = Math.max(0, (i4 - length) + 1); iMax <= Math.min(i4, length - 1); iMax++) {
                    int[] iArr3 = integerPolynomial2.coeffs;
                    iArr3[i4] = iArr3[i4] + (iArr2[iMax] * iArr[i4 - iMax]);
                }
            }
            return integerPolynomial2;
        }
        int i5 = length / 2;
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(Arrays.copyOf(iArr, i5));
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(Arrays.copyOfRange(iArr, i5, length));
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(Arrays.copyOf(iArr2, i5));
        IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(Arrays.copyOfRange(iArr2, i5, length));
        IntegerPolynomial integerPolynomial7 = (IntegerPolynomial) integerPolynomial3.clone();
        integerPolynomial7.add(integerPolynomial4);
        IntegerPolynomial integerPolynomial8 = (IntegerPolynomial) integerPolynomial5.clone();
        integerPolynomial8.add(integerPolynomial6);
        IntegerPolynomial integerPolynomialMultRecursive = integerPolynomial3.multRecursive(integerPolynomial5);
        IntegerPolynomial integerPolynomialMultRecursive2 = integerPolynomial4.multRecursive(integerPolynomial6);
        IntegerPolynomial integerPolynomialMultRecursive3 = integerPolynomial7.multRecursive(integerPolynomial8);
        integerPolynomialMultRecursive3.sub(integerPolynomialMultRecursive);
        integerPolynomialMultRecursive3.sub(integerPolynomialMultRecursive2);
        IntegerPolynomial integerPolynomial9 = new IntegerPolynomial((length * 2) - 1);
        int i6 = 0;
        while (true) {
            int[] iArr4 = integerPolynomialMultRecursive.coeffs;
            if (i6 >= iArr4.length) {
                break;
            }
            integerPolynomial9.coeffs[i6] = iArr4[i6];
            i6++;
        }
        int i7 = 0;
        while (true) {
            int[] iArr5 = integerPolynomialMultRecursive3.coeffs;
            if (i7 >= iArr5.length) {
                break;
            }
            int[] iArr6 = integerPolynomial9.coeffs;
            int i8 = i5 + i7;
            iArr6[i8] = iArr6[i8] + iArr5[i7];
            i7++;
        }
        while (true) {
            int[] iArr7 = integerPolynomialMultRecursive2.coeffs;
            if (i2 >= iArr7.length) {
                return integerPolynomial9;
            }
            int[] iArr8 = integerPolynomial9.coeffs;
            int i9 = (i5 * 2) + i2;
            iArr8[i9] = iArr8[i9] + iArr7[i2];
            i2++;
        }
    }

    private void multShiftSub(IntegerPolynomial integerPolynomial, int i2, int i3, int i4) {
        int length = this.coeffs.length;
        for (int i5 = i3; i5 < length; i5++) {
            int[] iArr = this.coeffs;
            iArr[i5] = (iArr[i5] - (integerPolynomial.coeffs[i5 - i3] * i2)) % i4;
        }
    }

    private void sort(int[] iArr) {
        boolean z2;
        for (boolean z3 = true; z3; z3 = z2) {
            int i2 = 0;
            z2 = false;
            while (i2 != iArr.length - 1) {
                int i3 = iArr[i2];
                int i4 = i2 + 1;
                int i5 = iArr[i4];
                if (i3 > i5) {
                    iArr[i2] = i5;
                    iArr[i4] = i3;
                    z2 = true;
                }
                i2 = i4;
            }
        }
    }

    private BigInteger squareSum() {
        BigInteger bigIntegerAdd = Constants.BIGINT_ZERO;
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return bigIntegerAdd;
            }
            int i3 = iArr[i2];
            bigIntegerAdd = bigIntegerAdd.add(BigInteger.valueOf(i3 * i3));
            i2++;
        }
    }

    public void add(IntegerPolynomial integerPolynomial, int i2) {
        add(integerPolynomial);
        mod(i2);
    }

    public void center0(int i2) {
        for (int i3 = 0; i3 < this.coeffs.length; i3++) {
            while (true) {
                int[] iArr = this.coeffs;
                int i4 = iArr[i3];
                if (i4 >= (-i2) / 2) {
                    break;
                } else {
                    iArr[i3] = i4 + i2;
                }
            }
            while (true) {
                int[] iArr2 = this.coeffs;
                int i5 = iArr2[i3];
                if (i5 > i2 / 2) {
                    iArr2[i3] = i5 - i2;
                }
            }
        }
    }

    public long centeredNormSq(int i2) {
        int length = this.coeffs.length;
        IntegerPolynomial integerPolynomial = (IntegerPolynomial) clone();
        integerPolynomial.shiftGap(i2);
        long j2 = 0;
        int i3 = 0;
        long j3 = 0;
        while (true) {
            int[] iArr = integerPolynomial.coeffs;
            if (i3 == iArr.length) {
                return j2 - ((j3 * j3) / ((long) length));
            }
            int i4 = iArr[i3];
            j3 += (long) i4;
            j2 += (long) (i4 * i4);
            i3++;
        }
    }

    public void clear() {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public Object clone() {
        return new IntegerPolynomial((int[]) this.coeffs.clone());
    }

    public int count(int i2) {
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 == iArr.length) {
                return i4;
            }
            if (iArr[i3] == i2) {
                i4++;
            }
            i3++;
        }
    }

    public int degree() {
        int length = this.coeffs.length - 1;
        while (length > 0 && this.coeffs[length] == 0) {
            length--;
        }
        return length;
    }

    public void div(int i2) {
        int i3 = (i2 + 1) / 2;
        int i4 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i4 >= iArr.length) {
                return;
            }
            int i5 = iArr[i4];
            int i6 = i5 + (i5 > 0 ? i3 : -i3);
            iArr[i4] = i6;
            iArr[i4] = i6 / i2;
            i4++;
        }
    }

    public void ensurePositive(int i2) {
        for (int i3 = 0; i3 < this.coeffs.length; i3++) {
            while (true) {
                int[] iArr = this.coeffs;
                int i4 = iArr[i3];
                if (i4 < 0) {
                    iArr[i3] = i4 + i2;
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof IntegerPolynomial) {
            return Arrays.areEqual(this.coeffs, ((IntegerPolynomial) obj).coeffs);
        }
        return false;
    }

    public boolean equalsOne() {
        int i2 = 1;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return iArr[0] == 1;
            }
            if (iArr[i2] != 0) {
                return false;
            }
            i2++;
        }
    }

    public IntegerPolynomial invertF3() {
        int length = this.coeffs.length;
        int i2 = length + 1;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i2);
        integerPolynomial.coeffs[0] = 1;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(i2);
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(i2);
        integerPolynomial3.coeffs = Arrays.copyOf(this.coeffs, i2);
        integerPolynomial3.modPositive(3);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(i2);
        int[] iArr = integerPolynomial4.coeffs;
        iArr[0] = -1;
        iArr[length] = 1;
        int i3 = 0;
        while (true) {
            if (integerPolynomial3.coeffs[0] == 0) {
                for (int i4 = 1; i4 <= length; i4++) {
                    int[] iArr2 = integerPolynomial3.coeffs;
                    iArr2[i4 - 1] = iArr2[i4];
                    int[] iArr3 = integerPolynomial2.coeffs;
                    iArr3[i2 - i4] = iArr3[length - i4];
                }
                integerPolynomial3.coeffs[length] = 0;
                integerPolynomial2.coeffs[0] = 0;
                i3++;
                if (integerPolynomial3.equalsZero()) {
                    return null;
                }
            } else {
                if (integerPolynomial3.equalsAbsOne()) {
                    if (integerPolynomial.coeffs[length] != 0) {
                        return null;
                    }
                    IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
                    int i5 = i3 % length;
                    for (int i6 = length - 1; i6 >= 0; i6--) {
                        int i7 = i6 - i5;
                        if (i7 < 0) {
                            i7 += length;
                        }
                        integerPolynomial5.coeffs[i7] = integerPolynomial3.coeffs[0] * integerPolynomial.coeffs[i6];
                    }
                    integerPolynomial5.ensurePositive(3);
                    return integerPolynomial5;
                }
                if (integerPolynomial3.degree() < integerPolynomial4.degree()) {
                    IntegerPolynomial integerPolynomial6 = integerPolynomial2;
                    integerPolynomial2 = integerPolynomial;
                    integerPolynomial = integerPolynomial6;
                    IntegerPolynomial integerPolynomial7 = integerPolynomial4;
                    integerPolynomial4 = integerPolynomial3;
                    integerPolynomial3 = integerPolynomial7;
                }
                if (integerPolynomial3.coeffs[0] == integerPolynomial4.coeffs[0]) {
                    integerPolynomial3.sub(integerPolynomial4, 3);
                    integerPolynomial.sub(integerPolynomial2, 3);
                } else {
                    integerPolynomial3.add(integerPolynomial4, 3);
                    integerPolynomial.add(integerPolynomial2, 3);
                }
            }
        }
    }

    public IntegerPolynomial invertFq(int i2) {
        int length = this.coeffs.length;
        int i3 = length + 1;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i3);
        integerPolynomial.coeffs[0] = 1;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(i3);
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(i3);
        integerPolynomial3.coeffs = Arrays.copyOf(this.coeffs, i3);
        integerPolynomial3.modPositive(2);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(i3);
        int[] iArr = integerPolynomial4.coeffs;
        iArr[0] = 1;
        iArr[length] = 1;
        int i4 = 0;
        while (true) {
            if (integerPolynomial3.coeffs[0] == 0) {
                for (int i5 = 1; i5 <= length; i5++) {
                    int[] iArr2 = integerPolynomial3.coeffs;
                    iArr2[i5 - 1] = iArr2[i5];
                    int[] iArr3 = integerPolynomial2.coeffs;
                    iArr3[i3 - i5] = iArr3[length - i5];
                }
                integerPolynomial3.coeffs[length] = 0;
                integerPolynomial2.coeffs[0] = 0;
                i4++;
                if (integerPolynomial3.equalsZero()) {
                    return null;
                }
            } else {
                if (integerPolynomial3.equalsOne()) {
                    if (integerPolynomial.coeffs[length] != 0) {
                        return null;
                    }
                    IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
                    int i6 = i4 % length;
                    for (int i7 = length - 1; i7 >= 0; i7--) {
                        int i8 = i7 - i6;
                        if (i8 < 0) {
                            i8 += length;
                        }
                        integerPolynomial5.coeffs[i8] = integerPolynomial.coeffs[i7];
                    }
                    return mod2ToModq(integerPolynomial5, i2);
                }
                if (integerPolynomial3.degree() < integerPolynomial4.degree()) {
                    IntegerPolynomial integerPolynomial6 = integerPolynomial2;
                    integerPolynomial2 = integerPolynomial;
                    integerPolynomial = integerPolynomial6;
                    IntegerPolynomial integerPolynomial7 = integerPolynomial4;
                    integerPolynomial4 = integerPolynomial3;
                    integerPolynomial3 = integerPolynomial7;
                }
                integerPolynomial3.add(integerPolynomial4, 2);
                integerPolynomial.add(integerPolynomial2, 2);
            }
        }
    }

    public void mod(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 >= iArr.length) {
                return;
            }
            iArr[i3] = iArr[i3] % i2;
            i3++;
        }
    }

    public void mod3() {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return;
            }
            int i3 = iArr[i2] % 3;
            iArr[i2] = i3;
            if (i3 > 1) {
                iArr[i2] = i3 - 3;
            }
            int i4 = iArr[i2];
            if (i4 < -1) {
                iArr[i2] = i4 + 3;
            }
            i2++;
        }
    }

    public void modCenter(int i2) {
        int i3;
        mod(i2);
        for (int i4 = 0; i4 < this.coeffs.length; i4++) {
            while (true) {
                int[] iArr = this.coeffs;
                int i5 = iArr[i4];
                i3 = i2 / 2;
                if (i5 >= i3) {
                    break;
                } else {
                    iArr[i4] = i5 + i2;
                }
            }
            while (true) {
                int[] iArr2 = this.coeffs;
                int i6 = iArr2[i4];
                if (i6 >= i3) {
                    iArr2[i4] = i6 - i2;
                }
            }
        }
    }

    public void modPositive(int i2) {
        mod(i2);
        ensurePositive(i2);
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i2) {
        IntegerPolynomial integerPolynomialMult = mult(integerPolynomial);
        integerPolynomialMult.mod(i2);
        return integerPolynomialMult;
    }

    public void mult3(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 >= iArr.length) {
                return;
            }
            int i4 = iArr[i3] * 3;
            iArr[i3] = i4;
            iArr[i3] = i4 % i2;
            i3++;
        }
    }

    public Resultant resultant() {
        BigInteger bigIntegerMultiply;
        BigInteger bigIntegerAdd;
        int length = this.coeffs.length;
        LinkedList linkedList = new LinkedList();
        BigInteger bigInteger = Constants.BIGINT_ONE;
        PrimeGenerator primeGenerator = new PrimeGenerator();
        BigInteger bigInteger2 = bigInteger;
        int i2 = 1;
        while (true) {
            BigInteger bigIntegerNextPrime = primeGenerator.nextPrime();
            ModularResultant modularResultantResultant = resultant(bigIntegerNextPrime.intValue());
            linkedList.add(modularResultantResultant);
            bigIntegerMultiply = bigInteger.multiply(bigIntegerNextPrime);
            BigIntEuclidean bigIntEuclideanCalculate = BigIntEuclidean.calculate(bigIntegerNextPrime, bigInteger);
            BigInteger bigIntegerMod = bigInteger2.multiply(bigIntEuclideanCalculate.f7691x.multiply(bigIntegerNextPrime)).add(modularResultantResultant.res.multiply(bigIntEuclideanCalculate.f7692y.multiply(bigInteger))).mod(bigIntegerMultiply);
            BigInteger bigIntegerDivide = bigIntegerMultiply.divide(BigInteger.valueOf(2L));
            BigInteger bigIntegerNegate = bigIntegerDivide.negate();
            if (bigIntegerMod.compareTo(bigIntegerDivide) > 0) {
                bigIntegerAdd = bigIntegerMod.subtract(bigIntegerMultiply);
            } else {
                int iCompareTo = bigIntegerMod.compareTo(bigIntegerNegate);
                bigIntegerAdd = bigIntegerMod;
                if (iCompareTo < 0) {
                    bigIntegerAdd = bigIntegerMod.add(bigIntegerMultiply);
                }
            }
            if (bigIntegerAdd.equals(bigInteger2)) {
                i2++;
                if (i2 >= 3) {
                    break;
                }
            } else {
                i2 = 1;
            }
            bigInteger2 = bigIntegerAdd;
            bigInteger = bigIntegerMultiply;
        }
        while (linkedList.size() > 1) {
            linkedList.addLast(ModularResultant.combineRho((ModularResultant) linkedList.removeFirst(), (ModularResultant) linkedList.removeFirst()));
        }
        BigIntPolynomial bigIntPolynomial = ((ModularResultant) linkedList.getFirst()).rho;
        BigInteger bigIntegerDivide2 = bigIntegerMultiply.divide(BigInteger.valueOf(2L));
        BigInteger bigIntegerNegate2 = bigIntegerDivide2.negate();
        int iCompareTo2 = bigIntegerAdd.compareTo(bigIntegerDivide2);
        BigInteger bigIntegerSubtract = bigIntegerAdd;
        if (iCompareTo2 > 0) {
            bigIntegerSubtract = bigIntegerAdd.subtract(bigIntegerMultiply);
        }
        int iCompareTo3 = bigIntegerSubtract.compareTo(bigIntegerNegate2);
        BigInteger bigIntegerAdd2 = bigIntegerSubtract;
        if (iCompareTo3 < 0) {
            bigIntegerAdd2 = bigIntegerSubtract.add(bigIntegerMultiply);
        }
        for (int i3 = 0; i3 < length; i3++) {
            BigInteger bigInteger3 = bigIntPolynomial.coeffs[i3];
            if (bigInteger3.compareTo(bigIntegerDivide2) > 0) {
                bigIntPolynomial.coeffs[i3] = bigInteger3.subtract(bigIntegerMultiply);
            }
            if (bigInteger3.compareTo(bigIntegerNegate2) < 0) {
                bigIntPolynomial.coeffs[i3] = bigInteger3.add(bigIntegerMultiply);
            }
        }
        return new Resultant(bigIntPolynomial, bigIntegerAdd2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x008d, code lost:
    
        r10 = (org.spongycastle.pqc.math.ntru.polynomial.ModularResultant) r1.get();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.spongycastle.pqc.math.ntru.polynomial.Resultant resultantMultiThread() {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial.resultantMultiThread():org.spongycastle.pqc.math.ntru.polynomial.Resultant");
    }

    public void rotate1() {
        int[] iArr = this.coeffs;
        int i2 = iArr[iArr.length - 1];
        for (int length = iArr.length - 1; length > 0; length--) {
            int[] iArr2 = this.coeffs;
            iArr2[length] = iArr2[length - 1];
        }
        this.coeffs[0] = i2;
    }

    public void shiftGap(int i2) {
        modCenter(i2);
        int[] iArrClone = Arrays.clone(this.coeffs);
        sort(iArrClone);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < iArrClone.length - 1) {
            int i6 = i3 + 1;
            int i7 = iArrClone[i6];
            int i8 = iArrClone[i3];
            int i9 = i7 - i8;
            if (i9 > i4) {
                i5 = i8;
                i4 = i9;
            }
            i3 = i6;
        }
        int i10 = iArrClone[0];
        int i11 = iArrClone[iArrClone.length - 1];
        sub((i2 - i11) + i10 > i4 ? (i11 + i10) / 2 : i5 + (i4 / 2) + (i2 / 2));
    }

    public void sub(IntegerPolynomial integerPolynomial, int i2) {
        sub(integerPolynomial);
        mod(i2);
    }

    public int sumCoeffs() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 >= iArr.length) {
                return i3;
            }
            i3 += iArr[i2];
            i2++;
        }
    }

    public byte[] toBinary(int i2) {
        return ArrayEncoder.encodeModQ(this.coeffs, i2);
    }

    public byte[] toBinary3Sves() {
        return ArrayEncoder.encodeMod3Sves(this.coeffs);
    }

    public byte[] toBinary3Tight() {
        BigInteger bigIntegerAdd = Constants.BIGINT_ZERO;
        for (int length = this.coeffs.length - 1; length >= 0; length--) {
            bigIntegerAdd = bigIntegerAdd.multiply(BigInteger.valueOf(3L)).add(BigInteger.valueOf(this.coeffs[length] + 1));
        }
        int iBitLength = (BigInteger.valueOf(3L).pow(this.coeffs.length).bitLength() + 7) / 8;
        byte[] byteArray = bigIntegerAdd.toByteArray();
        if (byteArray.length >= iBitLength) {
            return byteArray.length > iBitLength ? Arrays.copyOfRange(byteArray, 1, byteArray.length) : byteArray;
        }
        byte[] bArr = new byte[iBitLength];
        System.arraycopy(byteArray, 0, bArr, iBitLength - byteArray.length, byteArray.length);
        return bArr;
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial toIntegerPolynomial() {
        return (IntegerPolynomial) clone();
    }

    public static IntegerPolynomial fromBinary(InputStream inputStream, int i2, int i3) {
        return new IntegerPolynomial(ArrayEncoder.decodeModQ(inputStream, i2, i3));
    }

    public static IntegerPolynomial fromBinary3Tight(InputStream inputStream, int i2) {
        return new IntegerPolynomial(ArrayEncoder.decodeMod3Tight(inputStream, i2));
    }

    public IntegerPolynomial(int[] iArr) {
        this.coeffs = iArr;
    }

    public void add(IntegerPolynomial integerPolynomial) {
        int[] iArr = integerPolynomial.coeffs;
        int length = iArr.length;
        int[] iArr2 = this.coeffs;
        if (length > iArr2.length) {
            this.coeffs = Arrays.copyOf(iArr2, iArr.length);
        }
        int i2 = 0;
        while (true) {
            int[] iArr3 = integerPolynomial.coeffs;
            if (i2 >= iArr3.length) {
                return;
            }
            int[] iArr4 = this.coeffs;
            iArr4[i2] = iArr4[i2] + iArr3[i2];
            i2++;
        }
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial) {
        int[] iArr;
        int length = this.coeffs.length;
        if (integerPolynomial.coeffs.length == length) {
            IntegerPolynomial integerPolynomialMultRecursive = multRecursive(integerPolynomial);
            if (integerPolynomialMultRecursive.coeffs.length > length) {
                int i2 = length;
                while (true) {
                    iArr = integerPolynomialMultRecursive.coeffs;
                    if (i2 >= iArr.length) {
                        break;
                    }
                    int i3 = i2 - length;
                    iArr[i3] = iArr[i3] + iArr[i2];
                    i2++;
                }
                integerPolynomialMultRecursive.coeffs = Arrays.copyOf(iArr, length);
            }
            return integerPolynomialMultRecursive;
        }
        throw new IllegalArgumentException("Number of coefficients must be the same");
    }

    public void sub(IntegerPolynomial integerPolynomial) {
        int[] iArr = integerPolynomial.coeffs;
        int length = iArr.length;
        int[] iArr2 = this.coeffs;
        if (length > iArr2.length) {
            this.coeffs = Arrays.copyOf(iArr2, iArr.length);
        }
        int i2 = 0;
        while (true) {
            int[] iArr3 = integerPolynomial.coeffs;
            if (i2 >= iArr3.length) {
                return;
            }
            int[] iArr4 = this.coeffs;
            iArr4[i2] = iArr4[i2] - iArr3[i2];
            i2++;
        }
    }

    public IntegerPolynomial(BigIntPolynomial bigIntPolynomial) {
        this.coeffs = new int[bigIntPolynomial.coeffs.length];
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr = bigIntPolynomial.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return;
            }
            this.coeffs[i2] = bigIntegerArr[i2].intValue();
            i2++;
        }
    }

    public void sub(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 >= iArr.length) {
                return;
            }
            iArr[i3] = iArr[i3] - i2;
            i3++;
        }
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        return new BigIntPolynomial(this).mult(bigIntPolynomial);
    }

    public void mult(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 >= iArr.length) {
                return;
            }
            iArr[i3] = iArr[i3] * i2;
            i3++;
        }
    }

    public ModularResultant resultant(int i2) {
        int[] iArr = this.coeffs;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length + 1);
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(iArrCopyOf);
        int length = iArrCopyOf.length;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(length);
        int[] iArr2 = integerPolynomial2.coeffs;
        iArr2[0] = -1;
        int i3 = length - 1;
        iArr2[i3] = 1;
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(integerPolynomial.coeffs);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(length);
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
        integerPolynomial5.coeffs[0] = 1;
        int i4 = i3;
        int iPow = 1;
        int iDegree = integerPolynomial3.degree();
        IntegerPolynomial integerPolynomial6 = integerPolynomial4;
        IntegerPolynomial integerPolynomial7 = integerPolynomial5;
        int iDegree2 = i4;
        IntegerPolynomial integerPolynomial8 = integerPolynomial2;
        IntegerPolynomial integerPolynomial9 = integerPolynomial3;
        while (iDegree > 0) {
            int iInvert = (Util.invert(integerPolynomial9.coeffs[iDegree], i2) * integerPolynomial8.coeffs[iDegree2]) % i2;
            int i5 = iDegree2 - iDegree;
            integerPolynomial8.multShiftSub(integerPolynomial9, iInvert, i5, i2);
            integerPolynomial6.multShiftSub(integerPolynomial7, iInvert, i5, i2);
            iDegree2 = integerPolynomial8.degree();
            if (iDegree2 < iDegree) {
                iPow = (iPow * Util.pow(integerPolynomial9.coeffs[iDegree], i4 - iDegree2, i2)) % i2;
                if (i4 % 2 == 1 && iDegree % 2 == 1) {
                    iPow = (-iPow) % i2;
                }
                i4 = iDegree;
                iDegree = iDegree2;
                iDegree2 = i4;
                IntegerPolynomial integerPolynomial10 = integerPolynomial7;
                integerPolynomial7 = integerPolynomial6;
                integerPolynomial6 = integerPolynomial10;
                IntegerPolynomial integerPolynomial11 = integerPolynomial8;
                integerPolynomial8 = integerPolynomial9;
                integerPolynomial9 = integerPolynomial11;
            }
        }
        int iPow2 = (iPow * Util.pow(integerPolynomial9.coeffs[0], iDegree2, i2)) % i2;
        integerPolynomial7.mult(Util.invert(integerPolynomial9.coeffs[0], i2));
        integerPolynomial7.mod(i2);
        integerPolynomial7.mult(iPow2);
        integerPolynomial7.mod(i2);
        int[] iArr3 = integerPolynomial7.coeffs;
        integerPolynomial7.coeffs = Arrays.copyOf(iArr3, iArr3.length - 1);
        return new ModularResultant(new BigIntPolynomial(integerPolynomial7), BigInteger.valueOf(iPow2), BigInteger.valueOf(i2));
    }
}
