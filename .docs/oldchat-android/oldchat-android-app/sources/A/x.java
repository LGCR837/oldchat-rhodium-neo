package a;

import android.content.res.TypedArray;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static boolean a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, boolean z2) {
        return !e(xmlPullParser, str) ? z2 : typedArray.getBoolean(i2, z2);
    }

    public static int b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !e(xmlPullParser, str) ? i3 : typedArray.getColor(i2, i3);
    }

    public static float c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, float f2) {
        return !e(xmlPullParser, str) ? f2 : typedArray.getFloat(i2, f2);
    }

    public static int d(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !e(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static boolean e(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }
}
