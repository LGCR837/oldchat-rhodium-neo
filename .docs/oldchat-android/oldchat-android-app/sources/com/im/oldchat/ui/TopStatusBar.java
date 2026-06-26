package com.im.oldchat.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import o0.G;

/* JADX INFO: loaded from: classes.dex */
public class TopStatusBar extends FrameLayout implements G.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ProgressBar f4144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f4145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f4146c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4147d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f4148e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f4149f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f4150g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TopStatusBar.this.e(o0.G.d().f());
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TopStatusBar.this.f4148e != null) {
                TopStatusBar.this.f4148e.a();
            }
        }
    }

    public interface c {
        void a();
    }

    public TopStatusBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4149f = new Handler(Looper.getMainLooper());
        this.f4150g = new a();
        d(context);
    }

    @Override // o0.G.b
    public void a(boolean z2) {
        e(z2);
    }

    public final void d(Context context) {
        LayoutInflater.from(context).inflate(f0.g.f1, (ViewGroup) this, true);
        this.f4144a = (ProgressBar) findViewById(f0.f.J7);
        this.f4145b = (TextView) findViewById(f0.f.o8);
        View viewFindViewById = findViewById(f0.f.n8);
        this.f4146c = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setClickable(true);
            this.f4146c.setFocusable(true);
            this.f4146c.setOnClickListener(new b());
        }
        o0.G.d().c(this);
        e(o0.G.d().f());
    }

    public final void e(boolean z2) {
        this.f4149f.removeCallbacks(this.f4150g);
        if (z2) {
            this.f4146c.setVisibility(8);
        } else {
            long jE = o0.G.d().e(5000L);
            if (jE > 0) {
                this.f4146c.setVisibility(8);
                this.f4149f.postDelayed(this.f4150g, jE);
                return;
            }
            this.f4146c.setVisibility(0);
        }
        this.f4144a.setVisibility(this.f4147d || this.f4146c.getVisibility() == 0 ? 0 : 8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f4149f.removeCallbacks(this.f4150g);
        o0.G.d().j(this);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int i4 = (int) ((getResources().getDisplayMetrics().density * 40.0f) + 0.5f);
        if (i4 <= 0 || getMeasuredHeight() <= i4) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), i4);
    }

    public void setLoading(boolean z2) {
        this.f4147d = z2;
        e(o0.G.d().f());
    }

    public void setOnRetryClickListener(c cVar) {
        this.f4148e = cVar;
    }
}
