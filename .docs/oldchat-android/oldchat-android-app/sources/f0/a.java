package f0;

import android.R;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractC0121a;
import com.im.oldchat.ui.widget.FontAwesomeTextView;
import d.AbstractC0235a;
import java.util.ArrayList;
import java.util.List;
import o0.C0442h;
import o0.V;
import o0.Y;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends o.d {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f4954r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static boolean f4955s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static boolean f4956t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static Typeface f4957u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static Typeface f4958v;

    public final void P(List list, String str) {
        if (AbstractC0235a.a(this, str) != 0) {
            list.add(str);
        }
    }

    public final void Q() {
        if (C0442h.c()) {
            return;
        }
        try {
            setTheme(Y.a(this));
        } catch (Throwable unused) {
        }
    }

    public final void R() {
        W();
        S();
        T();
        n0.b.f(findViewById(R.id.content));
    }

    public final void S() {
        View viewFindViewById = findViewById(R.id.content);
        if (viewFindViewById == null) {
            return;
        }
        try {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, b.f4959a);
            viewFindViewById.clearAnimation();
            viewFindViewById.startAnimation(animationLoadAnimation);
        } catch (Throwable unused) {
        }
    }

    public final void T() {
        LayoutAnimationController layoutAnimationControllerLoadLayoutAnimation;
        View viewFindViewById = findViewById(R.id.content);
        if (viewFindViewById == null) {
            return;
        }
        try {
            layoutAnimationControllerLoadLayoutAnimation = AnimationUtils.loadLayoutAnimation(this, b.f4960b);
        } catch (Throwable unused) {
            layoutAnimationControllerLoadLayoutAnimation = null;
        }
        if (layoutAnimationControllerLoadLayoutAnimation == null) {
            return;
        }
        U(viewFindViewById, layoutAnimationControllerLoadLayoutAnimation);
    }

    public final void U(View view, LayoutAnimationController layoutAnimationController) {
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            if (!d0(absListView)) {
                absListView.setLayoutAnimation(layoutAnimationController);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                U(viewGroup.getChildAt(i2), layoutAnimationController);
            }
        }
    }

    public final void V(View view) {
        if ((view instanceof TextView) && !(view instanceof FontAwesomeTextView)) {
            TextView textView = (TextView) view;
            Typeface typefaceB0 = e0(textView) ? b0() : c0();
            if (typefaceB0 != null) {
                Typeface typeface = textView.getTypeface();
                textView.setTypeface(typefaceB0, typeface != null ? typeface.getStyle() : 0);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                V(viewGroup.getChildAt(i2));
            }
        }
    }

    public final void W() {
        View viewFindViewById = findViewById(R.id.content);
        if (viewFindViewById == null) {
            return;
        }
        V(viewFindViewById);
    }

    public final void X() {
        if (C0442h.c()) {
            return;
        }
        boolean zG = V.g(this);
        boolean z2 = true;
        int i2 = zG ? 2 : 1;
        if (o.f.i() != i2) {
            o.f.B(i2);
        }
        try {
            H().C(i2);
            if ((getResources().getConfiguration().uiMode & 48) != 32) {
                z2 = false;
            }
            if (z2 != zG) {
                H().d();
            }
        } catch (Throwable unused) {
        }
    }

    public final void Y() {
        if (f4954r) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 23) {
            f4954r = true;
            return;
        }
        if (C0442h.c()) {
            f4954r = true;
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (i2 >= 33) {
            P(arrayList, "android.permission.READ_MEDIA_IMAGES");
            P(arrayList, "android.permission.READ_MEDIA_VIDEO");
            P(arrayList, "android.permission.READ_MEDIA_AUDIO");
            P(arrayList, "android.permission.POST_NOTIFICATIONS");
        } else {
            P(arrayList, "android.permission.READ_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            f4954r = true;
        } else {
            if (f4955s) {
                return;
            }
            f4955s = true;
            AbstractC0121a.i(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 4101);
        }
    }

    public final void Z() {
        if (f4956t) {
            return;
        }
        f4956t = true;
        try {
            f4957u = Typeface.createFromAsset(getAssets(), "fonts/inter_regular.ttf");
        } catch (Throwable unused) {
            f4957u = null;
        }
        try {
            f4958v = Typeface.createFromAsset(getAssets(), "fonts/jetbrains_mono_regular.ttf");
        } catch (Throwable unused2) {
            f4958v = null;
        }
    }

    public View a0(int i2) {
        return super.findViewById(i2);
    }

    @Override // o.d, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addContentView(view, layoutParams);
        R();
    }

    public final Typeface b0() {
        Z();
        Typeface typeface = f4958v;
        return typeface != null ? typeface : Typeface.MONOSPACE;
    }

    public final Typeface c0() {
        Z();
        Typeface typeface = f4957u;
        return typeface != null ? typeface : Typeface.SANS_SERIF;
    }

    public final boolean d0(View view) {
        return view == null || view.getId() == f.Q6;
    }

    public final boolean e0(TextView textView) {
        Object tag = textView.getTag();
        return tag != null && "square_mono".equals(String.valueOf(tag));
    }

    @Override // o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        Q();
        X();
        super.onCreate(bundle);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 != 4101) {
            return;
        }
        f4955s = false;
        f4954r = true;
        if (iArr == null || iArr.length == 0) {
            return;
        }
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (i3 < iArr.length) {
            if (iArr[i3] != 0) {
                String str = (strArr == null || i3 >= strArr.length) ? "" : strArr[i3];
                if ("android.permission.POST_NOTIFICATIONS".equals(str)) {
                    z3 = true;
                } else {
                    z2 = true;
                }
                if (str != null && str.length() > 0 && !AbstractC0121a.j(this, str)) {
                    z4 = true;
                }
            }
            i3++;
        }
        if (z2 || z3) {
            String str2 = (z2 && z3) ? "通知和媒体权限未授予，部分功能可能受限" : z3 ? "通知权限未授予，可能收不到消息提醒" : "媒体读取权限未授予，图片/视频/音频功能受限";
            if (z4) {
                str2 = str2 + "，可在系统设置中手动开启";
            }
            Toast.makeText(this, str2, 0).show();
        }
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onStart() {
        super.onStart();
        X();
        Y();
    }

    @Override // o.d, android.app.Activity
    public void setContentView(int i2) {
        super.setContentView(i2);
        R();
    }

    @Override // o.d, android.app.Activity
    public void setContentView(View view) {
        super.setContentView(view);
        R();
    }

    @Override // o.d, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        R();
    }
}
