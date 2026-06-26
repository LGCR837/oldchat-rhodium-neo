package z;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f8332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f8333b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ n[] f8334c;

    public enum a extends n {
        public a(String str, int i2) {
            super(str, i2, null);
        }
    }

    static {
        a aVar = new a("DEFAULT", 0);
        f8332a = aVar;
        n nVar = new n("STRING", 1) { // from class: z.n.b
            {
                a aVar2 = null;
            }
        };
        f8333b = nVar;
        f8334c = new n[]{aVar, nVar};
    }

    public n(String str, int i2) {
    }

    public static n valueOf(String str) {
        return (n) Enum.valueOf(n.class, str);
    }

    public static n[] values() {
        return (n[]) f8334c.clone();
    }

    public /* synthetic */ n(String str, int i2, a aVar) {
        this(str, i2);
    }
}
