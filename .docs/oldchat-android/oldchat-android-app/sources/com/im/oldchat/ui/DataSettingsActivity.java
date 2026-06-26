package com.im.oldchat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import o0.AbstractC0440f;
import o0.AbstractC0449o;
import o0.AbstractC0455v;
import o0.AbstractC0458y;
import o0.C0457x;

/* JADX INFO: loaded from: classes.dex */
public class DataSettingsActivity extends f0.a {

    public class a implements AbstractC0449o.d {
        public a() {
        }

        @Override // o0.AbstractC0449o.d
        public void a(String str) {
            Toast.makeText(DataSettingsActivity.this, str, 0).show();
        }

        @Override // o0.AbstractC0449o.d
        public void b(String str, int i2) {
            Toast.makeText(DataSettingsActivity.this, str, 1).show();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataSettingsActivity.this.finish();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataSettingsActivity.this.startActivity(new Intent(DataSettingsActivity.this, (Class<?>) CacheSettingsActivity.class));
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC0455v.c(DataSettingsActivity.this);
            Toast.makeText(DataSettingsActivity.this, "已清理图片缓存", 0).show();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            long jA = AbstractC0458y.a(DataSettingsActivity.this);
            AbstractC0458y.b(DataSettingsActivity.this);
            long jA2 = jA - AbstractC0458y.a(DataSettingsActivity.this);
            if (jA2 < 0) {
                jA2 = 0;
            }
            Toast.makeText(DataSettingsActivity.this, "已深度清理媒体缓存，释放 " + AbstractC0440f.b(jA2), 0).show();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataSettingsActivity.this.i0();
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataSettingsActivity.this.k0();
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataSettingsActivity.this.j0();
        }
    }

    public class i implements Runnable {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(DataSettingsActivity.this, "没有日志文件", 0).show();
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ File f2593a;

            public b(File file) {
                this.f2593a = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(DataSettingsActivity.this, "日志已导出到: " + this.f2593a.getAbsolutePath(), 1).show();
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(DataSettingsActivity.this, "导出日志失败", 0).show();
            }
        }

        public i() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            ZipOutputStream zipOutputStream;
            Throwable th;
            File fileB;
            File[] fileArrListFiles;
            FileInputStream fileInputStream;
            Throwable th2;
            ZipOutputStream zipOutputStream2 = null;
            try {
                try {
                    try {
                        fileB = C0457x.a(DataSettingsActivity.this).b();
                        fileArrListFiles = fileB.listFiles();
                    } catch (IOException unused) {
                    }
                    if (fileB.exists() && fileArrListFiles != null && fileArrListFiles.length != 0) {
                        File file = new File(DataSettingsActivity.this.getExternalFilesDir(null), "logs_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date()) + ".zip");
                        zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
                        for (File file2 : fileArrListFiles) {
                            try {
                                try {
                                    if (file2 != null && file2.isFile()) {
                                        zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                                        try {
                                            fileInputStream = new FileInputStream(file2);
                                            try {
                                                byte[] bArr = new byte[1024];
                                                while (true) {
                                                    int i2 = fileInputStream.read(bArr);
                                                    if (i2 > 0) {
                                                        zipOutputStream.write(bArr, 0, i2);
                                                    } else {
                                                        try {
                                                            break;
                                                        } catch (Exception unused2) {
                                                        }
                                                    }
                                                }
                                                fileInputStream.close();
                                                zipOutputStream.closeEntry();
                                            } catch (Throwable th3) {
                                                th2 = th3;
                                                if (fileInputStream != null) {
                                                    try {
                                                        fileInputStream.close();
                                                    } catch (Exception unused3) {
                                                    }
                                                }
                                                throw th2;
                                            }
                                        } catch (Throwable th4) {
                                            fileInputStream = null;
                                            th2 = th4;
                                        }
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    if (zipOutputStream != null) {
                                        try {
                                            zipOutputStream.close();
                                        } catch (Exception unused4) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (IOException unused5) {
                                zipOutputStream2 = zipOutputStream;
                                DataSettingsActivity.this.runOnUiThread(new c());
                                if (zipOutputStream2 == null) {
                                    return;
                                } else {
                                    zipOutputStream2.close();
                                }
                            }
                        }
                        DataSettingsActivity.this.runOnUiThread(new b(file));
                        zipOutputStream.close();
                        return;
                    }
                    DataSettingsActivity.this.runOnUiThread(new a());
                } catch (Throwable th6) {
                    zipOutputStream = null;
                    th = th6;
                }
            } catch (Exception unused6) {
            }
        }
    }

    public class j implements AbstractC0449o.c {
        public j() {
        }

        @Override // o0.AbstractC0449o.c
        public void a(String str) {
            Toast.makeText(DataSettingsActivity.this, str, 0).show();
        }

        @Override // o0.AbstractC0449o.c
        public void b(String str, File file) {
            Toast.makeText(DataSettingsActivity.this, str, 1).show();
        }
    }

    public final void i0() {
        Toast.makeText(this, "正在导出表情包...", 0).show();
        AbstractC0449o.d(this, new j());
    }

    public final void j0() {
        new Thread(new i()).start();
    }

    public final void k0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("application/zip");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, "选择表情包 ZIP 文件"), 5201);
        } catch (Exception unused) {
            Toast.makeText(this, "无法打开文件选择器", 0).show();
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 5201 || i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        Toast.makeText(this, "正在导入表情包...", 0).show();
        AbstractC0449o.f(this, data, new a());
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5149i);
        View viewA0 = a0(f0.f.f5065Z);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new b());
        }
        View viewA02 = a0(f0.f.f5104x);
        if (viewA02 != null) {
            viewA02.setOnClickListener(new c());
        }
        View viewA03 = a0(f0.f.f5048I);
        if (viewA03 != null) {
            viewA03.setOnClickListener(new d());
        }
        View viewA04 = a0(f0.f.f5067a0);
        if (viewA04 != null) {
            viewA04.setOnClickListener(new e());
        }
        View viewA05 = a0(f0.f.G0);
        if (viewA05 != null) {
            viewA05.setOnClickListener(new f());
        }
        View viewA06 = a0(f0.f.f1);
        if (viewA06 != null) {
            viewA06.setOnClickListener(new g());
        }
        View viewA07 = a0(f0.f.H0);
        if (viewA07 != null) {
            viewA07.setOnClickListener(new h());
        }
    }
}
