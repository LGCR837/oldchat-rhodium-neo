package m0;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import c.AbstractComponentCallbacksC0165t;
import com.im.oldchat.MainActivity;
import g0.d;
import j0.w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import k.AbstractC0285k0;
import k.U1;
import l0.C0408x;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends AbstractComponentCallbacksC0165t {

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public ListView f6485Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public EditText f6486a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public View f6487b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public View f6488c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public View f6489d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public View f6490e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public View f6491f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public ProgressBar f6492g0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public String f6494i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public C0408x f6495j0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public boolean f6493h0 = false;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public List f6496k0 = new ArrayList();

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public List f6497l0 = new ArrayList();

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public List f6498m0 = new ArrayList();

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public List f6499n0 = new ArrayList();

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public boolean f6500o0 = false;
    public boolean p0 = false;
    public boolean q0 = false;
    public boolean r0 = false;
    public String s0 = "";

    public class a extends U1 {
        public a() {
        }

        @Override // k.T1
        public void a(View view) {
            View view2 = f.this.f6489d0;
            if (view2 != null) {
                view2.setVisibility(8);
            }
        }
    }

    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6502a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6503b;

        public b(String str, boolean z2) {
            this.f6502a = str;
            this.f6503b = z2;
        }

        @Override // g0.d.i
        public void b(String str) {
            if (f.this.e() == null) {
                f.this.T0(false, null);
                return;
            }
            C0408x.b bVarR0 = f.this.R0(this.f6502a);
            boolean zL0 = this.f6503b ? f.this.L0(bVarR0) : false;
            if (bVarR0 != null || zL0) {
                f.this.X0();
            }
            Toast.makeText(f.this.e(), this.f6503b ? "已接受好友申请" : "已拒绝好友申请", 0).show();
            f.this.T0(false, null);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (f.this.e() == null) {
                f.this.T0(false, null);
                return;
            }
            f.this.T0(false, null);
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(f.this.e(), f.this.M0(i2, str), 0).show();
        }
    }

    public final boolean L0(C0408x.b bVar) {
        String str;
        if (bVar != null && (str = bVar.f6372b) != null && str.length() != 0) {
            String str2 = bVar.f6372b;
            if (!N0(this.f6496k0, str2) && !N0(this.f6497l0, str2)) {
                w wVar = new w();
                wVar.f5646b = str2;
                String str3 = bVar.f6373c;
                wVar.f5648d = str3;
                wVar.f5647c = str3;
                wVar.f5650f = bVar.f6374d;
                wVar.f5651g = bVar.f6375e;
                wVar.f5653i = System.currentTimeMillis() / 1000;
                this.f6496k0.add(0, wVar);
                Q0();
                return true;
            }
        }
        return false;
    }

    public final String M0(int i2, String str) {
        String strSubstring = "";
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.length() > 0 && !"null".equalsIgnoreCase(strTrim)) {
                if (strTrim.startsWith("{") && strTrim.endsWith("}")) {
                    try {
                        JSONObject jSONObject = new JSONObject(strTrim);
                        strTrim = jSONObject.optString("error", "").trim();
                        try {
                            if (strTrim.length() == 0) {
                                strTrim = jSONObject.optString("message", "").trim();
                            }
                            if (strTrim.length() == 0) {
                                strTrim = jSONObject.optString("msg", "").trim();
                            }
                        } catch (Exception unused) {
                        }
                        strSubstring = strTrim;
                    } catch (Exception unused2) {
                    }
                } else {
                    strSubstring = strTrim;
                }
            }
        }
        if (strSubstring.length() > 64) {
            strSubstring = strSubstring.substring(0, 64);
        }
        if (strSubstring.length() <= 0) {
            if (i2 <= 0) {
                return "处理失败，请稍后重试";
            }
            return "处理失败（" + i2 + "）";
        }
        if (i2 <= 0) {
            return "处理失败：" + strSubstring;
        }
        return "处理失败（" + i2 + "）：" + strSubstring;
    }

    public final boolean N0(List list, String str) {
        if (list != null && str != null && str.length() != 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                w wVar = (w) list.get(i2);
                if (wVar != null && str.equals(wVar.f5646b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void O0(View view) {
        if (view == null || e() == null) {
            return;
        }
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) e().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception unused) {
        }
    }

    public void P0() {
        if (e() == null) {
            return;
        }
        List listB = j0.f.b(e());
        List listB2 = j0.c.b(e());
        this.f6498m0.clear();
        this.f6496k0.clear();
        this.f6497l0.clear();
        if (!listB.isEmpty()) {
            this.f6498m0.addAll(listB);
        }
        if (!listB2.isEmpty()) {
            V0(listB2);
        }
        X0();
    }

    public final void Q0() {
        if (e() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f6496k0);
        arrayList.addAll(this.f6497l0);
        j0.c.c(e(), arrayList);
    }

    public final C0408x.b R0(String str) {
        List list;
        if (str == null || str.length() == 0 || (list = this.f6499n0) == null) {
            return null;
        }
        boolean z2 = false;
        C0408x.b bVar = null;
        for (int size = list.size() - 1; size >= 0; size--) {
            C0408x.b bVar2 = (C0408x.b) this.f6499n0.get(size);
            if (bVar2 != null && str.equals(bVar2.f6371a)) {
                if (bVar == null) {
                    bVar = bVar2;
                }
                this.f6499n0.remove(size);
                z2 = true;
            }
        }
        if (!z2) {
            return null;
        }
        if (e() != null) {
            int size2 = this.f6499n0.size();
            i0.b.c(e(), size2);
            if (e() instanceof MainActivity) {
                ((MainActivity) e()).w0(size2);
            }
        }
        return bVar;
    }

    public void S0(String str, boolean z2) {
        if (str == null || str.length() == 0 || e() == null) {
            return;
        }
        if (this.r0) {
            if (str.equals(this.s0)) {
                return;
            }
            Toast.makeText(e(), "正在处理上一条申请，请稍候", 0).show();
            return;
        }
        String str2 = this.f6494i0;
        if (str2 == null || str2.length() == 0) {
            Toast.makeText(e(), "请先登录", 0).show();
            return;
        }
        T0(true, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("request_id", str);
            jSONObject.put("accept", z2);
            g0.d.S("/friends/respond", jSONObject, this.f6494i0, new b(str, z2));
        } catch (Exception unused) {
            T0(false, null);
        }
    }

    public final void T0(boolean z2, String str) {
        this.r0 = z2;
        if (!z2 || str == null) {
            str = "";
        }
        this.s0 = str;
        U0((this.f6500o0 && this.p0 && this.q0) ? false : true);
    }

    public void U0(boolean z2) {
        ProgressBar progressBar = this.f6492g0;
        if (progressBar != null) {
            progressBar.setVisibility((z2 || this.r0) ? 0 : 8);
        }
    }

    public void V0(List list) {
        this.f6496k0.clear();
        this.f6497l0.clear();
        long jCurrentTimeMillis = System.currentTimeMillis() - 86400000;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            w wVar = (w) it.next();
            long j2 = wVar.f5653i;
            long j3 = j2 > 0 ? j2 * 1000 : 0L;
            if (j3 <= 0 || j3 < jCurrentTimeMillis) {
                this.f6497l0.add(wVar);
            } else {
                this.f6496k0.add(wVar);
            }
        }
    }

    public void W0() {
        boolean z2 = this.f6493h0;
        this.f6493h0 = !z2;
        if (z2) {
            AbstractC0285k0.a(this.f6489d0).a(0.0f).d(120L).f(new a()).i();
            return;
        }
        this.f6489d0.setVisibility(0);
        AbstractC0285k0.K(this.f6489d0, 0.0f);
        AbstractC0285k0.a(this.f6489d0).a(1.0f).d(120L).f(null).i();
    }

    public void X0() {
        C0408x c0408x = this.f6495j0;
        if (c0408x == null) {
            return;
        }
        c0408x.o(this.f6498m0, this.f6496k0, this.f6497l0, this.f6499n0);
        U0((this.f6500o0 && this.p0 && this.q0) ? false : true);
    }
}
