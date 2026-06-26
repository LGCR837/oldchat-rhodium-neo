package com.im.oldchat.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class FontAwesomeTextView extends TextView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Typeface f4809a;

    public FontAwesomeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public final void a(Context context) {
        if (isInEditMode() || context == null) {
            return;
        }
        if (f4809a == null) {
            try {
                f4809a = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
            } catch (Throwable unused) {
                f4809a = null;
            }
        }
        Typeface typeface = f4809a;
        if (typeface != null) {
            setTypeface(typeface);
        }
    }
}
