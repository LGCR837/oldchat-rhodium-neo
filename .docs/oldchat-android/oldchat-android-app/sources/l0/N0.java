package l0;

import android.content.Context;
import g0.j;
import j0.j;
import j0.r;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class N0 {
    public static j.InterfaceC0042j b(Runnable runnable) {
        return new a(runnable);
    }

    public static boolean c(Context context, String str, String str2) {
        String str3;
        String str4;
        String str5;
        if (context == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        List listB = j0.c.b(context);
        if (listB != null) {
            for (int i2 = 0; i2 < listB.size(); i2++) {
                j0.w wVar = (j0.w) listB.get(i2);
                if (wVar != null) {
                    String str6 = wVar.f5646b;
                    if (str6 != null && str6.length() > 0) {
                        hashSet.add(wVar.f5646b);
                    }
                    String str7 = wVar.f5645a;
                    if (str7 != null && str7.length() > 0) {
                        hashSet.add(wVar.f5645a);
                    }
                }
            }
        }
        HashSet hashSet2 = new HashSet();
        List listB2 = j0.f.b(context);
        if (listB2 != null) {
            for (int i3 = 0; i3 < listB2.size(); i3++) {
                j0.e eVar = (j0.e) listB2.get(i3);
                if (eVar != null && (str5 = eVar.f5504a) != null && str5.length() > 0) {
                    hashSet2.add(eVar.f5504a);
                }
            }
        }
        String str8 = "";
        if (str != null && str.length() > 0 && listB != null) {
            int i4 = 0;
            while (true) {
                if (i4 >= listB.size()) {
                    break;
                }
                j0.w wVar2 = (j0.w) listB.get(i4);
                if (wVar2 == null || (str4 = wVar2.f5646b) == null || str4.length() == 0 || !str.equals(wVar2.f5646b)) {
                    i4++;
                } else {
                    String str9 = wVar2.f5645a;
                    if (str9 != null) {
                        str8 = str9;
                    }
                }
            }
        }
        List listD = j0.r.d(context);
        if (listD != null) {
            for (int i5 = 0; i5 < listD.size(); i5++) {
                r.b bVar = (r.b) listD.get(i5);
                if (bVar != null && bVar.f5609g > 0) {
                    String str10 = bVar.f5604b;
                    String str11 = (str10 == null || str10.length() <= 0) ? bVar.f5603a : bVar.f5604b;
                    if (str11 != null && str11.length() != 0 && ((hashSet.isEmpty() || hashSet.contains(str11)) && (str == null || str.length() <= 0 || (!str.equals(str11) && (str8.length() <= 0 || !str8.equals(str11)))))) {
                        return true;
                    }
                }
            }
        }
        List listC = j0.j.c(context);
        if (listC != null) {
            for (int i6 = 0; i6 < listC.size(); i6++) {
                j.b bVar2 = (j.b) listC.get(i6);
                if (bVar2 != null && (str3 = bVar2.f5541a) != null && str3.length() != 0 && ((hashSet2.isEmpty() || hashSet2.contains(bVar2.f5541a)) && ((str2 == null || !str2.equals(bVar2.f5541a)) && (bVar2.f5546f > 0 || bVar2.f5548h)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void d(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public class a implements j.InterfaceC0042j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f5942a;

        public a(Runnable runnable) {
            this.f5942a = runnable;
        }

        @Override // g0.j.InterfaceC0042j
        public void b(g0.l lVar) {
            N0.d(this.f5942a);
        }

        @Override // g0.j.InterfaceC0042j
        public void c(String str, String str2, long j2) {
            N0.d(this.f5942a);
        }

        @Override // g0.j.InterfaceC0042j
        public void f(g0.n nVar) {
            N0.d(this.f5942a);
        }

        @Override // g0.j.InterfaceC0042j
        public void a(g0.m mVar) {
        }

        @Override // g0.j.InterfaceC0042j
        public void d(g0.o oVar) {
        }

        @Override // g0.j.InterfaceC0042j
        public void e(g0.p pVar) {
        }

        @Override // g0.j.InterfaceC0042j
        public void g(boolean z2) {
        }
    }
}
