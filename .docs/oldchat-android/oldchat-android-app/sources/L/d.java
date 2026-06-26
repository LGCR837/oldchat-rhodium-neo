package L;

import java.util.HashMap;
import java.util.Map;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.i18n.LocalizedMessage;

/* JADX INFO: loaded from: classes.dex */
public enum d {
    Cp437(new int[]{0, 2}, new String[0]),
    ISO8859_1(new int[]{1, 3}, LocalizedMessage.DEFAULT_ENCODING),
    ISO8859_2(4, "ISO-8859-2"),
    ISO8859_3(5, "ISO-8859-3"),
    ISO8859_4(6, "ISO-8859-4"),
    ISO8859_5(7, "ISO-8859-5"),
    ISO8859_6(8, "ISO-8859-6"),
    ISO8859_7(9, "ISO-8859-7"),
    ISO8859_8(10, "ISO-8859-8"),
    ISO8859_9(11, "ISO-8859-9"),
    ISO8859_10(12, "ISO-8859-10"),
    ISO8859_11(13, "ISO-8859-11"),
    ISO8859_13(15, "ISO-8859-13"),
    ISO8859_14(16, "ISO-8859-14"),
    ISO8859_15(17, "ISO-8859-15"),
    ISO8859_16(18, "ISO-8859-16"),
    SJIS(20, "Shift_JIS"),
    Cp1250(21, "windows-1250"),
    Cp1251(22, "windows-1251"),
    Cp1252(23, "windows-1252"),
    Cp1256(24, "windows-1256"),
    UnicodeBigUnmarked(25, "UTF-16BE", "UnicodeBig"),
    UTF8(26, "UTF-8"),
    ASCII(new int[]{27, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256}, "US-ASCII"),
    Big5(28),
    GB18030(29, "GB2312", "EUC_CN", "GBK"),
    EUC_KR(30, "EUC-KR");


    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final Map f358D = new HashMap();

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final Map f359E = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String[] f386b;

    static {
        for (d dVar : values()) {
            for (int i2 : dVar.f385a) {
                f358D.put(Integer.valueOf(i2), dVar);
            }
            f359E.put(dVar.name(), dVar);
            for (String str : dVar.f386b) {
                f359E.put(str, dVar);
            }
        }
    }

    d(int i2) {
        this(new int[]{i2}, new String[0]);
    }

    public static d a(String str) {
        return (d) f359E.get(str);
    }

    public static d b(int i2) throws H.g {
        if (i2 < 0 || i2 >= 900) {
            throw H.g.a();
        }
        return (d) f358D.get(Integer.valueOf(i2));
    }

    public int c() {
        return this.f385a[0];
    }

    d(int i2, String... strArr) {
        this.f385a = new int[]{i2};
        this.f386b = strArr;
    }

    d(int[] iArr, String... strArr) {
        this.f385a = iArr;
        this.f386b = strArr;
    }
}
