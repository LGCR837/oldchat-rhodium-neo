package com.im.oldchat.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.im.oldchat.ui.F;
import com.im.oldchat.ui.G;
import com.im.oldchat.ui.N;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class K extends L {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Handler f3004t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f3005u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f3006v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Runnable f3007w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final LinkedHashSet f3008x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f3009y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public G.c f3010z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            K k2 = K.this;
            if (k2.f3005u == null) {
                return;
            }
            k2.f3005u = null;
            k2.f3006v = 0L;
            k2.notifyDataSetChanged();
        }
    }

    public class b implements F.e {
        public b() {
        }

        @Override // com.im.oldchat.ui.F.e
        public String a(String str) {
            return K.this.C(str);
        }
    }

    public class c implements F.f {
        public c() {
        }

        @Override // com.im.oldchat.ui.F.f
        public boolean a(j0.i iVar) {
            return K.this.n(iVar);
        }
    }

    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.i f3014a;

        public d(j0.i iVar) {
            this.f3014a = iVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            K.this.y0(this.f3014a);
        }
    }

    public class e implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.i f3016a;

        public e(j0.i iVar) {
            this.f3016a = iVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            K.this.y0(this.f3016a);
            return true;
        }
    }

    public K(Context context, List list, String str) {
        super(context, list, str);
        this.f3004t = new Handler(Looper.getMainLooper());
        this.f3007w = new a();
        this.f3008x = new LinkedHashSet();
        this.f3009y = false;
    }

    public void A0(Map map) {
        if (map != null) {
            this.f3459e = map;
            notifyDataSetChanged();
        }
    }

    public void B0(Map map) {
        if (map != null) {
            this.f3462h = map;
            notifyDataSetChanged();
        }
    }

    public void C0(Map map) {
        if (map != null) {
            this.f3461g = map;
            notifyDataSetChanged();
        }
    }

    public void U(View view, N.d dVar, j0.i iVar, o0.C c2) {
        if (this.f3009y) {
            W(view, dVar, iVar);
            return;
        }
        Z(view, dVar);
        F.e(dVar.f3523i, dVar.f3522h, dVar.f3492C, dVar.f3494E, dVar.f3497H, dVar.f3519e);
        boolean zC = o0.F.c(this.f3455a, iVar.f5531c, this.f3457c);
        F.c(this.f3455a, dVar.f3523i, dVar.f3522h, dVar.f3492C, dVar.f3494E, dVar.f3497H, dVar.f3519e, iVar, c2, zC, this.f3465k, new b(), new c());
        R(dVar, iVar, zC);
    }

    public void V(View view, String str) {
        if (view == null) {
            return;
        }
        if (v0(str)) {
            view.setBackgroundColor(721409359);
        } else {
            view.setBackgroundColor(0);
        }
    }

    public final void W(View view, N.d dVar, j0.i iVar) {
        if (dVar == null || iVar == null) {
            return;
        }
        F.e(dVar.f3523i, dVar.f3522h, dVar.f3492C, dVar.f3494E, dVar.f3497H, dVar.f3519e);
        d dVar2 = new d(iVar);
        e eVar = new e(iVar);
        Y(view, dVar2, eVar);
        Y(dVar.f3523i, dVar2, eVar);
        Y(dVar.f3522h, dVar2, eVar);
        Y(dVar.f3492C, dVar2, eVar);
        Y(dVar.f3494E, dVar2, eVar);
        Y(dVar.f3497H, dVar2, eVar);
        Y(dVar.f3524j, dVar2, eVar);
        Y(dVar.f3528n, dVar2, eVar);
        Y(dVar.f3532r, dVar2, eVar);
        Y(dVar.f3538x, dVar2, eVar);
        Y(dVar.f3519e, dVar2, eVar);
        Y(dVar.f3520f, dVar2, eVar);
        X(view, iVar);
    }

    public void X(View view, j0.i iVar) {
        if (view == null || iVar == null) {
            return;
        }
        if (this.f3009y && h0(iVar)) {
            view.setBackgroundColor(574325494);
        } else {
            if (v0(iVar.f5529a)) {
                return;
            }
            view.setBackgroundColor(0);
        }
    }

    public final void Y(View view, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
        view.setOnLongClickListener(onLongClickListener);
    }

    public final void Z(View view, N.d dVar) {
        a0(view);
    }

    public final void a0(View view) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
    }

    public void b0() {
        if (this.f3467m.isEmpty()) {
            return;
        }
        this.f3467m.clear();
        notifyDataSetChanged();
    }

    public final void c0() {
        G.c cVar = this.f3010z;
        if (cVar != null) {
            cVar.a(this.f3009y, this.f3008x.size());
        }
    }

    public void d0(j0.i iVar) {
        this.f3009y = true;
        this.f3008x.clear();
        if (iVar != null) {
            String strN0 = n0(iVar);
            if (strN0.length() > 0) {
                this.f3008x.add(strN0);
            }
        }
        c0();
        notifyDataSetChanged();
    }

    public void e0() {
        if (this.f3009y || !this.f3008x.isEmpty()) {
            this.f3009y = false;
            this.f3008x.clear();
            c0();
            notifyDataSetChanged();
        }
    }

    public List f0() {
        ArrayList arrayList = new ArrayList();
        if (this.f3008x.isEmpty()) {
            return arrayList;
        }
        for (int i2 = 0; i2 < this.f3456b.size(); i2++) {
            j0.i iVar = (j0.i) this.f3456b.get(i2);
            if (h0(iVar)) {
                arrayList.add(iVar);
            }
        }
        return arrayList;
    }

    public void g0(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        this.f3005u = str;
        this.f3006v = System.currentTimeMillis() + 1200;
        this.f3004t.removeCallbacks(this.f3007w);
        this.f3004t.postDelayed(this.f3007w, 1280L);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3009y) {
            m0();
        }
        return this.f3456b.size() + (j0() ? 1 : 0);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        if (P(i2) || ((this.f3469o && i2 == this.f3456b.size() - 1) || i2 < 0 || i2 >= this.f3456b.size())) {
            return null;
        }
        return this.f3456b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return ((this.f3469o && i2 == this.f3456b.size() - 1) || P(i2)) ? 1 : 0;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    public boolean h0(j0.i iVar) {
        if (iVar == null) {
            return false;
        }
        String strN0 = n0(iVar);
        return strN0.length() > 0 && this.f3008x.contains(strN0);
    }

    public boolean i0() {
        return this.f3009y;
    }

    public boolean j0() {
        return !O().isEmpty();
    }

    public void k0(j0.i iVar) {
        String strI = I(iVar);
        if (strI == null || strI.length() == 0 || !T(iVar)) {
            return;
        }
        this.f3472r.add(strI);
    }

    public void l0(j0.i iVar) {
        String strI = I(iVar);
        if (strI == null || strI.isEmpty()) {
            return;
        }
        this.f3471q.add(strI);
    }

    public void m0() {
        if (this.f3008x.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < this.f3456b.size(); i2++) {
            hashSet.add(n0((j0.i) this.f3456b.get(i2)));
        }
        Iterator it = this.f3008x.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (!hashSet.contains((String) it.next())) {
                it.remove();
                z2 = true;
            }
        }
        if (this.f3008x.isEmpty() && this.f3009y) {
            this.f3009y = false;
            z2 = true;
        }
        if (z2) {
            c0();
        }
    }

    public final String n0(j0.i iVar) {
        if (iVar == null) {
            return "";
        }
        String str = iVar.f5529a;
        if (str != null && str.length() > 0) {
            return "id:" + iVar.f5529a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ts:");
        sb.append(iVar.f5537i);
        sb.append("|uid:");
        String str2 = iVar.f5531c;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("|type:");
        String str3 = iVar.f5533e;
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3);
        sb.append("|body:");
        String str4 = iVar.f5532d;
        sb.append(str4 != null ? str4 : "");
        return sb.toString();
    }

    public void o0(G.a aVar) {
        this.f3465k = aVar;
    }

    public void p0(float f2) {
        if (f2 <= 0.0f) {
            f2 = 1.0f;
        }
        this.f3466l = f2;
        notifyDataSetChanged();
    }

    public void q0(int i2) {
        if (this.f3458d != i2) {
            this.f3458d = i2;
            notifyDataSetChanged();
        }
    }

    public void r0(String str) {
        this.f3457c = str;
        notifyDataSetChanged();
    }

    public void s0(G.b bVar) {
        this.f3473s = bVar;
    }

    public void t0(G.c cVar) {
        this.f3010z = cVar;
    }

    public void u0(Collection collection) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str != null && !str.isEmpty()) {
                    linkedHashSet.add(str);
                }
            }
        }
        if (linkedHashSet.equals(this.f3467m)) {
            return;
        }
        this.f3467m.clear();
        this.f3467m.addAll(linkedHashSet);
        notifyDataSetChanged();
    }

    public boolean v0(String str) {
        String str2;
        if (str == null || str.length() == 0 || (str2 = this.f3005u) == null || !str.equals(str2)) {
            return false;
        }
        if (System.currentTimeMillis() <= this.f3006v) {
            return true;
        }
        this.f3005u = null;
        this.f3006v = 0L;
        return false;
    }

    public boolean w0(j0.i iVar) {
        String str;
        if (iVar == null || (str = iVar.f5531c) == null || str.isEmpty() || !this.f3467m.contains(iVar.f5531c) || this.f3469o) {
            return false;
        }
        this.f3468n = iVar;
        this.f3469o = true;
        this.f3470p = iVar.f5531c;
        notifyDataSetChanged();
        return true;
    }

    public void x0() {
        P p2 = this.f3464j;
        if (p2 != null) {
            p2.K();
        }
        this.f3004t.removeCallbacks(this.f3007w);
    }

    public void y0(j0.i iVar) {
        if (iVar == null) {
            return;
        }
        if (!this.f3009y) {
            d0(iVar);
            return;
        }
        String strN0 = n0(iVar);
        if (strN0.length() == 0) {
            return;
        }
        if (this.f3008x.contains(strN0)) {
            this.f3008x.remove(strN0);
        } else {
            this.f3008x.add(strN0);
        }
        if (this.f3008x.isEmpty()) {
            this.f3009y = false;
        }
        c0();
        notifyDataSetChanged();
    }

    public void z0(Map map) {
        if (map != null) {
            this.f3460f = map;
            notifyDataSetChanged();
        }
    }
}
