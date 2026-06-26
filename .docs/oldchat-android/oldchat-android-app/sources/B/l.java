package B;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public static void a(z.g gVar, G.c cVar) {
        C.l.f146X.d(cVar, gVar);
    }

    public static Writer b(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    public static final class a extends Writer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Appendable f68a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0002a f69b = new C0002a();

        /* JADX INFO: renamed from: B.l$a$a, reason: collision with other inner class name */
        public static class C0002a implements CharSequence {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public char[] f70a;

            @Override // java.lang.CharSequence
            public char charAt(int i2) {
                return this.f70a[i2];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f70a.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i2, int i3) {
                return new String(this.f70a, i2, i3 - i2);
            }
        }

        public a(Appendable appendable) {
            this.f68a = appendable;
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) throws IOException {
            C0002a c0002a = this.f69b;
            c0002a.f70a = cArr;
            this.f68a.append(c0002a, i2, i3 + i2);
        }

        @Override // java.io.Writer
        public void write(int i2) throws IOException {
            this.f68a.append((char) i2);
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }
    }
}
