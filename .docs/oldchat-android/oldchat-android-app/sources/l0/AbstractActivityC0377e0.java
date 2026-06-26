package l0;

import android.R;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import com.im.oldchat.ui.OldViewFavoritesActivity;
import com.im.oldchat.ui.OldViewHistoryActivity;
import x.U;

/* JADX INFO: renamed from: l0.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0377e0 extends AbstractActivityC0379f0 {

    /* JADX INFO: renamed from: l0.e0$a */
    public class a implements a.h {
        public a() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0377e0 abstractActivityC0377e0 = AbstractActivityC0377e0.this;
            abstractActivityC0377e0.f6041K = false;
            StringBuilder sb = new StringBuilder();
            sb.append("二维码请求失败: ");
            sb.append(str != null ? str : "unknown");
            abstractActivityC0377e0.g0(sb.toString());
            TextView textView = AbstractActivityC0377e0.this.f6052x;
            if (textView != null) {
                textView.setText((str == null || str.length() <= 0) ? AbstractActivityC0377e0.this.getString(f0.j.e2) : str);
            }
            AbstractActivityC0377e0 abstractActivityC0377e02 = AbstractActivityC0377e0.this;
            if (str == null) {
                str = "获取二维码失败";
            }
            Toast.makeText(abstractActivityC0377e02, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.QRAuthCodeResponse qRAuthCodeResponse) {
            AbstractActivityC0377e0 abstractActivityC0377e0 = AbstractActivityC0377e0.this;
            abstractActivityC0377e0.f6038H = qRAuthCodeResponse.authCode;
            abstractActivityC0377e0.f6043M = System.currentTimeMillis();
            AbstractActivityC0377e0 abstractActivityC0377e02 = AbstractActivityC0377e0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("requestQrCode: success authCode=");
            String str = AbstractActivityC0377e0.this.f6038H;
            sb.append(str != null && str.length() > 0);
            abstractActivityC0377e02.h0(sb.toString());
            TextView textView = AbstractActivityC0377e0.this.f6052x;
            if (textView != null) {
                textView.setText(f0.j.j2);
            }
            AbstractActivityC0377e0 abstractActivityC0377e03 = AbstractActivityC0377e0.this;
            if (abstractActivityC0377e03.f6051w != null) {
                try {
                    AbstractActivityC0377e0.this.f6051w.setImageBitmap(h0.b.a(qRAuthCodeResponse.url, (int) (abstractActivityC0377e03.getResources().getDisplayMetrics().density * 220.0f)));
                } catch (Exception unused) {
                    AbstractActivityC0377e0.this.f6051w.setImageResource(R.drawable.ic_media_play);
                }
            }
            AbstractActivityC0377e0.this.M0();
        }
    }

    /* JADX INFO: renamed from: l0.e0$b */
    public class b implements U.c {
        public b() {
        }

        @Override // x.U.c
        public boolean onMenuItemClick(MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == f0.f.f5070c) {
                AbstractActivityC0377e0.this.L0(false);
                return true;
            }
            if (itemId != f0.f.f5072d) {
                if (itemId == f0.f.f5068b) {
                    AbstractActivityC0377e0.this.startActivity(new Intent(AbstractActivityC0377e0.this, (Class<?>) OldViewHistoryActivity.class));
                    return true;
                }
                if (itemId != f0.f.f5066a) {
                    return false;
                }
                AbstractActivityC0377e0.this.startActivity(new Intent(AbstractActivityC0377e0.this, (Class<?>) OldViewFavoritesActivity.class));
                return true;
            }
            h0.a.a(AbstractActivityC0377e0.this);
            AbstractActivityC0377e0 abstractActivityC0377e0 = AbstractActivityC0377e0.this;
            abstractActivityC0377e0.f6038H = null;
            abstractActivityC0377e0.f6041K = false;
            abstractActivityC0377e0.N0();
            com.im.oldchat.ui.Z z2 = AbstractActivityC0377e0.this.f6037G;
            if (z2 != null) {
                z2.g(null);
            }
            AbstractActivityC0377e0.this.H0();
            return true;
        }
    }

    public void D0() {
        String str;
        com.im.oldchat.ui.Z z2;
        String strB = h0.a.b(this);
        boolean zE = h0.a.e(this);
        StringBuilder sb = new StringBuilder();
        sb.append("ensureLoginState: token=");
        sb.append(strB != null && strB.length() > 0);
        sb.append(" expired=");
        sb.append(zE);
        sb.append(" loginInProgress=");
        sb.append(this.f6041K);
        sb.append(" authCode=");
        String str2 = this.f6038H;
        sb.append(str2 != null && str2.length() > 0);
        h0(sb.toString());
        if (strB != null && strB.length() > 0 && !zE) {
            I0();
            if (!this.f6044N && (z2 = this.f6037G) != null && z2.getCount() == 0) {
                h0("ensureLoginState: has token -> loadRecommend");
                F0(strB);
            }
            this.f6041K = false;
            return;
        }
        if (!this.f6041K || (str = this.f6038H) == null || str.length() <= 0) {
            h0("ensureLoginState: guest");
            H0();
            return;
        }
        TextView textView = this.f6052x;
        if (textView != null) {
            textView.setText(f0.j.j2);
        }
        if (this.f6039I) {
            return;
        }
        h0("ensureLoginState: resume polling");
        M0();
    }

    public void E0() {
        View view = this.f6054z;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public abstract void F0(String str);

    public void G0() {
        h0("requestQrCode: start");
        com.im.oldchat.bili.a.C(new a());
    }

    public void H0() {
        com.im.oldchat.ui.Z z2;
        this.f6041K = false;
        N0();
        this.f6042L = true;
        View view = this.f6054z;
        if (view != null) {
            view.setVisibility(0);
        }
        TextView textView = this.f6053y;
        if (textView != null) {
            textView.setText(f0.j.W1);
        }
        ImageView imageView = this.f6051w;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        Button button = this.f6033C;
        if (button != null) {
            button.setVisibility(8);
        }
        TextView textView2 = this.f6052x;
        if (textView2 != null) {
            textView2.setText(f0.j.g2);
        }
        TextView textView3 = this.f6036F;
        if (textView3 != null) {
            textView3.setText(this.f6044N ? f0.j.a2 : f0.j.O1);
        }
        if (this.f6044N || (z2 = this.f6037G) == null || z2.getCount() != 0) {
            return;
        }
        h0("guest: loadRecommend");
        F0(null);
    }

    public void I0() {
        E0();
        this.f6042L = false;
        TextView textView = this.f6036F;
        if (textView != null) {
            textView.setText(f0.j.N1);
        }
    }

    public void J0() {
        View view = this.f6054z;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void K0(View view) {
        x.U u2 = new x.U(this, view);
        u2.b().inflate(f0.h.f5174a, u2.a());
        String strB = h0.a.b(this);
        boolean z2 = (strB == null || strB.length() <= 0 || h0.a.e(this)) ? false : true;
        MenuItem menuItemFindItem = u2.a().findItem(f0.f.f5070c);
        MenuItem menuItemFindItem2 = u2.a().findItem(f0.f.f5072d);
        if (menuItemFindItem != null) {
            menuItemFindItem.setVisible(!z2);
        }
        if (menuItemFindItem2 != null) {
            menuItemFindItem2.setVisible(z2);
        }
        u2.c(new b());
        u2.d();
    }

    public void L0(boolean z2) {
        N0();
        if (z2) {
            h0.a.a(this);
        }
        this.f6041K = true;
        this.f6042L = false;
        this.f6043M = System.currentTimeMillis();
        h0("startLogin: clearToken=" + z2);
        J0();
        TextView textView = this.f6036F;
        if (textView != null) {
            textView.setText(f0.j.N1);
        }
        ImageView imageView = this.f6051w;
        if (imageView != null) {
            imageView.setVisibility(0);
            this.f6051w.setImageResource(R.drawable.ic_media_play);
        }
        TextView textView2 = this.f6052x;
        if (textView2 != null) {
            textView2.setText(f0.j.f2);
        }
        TextView textView3 = this.f6053y;
        if (textView3 != null) {
            textView3.setText(f0.j.Y1);
        }
        Button button = this.f6033C;
        if (button != null) {
            button.setText(f0.j.K1);
            this.f6033C.setVisibility(0);
        }
        com.im.oldchat.ui.Z z3 = this.f6037G;
        if (z3 != null) {
            z3.g(null);
        }
        j0(false);
        G0();
    }

    public void M0() {
        String str = this.f6038H;
        if (str == null || str.length() == 0) {
            return;
        }
        if (this.f6043M <= 0 || System.currentTimeMillis() - this.f6043M <= 180000) {
            this.f6039I = true;
            this.f6040J = false;
            h0("startPolling: begin");
            i0(2000L);
            return;
        }
        TextView textView = this.f6052x;
        if (textView != null) {
            textView.setText(f0.j.e2);
        }
        this.f6041K = false;
        h0("startPolling: expired");
    }

    public void N0() {
        this.f6039I = false;
        this.f6040J = false;
        this.f6049S.removeCallbacks(this.f6050T);
        h0("stopPolling");
    }

    @Override // l0.AbstractActivityC0381g0
    public void f0(BiliModels.QRPollResult qRPollResult) {
        if (this.f6039I) {
            if (qRPollResult == null) {
                h0("poll: null response");
                i0(2000L);
                return;
            }
            int i2 = qRPollResult.code;
            h0("poll: code=" + i2);
            if (i2 == 0 && qRPollResult.data != null) {
                N0();
                this.f6041K = false;
                BiliModels.QRPollResponse qRPollResponse = qRPollResult.data;
                String str = qRPollResponse.accessToken;
                String strK0 = k0(qRPollResponse.cookieInfo);
                BiliModels.QRPollResponse qRPollResponse2 = qRPollResult.data;
                h0.a.g(this, str, strK0, qRPollResponse2.expiresIn, qRPollResponse2.mid);
                TextView textView = this.f6052x;
                if (textView != null) {
                    textView.setText(f0.j.h2);
                }
                I0();
                h0("poll: login success -> loadRecommend");
                F0(str);
                return;
            }
            if (i2 == 86090) {
                TextView textView2 = this.f6052x;
                if (textView2 != null) {
                    textView2.setText(f0.j.i2);
                }
                i0(2000L);
                return;
            }
            if (i2 == 86038) {
                TextView textView3 = this.f6052x;
                if (textView3 != null) {
                    textView3.setText(f0.j.e2);
                }
                this.f6041K = false;
                N0();
                return;
            }
            if (i2 == 86039 || i2 == 86101) {
                TextView textView4 = this.f6052x;
                if (textView4 != null) {
                    textView4.setText(f0.j.j2);
                }
                i0(2000L);
                return;
            }
            TextView textView5 = this.f6052x;
            if (textView5 != null) {
                String str2 = qRPollResult.message;
                if (str2 == null) {
                    str2 = "登录失败";
                }
                textView5.setText(str2);
            }
            i0(2000L);
        }
    }

    @Override // l0.AbstractActivityC0381g0
    public void i0(long j2) {
        this.f6049S.removeCallbacks(this.f6050T);
        this.f6049S.postDelayed(this.f6050T, j2);
    }
}
