package l0;

import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.ui.OldViewVideoDetailActivity;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: renamed from: l0.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0379f0 extends AbstractActivityC0381g0 {
    public String A0(String str) {
        return str == null ? "" : str;
    }

    public String B0(String str) {
        return str == null ? "" : str.replaceAll("<[^>]+>", "").replace("&amp;", "&");
    }

    public String C0(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    public String k0(BiliModels.CookieInfo cookieInfo) {
        if (cookieInfo == null || cookieInfo.cookies == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < cookieInfo.cookies.size(); i2++) {
            BiliModels.BiliCookie biliCookie = cookieInfo.cookies.get(i2);
            if (biliCookie != null && biliCookie.name != null && biliCookie.value != null) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append(biliCookie.name);
                sb.append('=');
                sb.append(biliCookie.value);
            }
        }
        return sb.toString();
    }

    public long l0(BiliModels.RecommendItem recommendItem) {
        if (recommendItem == null) {
            return 0L;
        }
        BiliModels.RecommendArgs recommendArgs = recommendItem.args;
        if (recommendArgs != null && t0(recommendArgs.aid)) {
            return recommendItem.args.aid;
        }
        long jY0 = y0(recommendItem.param);
        if (t0(jY0)) {
            return jY0;
        }
        long jX0 = x0(recommendItem.uri);
        if (t0(jX0)) {
            return jX0;
        }
        return 0L;
    }

    public String m0(BiliModels.RecommendItem recommendItem) {
        char cCharAt;
        if (recommendItem == null) {
            return null;
        }
        String str = recommendItem.param;
        if (str != null) {
            String strTrim = str.trim();
            if (u0(strTrim)) {
                return strTrim;
            }
            if (s0(strTrim) || strTrim.startsWith("av") || strTrim.startsWith("AV")) {
                return null;
            }
        }
        String str2 = recommendItem.uri;
        if (str2 != null) {
            int iIndexOf = str2.indexOf("BV");
            if (iIndexOf >= 0) {
                int i2 = iIndexOf;
                while (i2 < recommendItem.uri.length() && (cCharAt = recommendItem.uri.charAt(i2)) != '/' && cCharAt != '?' && cCharAt != '&') {
                    i2++;
                }
                String strSubstring = recommendItem.uri.substring(iIndexOf, i2);
                if (u0(strSubstring)) {
                    return strSubstring;
                }
                return null;
            }
            String strQ0 = q0(recommendItem.uri, "bvid");
            if (u0(strQ0)) {
                return strQ0;
            }
        }
        return null;
    }

    public long n0(String str) {
        String strQ0;
        if (str == null || str.length() == 0 || (strQ0 = q0(str, "cid")) == null || strQ0.length() <= 0) {
            return 0L;
        }
        try {
            return Long.parseLong(strQ0);
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
    
        r7 = q0(r7, "player_preload");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String o0(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "&"
            java.lang.String r1 = "\\u0026"
            r2 = 0
            if (r7 == 0) goto L69
            int r3 = r7.length()
            if (r3 != 0) goto Le
            goto L69
        Le:
            java.lang.String r3 = "player_preload"
            java.lang.String r7 = r6.q0(r7, r3)
            if (r7 == 0) goto L69
            int r3 = r7.length()
            if (r3 != 0) goto L1d
            goto L69
        L1d:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L35
            r3.<init>(r7)     // Catch: java.lang.Exception -> L35
            java.lang.String r4 = "url"
            java.lang.String r3 = r3.optString(r4)     // Catch: java.lang.Exception -> L35
            if (r3 == 0) goto L37
            int r4 = r3.length()     // Catch: java.lang.Exception -> L35
            if (r4 <= 0) goto L37
            java.lang.String r7 = r3.replace(r1, r0)     // Catch: java.lang.Exception -> L35
            return r7
        L35:
            goto L38
        L37:
            return r2
        L38:
            java.lang.String r3 = "\"url\""
            int r3 = r7.indexOf(r3)
            if (r3 < 0) goto L69
            r4 = 58
            int r3 = r7.indexOf(r4, r3)
            if (r3 <= 0) goto L69
            int r3 = r3 + 1
            r4 = 34
            int r3 = r7.indexOf(r4, r3)
            if (r3 < 0) goto L69
            int r5 = r3 + 1
            int r4 = r7.indexOf(r4, r5)
            if (r4 <= r3) goto L69
            java.lang.String r7 = r7.substring(r5, r4)
            int r3 = r7.length()
            if (r3 <= 0) goto L69
            java.lang.String r7 = r7.replace(r1, r0)
            return r7
        L69:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractActivityC0379f0.o0(java.lang.String):java.lang.String");
    }

    public List p0(List list) {
        int i2;
        int i3;
        int i4;
        boolean z2;
        List list2 = list;
        ArrayList arrayList = new ArrayList();
        if (list2 == null) {
            h0("filterRecommendItems: data=null");
            return arrayList;
        }
        int size = list.size();
        int i5 = 8;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i6 < list.size()) {
            BiliModels.RecommendItem recommendItem = (BiliModels.RecommendItem) list2.get(i6);
            if (recommendItem == null) {
                i2 = size;
                i3 = i6;
            } else {
                boolean zV0 = v0(recommendItem);
                String strM0 = m0(recommendItem);
                long jL0 = l0(recommendItem);
                String strO0 = o0(recommendItem.uri);
                int i10 = i8;
                int i11 = i9;
                long jN0 = n0(recommendItem.uri);
                boolean z3 = true;
                boolean z4 = jL0 > 0 || strM0 != null;
                boolean z5 = strO0 != null && strO0.length() > 0;
                if ((!zV0 && !z5) || (!z4 && !z5)) {
                    z3 = false;
                }
                i2 = size;
                if (i5 > 0) {
                    StringBuilder sb = new StringBuilder();
                    z2 = zV0;
                    sb.append("item#");
                    sb.append(i6);
                    sb.append(" cardType=");
                    sb.append(A0(recommendItem.cardType));
                    sb.append(" goto=");
                    sb.append(A0(recommendItem.gotoType));
                    sb.append(" cardGoto=");
                    sb.append(A0(recommendItem.cardGoto));
                    sb.append(" param=");
                    sb.append(A0(recommendItem.param));
                    sb.append(" uri=");
                    sb.append(A0(recommendItem.uri));
                    sb.append(" argsAid=");
                    BiliModels.RecommendArgs recommendArgs = recommendItem.args;
                    i3 = i6;
                    i4 = i7;
                    sb.append(recommendArgs != null ? recommendArgs.aid : 0L);
                    sb.append(" aid=");
                    sb.append(jL0);
                    sb.append(" bvid=");
                    sb.append(A0(strM0));
                    sb.append(" cid=");
                    sb.append(jN0);
                    sb.append(" preload=");
                    sb.append(z5 ? "Y" : "N");
                    sb.append(" keep=");
                    sb.append(z3);
                    h0(sb.toString());
                    i5--;
                } else {
                    i3 = i6;
                    i4 = i7;
                    z2 = zV0;
                }
                if (z3) {
                    recommendItem.cover = com.im.oldchat.bili.c.i(recommendItem.cover);
                    recommendItem.face = com.im.oldchat.bili.c.i(recommendItem.face);
                    arrayList.add(recommendItem);
                    i7 = i4 + 1;
                    i8 = i10;
                    i9 = i11;
                } else {
                    if (z2) {
                        i9 = i11 + 1;
                        i8 = i10;
                    } else {
                        i8 = i10 + 1;
                        i9 = i11;
                    }
                    i7 = i4;
                }
            }
            i6 = i3 + 1;
            list2 = list;
            size = i2;
        }
        h0("filterRecommendItems: total=" + size + " keep=" + i7 + " dropNonVideo=" + i8 + " dropNoId=" + i9);
        return arrayList;
    }

    public String q0(String str, String str2) {
        int iIndexOf;
        if (str != null && str2 != null && (iIndexOf = str.indexOf(63)) >= 0 && iIndexOf < str.length() - 1) {
            String strSubstring = str.substring(iIndexOf + 1);
            int length = strSubstring.length();
            int i2 = 0;
            while (i2 < length) {
                int iIndexOf2 = strSubstring.indexOf(38, i2);
                if (iIndexOf2 < 0) {
                    iIndexOf2 = length;
                }
                int iIndexOf3 = strSubstring.indexOf(61, i2);
                if (iIndexOf3 >= 0 && iIndexOf3 <= iIndexOf2 && str2.equals(strSubstring.substring(i2, iIndexOf3))) {
                    return C0(strSubstring.substring(iIndexOf3 + 1, iIndexOf2));
                }
                i2 = iIndexOf2 + 1;
            }
        }
        return null;
    }

    public void r0(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (inputMethodManager == null || view == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception unused) {
        }
    }

    public boolean s0(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
        }
        return true;
    }

    public boolean t0(long j2) {
        return j2 > 0;
    }

    public boolean u0(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        if (strTrim.length() < 10) {
            return false;
        }
        return strTrim.startsWith("BV") || strTrim.startsWith("bv");
    }

    public boolean v0(BiliModels.RecommendItem recommendItem) {
        if (recommendItem == null) {
            return false;
        }
        if ("av".equals(recommendItem.gotoType) || "av".equals(recommendItem.cardGoto) || "small_cover_v2".equals(recommendItem.cardType) || "small_cover_v1".equals(recommendItem.cardType)) {
            return true;
        }
        return u0(recommendItem.param);
    }

    public void w0(BiliModels.RecommendItem recommendItem) {
        if (recommendItem == null) {
            return;
        }
        long jN0 = n0(recommendItem.uri);
        String strO0 = o0(recommendItem.uri);
        String strM0 = m0(recommendItem);
        long jL0 = l0(recommendItem);
        if ((strM0 == null || strM0.length() == 0) && jL0 <= 0 && (strO0 == null || strO0.length() == 0)) {
            Toast.makeText(this, "无法识别该视频", 0).show();
            g0("打开详情失败: 无有效bvid/aid param=" + recommendItem.param + " uri=" + recommendItem.uri);
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OldViewVideoDetailActivity.class);
        if (strM0 != null) {
            intent.putExtra("bvid", strM0);
        }
        intent.putExtra("aid", jL0);
        if (jN0 > 0) {
            intent.putExtra("cid", jN0);
        }
        if (strO0 != null && strO0.length() > 0) {
            intent.putExtra("preload_url", strO0);
        }
        String str = recommendItem.cover;
        if (str != null && str.length() > 0) {
            intent.putExtra("cover_url", recommendItem.cover);
        }
        String str2 = recommendItem.title;
        if (str2 != null) {
            intent.putExtra(MessageBundle.TITLE_ENTRY, str2);
        }
        startActivity(intent);
    }

    public long x0(String str) {
        if (str == null) {
            return 0L;
        }
        int iIndexOf = str.indexOf("aid=");
        if (iIndexOf >= 0) {
            long jZ0 = z0(str, iIndexOf + 4);
            if (jZ0 > 0) {
                return jZ0;
            }
        }
        int iIndexOf2 = str.indexOf("video/");
        if (iIndexOf2 >= 0) {
            long jZ02 = z0(str, iIndexOf2 + 6);
            if (jZ02 > 0) {
                return jZ02;
            }
        }
        int iIndexOf3 = str.indexOf("av");
        if (iIndexOf3 >= 0) {
            long jZ03 = z0(str, iIndexOf3 + 2);
            if (jZ03 > 0) {
                return jZ03;
            }
        }
        return 0L;
    }

    public long y0(String str) {
        if (str == null) {
            return 0L;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return 0L;
        }
        if (strTrim.startsWith("av") || strTrim.startsWith("AV")) {
            strTrim = strTrim.substring(2);
        } else if (strTrim.startsWith("aid=")) {
            strTrim = strTrim.substring(4);
        }
        if (!s0(strTrim)) {
            return 0L;
        }
        try {
            return Long.parseLong(strTrim);
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long z0(java.lang.String r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L34
            if (r10 < 0) goto L34
            int r2 = r9.length()
            if (r10 < r2) goto Ld
            goto L34
        Ld:
            r2 = 0
            r3 = r0
        Lf:
            int r5 = r9.length()
            if (r10 >= r5) goto L31
            char r5 = r9.charAt(r10)
            r6 = 48
            if (r5 < r6) goto L2d
            r6 = 57
            if (r5 > r6) goto L2d
            r6 = 10
            long r3 = r3 * r6
            int r5 = r5 + (-48)
            long r5 = (long) r5
            long r3 = r3 + r5
            int r10 = r10 + 1
            r2 = 1
            goto Lf
        L2d:
            if (r2 == 0) goto L30
            goto L31
        L30:
            return r0
        L31:
            if (r2 == 0) goto L34
            r0 = r3
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractActivityC0379f0.z0(java.lang.String, int):long");
    }
}
