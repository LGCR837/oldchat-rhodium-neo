package com.im.oldchat.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.C0218i;
import java.io.File;
import k.AbstractC0285k0;
import o.c;
import o0.W;

/* JADX INFO: renamed from: com.im.oldchat.ui.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0221l extends AbstractC0219j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f4667A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f4668B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public File f4669C;

    /* JADX INFO: renamed from: com.im.oldchat.ui.l$a */
    public class a implements DialogInterface.OnCancelListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AbstractC0221l.this.E(true);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.l$b */
    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                AbstractC0221l.this.M();
            } else {
                AbstractC0221l.this.E(true);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.l$c */
    public class c implements W.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f4672a;

        public c(File file) {
            this.f4672a = file;
        }

        @Override // o0.W.b
        public void a(String str) {
            AbstractC0221l.this.k(false);
            this.f4672a.delete();
            AbstractC0221l.this.J(str);
        }

        @Override // o0.W.b
        public void b(String str) {
            AbstractC0221l.this.k(false);
            this.f4672a.delete();
            String strTrim = str == null ? "" : str.trim();
            if (strTrim.length() == 0) {
                Toast.makeText(AbstractC0221l.this.f4623a, f0.j.f5178B, 0).show();
            } else {
                AbstractC0221l.this.H(strTrim);
                AbstractC0221l.this.K();
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.l$d */
    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractC0221l.this.G();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.l$e */
    public class e implements DialogInterface.OnClickListener {
        public e() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractC0221l.this.f4626d.performClick();
        }
    }

    public AbstractC0221l(Activity activity, String str, EditText editText, Button button, View view, View view2, View view3, View view4, View view5, View view6, View view7, ProgressBar progressBar, TextView textView, C0218i.j jVar, C0218i.k kVar) {
        super(activity, str, editText, button, view, view2, view3, view4, view5, view6, view7, progressBar, textView, jVar, kVar);
        this.f4667A = 0.0f;
        this.f4668B = false;
    }

    public void D() {
        File file = this.f4643u;
        if (file == null || !file.exists()) {
            return;
        }
        this.f4643u.delete();
    }

    public void E(boolean z2) {
        File file = this.f4669C;
        if (file != null && file.exists()) {
            this.f4669C.delete();
        }
        this.f4669C = null;
        if (z2) {
            Toast.makeText(this.f4623a, f0.j.f5243z, 0).show();
        }
    }

    public final float F(float f2) {
        Activity activity = this.f4623a;
        if (activity == null) {
            return f2;
        }
        float f3 = activity.getResources().getDisplayMetrics().density;
        if (f3 <= 0.0f) {
            f3 = 1.0f;
        }
        return f2 * f3;
    }

    public final void G() {
        EditText editText;
        if (this.f4623a == null || (editText = this.f4625c) == null) {
            return;
        }
        editText.requestFocus();
        Editable text = this.f4625c.getText();
        this.f4625c.setSelection(text == null ? 0 : text.length());
        Object systemService = this.f4623a.getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            ((InputMethodManager) systemService).showSoftInput(this.f4625c, 1);
        }
    }

    public final void H(String str) {
        EditText editText;
        if (str == null) {
            return;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0 || (editText = this.f4625c) == null) {
            return;
        }
        editText.requestFocus();
        this.f4625c.setText(strTrim);
        this.f4625c.setSelection(strTrim.length());
    }

    public void I() {
        this.f4668B = false;
        this.f4667A = 0.0f;
        View view = this.f4629g;
        if (view instanceof TextView) {
            ((TextView) view).setText(f0.j.f5236s);
        }
    }

    public final void J(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0 || "asr_failed".equalsIgnoreCase(strTrim) || "http_502".equalsIgnoreCase(strTrim) || "invalid_response".equalsIgnoreCase(strTrim)) {
            Toast.makeText(this.f4623a, f0.j.f5179C, 0).show();
            return;
        }
        if (strTrim.length() > 32) {
            strTrim = strTrim.substring(0, 32) + "...";
        }
        Toast.makeText(this.f4623a, "转写失败: " + strTrim, 0).show();
    }

    public final void K() {
        Activity activity = this.f4623a;
        if (activity == null) {
            return;
        }
        if (activity.isFinishing()) {
            Toast.makeText(this.f4623a, f0.j.f5180D, 0).show();
            return;
        }
        c.a aVarJ = new c.a(this.f4623a).s(f0.j.f5182F).h(f0.j.f5181E).j(f0.j.f5177A, new d());
        if (this.f4626d != null) {
            aVarJ.p(f0.j.f5183G, new e());
        }
        aVarJ.v();
    }

    public void L(File file) {
        this.f4669C = file;
        Activity activity = this.f4623a;
        if (activity == null || activity.isFinishing()) {
            E(false);
        } else {
            new c.a(this.f4623a).s(f0.j.f5241x).g(new CharSequence[]{this.f4623a.getString(f0.j.f5242y), this.f4623a.getString(f0.j.f5240w)}, new b()).n(new a()).v();
        }
    }

    public final void M() {
        File file = this.f4669C;
        this.f4669C = null;
        if (file == null || !file.exists()) {
            Toast.makeText(this.f4623a, f0.j.f5239v, 0).show();
            return;
        }
        try {
            byte[] bArrM = m(file);
            Toast.makeText(this.f4623a, f0.j.f5184H, 0).show();
            k(true);
            o0.W.e(this.f4624b, bArrM, file.getName(), t(file.getName()), new c(file));
        } catch (Exception unused) {
            file.delete();
            Toast.makeText(this.f4623a, f0.j.f5239v, 0).show();
        }
    }

    public void N(View view, MotionEvent motionEvent) {
        if (view == null || motionEvent == null) {
            return;
        }
        float F2 = F(48.0f);
        float F3 = F(16.0f);
        float rawY = this.f4667A - motionEvent.getRawY();
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        float f2 = -F3;
        boolean z2 = true;
        boolean z3 = x2 < f2 || x2 > ((float) view.getWidth()) + F3 || y2 < f2 || y2 > ((float) view.getHeight()) + F3;
        if (rawY < F2 && !z3) {
            z2 = false;
        }
        if (z2 == this.f4668B) {
            return;
        }
        this.f4668B = z2;
        View view2 = this.f4629g;
        if (view2 instanceof TextView) {
            ((TextView) view2).setText(z2 ? f0.j.f5235r : f0.j.f5234q);
        }
        View view3 = this.f4629g;
        if (view3 != null) {
            AbstractC0285k0.K(view3, z2 ? 0.52f : 0.68f);
        }
    }
}
