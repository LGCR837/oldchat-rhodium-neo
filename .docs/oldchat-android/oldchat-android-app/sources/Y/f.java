package Y;

import java.math.BigInteger;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f636a = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f637b = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger[] f638c;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f639a;

        static {
            int[] iArr = new int[b.values().length];
            f639a = iArr;
            try {
                iArr[b.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f639a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f639a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f639a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f639a[b.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f639a[b.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum b {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        f638c = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = bigIntegerValueOf;
        int i2 = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = f638c;
            if (i2 >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i2] = bigIntegerArr2[i2 - 1].multiply(bigIntegerValueOf);
            i2++;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:34:0x0074. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0021 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:282)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:65)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:282)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:65)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(int r16, int[] r17, java.nio.charset.Charset r18, int r19, java.lang.StringBuilder r20) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.f.a(int, int[], java.nio.charset.Charset, int, java.lang.StringBuilder):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static L.e b(int[] r6, java.lang.String r7) throws H.g {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r6.length
            r2 = 1
            int r1 = r1 << r2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = Y.e.a()
            r2 = r6[r2]
            X.c r3 = new X.c
            r3.<init>()
            r4 = 2
        L14:
            r5 = 0
            r5 = r6[r5]
            if (r4 >= r5) goto L6f
            r5 = 913(0x391, float:1.28E-42)
            if (r2 == r5) goto L5a
            switch(r2) {
                case 900: goto L55;
                case 901: goto L50;
                case 902: goto L4b;
                default: goto L20;
            }
        L20:
            switch(r2) {
                case 922: goto L46;
                case 923: goto L46;
                case 924: goto L50;
                case 925: goto L43;
                case 926: goto L40;
                case 927: goto L2f;
                case 928: goto L2a;
                default: goto L23;
            }
        L23:
            int r4 = r4 + (-1)
            int r2 = g(r6, r4, r0)
            goto L62
        L2a:
            int r2 = d(r6, r4, r3)
            goto L62
        L2f:
            int r2 = r4 + 1
            r1 = r6[r4]
            L.d r1 = L.d.b(r1)
            java.lang.String r1 = r1.name()
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            goto L62
        L40:
            int r2 = r4 + 2
            goto L62
        L43:
            int r2 = r4 + 1
            goto L62
        L46:
            H.g r6 = H.g.a()
            throw r6
        L4b:
            int r2 = f(r6, r4, r0)
            goto L62
        L50:
            int r2 = a(r2, r6, r1, r4, r0)
            goto L62
        L55:
            int r2 = g(r6, r4, r0)
            goto L62
        L5a:
            int r2 = r4 + 1
            r4 = r6[r4]
            char r4 = (char) r4
            r0.append(r4)
        L62:
            int r4 = r6.length
            if (r2 >= r4) goto L6a
            int r4 = r2 + 1
            r2 = r6[r2]
            goto L14
        L6a:
            H.g r6 = H.g.a()
            throw r6
        L6f:
            int r6 = r0.length()
            if (r6 == 0) goto L83
            L.e r6 = new L.e
            java.lang.String r0 = r0.toString()
            r1 = 0
            r6.<init>(r1, r0, r1, r7)
            r6.m(r3)
            return r6
        L83:
            H.g r6 = H.g.a()
            goto L89
        L88:
            throw r6
        L89:
            goto L88
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.f.b(int[], java.lang.String):L.e");
    }

    public static String c(int[] iArr, int i2) throws H.g {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerAdd = bigIntegerAdd.add(f638c[(i2 - i3) - 1].multiply(BigInteger.valueOf(iArr[i3])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) == '1') {
            return string.substring(1);
        }
        throw H.g.a();
    }

    public static int d(int[] iArr, int i2, X.c cVar) throws H.g {
        if (i2 + 2 > iArr[0]) {
            throw H.g.a();
        }
        int[] iArr2 = new int[2];
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i2];
            i3++;
            i2++;
        }
        cVar.j(Integer.parseInt(c(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int iG = g(iArr, i2, sb);
        cVar.d(sb.toString());
        int i4 = iArr[iG] == 923 ? iG + 1 : -1;
        while (iG < iArr[0]) {
            int i5 = iArr[iG];
            if (i5 == 922) {
                iG++;
                cVar.g(true);
            } else {
                if (i5 != 923) {
                    throw H.g.a();
                }
                switch (iArr[iG + 1]) {
                    case 0:
                        StringBuilder sb2 = new StringBuilder();
                        iG = g(iArr, iG + 2, sb2);
                        cVar.e(sb2.toString());
                        break;
                    case 1:
                        StringBuilder sb3 = new StringBuilder();
                        iG = f(iArr, iG + 2, sb3);
                        cVar.i(Integer.parseInt(sb3.toString()));
                        break;
                    case 2:
                        StringBuilder sb4 = new StringBuilder();
                        iG = f(iArr, iG + 2, sb4);
                        cVar.l(Long.parseLong(sb4.toString()));
                        break;
                    case 3:
                        StringBuilder sb5 = new StringBuilder();
                        iG = g(iArr, iG + 2, sb5);
                        cVar.k(sb5.toString());
                        break;
                    case 4:
                        StringBuilder sb6 = new StringBuilder();
                        iG = g(iArr, iG + 2, sb6);
                        cVar.b(sb6.toString());
                        break;
                    case 5:
                        StringBuilder sb7 = new StringBuilder();
                        iG = f(iArr, iG + 2, sb7);
                        cVar.f(Long.parseLong(sb7.toString()));
                        break;
                    case 6:
                        StringBuilder sb8 = new StringBuilder();
                        iG = f(iArr, iG + 2, sb8);
                        cVar.c(Integer.parseInt(sb8.toString()));
                        break;
                    default:
                        throw H.g.a();
                }
            }
        }
        if (i4 != -1) {
            int i6 = iG - i4;
            if (cVar.a()) {
                i6--;
            }
            cVar.h(Arrays.copyOfRange(iArr, i4, i6 + i4));
        }
        return iG;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void e(int[] iArr, int[] iArr2, int i2, StringBuilder sb) {
        b bVar;
        int i3;
        b bVar2 = b.ALPHA;
        b bVar3 = bVar2;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = iArr[i4];
            char c2 = ' ';
            switch (a.f639a[bVar2.ordinal()]) {
                case 1:
                    if (i5 < 26) {
                        i3 = i5 + 65;
                        c2 = (char) i3;
                    } else {
                        if (i5 == 900) {
                            bVar2 = b.ALPHA;
                        } else if (i5 != 913) {
                            switch (i5) {
                                case 27:
                                    bVar2 = b.LOWER;
                                    break;
                                case 28:
                                    bVar2 = b.MIXED;
                                    break;
                                case 29:
                                    bVar = b.PUNCT_SHIFT;
                                    c2 = 0;
                                    b bVar4 = bVar;
                                    bVar3 = bVar2;
                                    bVar2 = bVar4;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i4]);
                        }
                        c2 = 0;
                    }
                    break;
                case 2:
                    if (i5 < 26) {
                        i3 = i5 + 97;
                        c2 = (char) i3;
                    } else {
                        if (i5 == 900) {
                            bVar2 = b.ALPHA;
                        } else if (i5 != 913) {
                            switch (i5) {
                                case 27:
                                    bVar = b.ALPHA_SHIFT;
                                    c2 = 0;
                                    b bVar42 = bVar;
                                    bVar3 = bVar2;
                                    bVar2 = bVar42;
                                    break;
                                case 28:
                                    bVar2 = b.MIXED;
                                    break;
                                case 29:
                                    bVar = b.PUNCT_SHIFT;
                                    c2 = 0;
                                    b bVar422 = bVar;
                                    bVar3 = bVar2;
                                    bVar2 = bVar422;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i4]);
                        }
                        c2 = 0;
                    }
                    break;
                case 3:
                    if (i5 < 25) {
                        c2 = f637b[i5];
                    } else {
                        if (i5 == 900) {
                            bVar2 = b.ALPHA;
                        } else if (i5 != 913) {
                            switch (i5) {
                                case 25:
                                    bVar2 = b.PUNCT;
                                    break;
                                case 27:
                                    bVar2 = b.LOWER;
                                    break;
                                case 28:
                                    bVar2 = b.ALPHA;
                                    break;
                                case 29:
                                    bVar = b.PUNCT_SHIFT;
                                    c2 = 0;
                                    b bVar4222 = bVar;
                                    bVar3 = bVar2;
                                    bVar2 = bVar4222;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i4]);
                        }
                        c2 = 0;
                    }
                    break;
                case 4:
                    if (i5 < 29) {
                        c2 = f636a[i5];
                    } else {
                        if (i5 == 29 || i5 == 900) {
                            bVar2 = b.ALPHA;
                        } else if (i5 == 913) {
                            sb.append((char) iArr2[i4]);
                        }
                        c2 = 0;
                    }
                    break;
                case 5:
                    if (i5 < 26) {
                        c2 = (char) (i5 + 65);
                    } else if (i5 != 26) {
                        bVar2 = i5 != 900 ? bVar3 : b.ALPHA;
                        c2 = 0;
                    }
                    bVar2 = bVar3;
                    break;
                case 6:
                    if (i5 < 29) {
                        c2 = f636a[i5];
                        bVar2 = bVar3;
                    } else if (i5 == 29 || i5 == 900) {
                        bVar2 = b.ALPHA;
                        c2 = 0;
                    } else {
                        if (i5 == 913) {
                            sb.append((char) iArr2[i4]);
                        }
                        c2 = 0;
                    }
                    break;
                default:
                    c2 = 0;
                    break;
            }
            if (c2 != 0) {
                sb.append(c2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0038 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0007 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int f(int[] r8, int r9, java.lang.StringBuilder r10) {
        /*
            r0 = 15
            int[] r0 = new int[r0]
            r1 = 0
            r2 = 0
        L6:
            r3 = 0
        L7:
            r4 = r8[r1]
            if (r9 >= r4) goto L42
            if (r2 != 0) goto L42
            int r5 = r9 + 1
            r6 = r8[r9]
            r7 = 1
            if (r5 != r4) goto L15
            r2 = 1
        L15:
            r4 = 900(0x384, float:1.261E-42)
            if (r6 >= r4) goto L1f
            r0[r3] = r6
            int r3 = r3 + 1
        L1d:
            r9 = r5
            goto L2e
        L1f:
            if (r6 == r4) goto L2d
            r4 = 901(0x385, float:1.263E-42)
            if (r6 == r4) goto L2d
            r4 = 928(0x3a0, float:1.3E-42)
            if (r6 == r4) goto L2d
            switch(r6) {
                case 922: goto L2d;
                case 923: goto L2d;
                case 924: goto L2d;
                default: goto L2c;
            }
        L2c:
            goto L1d
        L2d:
            r2 = 1
        L2e:
            int r4 = r3 % 15
            if (r4 == 0) goto L38
            r4 = 902(0x386, float:1.264E-42)
            if (r6 == r4) goto L38
            if (r2 == 0) goto L7
        L38:
            if (r3 <= 0) goto L7
            java.lang.String r3 = c(r0, r3)
            r10.append(r3)
            goto L6
        L42:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.f.f(int[], int, java.lang.StringBuilder):int");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0033. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0036. Please report as an issue. */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public static int g(int[] r10, int r11, java.lang.StringBuilder r12) {
        /*
            r0 = 0
            r1 = r10[r0]
            int r2 = r1 - r11
            r3 = 1
            int r2 = r2 << r3
            int[] r2 = new int[r2]
            int r1 = r1 - r11
            int r1 = r1 << r3
            int[] r1 = new int[r1]
            r4 = 0
            r5 = 0
        Lf:
            r6 = r10[r0]
            if (r11 >= r6) goto L4d
            if (r4 != 0) goto L4d
            int r6 = r11 + 1
            r7 = r10[r11]
            r8 = 900(0x384, float:1.261E-42)
            if (r7 >= r8) goto L2b
            int r11 = r7 / 30
            r2[r5] = r11
            int r11 = r5 + 1
            int r7 = r7 % 30
            r2[r11] = r7
            int r5 = r5 + 2
        L29:
            r11 = r6
            goto Lf
        L2b:
            r9 = 913(0x391, float:1.28E-42)
            if (r7 == r9) goto L42
            r9 = 928(0x3a0, float:1.3E-42)
            if (r7 == r9) goto L40
            switch(r7) {
                case 900: goto L3a;
                case 901: goto L40;
                case 902: goto L40;
                default: goto L36;
            }
        L36:
            switch(r7) {
                case 922: goto L40;
                case 923: goto L40;
                case 924: goto L40;
                default: goto L39;
            }
        L39:
            goto L29
        L3a:
            int r11 = r5 + 1
            r2[r5] = r8
            r5 = r11
            goto L29
        L40:
            r4 = 1
            goto Lf
        L42:
            r2[r5] = r9
            int r11 = r11 + 2
            r6 = r10[r6]
            r1[r5] = r6
            int r5 = r5 + 1
            goto Lf
        L4d:
            e(r2, r1, r5, r12)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.f.g(int[], int, java.lang.StringBuilder):int");
    }
}
