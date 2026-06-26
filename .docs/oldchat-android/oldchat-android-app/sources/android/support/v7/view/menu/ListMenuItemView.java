package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.j;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import p.AbstractC0460a;
import x.f0;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements j.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ImageView f977b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RadioButton f978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f979d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CheckBox f980e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f981f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ImageView f982g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f983h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f984i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Context f985j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f986k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f987l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f988m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public LayoutInflater f989n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f990o;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7697A);
    }

    private LayoutInflater getInflater() {
        if (this.f989n == null) {
            this.f989n = LayoutInflater.from(getContext());
        }
        return this.f989n;
    }

    private void setSubMenuArrowVisible(boolean z2) {
        ImageView imageView = this.f982g;
        if (imageView != null) {
            imageView.setVisibility(z2 ? 0 : 8);
        }
    }

    public final void a() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(p.g.f7823g, (ViewGroup) this, false);
        this.f980e = checkBox;
        addView(checkBox);
    }

    @Override // android.support.v7.view.menu.j.a
    public void b(g gVar, int i2) {
        this.f976a = gVar;
        this.f988m = i2;
        setVisibility(gVar.isVisible() ? 0 : 8);
        setTitle(gVar.h(this));
        setCheckable(gVar.isCheckable());
        f(gVar.z(), gVar.f());
        setIcon(gVar.getIcon());
        setEnabled(gVar.isEnabled());
        setSubMenuArrowVisible(gVar.hasSubMenu());
    }

    public final void c() {
        ImageView imageView = (ImageView) getInflater().inflate(p.g.f7824h, (ViewGroup) this, false);
        this.f977b = imageView;
        addView(imageView, 0);
    }

    @Override // android.support.v7.view.menu.j.a
    public boolean d() {
        return false;
    }

    public final void e() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(p.g.f7826j, (ViewGroup) this, false);
        this.f978c = radioButton;
        addView(radioButton);
    }

    public void f(boolean z2, char c2) {
        int i2 = (z2 && this.f976a.z()) ? 0 : 8;
        if (i2 == 0) {
            this.f981f.setText(this.f976a.g());
        }
        if (this.f981f.getVisibility() != i2) {
            this.f981f.setVisibility(i2);
        }
    }

    @Override // android.support.v7.view.menu.j.a
    public g getItemData() {
        return this.f976a;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundDrawable(this.f983h);
        TextView textView = (TextView) findViewById(p.f.f7815y);
        this.f979d = textView;
        int i2 = this.f984i;
        if (i2 != -1) {
            textView.setTextAppearance(this.f985j, i2);
        }
        this.f981f = (TextView) findViewById(p.f.f7810t);
        ImageView imageView = (ImageView) findViewById(p.f.f7813w);
        this.f982g = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f987l);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.f977b != null && this.f986k) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f977b.getLayoutParams();
            int i4 = layoutParams.height;
            if (i4 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i4;
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setCheckable(boolean z2) {
        CompoundButton compoundButton;
        View view;
        if (!z2 && this.f978c == null && this.f980e == null) {
            return;
        }
        if (this.f976a.l()) {
            if (this.f978c == null) {
                e();
            }
            compoundButton = this.f978c;
            view = this.f980e;
        } else {
            if (this.f980e == null) {
                a();
            }
            compoundButton = this.f980e;
            view = this.f978c;
        }
        if (!z2) {
            CheckBox checkBox = this.f980e;
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            RadioButton radioButton = this.f978c;
            if (radioButton != null) {
                radioButton.setVisibility(8);
                return;
            }
            return;
        }
        compoundButton.setChecked(this.f976a.isChecked());
        int i2 = z2 ? 0 : 8;
        if (compoundButton.getVisibility() != i2) {
            compoundButton.setVisibility(i2);
        }
        if (view == null || view.getVisibility() == 8) {
            return;
        }
        view.setVisibility(8);
    }

    public void setChecked(boolean z2) {
        CompoundButton compoundButton;
        if (this.f976a.l()) {
            if (this.f978c == null) {
                e();
            }
            compoundButton = this.f978c;
        } else {
            if (this.f980e == null) {
                a();
            }
            compoundButton = this.f980e;
        }
        compoundButton.setChecked(z2);
    }

    public void setForceShowIcon(boolean z2) {
        this.f990o = z2;
        this.f986k = z2;
    }

    public void setIcon(Drawable drawable) {
        boolean z2 = this.f976a.y() || this.f990o;
        if (z2 || this.f986k) {
            ImageView imageView = this.f977b;
            if (imageView == null && drawable == null && !this.f986k) {
                return;
            }
            if (imageView == null) {
                c();
            }
            if (drawable == null && !this.f986k) {
                this.f977b.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f977b;
            if (!z2) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f977b.getVisibility() != 0) {
                this.f977b.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f979d.getVisibility() != 8) {
                this.f979d.setVisibility(8);
            }
        } else {
            this.f979d.setText(charSequence);
            if (this.f979d.getVisibility() != 0) {
                this.f979d.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        f0 f0VarS = f0.s(getContext(), attributeSet, p.j.q1, i2, 0);
        this.f983h = f0VarS.f(p.j.s1);
        this.f984i = f0VarS.l(p.j.r1, -1);
        this.f986k = f0VarS.a(p.j.t1, false);
        this.f985j = context;
        this.f987l = f0VarS.f(p.j.u1);
        f0VarS.t();
    }
}
