package l0;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.im.oldchat.service.ResourceUploadService;

/* JADX INFO: loaded from: classes.dex */
public abstract class C0 extends D0 {
    public void A0(Uri uri) {
        String str = this.f4496A;
        if (str == null || str.isEmpty() || !o0.G.d().f()) {
            Toast.makeText(this, "网络不可用", 0).show();
            return;
        }
        if (ResourceUploadService.l()) {
            if (ResourceUploadService.m(this.f4498C)) {
                return;
            }
            Toast.makeText(this, "正在上传其他资源", 0).show();
            return;
        }
        long jZ0 = z0(uri);
        if (jZ0 > 104857600) {
            Toast.makeText(this, "资源大小不能超过100MB", 0).show();
            return;
        }
        String strY0 = y0(uri);
        String type = getContentResolver().getType(uri);
        String str2 = this.f4498C;
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        this.f4512Q = true;
        s0(strY0, 0, jZ0 <= 0, 0L, jZ0, 0L);
        ResourceUploadService.u(this, this.f4498C, uri, strY0, type, jZ0);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 5201 || i3 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        A0(data);
    }

    @Override // f0.a, c.AbstractActivityC0167u, android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 5202) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "未授权读取存储", 0).show();
        } else {
            x0();
        }
    }

    public void x0() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "选择资源"), 5201);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0041 A[EXC_TOP_SPLITTER, PHI: r2
  0x0041: PHI (r2v3 android.database.Cursor) = (r2v2 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:29:0x004b, B:23:0x003f] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String y0(android.net.Uri r10) {
        /*
            r9 = this;
            java.lang.String r0 = "_display_name"
            java.lang.String r1 = "resource"
            if (r10 != 0) goto L7
            return r1
        L7:
            r2 = 0
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r4 = 0
            r5[r4] = r0     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r7 = 0
            r8 = 0
            r6 = 0
            r4 = r10
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r2 == 0) goto L3f
            boolean r10 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r10 == 0) goto L3f
            int r10 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r10 < 0) goto L3f
            java.lang.String r10 = r2.getString(r10)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r10 == 0) goto L3b
            boolean r0 = r10.isEmpty()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            if (r0 == 0) goto L35
            goto L3b
        L35:
            r1 = r10
            goto L3b
        L37:
            r10 = move-exception
            goto L45
        L39:
            goto L4b
        L3b:
            r2.close()     // Catch: java.lang.Exception -> L3e
        L3e:
            return r1
        L3f:
            if (r2 == 0) goto L4e
        L41:
            r2.close()     // Catch: java.lang.Exception -> L4e
            goto L4e
        L45:
            if (r2 == 0) goto L4a
            r2.close()     // Catch: java.lang.Exception -> L4a
        L4a:
            throw r10
        L4b:
            if (r2 == 0) goto L4e
            goto L41
        L4e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.C0.y0(android.net.Uri):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0036 A[EXC_TOP_SPLITTER, PHI: r3
  0x0036: PHI (r3v3 android.database.Cursor) = (r3v2 android.database.Cursor), (r3v4 android.database.Cursor) binds: [B:24:0x0040, B:18:0x0034] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long z0(android.net.Uri r11) {
        /*
            r10 = this;
            java.lang.String r0 = "_size"
            r1 = 0
            if (r11 != 0) goto L7
            return r1
        L7:
            r3 = 0
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r5 = 1
            java.lang.String[] r6 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r5 = 0
            r6[r5] = r0     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r8 = 0
            r9 = 0
            r7 = 0
            r5 = r11
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r3 == 0) goto L34
            boolean r11 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r11 == 0) goto L34
            int r11 = r3.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            if (r11 < 0) goto L34
            long r0 = r3.getLong(r11)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r3.close()     // Catch: java.lang.Exception -> L2f
        L2f:
            return r0
        L30:
            r11 = move-exception
            goto L3a
        L32:
            goto L40
        L34:
            if (r3 == 0) goto L43
        L36:
            r3.close()     // Catch: java.lang.Exception -> L43
            goto L43
        L3a:
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.lang.Exception -> L3f
        L3f:
            throw r11
        L40:
            if (r3 == 0) goto L43
            goto L36
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.C0.z0(android.net.Uri):long");
    }
}
