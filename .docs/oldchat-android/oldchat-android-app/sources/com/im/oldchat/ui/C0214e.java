package com.im.oldchat.ui;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.oldchat.ui.AbstractC0227s;
import java.util.List;
import l0.AbstractC0400q;

/* JADX INFO: renamed from: com.im.oldchat.ui.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0214e extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f4485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f f4487c;

    /* JADX INFO: renamed from: com.im.oldchat.ui.e$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0214e.this.f4487c != null) {
                C0214e.this.f4487c.c();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.e$b */
    public class b implements View.OnLongClickListener {
        public b() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return true;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.e$c */
    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f4490a;

        public c(AbstractC0227s.c cVar) {
            this.f4490a = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0214e.this.f4487c == null || this.f4490a == null) {
                return;
            }
            C0214e.this.f4487c.a(this.f4490a);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.e$d */
    public class d implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractC0227s.c f4492a;

        public d(AbstractC0227s.c cVar) {
            this.f4492a = cVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (C0214e.this.f4487c == null || this.f4492a == null) {
                return true;
            }
            C0214e.this.f4487c.b(this.f4492a);
            return true;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.e$e, reason: collision with other inner class name */
    public static class C0034e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f4494a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f4495b;

        public C0034e() {
        }

        public /* synthetic */ C0034e(a aVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.e$f */
    public interface f {
        void a(AbstractC0227s.c cVar);

        void b(AbstractC0227s.c cVar);

        void c();
    }

    public C0214e(Activity activity, List list, f fVar) {
        this.f4485a = activity;
        this.f4486b = list;
        this.f4487c = fVar;
    }

    public final int b(int i2) {
        return Math.max(1, (int) ((i2 * this.f4485a.getResources().getDisplayMetrics().density) + 0.5f));
    }

    public AbstractC0227s.c c(int i2) {
        int i3 = i2 - 1;
        if (i3 < 0 || i3 >= this.f4486b.size()) {
            return null;
        }
        return (AbstractC0227s.c) this.f4486b.get(i3);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4486b.size() + 1;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return c(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        C0034e c0034e;
        View view2;
        View view3 = (FrameLayout) view;
        a aVar = null;
        if (view3 == null) {
            FrameLayout frameLayout = new FrameLayout(this.f4485a);
            frameLayout.setLayoutParams(new AbsListView.LayoutParams(-1, b(64)));
            ImageView imageView = new ImageView(this.f4485a);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(b(48), b(48));
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(f0.e.f5010c);
            frameLayout.addView(imageView);
            TextView textView = new TextView(this.f4485a);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 17;
            textView.setLayoutParams(layoutParams2);
            textView.setText("+");
            textView.setTextSize(26.0f);
            textView.setTextColor(this.f4485a.getResources().getColor(f0.d.f4968e));
            frameLayout.addView(textView);
            c0034e = new C0034e(aVar);
            c0034e.f4494a = imageView;
            c0034e.f4495b = textView;
            frameLayout.setTag(c0034e);
            view2 = frameLayout;
        } else {
            c0034e = (C0034e) view3.getTag();
            view2 = view3;
        }
        if (i2 == 0) {
            c0034e.f4495b.setVisibility(0);
            c0034e.f4494a.setTag(null);
            c0034e.f4494a.setImageDrawable(null);
            view2.setOnClickListener(new a());
            view2.setOnLongClickListener(new b());
            return view2;
        }
        c0034e.f4495b.setVisibility(8);
        AbstractC0227s.c cVarC = c(i2);
        AbstractC0400q.f(this.f4485a, c0034e.f4494a, cVarC == null ? "" : cVarC.f4744b, b(48));
        view2.setOnClickListener(new c(cVarC));
        view2.setOnLongClickListener(new d(cVarC));
        return view2;
    }
}
