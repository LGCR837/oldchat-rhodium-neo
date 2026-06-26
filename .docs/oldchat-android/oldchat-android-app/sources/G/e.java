package g;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static void a(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        drawable.inflate(resources, xmlPullParser, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void b(Drawable drawable, int i2) {
        if (drawable instanceof InterfaceC0243D) {
            ((InterfaceC0243D) drawable).setTint(i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void c(Drawable drawable, ColorStateList colorStateList) {
        if (drawable instanceof InterfaceC0243D) {
            ((InterfaceC0243D) drawable).setTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void d(Drawable drawable, PorterDuff.Mode mode) {
        if (drawable instanceof InterfaceC0243D) {
            ((InterfaceC0243D) drawable).setTintMode(mode);
        }
    }

    public static Drawable e(Drawable drawable) {
        return !(drawable instanceof InterfaceC0243D) ? new x(drawable) : drawable;
    }
}
