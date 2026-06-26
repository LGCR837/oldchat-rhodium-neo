package j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class d extends Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public StringBuilder f5453b = new StringBuilder(128);

    public d(String str) {
        this.f5452a = str;
    }

    public final void a() {
        if (this.f5453b.length() > 0) {
            Log.d(this.f5452a, this.f5453b.toString());
            StringBuilder sb = this.f5453b;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                a();
            } else {
                this.f5453b.append(c2);
            }
        }
    }
}
