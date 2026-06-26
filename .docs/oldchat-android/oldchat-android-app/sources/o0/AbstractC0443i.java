package o0;

import android.util.Base64;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: renamed from: o0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0443i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f7104b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static byte[] f7105c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static byte[] f7106d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Boolean f7107e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static volatile boolean f7109g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f7103a = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f7108f = new Object();

    /* JADX INFO: renamed from: o0.i$a */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final PrivateKey f7110a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f7111b;

        public a(PrivateKey privateKey, String str) {
            this.f7110a = privateKey;
            this.f7111b = str;
        }

        public b a(String str) throws InvalidKeySpecException, InvalidKeyException {
            PublicKey publicKeyGeneratePublic = AbstractC0443i.l().generatePublic(new X509EncodedKeySpec(Base64.decode(str, 2)));
            KeyAgreement keyAgreementN = AbstractC0443i.n();
            keyAgreementN.init(this.f7110a);
            keyAgreementN.doPhase(publicKeyGeneratePublic, true);
            byte[] bArrT = AbstractC0443i.t(keyAgreementN.generateSecret(), 32);
            return new b(AbstractC0443i.v(AbstractC0443i.h(bArrT, "enc".getBytes("UTF-8"))), AbstractC0443i.v(AbstractC0443i.h(bArrT, "mac".getBytes("UTF-8"))));
        }

        public String b() {
            return this.f7111b;
        }
    }

    /* JADX INFO: renamed from: o0.i$b */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final byte[] f7112a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final byte[] f7113b;

        public b(byte[] bArr, byte[] bArr2) {
            this.f7112a = bArr;
            this.f7113b = bArr2;
        }
    }

    public static a f() throws InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGeneratorM = m();
        try {
            keyPairGeneratorM.initialize(new ECGenParameterSpec("secp256r1"));
        } catch (Exception unused) {
            keyPairGeneratorM.initialize(new ECGenParameterSpec("prime256v1"));
        }
        KeyPair keyPairGenerateKeyPair = keyPairGeneratorM.generateKeyPair();
        return new a(keyPairGenerateKeyPair.getPrivate(), Base64.encodeToString(keyPairGenerateKeyPair.getPublic().getEncoded(), 2));
    }

    public static void g() {
        synchronized (f7103a) {
            f7104b = null;
            f7105c = null;
            f7106d = null;
        }
    }

    public static byte[] h(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static String i(String str) {
        b bVarP;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("iv") || !jSONObject.has("data") || !jSONObject.has("mac") || (bVarP = p()) == null) {
                return null;
            }
            byte[] bArrDecode = Base64.decode(jSONObject.getString("iv"), 2);
            byte[] bArrDecode2 = Base64.decode(jSONObject.getString("data"), 2);
            if (!MessageDigest.isEqual(Base64.decode(jSONObject.getString("mac"), 2), r(bVarP.f7113b, h(bArrDecode, bArrDecode2)))) {
                return null;
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(bVarP.f7112a, "AES"), new IvParameterSpec(bArrDecode));
            return new String(cipher.doFinal(bArrDecode2), "UTF-8");
        } catch (Exception unused) {
        }
        return null;
    }

    public static String j(String str) throws BadPaddingException, JSONException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        b bVarP = p();
        if (bVarP == null) {
            throw new IllegalStateException("missing session keys");
        }
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(bVarP.f7112a, "AES"), new IvParameterSpec(bArr));
        byte[] bArrDoFinal = cipher.doFinal(str.getBytes("UTF-8"));
        byte[] bArrR = r(bVarP.f7113b, h(bArr, bArrDoFinal));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("iv", Base64.encodeToString(bArr, 2));
        jSONObject.put("data", Base64.encodeToString(bArrDoFinal, 2));
        jSONObject.put("mac", Base64.encodeToString(bArrR, 2));
        return jSONObject.toString();
    }

    public static void k() {
        if (f7109g) {
            return;
        }
        synchronized (f7108f) {
            if (f7109g) {
                return;
            }
            try {
                if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                    Security.insertProviderAt(new BouncyCastleProvider(), 1);
                }
            } catch (Throwable unused) {
            }
            f7109g = true;
        }
    }

    public static KeyFactory l() {
        k();
        try {
            return KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception unused) {
            return KeyFactory.getInstance("EC");
        }
    }

    public static KeyPairGenerator m() {
        k();
        try {
            return KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception unused) {
            return KeyPairGenerator.getInstance("EC");
        }
    }

    public static KeyAgreement n() {
        k();
        try {
            return KeyAgreement.getInstance("ECDH", BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception unused) {
            return KeyAgreement.getInstance("ECDH");
        }
    }

    public static String o() {
        String str;
        synchronized (f7103a) {
            str = f7104b;
        }
        return str;
    }

    public static b p() {
        synchronized (f7103a) {
            try {
                byte[] bArr = f7105c;
                if (bArr != null && f7106d != null) {
                    return new b((byte[]) bArr.clone(), (byte[]) f7106d.clone());
                }
                return null;
            } finally {
            }
        }
    }

    public static boolean q() {
        boolean z2;
        synchronized (f7103a) {
            try {
                z2 = (f7104b == null || f7105c == null || f7106d == null) ? false : true;
            } finally {
            }
        }
        return z2;
    }

    public static byte[] r(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
        return mac.doFinal(bArr2);
    }

    public static boolean s() {
        boolean z2;
        Boolean bool = f7107e;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            KeyPairGenerator keyPairGeneratorM = m();
            try {
                keyPairGeneratorM.initialize(new ECGenParameterSpec("secp256r1"));
            } catch (Exception unused) {
                keyPairGeneratorM.initialize(new ECGenParameterSpec("prime256v1"));
            }
            n();
            z2 = true;
        } catch (Exception unused2) {
            z2 = false;
        }
        f7107e = Boolean.valueOf(z2);
        return z2;
    }

    public static byte[] t(byte[] bArr, int i2) {
        if (bArr.length == i2) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        if (bArr.length > i2) {
            System.arraycopy(bArr, bArr.length - i2, bArr2, 0, i2);
        } else {
            System.arraycopy(bArr, 0, bArr2, i2 - bArr.length, bArr.length);
        }
        return bArr2;
    }

    public static void u(String str, byte[] bArr, byte[] bArr2) {
        synchronized (f7103a) {
            try {
                f7104b = str;
                f7105c = bArr != null ? (byte[]) bArr.clone() : null;
                f7106d = bArr2 != null ? (byte[]) bArr2.clone() : null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static byte[] v(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
        messageDigest.update(bArr);
        return messageDigest.digest();
    }
}
