package k;

import android.os.Build;
import android.view.MotionEvent;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public abstract class W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f5678a;

    public static class a implements d {
        @Override // k.W.d
        public float a(MotionEvent motionEvent, int i2) {
            return 0.0f;
        }
    }

    public static class b extends a {
        @Override // k.W.a, k.W.d
        public float a(MotionEvent motionEvent, int i2) {
            return Y.a(motionEvent, i2);
        }
    }

    public static class c extends b {
    }

    public interface d {
        float a(MotionEvent motionEvent, int i2);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 14) {
            f5678a = new c();
        } else if (i2 >= 12) {
            f5678a = new b();
        } else {
            f5678a = new a();
        }
    }

    public static int a(MotionEvent motionEvent) {
        return (motionEvent.getAction() & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) >> 8;
    }

    public static int b(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static float c(MotionEvent motionEvent, int i2) {
        return f5678a.a(motionEvent, i2);
    }
}
