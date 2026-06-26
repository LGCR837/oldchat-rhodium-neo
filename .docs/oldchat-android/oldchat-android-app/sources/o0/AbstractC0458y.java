package o0;

import android.content.Context;
import java.io.File;

/* JADX INFO: renamed from: o0.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0458y {
    public static long a(Context context) {
        if (context == null) {
            return 0L;
        }
        File cacheDir = context.getCacheDir();
        File filesDir = context.getFilesDir();
        return e(new File(cacheDir, "img_cache")) + e(new File(filesDir, "voice_cache")) + e(new File(cacheDir, "voice_cache")) + e(new File(filesDir, "music_player_cache")) + e(new File(cacheDir, "music_player_cache")) + e(new File(cacheDir, "video_cache")) + e(new File(cacheDir, "video_play_cache")) + e(new File(cacheDir, "upload_cache")) + f(cacheDir, "video_trim_") + f(cacheDir, "video_thumb_") + f(cacheDir, "emoji_pkg_");
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        AbstractC0455v.c(context);
        File cacheDir = context.getCacheDir();
        File filesDir = context.getFilesDir();
        d(new File(cacheDir, "img_cache"));
        d(new File(filesDir, "voice_cache"));
        d(new File(cacheDir, "voice_cache"));
        d(new File(filesDir, "music_player_cache"));
        d(new File(cacheDir, "music_player_cache"));
        d(new File(cacheDir, "video_cache"));
        d(new File(cacheDir, "video_play_cache"));
        d(new File(cacheDir, "upload_cache"));
        c(cacheDir, "video_trim_");
        c(cacheDir, "video_thumb_");
        c(cacheDir, "emoji_pkg_");
    }

    public static void c(File file, String str) {
        File[] fileArrListFiles;
        String name;
        if (file == null || !file.exists() || str == null || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2 != null && file2.isFile() && (name = file2.getName()) != null && name.startsWith(str)) {
                try {
                    file2.delete();
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void d(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                d(file2);
            }
        }
        try {
            file.delete();
        } catch (Exception unused) {
        }
    }

    public static long e(File file) {
        long jE = 0;
        if (file != null && file.exists()) {
            if (file.isFile()) {
                return Math.max(0L, file.length());
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return 0L;
            }
            for (File file2 : fileArrListFiles) {
                jE += e(file2);
            }
        }
        return jE;
    }

    public static long f(File file, String str) {
        File[] fileArrListFiles;
        String name;
        if (file == null || !file.exists() || str == null || (fileArrListFiles = file.listFiles()) == null) {
            return 0L;
        }
        long jMax = 0;
        for (File file2 : fileArrListFiles) {
            if (file2 != null && file2.isFile() && (name = file2.getName()) != null && name.startsWith(str)) {
                jMax += Math.max(0L, file2.length());
            }
        }
        return jMax;
    }
}
