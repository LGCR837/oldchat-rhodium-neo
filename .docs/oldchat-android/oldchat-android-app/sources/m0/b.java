package m0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import c.AbstractActivityC0167u;
import c.AbstractComponentCallbacksC0165t;
import com.im.oldchat.ui.AddFriendActivity;
import com.im.oldchat.ui.ChatActivity;
import com.im.oldchat.ui.GroupChatActivity;
import com.im.oldchat.ui.GroupCreateActivity;
import com.im.oldchat.ui.NotificationChatActivity;
import com.im.oldchat.ui.widget.TiltFrameLayout;
import g0.j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import m0.i;
import m0.j;

/* JADX INFO: loaded from: classes.dex */
public class b extends AbstractComponentCallbacksC0165t implements i.e {

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public Runnable f6435b0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f6438e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public i f6439f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public PopupWindow f6440g0;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final Handler f6433Z = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final ExecutorService f6434a0 = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f6436c0 = 0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public String f6437d0 = "";

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final j.InterfaceC0042j f6441h0 = new a();

    /* JADX INFO: renamed from: m0.b$b, reason: collision with other inner class name */
    public class ViewOnClickListenerC0057b implements View.OnClickListener {
        public ViewOnClickListenerC0057b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.Z0(view);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f6435b0 = null;
            b.this.W0();
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6445a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6446b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f6447c;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f6449a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ List f6450b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ List f6451c;

