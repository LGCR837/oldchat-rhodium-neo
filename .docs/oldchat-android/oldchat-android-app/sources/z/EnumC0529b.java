package z;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: z.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class EnumC0529b implements InterfaceC0530c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final EnumC0529b f8293a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final EnumC0529b f8294b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final EnumC0529b f8295c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final EnumC0529b f8296d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final EnumC0529b f8297e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final EnumC0529b f8298f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ EnumC0529b[] f8299g;

    /* JADX INFO: renamed from: z.b$a */
    public enum a extends EnumC0529b {
        public a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // z.InterfaceC0530c
        public String a(Field field) {
            return field.getName();
        }
    }

    static {
        a aVar = new a("IDENTITY", 0);
        f8293a = aVar;
        EnumC0529b enumC0529b = new EnumC0529b("UPPER_CAMEL_CASE", 1) { // from class: z.b.b
            {
                a aVar2 = null;
            }

            @Override // z.InterfaceC0530c
            public String a(Field field) {
                return EnumC0529b.d(field.getName());
            }
        };
        f8294b = enumC0529b;
        EnumC0529b enumC0529b2 = new EnumC0529b("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: z.b.c
            {
                a aVar2 = null;
            }

            @Override // z.InterfaceC0530c
            public String a(Field field) {
                return EnumC0529b.d(EnumC0529b.c(field.getName(), " "));
            }
        };
        f8295c = enumC0529b2;
        EnumC0529b enumC0529b3 = new EnumC0529b("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: z.b.d
            {
                a aVar2 = null;
            }

            @Override // z.InterfaceC0530c
            public String a(Field field) {
                return EnumC0529b.c(field.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        };
        f8296d = enumC0529b3;
        EnumC0529b enumC0529b4 = new EnumC0529b("LOWER_CASE_WITH_DASHES", 4) { // from class: z.b.e
            {
                a aVar2 = null;
            }

            @Override // z.InterfaceC0530c
            public String a(Field field) {
                return EnumC0529b.c(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        f8297e = enumC0529b4;
        EnumC0529b enumC0529b5 = new EnumC0529b("LOWER_CASE_WITH_DOTS", 5) { // from class: z.b.f
            {
                a aVar2 = null;
            }

            @Override // z.InterfaceC0530c
            public String a(Field field) {
                return EnumC0529b.c(field.getName(), ".").toLowerCase(Locale.ENGLISH);
            }
        };
        f8298f = enumC0529b5;
        f8299g = new EnumC0529b[]{aVar, enumC0529b, enumC0529b2, enumC0529b3, enumC0529b4, enumC0529b5};
    }

    public EnumC0529b(String str, int i2) {
    }

    public static String b(char c2, String str, int i2) {
        if (i2 >= str.length()) {
            return String.valueOf(c2);
        }
        return c2 + str.substring(i2);
    }

    public static String c(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        char cCharAt = str.charAt(0);
        int length = str.length();
        while (i2 < length - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i2++;
            cCharAt = str.charAt(i2);
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        sb.append(b(Character.toUpperCase(cCharAt), str, i2 + 1));
        return sb.toString();
    }

    public static EnumC0529b valueOf(String str) {
        return (EnumC0529b) Enum.valueOf(EnumC0529b.class, str);
    }

    public static EnumC0529b[] values() {
        return (EnumC0529b[]) f8299g.clone();
    }

    public /* synthetic */ EnumC0529b(String str, int i2, a aVar) {
        this(str, i2);
    }
}
