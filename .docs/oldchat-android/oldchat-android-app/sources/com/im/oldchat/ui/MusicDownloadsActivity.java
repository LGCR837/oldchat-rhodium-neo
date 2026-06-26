package com.im.oldchat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MusicDownloadsActivity extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final ArrayList f3162A = new ArrayList();

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public AsyncTask f3163B;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3164w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f3165x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f3166y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public f f3167z;

    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (i2 < 0 || i2 >= MusicDownloadsActivity.this.f3162A.size()) {
                return;
            }
            MusicDownloadsActivity musicDownloadsActivity = MusicDownloadsActivity.this;
            musicDownloadsActivity.v0((h) musicDownloadsActivity.f3162A.get(i2));
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicDownloadsActivity.this.finish();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MusicDownloadsActivity.this.u0();
        }
    }

    public class d extends AsyncTask {
        public d() {
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList doInBackground(Void... voidArr) {
            return MusicDownloadsActivity.this.w0();
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList arrayList) {
            if (MusicDownloadsActivity.this.isFinishing()) {
                return;
            }
            MusicDownloadsActivity.this.f3162A.clear();
            if (arrayList != null) {
                MusicDownloadsActivity.this.f3162A.addAll(arrayList);
            }
            MusicDownloadsActivity.this.f3167z.notifyDataSetChanged();
            if (MusicDownloadsActivity.this.f3165x != null) {
                MusicDownloadsActivity.this.f3165x.setText("本地歌曲 " + MusicDownloadsActivity.this.f3162A.size() + " 首 · 点击即可播放");
            }
            if (MusicDownloadsActivity.this.f3166y != null) {
                MusicDownloadsActivity.this.f3166y.setVisibility(MusicDownloadsActivity.this.f3162A.isEmpty() ? 0 : 8);
            }
        }
    }

    public class e implements Comparator {
        public e() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(h hVar, h hVar2) {
            long j2 = hVar == null ? 0L : hVar.f3179d;
            long j3 = hVar2 != null ? hVar2.f3179d : 0L;
            if (j2 == j3) {
                return 0;
            }
            return j2 > j3 ? -1 : 1;
        }
    }

    public class f extends BaseAdapter {
        public f() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return MusicDownloadsActivity.this.f3162A.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (i2 < 0 || i2 >= MusicDownloadsActivity.this.f3162A.size()) {
                return null;
            }
            return MusicDownloadsActivity.this.f3162A.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            g gVar;
            if (view == null) {
                view = LayoutInflater.from(MusicDownloadsActivity.this).inflate(f0.g.I0, viewGroup, false);
                gVar = new g(view);
                view.setTag(gVar);
            } else {
                gVar = (g) view.getTag();
            }
            h hVar = (h) getItem(i2);
            if (hVar != null) {
                gVar.f3174a.setText(hVar.f3177b);
                gVar.f3175b.setText(MusicDownloadsActivity.this.r0(hVar.f3178c) + " · " + MusicDownloadsActivity.this.s0(hVar.f3179d));
            }
            return view;
        }

        public /* synthetic */ f(MusicDownloadsActivity musicDownloadsActivity, a aVar) {
            this();
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f3174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f3175b;

        public g(View view) {
            this.f3174a = (TextView) view.findViewById(f0.f.Na);
            this.f3175b = (TextView) view.findViewById(f0.f.Ma);
        }
    }

    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public File f3176a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3177b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3178c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3179d;

        public h() {
        }

        public /* synthetic */ h(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String r0(long j2) {
        if (j2 < 1024) {
            return j2 + "B";
        }
        double d2 = j2;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        return d3 < 1024.0d ? String.format(Locale.US, "%.1fKB", Double.valueOf(d3)) : String.format(Locale.US, "%.1fMB", Double.valueOf(d3 / 1024.0d));
    }

    public final void o0(List list, File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                File file2 = (File) list.get(i2);
                if (file2 != null && file2.getAbsolutePath().equals(file.getAbsolutePath())) {
                    return;
                }
            }
            list.add(file);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5111E);
        this.f3164w = (ListView) a0(f0.f.T6);
        this.f3165x = (TextView) a0(f0.f.Pa);
        this.f3166y = (TextView) a0(f0.f.Oa);
        f fVar = new f(this, null);
        this.f3167z = fVar;
        this.f3164w.setAdapter((ListAdapter) fVar);
        this.f3164w.setOnItemClickListener(new a());
        View viewA0 = a0(f0.f.z1);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        View viewA02 = a0(f0.f.A1);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new c());
        }
        u0();
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        q0();
        super.onDestroy();
    }

    public final String p0(String str) {
        if (str == null || str.length() == 0) {
            return "本地歌曲";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            str = str.substring(0, iLastIndexOf);
        }
        String strTrim = str.replace("oldchat_music_", "").replace("oldchat_", "").replace('_', ' ').trim();
        return strTrim.length() == 0 ? "本地歌曲" : strTrim;
    }

    public final void q0() {
        AsyncTask asyncTask = this.f3163B;
        if (asyncTask == null) {
            return;
        }
        try {
            asyncTask.cancel(true);
        } catch (Exception unused) {
        }
        this.f3163B = null;
    }

    public final String s0(long j2) {
        return j2 <= 0 ? "未知时间" : new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date(j2));
    }

    public final boolean t0(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (lowerCase.endsWith(".mp3") || lowerCase.endsWith(".flac") || lowerCase.endsWith(".wav") || lowerCase.endsWith(".aac") || lowerCase.endsWith(".ogg") || lowerCase.endsWith(".m4a")) {
            return lowerCase.startsWith("oldchat_music_") || lowerCase.startsWith("oldchat_") || lowerCase.startsWith("music_");
        }
        return false;
    }

    public final void u0() {
        q0();
        TextView textView = this.f3165x;
        if (textView != null) {
            textView.setText("正在扫描本地音乐...");
        }
        d dVar = new d();
        this.f3163B = dVar;
        dVar.execute((Object[]) null);
    }

    public final void v0(h hVar) {
        File file;
        if (hVar == null || (file = hVar.f3176a) == null || !file.exists()) {
            Toast.makeText(this, "文件不存在", 0).show();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        String str = hVar.f3177b;
        if (str == null) {
            str = "本地歌曲";
        }
        intent.putExtra("song_name", str);
        intent.putExtra("song_url", Uri.fromFile(hVar.f3176a).toString());
        intent.putExtra("owner_name", "本地下载");
        startActivity(intent);
    }

    public final ArrayList w0() {
        File[] fileArrListFiles;
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        o0(arrayList2, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        o0(arrayList2, getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
        a aVar = null;
        o0(arrayList2, getExternalFilesDir(null));
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            try {
                fileArrListFiles = ((File) arrayList2.get(i2)).listFiles();
            } catch (Exception unused) {
                fileArrListFiles = null;
            }
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file != null && file.isFile()) {
                        String name = file.getName();
                        if (t0(name)) {
                            String absolutePath = file.getAbsolutePath();
                            if (!hashSet.contains(absolutePath)) {
                                hashSet.add(absolutePath);
                                h hVar = new h(aVar);
                                hVar.f3176a = file;
                                hVar.f3177b = p0(name);
                                hVar.f3178c = Math.max(0L, file.length());
                                hVar.f3179d = Math.max(0L, file.lastModified());
                                arrayList.add(hVar);
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(arrayList, new e());
        return arrayList;
    }
}