            public a(List list, List list2, List list3) {
                this.f6449a = list;
                this.f6450b = list2;
                this.f6451c = list3;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!b.this.x() || b.this.e() == null) {
                    return;
                }
                d dVar = d.this;
                if (dVar.f6447c != b.this.f6436c0 || b.this.f6439f0 == null) {
                    return;
                }
                b.this.f6439f0.n(this.f6449a, this.f6450b, this.f6451c);
            }
        }

        public d(Context context, int i2, int i3) {
            this.f6445a = context;
            this.f6446b = i2;
            this.f6447c = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            List listA = l.a(this.f6445a, this.f6446b);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            b.this.a1(listA, arrayList, arrayList2, arrayList3);
            b.this.f6433Z.post(new a(arrayList, arrayList2, arrayList3));
        }
    }

    public class e implements j.d {
        public e() {
        }

        @Override // m0.j.d
        public void a() {
            b.this.Y0(0L);
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.T0();
            if (b.this.e() != null) {
                b.this.F0(new Intent(b.this.e(), (Class<?>) AddFriendActivity.class));
            }
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.T0();
            if (b.this.e() != null) {
                b.this.F0(new Intent(b.this.e(), (Class<?>) GroupCreateActivity.class));
            }
        }
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(f0.g.f5154k0, viewGroup, false);
        i iVar = new i(viewInflate, c1("fonts/MPLUSRounded1c-Bold.ttf"), this);
        this.f6439f0 = iVar;
        iVar.q(this.f6438e0);
        View viewFindViewById = viewInflate.findViewById(f0.f.f5044E);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new ViewOnClickListenerC0057b());
        }
        return viewInflate;
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void L() {
        super.L();
        T0();
        Runnable runnable = this.f6435b0;
        if (runnable != null) {
            this.f6433Z.removeCallbacks(runnable);
            this.f6435b0 = null;
        }
        this.f6434a0.shutdownNow();
    }

    public final TiltFrameLayout S0(Context context, String str) {
        TiltFrameLayout tiltFrameLayout = new TiltFrameLayout(context);
        tiltFrameLayout.setClickable(true);
        tiltFrameLayout.setFocusable(true);
        tiltFrameLayout.setBackgroundResource(f0.e.f4993N);
        TextView textView = new TextView(context);
        textView.setPadding(U0(16), U0(10), U0(16), U0(10));
        textView.setText(str);
        textView.setTextColor(m().getColor(f0.d.f4973j));
        textView.setTextSize(2, 15.0f);
        tiltFrameLayout.addView(textView, new FrameLayout.LayoutParams(-2, -2));
        return tiltFrameLayout;
    }

    public final void T0() {
        PopupWindow popupWindow = this.f6440g0;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.f6440g0 = null;
        }
    }

    public final int U0(int i2) {
        return e() == null ? i2 : (int) ((e().getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void V() {
        super.V();
        g0.j.u().x(this.f6441h0);
        T0();
    }

    public final int V0() {
        if (e() == null) {
            return 0;
        }
        return e().getSharedPreferences("notification", 0).getInt("unread_count", 0);
    }

    public final void W0() {
        if (!x() || e() == null) {
            return;
        }
        Context applicationContext = e().getApplicationContext();
        int iV0 = V0();
        int i2 = this.f6436c0 + 1;
        this.f6436c0 = i2;
        this.f6434a0.execute(new d(applicationContext, iV0, i2));
    }

    public void X0() {
        if (!x() || e() == null) {
            return;
        }
        this.f6437d0 = e().getSharedPreferences("auth", 0).getString("access_token", "");
        Y0(0L);
    }

    public final void Y0(long j2) {
        Runnable runnable = this.f6435b0;
        if (runnable != null) {
            this.f6433Z.removeCallbacks(runnable);
        }
        c cVar = new c();
        this.f6435b0 = cVar;
        if (j2 <= 0) {
            this.f6433Z.post(cVar);
        } else {
            this.f6433Z.postDelayed(cVar, j2);
        }
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        this.f6438e0 = g0.j.u().w();
        g0.j.u().r(this.f6441h0);
        i iVar = this.f6439f0;
        if (iVar != null) {
            iVar.q(this.f6438e0);
        }
        X0();
        b1();
    }

    public final void Z0(View view) {
        if (e() == null) {
            return;
        }
        T0();
        AbstractActivityC0167u abstractActivityC0167uE = e();
        LinearLayout linearLayout = new LinearLayout(abstractActivityC0167uE);
        linearLayout.setOrientation(1);
        TiltFrameLayout tiltFrameLayoutS0 = S0(abstractActivityC0167uE, "添加联系人");
        tiltFrameLayoutS0.setOnClickListener(new f());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = U0(8);
        linearLayout.addView(tiltFrameLayoutS0, layoutParams);
        TiltFrameLayout tiltFrameLayoutS02 = S0(abstractActivityC0167uE, "创建群聊");
        tiltFrameLayoutS02.setOnClickListener(new g());
        linearLayout.addView(tiltFrameLayoutS02, new LinearLayout.LayoutParams(-2, -2));
        PopupWindow popupWindow = new PopupWindow((View) linearLayout, -2, -2, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        linearLayout.measure(0, 0);
        popupWindow.showAsDropDown(view, view.getWidth() - linearLayout.getMeasuredWidth(), U0(6));
        this.f6440g0 = popupWindow;
    }

    @Override // m0.i.e
    public void a(View view, n nVar) {
        j.f(this, view, nVar, this.f6437d0, new e());
    }

    public final void a1(List list, List list2, List list3, List list4) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            n nVar = (n) list.get(i2);
            if (nVar != null && !nVar.f6570c) {
                boolean z2 = (nVar.f6580m > 0 || nVar.f6582o) && !nVar.f6584q;
                if (nVar.f6569b) {
                    if (z2) {
                        list2.add(nVar);
                    } else {
                        list4.add(nVar);
                    }
                } else if (nVar.f6572e) {
                    list4.add(nVar);
                } else if (z2) {
                    list2.add(nVar);
                } else {
                    list3.add(nVar);
                }
            }
        }
    }

    @Override // m0.i.e
    public void b(n nVar) {
        if (nVar == null || e() == null) {
            return;
        }
        if (nVar.f6569b) {
            F0(new Intent(e(), (Class<?>) NotificationChatActivity.class));
            return;
        }
        if (!nVar.f6568a) {
            Intent intent = new Intent(e(), (Class<?>) ChatActivity.class);
            intent.putExtra("friend_uid", nVar.f6573f);
            intent.putExtra("friend_name", nVar.f6574g);
            intent.putExtra("friend_avatar", nVar.f6578k);
            intent.putExtra("unread_count", nVar.f6580m);
            F0(intent);
            return;
        }
        Intent intent2 = new Intent(e(), (Class<?>) GroupChatActivity.class);
        intent2.putExtra("group_id", nVar.f6573f);
        intent2.putExtra("group_name", nVar.f6574g);
        intent2.putExtra("group_avatar", nVar.f6578k);
        intent2.putExtra("group_role", nVar.f6581n);
        intent2.putExtra("unread_count", nVar.f6580m);
        F0(intent2);
    }

    public final void b1() {
        String str = this.f6437d0;
        if (str == null || str.length() <= 0 || e() == null) {
            return;
        }
        g0.j.u().C(e(), this.f6437d0);
        g0.j.u().B(e(), this.f6437d0);
    }

    public final Typeface c1(String str) {
        if (e() == null) {
            return null;
        }
        try {
            return Typeface.createFromAsset(e().getAssets(), str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public class a implements j.InterfaceC0042j {
        public a() {
        }

        @Override // g0.j.InterfaceC0042j
        public void a(g0.m mVar) {
            b.this.Y0(220L);
        }

        @Override // g0.j.InterfaceC0042j
        public void b(g0.l lVar) {
            b.this.Y0(220L);
        }

        @Override // g0.j.InterfaceC0042j
        public void d(g0.o oVar) {
            b.this.Y0(220L);
        }

        @Override // g0.j.InterfaceC0042j
        public void f(g0.n nVar) {
            b.this.Y0(220L);
        }

        @Override // g0.j.InterfaceC0042j
        public void g(boolean z2) {
            b.this.f6438e0 = z2;
            if (b.this.f6439f0 != null) {
                b.this.f6439f0.q(z2);
            }
            if (z2) {
                b.this.b1();
            }
        }

        @Override // g0.j.InterfaceC0042j
        public void e(g0.p pVar) {
        }

        @Override // g0.j.InterfaceC0042j
        public void c(String str, String str2, long j2) {
        }
    }
}
