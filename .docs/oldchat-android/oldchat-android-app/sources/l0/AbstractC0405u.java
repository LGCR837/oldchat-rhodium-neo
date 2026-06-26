package l0;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import java.util.List;
import o0.C;

/* JADX INFO: renamed from: l0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0405u {
    public static void a(SpannableStringBuilder spannableStringBuilder, String str) {
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) str);
        int length2 = spannableStringBuilder.length();
        if (length2 > length) {
            spannableStringBuilder.setSpan(new StyleSpan(1), length, length2, 33);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.04f), length, length2, 33);
        }
    }

    public static void b(SpannableStringBuilder spannableStringBuilder, Context context, int i2, int i3) {
        String string;
        if (i2 <= i3) {
            string = context != null ? context.getString(f0.j.f5197U) : "点击查看聊天记录 >";
        } else if (context != null) {
            string = context.getString(f0.j.f5196T, Integer.valueOf(i2));
        } else {
            string = "查看" + i2 + "条聊天记录 >";
        }
        if (string.length() == 0) {
            return;
        }
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append('\n').append((CharSequence) string);
        int length2 = spannableStringBuilder.length();
        if (length2 > length) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.88f), length, length2, 33);
        }
    }

    public static void c(SpannableStringBuilder spannableStringBuilder, Context context, C.b bVar) {
        String strF = f(context, bVar);
        String strG = g(context, bVar);
        spannableStringBuilder.append('\n').append("- ");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) strF);
        int length2 = spannableStringBuilder.length();
        if (length2 > length) {
            spannableStringBuilder.setSpan(new StyleSpan(1), length, length2, 33);
        }
        spannableStringBuilder.append(": ").append((CharSequence) strG);
    }

    public static CharSequence d(Context context, C.a aVar) {
        List list;
        if (aVar == null || (list = aVar.f6997b) == null || list.isEmpty()) {
            return "";
        }
        String strH = h(context, aVar.f6996a);
        int size = aVar.f6997b.size();
        int iMin = Math.min(2, size);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        a(spannableStringBuilder, strH);
        for (int i2 = 0; i2 < iMin; i2++) {
            c(spannableStringBuilder, context, (C.b) aVar.f6997b.get(i2));
        }
        b(spannableStringBuilder, context, size, iMin);
        return spannableStringBuilder;
    }

    public static String e(String str, int i2) {
        if (str == null) {
            return "";
        }
        if (i2 <= 0 || str.length() <= i2) {
            return str;
        }
        if (i2 <= 3) {
            return str.substring(0, i2);
        }
        return str.substring(0, i2 - 3) + "...";
    }

    public static String f(Context context, C.b bVar) {
        String strJ = bVar == null ? "" : j(bVar.f7000c);
        if (strJ.length() == 0) {
            strJ = bVar != null ? j(bVar.f6999b) : "";
        }
        if (strJ.length() == 0) {
            strJ = context != null ? context.getString(f0.j.f5222j0) : "成员";
        }
        return e(strJ, 12);
    }

    public static String g(Context context, C.b bVar) {
        String strJ = bVar == null ? "" : j(bVar.f7004g);
        if (strJ.length() == 0) {
            strJ = context != null ? context.getString(f0.j.f5216g0) : "...";
        }
        return e(strJ, 28);
    }

    public static String h(Context context, String str) {
        String strJ = j(str);
        return strJ.length() > 0 ? strJ : context != null ? context.getString(f0.j.f5198V) : "聊天记录";
    }

    public static String i(String str) {
        return str == null ? "" : str;
    }

    public static String j(String str) {
        String strReplace = i(str).replace('\r', ' ').replace('\n', ' ').replace('\t', ' ');
        while (strReplace.indexOf("  ") >= 0) {
            strReplace = strReplace.replace("  ", " ");
        }
        return strReplace.trim();
    }
}
