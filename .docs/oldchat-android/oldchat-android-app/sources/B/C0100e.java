package b;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: b.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0100e implements InterfaceC0098c {

    /* JADX INFO: renamed from: b.e$a */
    public static class a implements r {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public View f1745c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1746d;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List f1743a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List f1744b = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1747e = 200;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f1748f = 0.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1749g = false;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1750h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Runnable f1751i = new RunnableC0017a();

        /* JADX INFO: renamed from: b.e$a$a, reason: collision with other inner class name */
        public class RunnableC0017a implements Runnable {
            public RunnableC0017a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                float fQ = ((a.this.q() - a.this.f1746d) * 1.0f) / a.this.f1747e;
                if (fQ > 1.0f || a.this.f1745c.getParent() == null) {
                    fQ = 1.0f;
                }
                a.this.f1748f = fQ;
                a.this.r();
                if (a.this.f1748f >= 1.0f) {
                    a.this.o();
                } else {
                    a aVar = a.this;
                    aVar.f1745c.postDelayed(aVar.f1751i, 16L);
                }
            }
        }

        @Override // b.r
        public void a(long j2) {
            if (this.f1749g) {
                return;
            }
            this.f1747e = j2;
        }

        @Override // b.r
        public void b(InterfaceC0099d interfaceC0099d) {
            this.f1744b.add(interfaceC0099d);
        }

        @Override // b.r
        public float c() {
            return this.f1748f;
        }

        @Override // b.r
        public void cancel() {
            if (this.f1750h) {
                return;
            }
            this.f1750h = true;
            if (this.f1749g) {
                n();
            }
            o();
        }

        @Override // b.r
        public void d(InterfaceC0097b interfaceC0097b) {
            this.f1743a.add(interfaceC0097b);
        }

        @Override // b.r
        public void e(View view) {
            this.f1745c = view;
        }

        public final void n() {
            for (int size = this.f1743a.size() - 1; size >= 0; size--) {
                ((InterfaceC0097b) this.f1743a.get(size)).d(this);
            }
        }

        public final void o() {
            for (int size = this.f1743a.size() - 1; size >= 0; size--) {
                ((InterfaceC0097b) this.f1743a.get(size)).a(this);
            }
        }

        public final void p() {
            for (int size = this.f1743a.size() - 1; size >= 0; size--) {
                ((InterfaceC0097b) this.f1743a.get(size)).c(this);
            }
        }

        public final long q() {
            return this.f1745c.getDrawingTime();
        }

        public final void r() {
            for (int size = this.f1744b.size() - 1; size >= 0; size--) {
                ((InterfaceC0099d) this.f1744b.get(size)).a(this);
            }
        }

        @Override // b.r
        public void start() {
            if (this.f1749g) {
                return;
            }
            this.f1749g = true;
            p();
            this.f1748f = 0.0f;
            this.f1746d = q();
            this.f1745c.postDelayed(this.f1751i, 16L);
        }
    }

    @Override // b.InterfaceC0098c
    public r a() {
        return new a();
    }

    @Override // b.InterfaceC0098c
    public void b(View view) {
    }
}
