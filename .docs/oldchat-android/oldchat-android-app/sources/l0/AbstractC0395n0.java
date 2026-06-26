package l0;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.ui.OldViewVideoDetailActivity;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: renamed from: l0.n0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0395n0 {
    public static long a(BiliModels.RecommendItem recommendItem) {
        if (recommendItem == null) {
            return 0L;
        }
        BiliModels.RecommendArgs recommendArgs = recommendItem.args;
        if (recommendArgs != null) {
            long j2 = recommendArgs.aid;
            if (j2 > 0) {
                return j2;
            }
        }
        String str = recommendItem.param;
        if (str == null || str.length() <= 0) {
            return 0L;
        }
        String strTrim = recommendItem.param.trim();
        if (strTrim.startsWith("av") || strTrim.startsWith("AV")) {
            strTrim = strTrim.substring(2);
        }
        try {
            return Long.parseLong(strTrim);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String b(BiliModels.RecommendItem recommendItem) {
        String str;
        if (recommendItem == null || (str = recommendItem.param) == null) {
            return "";
        }
        String strTrim = str.trim();
        return (strTrim.startsWith("BV") || strTrim.startsWith("bv")) ? strTrim : "";
    }

    public static void c(Context context, BiliModels.RecommendItem recommendItem) {
        String str;
        String str2;
        if (context == null) {
            return;
        }
        String strB = b(recommendItem);
        long jA = a(recommendItem);
        if ((strB == null || strB.length() == 0) && jA <= 0) {
            Toast.makeText(context, "视频信息不完整", 0).show();
            return;
        }
        Intent intent = new Intent(context, (Class<?>) OldViewVideoDetailActivity.class);
        if (strB != null && strB.length() > 0) {
            intent.putExtra("bvid", strB);
        }
        if (jA > 0) {
            intent.putExtra("aid", jA);
        }
        if (recommendItem != null && (str2 = recommendItem.cover) != null && str2.length() > 0) {
            intent.putExtra("cover_url", recommendItem.cover);
        }
        if (recommendItem != null && (str = recommendItem.title) != null) {
            intent.putExtra(MessageBundle.TITLE_ENTRY, str);
        }
        context.startActivity(intent);
    }
}
