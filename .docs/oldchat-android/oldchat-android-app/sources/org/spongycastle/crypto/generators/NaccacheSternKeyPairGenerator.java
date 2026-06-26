package org.spongycastle.crypto.generators;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.spongycastle.crypto.params.NaccacheSternKeyParameters;
import org.spongycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.crypto.tls.ExtensionType;
import org.spongycastle.math.Primes;

/* JADX INFO: loaded from: classes.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, ExtensionType.negotiated_ff_dhe_groups, 103, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256, 113, CertificateBody.profileType, 131, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int i2) {
        Vector vector = new Vector(i2);
        for (int i3 = 0; i3 != i2; i3++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i3]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i2, int i3, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(i2, i3, secureRandom);
        while (bigInteger.bitLength() != i2) {
            bigInteger = new BigInteger(i2, i3, secureRandom);
        }
        return bigInteger;
    }

    private static int getInt(SecureRandom secureRandom, int i2) {
        int iNextInt;
        int i3;
        if (((-i2) & i2) == i2) {
            return (int) ((((long) i2) * ((long) (secureRandom.nextInt() & Integer.MAX_VALUE))) >> 31);
        }
        do {
            iNextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i3 = iNextInt % i2;
        } while ((iNextInt - i3) + (i2 - 1) < 0);
        return i3;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i2 = 0; i2 < vector.size(); i2++) {
            vector3.addElement(vector.elementAt(i2));
        }
        vector2.addElement(vector3.elementAt(0));
        vector3.removeElementAt(0);
        while (vector3.size() != 0) {
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
            vector3.removeElementAt(0);
        }
        return vector2;
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j2;
        BigInteger bigIntegerGeneratePrime;
        BigInteger bigIntegerAdd;
        BigInteger bigIntegerGeneratePrime2;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigIntegerAdd2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigIntegerMod;
        BigInteger bigInteger7;
        BigInteger bigInteger8;
        int i2;
        long j3;
        BigInteger bigInteger9;
        int i3;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean zIsDebug = this.param.isDebug();
        if (zIsDebug) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector vectorPermuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigIntegerMultiply = ONE;
        BigInteger bigIntegerMultiply2 = bigIntegerMultiply;
        for (int i4 = 0; i4 < vectorPermuteList.size() / 2; i4++) {
            bigIntegerMultiply2 = bigIntegerMultiply2.multiply((BigInteger) vectorPermuteList.elementAt(i4));
        }
        for (int size = vectorPermuteList.size() / 2; size < vectorPermuteList.size(); size++) {
            bigIntegerMultiply = bigIntegerMultiply.multiply((BigInteger) vectorPermuteList.elementAt(size));
        }
        BigInteger bigIntegerMultiply3 = bigIntegerMultiply2.multiply(bigIntegerMultiply);
        int iBitLength = (((strength - bigIntegerMultiply3.bitLength()) - 48) / 2) + 1;
        BigInteger bigIntegerGeneratePrime3 = generatePrime(iBitLength, certainty, random);
        BigInteger bigIntegerGeneratePrime4 = generatePrime(iBitLength, certainty, random);
        if (zIsDebug) {
            System.out.println("generating p and q");
        }
        BigInteger bigIntegerShiftLeft = bigIntegerGeneratePrime3.multiply(bigIntegerMultiply2).shiftLeft(1);
        BigInteger bigIntegerShiftLeft2 = bigIntegerGeneratePrime4.multiply(bigIntegerMultiply).shiftLeft(1);
        long j4 = 0;
        while (true) {
            j2 = j4 + 1;
            bigIntegerGeneratePrime = generatePrime(24, certainty, random);
            bigIntegerAdd = bigIntegerGeneratePrime.multiply(bigIntegerShiftLeft).add(ONE);
            if (bigIntegerAdd.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        bigIntegerGeneratePrime2 = generatePrime(24, certainty, random);
                    } while (bigIntegerGeneratePrime.equals(bigIntegerGeneratePrime2));
                    BigInteger bigIntegerMultiply4 = bigIntegerGeneratePrime2.multiply(bigIntegerShiftLeft2);
                    bigInteger = bigIntegerShiftLeft2;
                    bigInteger2 = ONE;
                    bigIntegerAdd2 = bigIntegerMultiply4.add(bigInteger2);
                    if (bigIntegerAdd2.isProbablePrime(certainty)) {
                        break;
                    }
                    bigIntegerShiftLeft2 = bigInteger;
                }
                bigInteger3 = bigIntegerShiftLeft;
                if (!bigIntegerMultiply3.gcd(bigIntegerGeneratePrime.multiply(bigIntegerGeneratePrime2)).equals(bigInteger2)) {
                    continue;
                } else {
                    if (bigIntegerAdd.multiply(bigIntegerAdd2).bitLength() >= strength) {
                        break;
                    }
                    if (zIsDebug) {
                        System.out.println("key size too small. Should be " + strength + " but is actually " + bigIntegerAdd.multiply(bigIntegerAdd2).bitLength());
                    }
                }
            } else {
                bigInteger = bigIntegerShiftLeft2;
                bigInteger3 = bigIntegerShiftLeft;
            }
            j4 = j2;
            bigIntegerShiftLeft2 = bigInteger;
            bigIntegerShiftLeft = bigInteger3;
        }
        BigInteger bigInteger10 = bigIntegerGeneratePrime4;
        if (zIsDebug) {
            bigInteger4 = bigIntegerGeneratePrime3;
            System.out.println("needed " + j2 + " tries to generate p and q.");
        } else {
            bigInteger4 = bigIntegerGeneratePrime3;
        }
        BigInteger bigIntegerMultiply5 = bigIntegerAdd.multiply(bigIntegerAdd2);
        BigInteger bigIntegerMultiply6 = bigIntegerAdd.subtract(bigInteger2).multiply(bigIntegerAdd2.subtract(bigInteger2));
        if (zIsDebug) {
            System.out.println("generating g");
        }
        long j5 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger5 = bigIntegerAdd;
            bigInteger6 = bigIntegerAdd2;
            int i5 = 0;
            while (i5 != vectorPermuteList.size()) {
                BigInteger bigIntegerDivide = bigIntegerMultiply6.divide((BigInteger) vectorPermuteList.elementAt(i5));
                while (true) {
                    j3 = j5 + 1;
                    bigInteger9 = new BigInteger(strength, certainty, random);
                    i3 = strength;
                    if (bigInteger9.modPow(bigIntegerDivide, bigIntegerMultiply5).equals(ONE)) {
                        j5 = j3;
                        strength = i3;
                    }
                }
                vector.addElement(bigInteger9);
                i5++;
                j5 = j3;
                strength = i3;
            }
            int i6 = strength;
            bigIntegerMod = ONE;
            int i7 = 0;
            while (i7 < vectorPermuteList.size()) {
                bigIntegerMod = bigIntegerMod.multiply(((BigInteger) vector.elementAt(i7)).modPow(bigIntegerMultiply3.divide((BigInteger) vectorPermuteList.elementAt(i7)), bigIntegerMultiply5)).mod(bigIntegerMultiply5);
                i7++;
                random = random;
            }
            SecureRandom secureRandom = random;
            int i8 = 0;
            while (true) {
                if (i8 >= vectorPermuteList.size()) {
                    BigInteger bigIntegerModPow = bigIntegerMod.modPow(bigIntegerMultiply6.divide(BigInteger.valueOf(4L)), bigIntegerMultiply5);
                    BigInteger bigInteger11 = ONE;
                    if (!bigIntegerModPow.equals(bigInteger11)) {
                        if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigIntegerGeneratePrime), bigIntegerMultiply5).equals(bigInteger11)) {
                            if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigIntegerGeneratePrime2), bigIntegerMultiply5).equals(bigInteger11)) {
                                bigInteger7 = bigInteger4;
                                if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigInteger7), bigIntegerMultiply5).equals(bigInteger11)) {
                                    bigInteger8 = bigInteger10;
                                    if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigInteger8), bigIntegerMultiply5).equals(bigInteger11)) {
                                        break;
                                    }
                                    if (zIsDebug) {
                                        PrintStream printStream = System.out;
                                        StringBuilder sb = new StringBuilder();
                                        i2 = certainty;
                                        sb.append("g has order phi(n)/b\n g: ");
                                        sb.append(bigIntegerMod);
                                        printStream.println(sb.toString());
                                    }
                                } else {
                                    if (zIsDebug) {
                                        System.out.println("g has order phi(n)/a\n g: " + bigIntegerMod);
                                    }
                                    bigInteger8 = bigInteger10;
                                }
                            } else if (zIsDebug) {
                                System.out.println("g has order phi(n)/q'\n g: " + bigIntegerMod);
                            }
                        } else if (zIsDebug) {
                            System.out.println("g has order phi(n)/p'\n g: " + bigIntegerMod);
                        }
                    } else if (zIsDebug) {
                        System.out.println("g has order phi(n)/4\n g:" + bigIntegerMod);
                    }
                } else if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide((BigInteger) vectorPermuteList.elementAt(i8)), bigIntegerMultiply5).equals(ONE)) {
                    i8++;
                } else if (zIsDebug) {
                    System.out.println("g has order phi(n)/" + vectorPermuteList.elementAt(i8) + "\n g: " + bigIntegerMod);
                }
            }
            bigInteger8 = bigInteger10;
            bigInteger7 = bigInteger4;
            i2 = certainty;
            bigInteger4 = bigInteger7;
            certainty = i2;
            bigIntegerAdd = bigInteger5;
            strength = i6;
            random = secureRandom;
            bigInteger10 = bigInteger8;
            bigIntegerAdd2 = bigInteger6;
        }
        if (zIsDebug) {
            PrintStream printStream2 = System.out;
            printStream2.println("needed " + j5 + " tries to generate g");
            printStream2.println();
            printStream2.println("found new NaccacheStern cipher variables:");
            printStream2.println("smallPrimes: " + vectorPermuteList);
            printStream2.println("sigma:...... " + bigIntegerMultiply3 + " (" + bigIntegerMultiply3.bitLength() + " bits)");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("a:.......... ");
            sb2.append(bigInteger7);
            printStream2.println(sb2.toString());
            printStream2.println("b:.......... " + bigInteger8);
            printStream2.println("p':......... " + bigIntegerGeneratePrime);
            printStream2.println("q':......... " + bigIntegerGeneratePrime2);
            printStream2.println("p:.......... " + bigInteger5);
            printStream2.println("q:.......... " + bigInteger6);
            printStream2.println("n:.......... " + bigIntegerMultiply5);
            printStream2.println("phi(n):..... " + bigIntegerMultiply6);
            printStream2.println("g:.......... " + bigIntegerMod);
            printStream2.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigIntegerMod, bigIntegerMultiply5, bigIntegerMultiply3.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigIntegerMod, bigIntegerMultiply5, bigIntegerMultiply3.bitLength(), vectorPermuteList, bigIntegerMultiply6));
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
