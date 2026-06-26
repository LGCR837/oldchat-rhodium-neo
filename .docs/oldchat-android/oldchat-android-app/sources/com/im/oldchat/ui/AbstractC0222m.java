package com.im.oldchat.ui;

import android.app.Activity;
import android.media.MediaRecorder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import c.AbstractC0121a;
import com.im.oldchat.ui.C0218i;
import d.AbstractC0235a;
import java.io.File;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: com.im.oldchat.ui.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0222m extends AbstractC0221l {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final a[] f4676E = {new a(".m4a", 2, 3), new a(".3gp", 1, 1)};

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f4677D;

    /* JADX INFO: renamed from: com.im.oldchat.ui.m$a */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4678a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f4679b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f4680c;

        public a(String str, int i2, int i3) {
            this.f4678a = str;
            this.f4679b = i2;
            this.f4680c = i3;
        }
    }

    public AbstractC0222m(Activity activity, String str, EditText editText, Button button, View view, View view2, View view3, View view4, View view5, View view6, View view7, ProgressBar progressBar, TextView textView, C0218i.j jVar, C0218i.k kVar) {
        super(activity, str, editText, button, view, view2, view3, view4, view5, view6, view7, progressBar, textView, jVar, kVar);
        this.f4677D = false;
    }

    public final boolean O() {
        if (AbstractC0235a.a(this.f4623a, "android.permission.RECORD_AUDIO") == 0) {
            return true;
        }
        AbstractC0121a.i(this.f4623a, new String[]{"android.permission.RECORD_AUDIO"}, 2002);
        return false;
    }

    public boolean P(int[] iArr) {
        if (iArr.length <= 0 || iArr[0] != 0) {
            S(false);
            Toast.makeText(this.f4623a, f0.j.f5207c, 0).show();
        } else if (this.f4677D) {
            S(true);
            T();
        }
        this.f4677D = false;
        return true;
    }

    public boolean Q(View view, MotionEvent motionEvent) {
        if (motionEvent == null) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f4677D = true;
            this.f4668B = false;
            this.f4667A = motionEvent.getRawY();
            if (O()) {
                S(true);
                T();
            } else {
                S(false);
            }
            return true;
        }
        if (action == 2) {
            if (this.f4642t != null) {
                N(view, motionEvent);
            }
            return true;
        }
        if (action != 1 && action != 3) {
            return true;
        }
        this.f4677D = false;
        boolean z2 = this.f4642t != null;
        boolean z3 = action == 3;
        if (z3) {
            this.f4668B = false;
        }
        S(false);
        p(!z3);
        I();
        if (z3 && z2) {
            Toast.makeText(this.f4623a, f0.j.f5243z, 0).show();
        }
        return true;
    }

    public void R() {
        if (this.f4642t != null) {
            p(false);
        }
        E(false);
    }

    public final void S(boolean z2) {
        View view = this.f4629g;
        if (view == null) {
            return;
        }
        view.setPressed(z2);
        AbstractC0285k0.K(this.f4629g, z2 ? 0.68f : 1.0f);
    }

    public final void T() {
        boolean z2;
        if (this.f4642t != null) {
            return;
        }
        File file = new File(this.f4623a.getCacheDir(), "voice");
        if (!file.exists()) {
            file.mkdirs();
        }
        int i2 = 0;
        while (true) {
            a[] aVarArr = f4676E;
            if (i2 >= aVarArr.length) {
                z2 = false;
                break;
            } else {
                if (U(file, aVarArr[i2])) {
                    z2 = true;
                    break;
                }
                i2++;
            }
        }
        if (!((z2 || !U(file, new a(".3gp", 1, 1))) ? z2 : true)) {
            this.f4642t = null;
            this.f4643u = null;
            this.f4668B = false;
            S(false);
            Toast.makeText(this.f4623a, f0.j.f5239v, 0).show();
            return;
        }
        this.f4644v = System.currentTimeMillis();
        this.f4647y = false;
        v();
        View view = this.f4629g;
        if (view instanceof TextView) {
            ((TextView) view).setText(f0.j.f5234q);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean U(java.io.File r7, com.im.oldchat.ui.AbstractC0222m.a r8) {
        /*
            r6 = this;
            r0 = 0
            if (r8 == 0) goto L14
            java.lang.String r1 = r8.f4678a     // Catch: java.lang.Throwable -> L11
            if (r1 == 0) goto L14
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L11
            if (r1 != 0) goto Le
            goto L14
        Le:
            java.lang.String r1 = r8.f4678a     // Catch: java.lang.Throwable -> L11
            goto L16
        L11:
            r2 = r0
            goto L5c
        L14:
            java.lang.String r1 = ".3gp"
        L16:
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L11
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L11
            r3.<init>()     // Catch: java.lang.Throwable -> L11
            java.lang.String r4 = "voice_"
            r3.append(r4)     // Catch: java.lang.Throwable -> L11
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L11
            r3.append(r4)     // Catch: java.lang.Throwable -> L11
            r3.append(r1)     // Catch: java.lang.Throwable -> L11
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L11
            r2.<init>(r7, r1)     // Catch: java.lang.Throwable -> L11
            android.media.MediaRecorder r7 = new android.media.MediaRecorder     // Catch: java.lang.Throwable -> L5b
            r7.<init>()     // Catch: java.lang.Throwable -> L5b
            r0 = 1
            r7.setAudioSource(r0)     // Catch: java.lang.Throwable -> L58
            int r1 = r8.f4679b     // Catch: java.lang.Throwable -> L58
            r7.setOutputFormat(r1)     // Catch: java.lang.Throwable -> L58
            int r8 = r8.f4680c     // Catch: java.lang.Throwable -> L58
            r7.setAudioEncoder(r8)     // Catch: java.lang.Throwable -> L58
            java.lang.String r8 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> L58
            r7.setOutputFile(r8)     // Catch: java.lang.Throwable -> L58
            r7.prepare()     // Catch: java.lang.Throwable -> L58
            r7.start()     // Catch: java.lang.Throwable -> L58
            r6.f4642t = r7     // Catch: java.lang.Throwable -> L58
            r6.f4643u = r2     // Catch: java.lang.Throwable -> L58
            return r0
        L58:
            r0 = r7
            goto L5c
        L5b:
        L5c:
            if (r0 == 0) goto L63
            r0.release()     // Catch: java.lang.Throwable -> L62
            goto L63
        L62:
        L63:
            if (r2 == 0) goto L6e
            boolean r7 = r2.exists()
            if (r7 == 0) goto L6e
            r2.delete()
        L6e:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.AbstractC0222m.U(java.io.File, com.im.oldchat.ui.m$a):boolean");
    }

    @Override // com.im.oldchat.ui.AbstractC0220k
    public void p(boolean z2) {
        MediaRecorder mediaRecorder = this.f4642t;
        if (mediaRecorder == null) {
            S(false);
            this.f4668B = false;
            View view = this.f4629g;
            if (view instanceof TextView) {
                ((TextView) view).setText(f0.j.f5236s);
                return;
            }
            return;
        }
        try {
            mediaRecorder.stop();
        } catch (Exception unused) {
        }
        this.f4642t.release();
        this.f4642t = null;
        s();
        S(false);
        View view2 = this.f4629g;
        if (view2 instanceof TextView) {
            ((TextView) view2).setText(f0.j.f5236s);
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f4644v;
        if (jCurrentTimeMillis > 60000) {
            jCurrentTimeMillis = 60000;
        }
        boolean z3 = z2 && this.f4668B && !this.f4647y;
        this.f4668B = false;
        if (!z2) {
            D();
            return;
        }
        if (jCurrentTimeMillis < 800) {
            D();
            Toast.makeText(this.f4623a, "Record too short", 0).show();
            return;
        }
        File file = this.f4643u;
        if (file == null || !file.exists()) {
            return;
        }
        if (z3) {
            L(this.f4643u);
        } else {
            x(this.f4643u, jCurrentTimeMillis);
        }
    }
}
