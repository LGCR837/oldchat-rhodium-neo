package c0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f2082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f2083b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f2084c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c f2085d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f2086e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f2087f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f2088g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c f2089h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ c[] f2090i;

    public enum a extends c {
        public a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // c0.c
        public boolean a(int i2, int i3) {
            return ((i2 + i3) & 1) == 0;
        }
    }

    static {
        a aVar = new a("DATA_MASK_000", 0);
        f2082a = aVar;
        c cVar = new c("DATA_MASK_001", 1) { // from class: c0.c.b
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return (i2 & 1) == 0;
            }
        };
        f2083b = cVar;
        c cVar2 = new c("DATA_MASK_010", 2) { // from class: c0.c.c
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return i3 % 3 == 0;
            }
        };
        f2084c = cVar2;
        c cVar3 = new c("DATA_MASK_011", 3) { // from class: c0.c.d
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return (i2 + i3) % 3 == 0;
            }
        };
        f2085d = cVar3;
        c cVar4 = new c("DATA_MASK_100", 4) { // from class: c0.c.e
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return (((i2 / 2) + (i3 / 3)) & 1) == 0;
            }
        };
        f2086e = cVar4;
        c cVar5 = new c("DATA_MASK_101", 5) { // from class: c0.c.f
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return (i2 * i3) % 6 == 0;
            }
        };
        f2087f = cVar5;
        c cVar6 = new c("DATA_MASK_110", 6) { // from class: c0.c.g
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return (i2 * i3) % 6 < 3;
            }
        };
        f2088g = cVar6;
        c cVar7 = new c("DATA_MASK_111", 7) { // from class: c0.c.h
            {
                a aVar2 = null;
            }

            @Override // c0.c
            public boolean a(int i2, int i3) {
                return (((i2 + i3) + ((i2 * i3) % 3)) & 1) == 0;
            }
        };
        f2089h = cVar7;
        f2090i = new c[]{aVar, cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7};
    }

    public c(String str, int i2) {
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) f2090i.clone();
    }

    public abstract boolean a(int i2, int i3);

    public final void b(L.b bVar, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (a(i3, i4)) {
                    bVar.c(i4, i3);
                }
            }
        }
    }

    public /* synthetic */ c(String str, int i2, a aVar) {
        this(str, i2);
    }
}
