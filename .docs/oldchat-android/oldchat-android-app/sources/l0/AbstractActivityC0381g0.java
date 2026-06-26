package l0;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;

/* JADX INFO: renamed from: l0.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0381g0 extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public View f6031A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View f6032B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Button f6033C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ProgressBar f6034D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ListView f6035E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f6036F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public com.im.oldchat.ui.Z f6037G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public String f6038H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f6039I = false;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f6040J = false;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f6041K = false;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f6042L = false;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public long f6043M = 0;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f6044N = false;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f6045O = "";

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f6046P = 0;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f6047Q = false;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f6048R = true;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final Handler f6049S = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final Runnable f6050T = new a();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ImageView f6051w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f6052x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f6053y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f6054z;

    /* JADX INFO: renamed from: l0.g0$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: l0.g0$a$a, reason: collision with other inner class name */
        public class C0053a implements a.h {
            public C0053a() {
            }

            @Override // com.im.oldchat.bili.a.h
            public void a(String str) {
                AbstractActivityC0381g0 abstractActivityC0381g0 = AbstractActivityC0381g0.this;
                abstractActivityC0381g0.f6040J = false;
                if (abstractActivityC0381g0.f6039I) {
                    abstractActivityC0381g0.i0(2000L);
                }
            }

            @Override // com.im.oldchat.bili.a.h
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public void b(BiliModels.QRPollResult qRPollResult) {
                AbstractActivityC0381g0 abstractActivityC0381g0 = AbstractActivityC0381g0.this;
                abstractActivityC0381g0.f6040J = false;
                abstractActivityC0381g0.f0(qRPollResult);
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            AbstractActivityC0381g0 abstractActivityC0381g0 = AbstractActivityC0381g0.this;
            if (!abstractActivityC0381g0.f6039I || abstractActivityC0381g0.f6040J || (str = abstractActivityC0381g0.f6038H) == null || str.length() == 0) {
                return;
            }
            AbstractActivityC0381g0 abstractActivityC0381g02 = AbstractActivityC0381g0.this;
            abstractActivityC0381g02.f6040J = true;
            com.im.oldchat.bili.a.z(abstractActivityC0381g02.f6038H, new C0053a());
        }
    }

    public abstract void f0(BiliModels.QRPollResult qRPollResult);

    public void g0(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("【bilibili 测试报错！！！！！】 ");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        Log.e("OldView", sb.toString());
    }

    public void h0(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("【bilibili 测试报错！！！！！】 ");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        Log.d("OldView", sb.toString());
    }

    public abstract void i0(long j2);

    public void j0(boolean z2) {
        ProgressBar progressBar = this.f6034D;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
        TextView textView = this.f6036F;
        if (textView != null) {
            if (z2) {
                textView.setText(f0.j.V1);
            } else {
                textView.setText(this.f6042L ? f0.j.O1 : f0.j.N1);
            }
        }
    }
}
