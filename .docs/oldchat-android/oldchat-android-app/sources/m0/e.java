package m0;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.im.oldchat.MainActivity;
import com.im.oldchat.ui.AddFriendActivity;
import com.im.oldchat.ui.ChatActivity;
import com.im.oldchat.ui.GroupChatActivity;
import com.im.oldchat.ui.GroupCreateActivity;
import com.im.oldchat.ui.NotificationChatActivity;
import com.im.oldchat.ui.UserSpaceActivity;
import g0.d;
import j0.r;
import j0.w;
import j0.x;
import j0.y;
import java.util.ArrayList;
import java.util.List;
import l0.C0408x;
import o0.G;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e extends m0.f {
    public final Handler t0 = new Handler(Looper.getMainLooper());
    public long u0;
    public long v0;
    public long w0;
    public int x0;

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6469b;

        /* JADX INFO: renamed from: m0.e$a$a, reason: collision with other inner class name */
        public class RunnableC0059a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f6471a;

            public RunnableC0059a(int i2) {
                this.f6471a = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (e.this.x()) {
                    a aVar = a.this;
                    e.this.o1(aVar.f6468a, this.f6471a);
                }
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (e.this.x()) {
                    e.this.n1();
                }
            }
        }

        public a(int i2, int i3) {
            this.f6468a = i2;
            this.f6469b = i3;
        }

        @Override // g0.d.i
        public void b(String str) {
            if (this.f6468a == e.this.x0 && e.this.x() && e.this.e() != null) {
                try {
                    List listA = m0.d.a(new JSONObject(str).getJSONArray("friends"));
                    e.this.V0(listA);
                    j0.c.c(e.this.e(), listA);
                    r.e(e.this.e(), listA);
                    r.a(e.this.e(), listA);
                    x.c(e.this.e(), listA);
                    y.b(e.this.e(), listA);
                    e.this.v0 = 0L;
                    e eVar = e.this;
                    eVar.p0 = true;
                    eVar.q1();
                } catch (Exception unused) {
                    if (e.this.e() != null && !e.this.l1()) {
                        Toast.makeText(e.this.e(), "解析好友列表失败", 0).show();
                    }
                    e eVar2 = e.this;
                    eVar2.p0 = true;
                    eVar2.q0 = true;
                    eVar2.X0();
                }
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            boolean z2;
            int i3;
            if (this.f6468a == e.this.x0 && e.this.x() && e.this.e() != null) {
                boolean zV = g0.e.v(i2, str);
                List listK1 = e.this.k1();
                boolean z3 = (listK1 == null || listK1.isEmpty()) ? false : true;
                if (z3) {
                    e.this.V0(listK1);
                }
                if (e.this.m1(i2) && (i3 = this.f6469b) < 2) {
                    e.this.t0.postDelayed(new RunnableC0059a(i3 + 1), 900L);
                }
                if (e.this.r1(i2, this.f6469b)) {
                    e.this.t0.postDelayed(new b(), 1800L);
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!zV && !z2 && e.this.s1(i2, z3, this.f6469b)) {
                    Toast.makeText(e.this.e(), "加载好友失败: " + i2, 0).show();
                }
                e eVar = e.this;
                eVar.p0 = true;
                eVar.q0 = true;
                eVar.X0();
            }
        }
    }

    public class b implements d.i {
        public b() {
        }

        @Override // g0.d.i
        public void b(String str) {
            try {
                List listC = m0.d.c(new JSONObject(str).getJSONArray("requests"));
                e eVar = e.this;
                eVar.f6499n0 = listC;
                i0.b.c(eVar.e(), listC.size());
                if (e.this.e() instanceof MainActivity) {
                    ((MainActivity) e.this.e()).w0(listC.size());
                }
                e eVar2 = e.this;
                eVar2.q0 = true;
                eVar2.X0();
            } catch (Exception unused) {
                e.this.f6499n0.clear();
                e eVar3 = e.this;
                eVar3.q0 = true;
                eVar3.X0();
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            e.this.f6499n0.clear();
            e eVar = e.this;
            eVar.q0 = true;
            eVar.X0();
        }
    }

    public class c implements C0408x.c {
        public c() {
        }

        @Override // l0.C0408x.c
        public void a(String str) {
            e.this.S0(str, true);
        }

        @Override // l0.C0408x.c
        public void b(w wVar) {
            Intent intent = new Intent(e.this.e(), (Class<?>) ChatActivity.class);
            intent.putExtra("friend_id", wVar.f5645a);
            intent.putExtra("friend_uid", wVar.f5646b);
            intent.putExtra("friend_name", j0.d.b(wVar));
            intent.putExtra("friend_avatar", wVar.f5651g);
            e.this.F0(intent);
        }

        @Override // l0.C0408x.c
        public void c(String str) {
            e.this.S0(str, false);
        }

        @Override // l0.C0408x.c
        public void d(w wVar) {
            Intent intent = new Intent(e.this.e(), (Class<?>) UserSpaceActivity.class);
            intent.putExtra("uid", wVar.f5646b);
            e.this.F0(intent);
        }

        @Override // l0.C0408x.c
        public void e() {
            e.this.F0(new Intent(e.this.e(), (Class<?>) NotificationChatActivity.class));
        }

        @Override // l0.C0408x.c
        public void f(j0.e eVar) {
            Intent intent = new Intent(e.this.e(), (Class<?>) GroupChatActivity.class);
            intent.putExtra("group_id", eVar.f5504a);
            intent.putExtra("group_name", eVar.f5505b);
            intent.putExtra("group_avatar", eVar.f5506c);
            intent.putExtra("group_role", eVar.f5509f);
            e.this.F0(intent);
        }
    }

    /* JADX INFO: renamed from: m0.e$e, reason: collision with other inner class name */
    public class ViewOnFocusChangeListenerC0060e implements View.OnFocusChangeListener {
        public ViewOnFocusChangeListenerC0060e() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z2) {
            if (z2) {
                e eVar = e.this;
                if (eVar.f6493h0) {
                    eVar.W0();
                }
            }
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = e.this.f6486a0;
            if (editText != null) {
                editText.setText("");
                e.this.f6486a0.clearFocus();
                e eVar = e.this;
                eVar.O0(eVar.f6486a0);
            }
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            e.this.W0();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            e.this.W0();
            e.this.F0(new Intent(e.this.e(), (Class<?>) AddFriendActivity.class));
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            e.this.W0();
            e.this.F0(new Intent(e.this.e(), (Class<?>) GroupCreateActivity.class));
        }
    }

    public class k implements d.i {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (e.this.x()) {
                    e.this.p1();
                }
            }
        }

        public k() {
        }

        @Override // g0.d.i
        public void b(String str) {
            boolean z2;
            JSONArray jSONArrayOptJSONArray;
            if (!e.this.x() || e.this.e() == null) {
                return;
            }
            boolean zJ1 = false;
            List listB = null;
            try {
                jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("groups");
            } catch (Exception unused) {
            }
            if (jSONArrayOptJSONArray != null) {
                listB = m0.d.b(jSONArrayOptJSONArray);
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2 || listB == null) {
                e.this.j1();
            } else {
                if (listB.isEmpty() && e.this.l1()) {
                    zJ1 = e.this.j1();
                }
                if (!zJ1) {
                    e.this.f6498m0.clear();
                    e.this.f6498m0.addAll(listB);
                    j0.f.d(e.this.e(), e.this.f6498m0);
                    j0.j.d(e.this.e(), e.this.f6498m0);
                }
            }
            e eVar = e.this;
            eVar.f6500o0 = true;
            eVar.X0();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (!e.this.x() || e.this.e() == null) {
                return;
            }
            if (!e.this.j1() && e.this.l1() && e.this.r1(i2, 2)) {
                e.this.t0.postDelayed(new a(), 1800L);
            }
            e eVar = e.this;
            eVar.f6500o0 = true;
            eVar.X0();
        }
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public View K(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(f0.g.f5158m0, viewGroup, false);
        this.f6485Z = (ListView) viewInflate.findViewById(f0.f.N6);
        this.f6486a0 = (EditText) viewInflate.findViewById(f0.f.y4);
        this.f6487b0 = viewInflate.findViewById(f0.f.f5052M);
        this.f6488c0 = viewInflate.findViewById(f0.f.X4);
        this.f6489d0 = viewInflate.findViewById(f0.f.Y4);
        this.f6490e0 = viewInflate.findViewById(f0.f.Z4);
        this.f6491f0 = viewInflate.findViewById(f0.f.a5);
        this.f6492g0 = (ProgressBar) viewInflate.findViewById(f0.f.o7);
        this.f6494i0 = e().getSharedPreferences("auth", 0).getString("access_token", "");
        C0408x c0408x = new C0408x(e(), new c());
        this.f6495j0 = c0408x;
        this.f6485Z.setAdapter((ListAdapter) c0408x);
        EditText editText = this.f6486a0;
        if (editText != null) {
            editText.setImeOptions(3);
            this.f6486a0.addTextChangedListener(new d());
            this.f6486a0.setOnFocusChangeListener(new ViewOnFocusChangeListenerC0060e());
        }
        View view = this.f6487b0;
        if (view != null) {
            view.setOnClickListener(new f());
        }
        this.f6488c0.setOnClickListener(new g());
        this.f6490e0.setOnClickListener(new h());
        this.f6491f0.setOnClickListener(new i());
        this.f6485Z.setOnScrollListener(new j());
        return viewInflate;
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void V() {
        this.t0.removeCallbacksAndMessages(null);
        super.V();
    }

    @Override // c.AbstractComponentCallbacksC0165t
    public void Z() {
        super.Z();
        this.f6494i0 = e().getSharedPreferences("auth", 0).getString("access_token", "");
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.u0 = 2400 + jCurrentTimeMillis;
        this.v0 = jCurrentTimeMillis + 15000;
        this.w0 = 0L;
        this.t0.removeCallbacksAndMessages(null);
        this.f6500o0 = false;
        this.p0 = false;
        this.q0 = false;
        U0(true);
        P0();
        p1();
        n1();
    }

    public final boolean j1() {
        List listB;
        if (!x() || e() == null || (listB = j0.f.b(e())) == null || listB.isEmpty()) {
            return false;
        }
        this.f6498m0.clear();
        this.f6498m0.addAll(listB);
        j0.j.d(e(), this.f6498m0);
        return true;
    }

    public final List k1() {
        if (!x() || e() == null) {
            return new ArrayList();
        }
        List listB = j0.c.b(e());
        return listB == null ? new ArrayList() : listB;
    }

    public final boolean l1() {
        return System.currentTimeMillis() <= this.v0;
    }

    public final boolean m1(int i2) {
        return i2 <= 0 || i2 == 408 || i2 == 429 || i2 == 500 || i2 == 502 || i2 == 503 || i2 == 504;
    }

    public void n1() {
        int i2 = this.x0 + 1;
        this.x0 = i2;
        o1(i2, 0);
    }

    public final void o1(int i2, int i3) {
        String str = this.f6494i0;
        if (str != null && !str.isEmpty() && G.d().f()) {
            g0.d.R("/friends", this.f6494i0, new a(i2, i3));
            return;
        }
        V0(k1());
        this.p0 = true;
        this.q0 = true;
        X0();
    }

    public final void p1() {
        String str = this.f6494i0;
        if (str != null && !str.isEmpty() && G.d().f()) {
            g0.d.R("/groups/list", this.f6494i0, new k());
            return;
        }
        if (!j1() && e() != null) {
            j0.j.d(e(), this.f6498m0);
        }
        this.f6500o0 = true;
        X0();
    }

    public void q1() {
        g0.d.R("/friends/requests", this.f6494i0, new b());
    }

    public final boolean r1(int i2, int i3) {
        if (!m1(i2) || i3 < 2) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.w0 < (l1() ? 2500L : 12000L)) {
            return false;
        }
        this.w0 = jCurrentTimeMillis;
        return true;
    }

    public final boolean s1(int i2, boolean z2, int i3) {
        if (l1() || z2) {
            return false;
        }
        return (i3 >= 2 || !m1(i2)) && System.currentTimeMillis() > this.u0;
    }

    public class d implements TextWatcher {
        public d() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            String string = editable == null ? "" : editable.toString();
            C0408x c0408x = e.this.f6495j0;
            if (c0408x != null) {
                c0408x.g(string);
            }
            View view = e.this.f6487b0;
            if (view != null) {
                view.setVisibility((string == null || string.trim().length() <= 0) ? 8 : 0);
            }
            e eVar = e.this;
            if (eVar.f6493h0) {
                eVar.W0();
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public class j implements AbsListView.OnScrollListener {
        public j() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 0) {
                e eVar = e.this;
                if (eVar.f6493h0) {
                    eVar.W0();
                }
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }
    }
}
