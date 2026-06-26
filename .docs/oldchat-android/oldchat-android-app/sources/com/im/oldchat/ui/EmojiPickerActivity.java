package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.AbstractC0227s;
import d.AbstractC0235a;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import k.AbstractC0285k0;
import l0.AbstractC0400q;
import o.c;
import y.C0523a;

/* JADX INFO: loaded from: classes.dex */
public class EmojiPickerActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public HorizontalScrollView f2627A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public LinearLayout f2628B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public q f2629C;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public C0523a f2634H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f2635I;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public GestureDetector f2637K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f2638L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public LinearLayout f2639M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public TextView f2640N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public TextView f2641O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public TextView f2642P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public TextView f2643Q;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public RecyclerView f2647w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f2648x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f2649y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f2650z;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final List f2630D = new ArrayList();

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final List f2631E = new ArrayList();

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final List f2632F = new ArrayList();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f2633G = false;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public String f2636J = "全部";

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f2644R = false;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final LinkedHashSet f2645S = new LinkedHashSet();

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public String f2646T = "";

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2651a;

        public a(View view) {
            this.f2651a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            int left = this.f2651a.getLeft() - EmojiPickerActivity.this.S0(16);
            if (left < 0) {
                left = 0;
            }
            EmojiPickerActivity.this.f2627A.smoothScrollTo(left, 0);
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f2653a;

        public b(AbstractC0227s.c cVar) {
            this.f2653a = cVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                EmojiPickerActivity.this.k1(this.f2653a);
            } else if (i2 == 1) {
                EmojiPickerActivity.this.R0(this.f2653a);
            } else {
                EmojiPickerActivity.this.T0(this.f2653a);
            }
        }
    }

    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2655a;

        public c(ArrayList arrayList) {
            this.f2655a = arrayList;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = (String) this.f2655a.get(i2);
            if ("＋ 新建分类".equals(str)) {
                EmojiPickerActivity.this.d1();
            } else {
                EmojiPickerActivity.this.N0(str);
            }
        }
    }

    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2657a;

        public d(EditText editText) {
            this.f2657a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String string = this.f2657a.getText() == null ? "" : this.f2657a.getText().toString();
            String strTrim = string != null ? string.trim() : "";
            if (strTrim.length() == 0) {
                Toast.makeText(EmojiPickerActivity.this, "分类名不能为空", 0).show();
            } else if ("全部".equals(strTrim)) {
                Toast.makeText(EmojiPickerActivity.this, "“全部”为系统分类，不可作为自定义分类", 0).show();
            } else {
                EmojiPickerActivity.this.N0(AbstractC0227s.x(strTrim));
            }
        }
    }

    public class e implements DialogInterface.OnClickListener {
        public e() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            EmojiPickerActivity.this.P0();
        }
    }

    public class f implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2660a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f2661b;

        public f(ArrayList arrayList, AbstractC0227s.c cVar) {
            this.f2660a = arrayList;
            this.f2661b = cVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = (String) this.f2660a.get(i2);
            dialogInterface.dismiss();
            if ("＋ 新建分类".equals(str)) {
                EmojiPickerActivity.this.c1(this.f2661b);
            } else {
                EmojiPickerActivity.this.M0(this.f2661b, str);
            }
        }
    }

    public class g implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f2663a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f2664b;

        public g(EditText editText, AbstractC0227s.c cVar) {
            this.f2663a = editText;
            this.f2664b = cVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String string = this.f2663a.getText() == null ? "" : this.f2663a.getText().toString();
            String strTrim = string != null ? string.trim() : "";
            if (strTrim.length() == 0) {
                Toast.makeText(EmojiPickerActivity.this, "分类名不能为空", 0).show();
                return;
            }
            if ("全部".equals(strTrim)) {
                Toast.makeText(EmojiPickerActivity.this, "“全部”为系统分类，不可作为自定义分类", 0).show();
                return;
            }
            String strX = AbstractC0227s.x(strTrim);
            AbstractC0227s.c cVar = this.f2664b;
            if (cVar != null) {
                EmojiPickerActivity.this.M0(cVar, strX);
            } else {
                Toast.makeText(EmojiPickerActivity.this, "请先选择一个表情再分类", 0).show();
            }
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPickerActivity.this.finish();
        }
    }

    public class i extends GestureDetector.SimpleOnGestureListener {
        public i() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (motionEvent != null && motionEvent2 != null) {
                float x2 = motionEvent2.getX() - motionEvent.getX();
                float y2 = motionEvent2.getY() - motionEvent.getY();
                if (Math.abs(x2) >= 100.0f && Math.abs(x2) >= Math.abs(y2) && Math.abs(f2) >= 180.0f) {
                    return x2 < 0.0f ? EmojiPickerActivity.this.m1(1) : EmojiPickerActivity.this.m1(-1);
                }
            }
            return false;
        }
    }

    public class j implements View.OnTouchListener {
        public j() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (EmojiPickerActivity.this.f2637K == null) {
                return false;
            }
            EmojiPickerActivity.this.f2637K.onTouchEvent(motionEvent);
            return false;
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPickerActivity.this.b1();
        }
    }

    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPickerActivity.this.o1();
        }
    }

    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!EmojiPickerActivity.this.f2644R || EmojiPickerActivity.this.f2645S.isEmpty()) {
                Toast.makeText(EmojiPickerActivity.this, "请先选择表情", 0).show();
            } else {
                EmojiPickerActivity.this.j1();
            }
        }
    }

    public class n implements View.OnClickListener {
        public n() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!EmojiPickerActivity.this.f2644R || EmojiPickerActivity.this.f2645S.isEmpty()) {
                Toast.makeText(EmojiPickerActivity.this, "请先选择表情", 0).show();
            } else {
                EmojiPickerActivity.this.Q0();
            }
        }
    }

    public class o implements View.OnClickListener {
        public o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EmojiPickerActivity.this.U0();
        }
    }

    public class p implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2674a;

        public p(String str) {
            this.f2674a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2674a.equals(EmojiPickerActivity.this.f2636J)) {
                return;
            }
            EmojiPickerActivity.this.f2636J = this.f2674a;
            EmojiPickerActivity.this.O0(true);
        }
    }

    public class q extends RecyclerView.g {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbstractC0227s.c f2677a;

            public a(AbstractC0227s.c cVar) {
                this.f2677a = cVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiPickerActivity.this.R0(this.f2677a);
            }
        }

        public class b implements View.OnLongClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ s f2679a;

            public b(s sVar) {
                this.f2679a = sVar;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (!EmojiPickerActivity.this.f2633G || EmojiPickerActivity.this.f2644R) {
                    return false;
                }
                if (!EmojiPickerActivity.this.W0()) {
                    Toast.makeText(EmojiPickerActivity.this, "请切换到“全部”分类后再拖动排序", 0).show();
                    return true;
                }
                if (EmojiPickerActivity.this.f2634H != null) {
                    EmojiPickerActivity.this.f2634H.C(this.f2679a);
                }
                return true;
            }
        }

        public class c implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbstractC0227s.c f2681a;

            public c(AbstractC0227s.c cVar) {
                this.f2681a = cVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!EmojiPickerActivity.this.f2633G) {
                    EmojiPickerActivity.this.i1(this.f2681a);
                } else if (EmojiPickerActivity.this.f2644R) {
                    EmojiPickerActivity.this.n1(this.f2681a);
                } else {
                    EmojiPickerActivity.this.l1(this.f2681a);
                }
            }
        }

        public class d implements View.OnLongClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ s f2683a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AbstractC0227s.c f2684b;

            public d(s sVar, AbstractC0227s.c cVar) {
                this.f2683a = sVar;
                this.f2684b = cVar;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (!EmojiPickerActivity.this.f2633G) {
                    return false;
                }
                if (EmojiPickerActivity.this.W0() && !EmojiPickerActivity.this.f2644R && EmojiPickerActivity.this.f2634H != null) {
                    EmojiPickerActivity.this.f2634H.C(this.f2683a);
                    return true;
                }
                if (EmojiPickerActivity.this.f2644R) {
                    EmojiPickerActivity.this.n1(this.f2684b);
                } else {
                    EmojiPickerActivity.this.T0(this.f2684b);
                }
                return true;
            }
        }

        public q() {
        }

        @Override // android.support.v7.widget.RecyclerView.g
        public int c() {
            return EmojiPickerActivity.this.f2631E.size();
        }

        @Override // android.support.v7.widget.RecyclerView.g
        /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
        public void l(s sVar, int i2) {
            AbstractC0227s.c cVar = (AbstractC0227s.c) EmojiPickerActivity.this.f2631E.get(i2);
            sVar.V(cVar);
            sVar.f2689t.setVisibility(EmojiPickerActivity.this.f2644R && EmojiPickerActivity.this.X0(cVar) ? 0 : 8);
            sVar.f2688s.setVisibility((!EmojiPickerActivity.this.f2633G || EmojiPickerActivity.this.f2644R) ? 8 : 0);
            sVar.f2688s.setOnClickListener(new a(cVar));
            sVar.f2688s.setOnLongClickListener(new b(sVar));
            sVar.f1354a.setOnClickListener(new c(cVar));
            sVar.f1354a.setOnLongClickListener(new d(sVar, cVar));
        }

        @Override // android.support.v7.widget.RecyclerView.g
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public s n(ViewGroup viewGroup, int i2) {
            return EmojiPickerActivity.this.new s(LayoutInflater.from(viewGroup.getContext()).inflate(f0.g.s0, viewGroup, false));
        }

        public /* synthetic */ q(EmojiPickerActivity emojiPickerActivity, h hVar) {
            this();
        }
    }

    public class r extends C0523a.f {
        public r() {
        }

        @Override // y.C0523a.f
        public void B(RecyclerView.B b2, int i2) {
        }

        @Override // y.C0523a.f
        public int k(RecyclerView recyclerView, RecyclerView.B b2) {
            if (EmojiPickerActivity.this.f2633G && !EmojiPickerActivity.this.f2644R && EmojiPickerActivity.this.W0()) {
                return C0523a.f.t(15, 0);
            }
            return 0;
        }

        @Override // y.C0523a.f
        public boolean r() {
            return false;
        }

        @Override // y.C0523a.f
        public boolean y(RecyclerView recyclerView, RecyclerView.B b2, RecyclerView.B b3) {
            if (!EmojiPickerActivity.this.W0()) {
                Toast.makeText(EmojiPickerActivity.this, "请切换到“全部”分类后再拖动排序", 0).show();
                return false;
            }
            int iR = b2.r();
            int iR2 = b3.r();
            if (iR == -1 || iR2 == -1) {
                return false;
            }
            Collections.swap(EmojiPickerActivity.this.f2631E, iR, iR2);
            Collections.swap(EmojiPickerActivity.this.f2630D, iR, iR2);
            EmojiPickerActivity.this.f2629C.i(iR, iR2);
            EmojiPickerActivity emojiPickerActivity = EmojiPickerActivity.this;
            AbstractC0227s.E(emojiPickerActivity, emojiPickerActivity.f2630D);
            return true;
        }

        public /* synthetic */ r(EmojiPickerActivity emojiPickerActivity, h hVar) {
            this();
        }
    }

    public class s extends RecyclerView.B {

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public final ImageView f2687r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public final ImageView f2688s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public final TextView f2689t;

        public s(View view) {
            super(view);
            this.f2687r = (ImageView) view.findViewById(f0.f.v5);
            this.f2688s = (ImageView) view.findViewById(f0.f.f5071c0);
            this.f2689t = (TextView) view.findViewById(f0.f.J9);
        }

        public void V(AbstractC0227s.c cVar) {
            ImageView imageView = this.f2687r;
            if (imageView == null || cVar == null) {
                return;
            }
            EmojiPickerActivity.this.Y0(imageView, cVar.f4744b);
        }
    }

    public final void M0(AbstractC0227s.c cVar, String str) {
        if (cVar == null) {
            return;
        }
        String strX = AbstractC0227s.x(str);
        cVar.f4747e = strX;
        AbstractC0227s.E(this, this.f2630D);
        f1();
        if (!this.f2632F.contains(this.f2636J)) {
            this.f2636J = "全部";
        }
        O0(false);
        Toast.makeText(this, "已移动到“" + strX + "”", 0).show();
    }

    public final void N0(String str) {
        if (this.f2645S.isEmpty()) {
            Toast.makeText(this, "请先选择表情", 0).show();
            return;
        }
        String strX = AbstractC0227s.x(str);
        int i2 = 0;
        for (int i3 = 0; i3 < this.f2630D.size(); i3++) {
            AbstractC0227s.c cVar = (AbstractC0227s.c) this.f2630D.get(i3);
            if (X0(cVar)) {
                cVar.f4747e = strX;
                i2++;
            }
        }
        if (i2 <= 0) {
            Toast.makeText(this, "未选择有效表情", 0).show();
            return;
        }
        AbstractC0227s.E(this, this.f2630D);
        f1();
        if (!this.f2632F.contains(this.f2636J)) {
            this.f2636J = "全部";
        }
        U0();
        O0(false);
        Toast.makeText(this, "已移动 " + i2 + " 项到“" + strX + "”", 0).show();
    }

    public final void O0(boolean z2) {
        this.f2631E.clear();
        if (W0()) {
            this.f2631E.addAll(this.f2630D);
        } else {
            for (AbstractC0227s.c cVar : this.f2630D) {
                if (cVar != null) {
                    if (this.f2636J.equals(AbstractC0227s.x(cVar.f4747e))) {
                        this.f2631E.add(cVar);
                    }
                }
            }
        }
        this.f2629C.g();
        q1();
        r1();
        if (z2 && !this.f2631E.isEmpty()) {
            this.f2647w.T0(0);
        }
        a1();
    }

    public final void P0() {
        String str;
        if (this.f2645S.isEmpty()) {
            return;
        }
        int i2 = 0;
        for (int size = this.f2630D.size() - 1; size >= 0; size--) {
            AbstractC0227s.c cVar = (AbstractC0227s.c) this.f2630D.get(size);
            if (X0(cVar)) {
                if (cVar != null && (str = cVar.f4744b) != null && str.length() > 0) {
                    File file = new File(cVar.f4744b);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                this.f2630D.remove(size);
                i2++;
            }
        }
        AbstractC0227s.E(this, this.f2630D);
        f1();
        if (!this.f2632F.contains(this.f2636J)) {
            this.f2636J = "全部";
        }
        U0();
        O0(false);
        Toast.makeText(this, "已删除 " + i2 + " 个表情", 0).show();
    }

    public final void Q0() {
        int size = this.f2645S.size();
        if (size <= 0) {
            Toast.makeText(this, "请先选择表情", 0).show();
            return;
        }
        new c.a(this).t("批量删除").i("确定删除已选的 " + size + " 个表情吗？").k("取消", null).q("删除", new e()).v();
    }

    public final void R0(AbstractC0227s.c cVar) {
        if (cVar == null) {
            return;
        }
        if (!AbstractC0227s.p(this, cVar)) {
            Toast.makeText(this, "删除失败", 0).show();
            return;
        }
        int size = this.f2630D.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            AbstractC0227s.c cVar2 = (AbstractC0227s.c) this.f2630D.get(size);
            if (cVar2 != null) {
                String str = cVar.f4743a;
                if (str != null && str.equals(cVar2.f4743a)) {
                    this.f2630D.remove(size);
                    break;
                }
                String str2 = cVar.f4744b;
                if (str2 != null && str2.equals(cVar2.f4744b)) {
                    this.f2630D.remove(size);
                    break;
                }
            } else {
                this.f2630D.remove(size);
            }
            size--;
        }
        AbstractC0227s.E(this, this.f2630D);
        f1();
        if (!this.f2632F.contains(this.f2636J)) {
            this.f2636J = "全部";
        }
        O0(false);
    }

    public final int S0(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public final void T0(AbstractC0227s.c cVar) {
        if (this.f2633G) {
            this.f2644R = true;
            this.f2645S.clear();
            if (cVar != null) {
                String strV0 = V0(cVar);
                if (strV0.length() > 0) {
                    this.f2645S.add(strV0);
                }
            }
            p1();
            this.f2629C.g();
        }
    }

    public final void U0() {
        boolean z2 = this.f2644R || !this.f2645S.isEmpty();
        this.f2644R = false;
        this.f2645S.clear();
        if (z2) {
            p1();
            this.f2629C.g();
        }
    }

    public final String V0(AbstractC0227s.c cVar) {
        if (cVar == null) {
            return "";
        }
        String str = cVar.f4743a;
        if (str != null && str.length() > 0) {
            return "id:" + cVar.f4743a;
        }
        String str2 = cVar.f4744b;
        if (str2 == null || str2.length() <= 0) {
            return "";
        }
        return "path:" + cVar.f4744b;
    }

    public final boolean W0() {
        return "全部".equals(this.f2636J);
    }

    public final boolean X0(AbstractC0227s.c cVar) {
        if (cVar == null) {
            return false;
        }
        String strV0 = V0(cVar);
        return strV0.length() > 0 && this.f2645S.contains(strV0);
    }

    public final void Y0(ImageView imageView, String str) {
        AbstractC0400q.f(this, imageView, str, this.f2635I);
    }

    public final void Z0(boolean z2) {
        this.f2630D.clear();
        List listV = AbstractC0227s.v(this);
        if (listV != null) {
            this.f2630D.addAll(listV);
        }
        f1();
        if (z2 || !this.f2632F.contains(this.f2636J)) {
            this.f2636J = "全部";
        }
        e1();
        if (this.f2645S.isEmpty()) {
            this.f2644R = false;
        }
        O0(true);
        p1();
    }

    public final void a1() {
        String str = this.f2636J;
        if (str == null || str.trim().length() == 0) {
            str = "全部";
        }
        if (str.equals(this.f2646T)) {
            return;
        }
        getSharedPreferences("emoji_picker_ui", 0).edit().putString("last_category", str).apply();
        this.f2646T = str;
    }

    public final void b1() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, "选择表情"), 4101);
        } catch (Exception unused) {
            Toast.makeText(this, "无法选择图片", 0).show();
        }
    }

    public final void c1(AbstractC0227s.c cVar) {
        EditText editText = new EditText(this);
        editText.setHint("输入分类名（最多10字）");
        editText.setSingleLine();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        new c.a(this).t("新建分类").u(editText).k("取消", null).q("确定", new g(editText, cVar)).v();
    }

    public final void d1() {
        EditText editText = new EditText(this);
        editText.setHint("输入分类名（最多10字）");
        editText.setSingleLine();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        new c.a(this).t("新建分类").u(editText).k("取消", null).q("确定", new d(editText)).v();
    }

    public final void e1() {
        if (this.f2645S.isEmpty()) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i2 = 0; i2 < this.f2630D.size(); i2++) {
            String strV0 = V0((AbstractC0227s.c) this.f2630D.get(i2));
            if (strV0.length() > 0) {
                linkedHashSet.add(strV0);
            }
        }
        this.f2645S.retainAll(linkedHashSet);
    }

    public final void f1() {
        this.f2632F.clear();
        this.f2632F.add("全部");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (AbstractC0227s.c cVar : this.f2630D) {
            if (cVar != null) {
                String strX = AbstractC0227s.x(cVar.f4747e);
                cVar.f4747e = strX;
                linkedHashSet.add(strX);
            }
        }
        this.f2632F.addAll(linkedHashSet);
        g1();
    }

    public final void g1() {
        LinearLayout linearLayout = this.f2628B;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        for (int i2 = 0; i2 < this.f2632F.size(); i2++) {
            String str = (String) this.f2632F.get(i2);
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = S0(8);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(S0(12), S0(6), S0(12), S0(6));
            textView.setBackgroundResource(f0.e.f4985F);
            textView.setTextSize(13.0f);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxEms(8);
            textView.setText(str);
            textView.setSelected(str.equals(this.f2636J));
            s1(textView, str.equals(this.f2636J));
            textView.setOnClickListener(new p(str));
            this.f2628B.addView(textView);
        }
    }

    public final void h1() {
        String str = "全部";
        String string = getSharedPreferences("emoji_picker_ui", 0).getString("last_category", "全部");
        if (string != null && string.trim().length() != 0) {
            str = string;
        }
        this.f2636J = str;
        this.f2646T = str;
    }

    public final void i1(AbstractC0227s.c cVar) {
        String str;
        if (cVar == null || (str = cVar.f4744b) == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("emoji_path", cVar.f4744b);
        intent.putExtra("emoji_is_gif", cVar.f4745c);
        setResult(-1, intent);
        finish();
    }

    public final void j1() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f2632F.size(); i2++) {
            String str = (String) this.f2632F.get(i2);
            if (!"全部".equals(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add("未分类");
        }
        arrayList.add("＋ 新建分类");
        new c.a(this).t("批量移动到分类").g((CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]), new c(arrayList)).k("取消", null).v();
    }

    public final void k1(AbstractC0227s.c cVar) {
        if (cVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < this.f2632F.size(); i3++) {
            String str = (String) this.f2632F.get(i3);
            if (!"全部".equals(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add("未分类");
        }
        arrayList.add("＋ 新建分类");
        String strX = AbstractC0227s.x(cVar.f4747e);
        while (true) {
            if (i2 >= arrayList.size()) {
                i2 = -1;
                break;
            } else if (strX.equals(arrayList.get(i2))) {
                break;
            } else {
                i2++;
            }
        }
        new c.a(this).t("选择分类").r((CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]), i2, new f(arrayList, cVar)).k("取消", null).v();
    }

    public final void l1(AbstractC0227s.c cVar) {
        if (cVar == null) {
            return;
        }
        new c.a(this).t("管理表情").g(new CharSequence[]{"移动到分类", "删除表情", "进入多选"}, new b(cVar)).v();
    }

    public final boolean m1(int i2) {
        if (this.f2632F.isEmpty()) {
            return false;
        }
        int iIndexOf = this.f2632F.indexOf(this.f2636J);
        if (iIndexOf < 0) {
            iIndexOf = 0;
        }
        int i3 = iIndexOf + i2;
        if (i3 < 0 || i3 >= this.f2632F.size()) {
            return false;
        }
        this.f2636J = (String) this.f2632F.get(i3);
        O0(true);
        Toast.makeText(this, "分类：" + this.f2636J, 0).show();
        return true;
    }

    public final void n1(AbstractC0227s.c cVar) {
        if (cVar == null) {
            return;
        }
        String strV0 = V0(cVar);
        if (strV0.length() == 0) {
            return;
        }
        if (this.f2645S.contains(strV0)) {
            this.f2645S.remove(strV0);
        } else {
            this.f2645S.add(strV0);
        }
        if (this.f2645S.isEmpty()) {
            this.f2644R = false;
        }
        p1();
        this.f2629C.g();
    }

    public final void o1() {
        boolean z2 = this.f2633G;
        this.f2633G = !z2;
        if (z2) {
            U0();
        }
        TextView textView = this.f2648x;
        if (textView != null) {
            textView.setText(this.f2633G ? "完成" : "管理");
        }
        if (this.f2633G && !this.f2638L) {
            this.f2638L = true;
            Toast.makeText(this, "管理模式：点击表情可分类/删除；在“全部”分类长按表情可拖动排序", 0).show();
        }
        p1();
        this.f2629C.g();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) throws Throwable {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 4101 || i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        AbstractC0227s.c cVarK = AbstractC0227s.k(this, data);
        if (cVarK == null) {
            Toast.makeText(this, "不支持的表情格式或文件超过3MB", 0).show();
            return;
        }
        this.f2630D.add(cVarK);
        AbstractC0227s.E(this, this.f2630D);
        Z0(false);
        Toast.makeText(this, "已添加到“未分类”", 0).show();
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5155l);
        this.f2647w = (RecyclerView) a0(f0.f.g8);
        this.f2648x = (TextView) a0(f0.f.w0);
        this.f2649y = a0(f0.f.r0);
        this.f2650z = (TextView) a0(f0.f.D9);
        this.f2627A = (HorizontalScrollView) a0(f0.f.p8);
        this.f2628B = (LinearLayout) a0(f0.f.u6);
        this.f2639M = (LinearLayout) a0(f0.f.t6);
        this.f2640N = (TextView) a0(f0.f.C9);
        this.f2641O = (TextView) a0(f0.f.v0);
        this.f2642P = (TextView) a0(f0.f.u0);
        this.f2643Q = (TextView) a0(f0.f.t0);
        View viewA0 = a0(f0.f.s0);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new h());
        }
        this.f2647w.setLayoutManager(new GridLayoutManager(this, 4));
        h hVar = null;
        q qVar = new q(this, hVar);
        this.f2629C = qVar;
        this.f2647w.setAdapter(qVar);
        C0523a c0523a = new C0523a(new r(this, hVar));
        this.f2634H = c0523a;
        c0523a.g(this.f2647w);
        this.f2635I = S0(72);
        this.f2637K = new GestureDetector(this, new i());
        this.f2647w.setOnTouchListener(new j());
        View view = this.f2649y;
        if (view != null) {
            view.setOnClickListener(new k());
        }
        TextView textView = this.f2648x;
        if (textView != null) {
            textView.setOnClickListener(new l());
        }
        TextView textView2 = this.f2641O;
        if (textView2 != null) {
            textView2.setOnClickListener(new m());
        }
        TextView textView3 = this.f2642P;
        if (textView3 != null) {
            textView3.setOnClickListener(new n());
        }
        TextView textView4 = this.f2643Q;
        if (textView4 != null) {
            textView4.setOnClickListener(new o());
        }
        h1();
        Z0(false);
    }

    public final void p1() {
        LinearLayout linearLayout = this.f2639M;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setVisibility(this.f2633G && this.f2644R ? 0 : 8);
        int size = this.f2645S.size();
        TextView textView = this.f2640N;
        if (textView != null) {
            textView.setText("已选 " + size + " 项");
        }
        boolean z2 = size > 0;
        TextView textView2 = this.f2641O;
        if (textView2 != null) {
            textView2.setEnabled(z2);
            AbstractC0285k0.K(this.f2641O, z2 ? 1.0f : 0.5f);
        }
        TextView textView3 = this.f2642P;
        if (textView3 != null) {
            textView3.setEnabled(z2);
            AbstractC0285k0.K(this.f2642P, z2 ? 1.0f : 0.5f);
        }
    }

    public final void q1() {
        HorizontalScrollView horizontalScrollView;
        LinearLayout linearLayout = this.f2628B;
        if (linearLayout == null) {
            return;
        }
        int childCount = linearLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.f2628B.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                boolean zEquals = this.f2636J.equals(textView.getText() == null ? "" : textView.getText().toString());
                s1(textView, zEquals);
                if (zEquals && (horizontalScrollView = this.f2627A) != null) {
                    horizontalScrollView.post(new a(textView));
                }
            }
        }
    }

    public final void r1() {
        if (this.f2650z == null) {
            return;
        }
        if (!this.f2631E.isEmpty()) {
            this.f2650z.setVisibility(8);
            return;
        }
        if (W0()) {
            this.f2650z.setText("暂无表情，点击添加");
        } else {
            this.f2650z.setText("该分类暂无表情，管理中可移动表情到此");
        }
        this.f2650z.setVisibility(0);
    }

    public final void s1(TextView textView, boolean z2) {
        if (textView == null) {
            return;
        }
        textView.setTextColor(z2 ? AbstractC0235a.b(this, f0.d.f4970g) : AbstractC0235a.b(this, f0.d.f4974k));
        textView.setSelected(z2);
    }
}
