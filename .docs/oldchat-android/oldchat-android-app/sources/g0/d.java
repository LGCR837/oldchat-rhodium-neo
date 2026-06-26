package g0;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import g0.e;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends g0.f {

    public class a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ k f5280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5281c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5282d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ byte[] f5283e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f5284f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f5285g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f5286h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ j f5287i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ i f5288j;

        public a(String str, k kVar, String str2, String str3, byte[] bArr, String str4, String str5, String str6, j jVar, i iVar) {
            this.f5279a = str;
            this.f5280b = kVar;
            this.f5281c = str2;
            this.f5282d = str3;
            this.f5283e = bArr;
            this.f5284f = str4;
            this.f5285g = str5;
            this.f5286h = str6;
            this.f5287i = jVar;
            this.f5288j = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            return g0.f.E(this.f5279a, this.f5280b, this.f5281c, this.f5282d, this.f5283e, this.f5284f, this.f5285g, this.f5286h, this.f5287i);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5288j);
        }
    }

    public class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5289a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ JSONObject f5290b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5291c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ i f5292d;

        public b(String str, JSONObject jSONObject, String str2, i iVar) {
            this.f5289a = str;
            this.f5290b = jSONObject;
            this.f5291c = str2;
            this.f5292d = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            return g0.e.q("POST", this.f5289a, this.f5290b, this.f5291c);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5292d);
        }
    }

    public class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5294b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ i f5295c;

        public c(String str, String str2, i iVar) {
            this.f5293a = str;
            this.f5294b = str2;
            this.f5295c = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            return g0.e.q("GET", this.f5293a, null, this.f5294b);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5295c);
        }
    }

    /* JADX INFO: renamed from: g0.d$d, reason: collision with other inner class name */
    public class AsyncTaskC0040d extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5296a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5297b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ i f5298c;

        public AsyncTaskC0040d(String str, String str2, i iVar) {
            this.f5296a = str;
            this.f5297b = str2;
            this.f5298c = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            return g0.e.q("DELETE", this.f5296a, null, this.f5297b);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5298c);
        }
    }

    public class e extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5299a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ byte[] f5300b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5301c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5302d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f5303e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ i f5304f;

        public e(String str, byte[] bArr, String str2, String str3, String str4, i iVar) {
            this.f5299a = str;
            this.f5300b = bArr;
            this.f5301c = str2;
            this.f5302d = str3;
            this.f5303e = str4;
            this.f5304f = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            String strE;
            try {
                e.c cVarZ = g0.f.z(this.f5299a, this.f5300b, this.f5301c, this.f5302d, null, null, null, this.f5303e);
                return (cVarZ.f5348a == 401 && g0.e.s(this.f5299a, this.f5303e) && (strE = g0.a.e()) != null) ? g0.e.b(g0.f.z(this.f5299a, this.f5300b, this.f5301c, this.f5302d, null, null, null, strE), this.f5299a, strE) : g0.e.b(cVarZ, this.f5299a, this.f5303e);
            } catch (Exception e2) {
                return new e.c(-1, e2.getMessage());
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5304f);
        }
    }

    public class f extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5305a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ byte[] f5306b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5307c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5308d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ byte[] f5309e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f5310f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f5311g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f5312h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ i f5313i;

        public f(String str, byte[] bArr, String str2, String str3, byte[] bArr2, String str4, String str5, String str6, i iVar) {
            this.f5305a = str;
            this.f5306b = bArr;
            this.f5307c = str2;
            this.f5308d = str3;
            this.f5309e = bArr2;
            this.f5310f = str4;
            this.f5311g = str5;
            this.f5312h = str6;
            this.f5313i = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            String strE;
            try {
                e.c cVarZ = g0.f.z(this.f5305a, this.f5306b, this.f5307c, this.f5308d, this.f5309e, this.f5310f, this.f5311g, this.f5312h);
                return (cVarZ.f5348a == 401 && g0.e.s(this.f5305a, this.f5312h) && (strE = g0.a.e()) != null) ? g0.e.b(g0.f.z(this.f5305a, this.f5306b, this.f5307c, this.f5308d, this.f5309e, this.f5310f, this.f5311g, strE), this.f5305a, strE) : g0.e.b(cVarZ, this.f5305a, this.f5312h);
            } catch (Exception e2) {
                return new e.c(-1, e2.getMessage());
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5313i);
        }
    }

    public class g extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5314a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ byte[] f5315b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5316c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5317d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ byte[] f5318e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f5319f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f5320g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f5321h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ j f5322i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ i f5323j;

        public class a implements j {
            public a() {
            }

            @Override // g0.d.j
            public void a(long j2, long j3) {
                g gVar = g.this;
                if (gVar.f5322i != null) {
                    gVar.publishProgress(Integer.valueOf((int) ((j2 * 100) / j3)));
                }
            }
        }

        public class b implements j {
            public b() {
            }

            @Override // g0.d.j
            public void a(long j2, long j3) {
                g gVar = g.this;
                if (gVar.f5322i != null) {
                    gVar.publishProgress(Integer.valueOf((int) ((j2 * 100) / j3)));
                }
            }
        }

        public g(String str, byte[] bArr, String str2, String str3, byte[] bArr2, String str4, String str5, String str6, j jVar, i iVar) {
            this.f5314a = str;
            this.f5315b = bArr;
            this.f5316c = str2;
            this.f5317d = str3;
            this.f5318e = bArr2;
            this.f5319f = str4;
            this.f5320g = str5;
            this.f5321h = str6;
            this.f5322i = jVar;
            this.f5323j = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            String strE;
            try {
                e.c cVarC = g0.f.C(this.f5314a, this.f5315b, this.f5316c, this.f5317d, this.f5318e, this.f5319f, this.f5320g, this.f5321h, new a());
                return (cVarC.f5348a == 401 && g0.e.s(this.f5314a, this.f5321h) && (strE = g0.a.e()) != null) ? g0.e.b(g0.f.C(this.f5314a, this.f5315b, this.f5316c, this.f5317d, this.f5318e, this.f5319f, this.f5320g, strE, new b()), this.f5314a, strE) : g0.e.b(cVarC, this.f5314a, this.f5321h);
            } catch (Exception e2) {
                return new e.c(-1, e2.getMessage());
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5323j);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onProgressUpdate(Integer... numArr) {
            j jVar = this.f5322i;
            if (jVar == null || numArr.length <= 0) {
                return;
            }
            jVar.a(numArr[0].intValue(), 100L);
        }
    }

    public class h extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ k f5327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5328c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f5329d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f5330e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f5331f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f5332g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ j f5333h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ i f5334i;

        public h(String str, k kVar, String str2, String str3, String str4, String str5, String str6, j jVar, i iVar) {
            this.f5326a = str;
            this.f5327b = kVar;
            this.f5328c = str2;
            this.f5329d = str3;
            this.f5330e = str4;
            this.f5331f = str5;
            this.f5332g = str6;
            this.f5333h = jVar;
            this.f5334i = iVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e.c doInBackground(Void... voidArr) {
            return g0.f.D(this.f5326a, this.f5327b, this.f5328c, this.f5329d, this.f5330e, this.f5331f, this.f5332g, this.f5333h);
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(e.c cVar) {
            d.P(cVar, this.f5334i);
        }
    }

    public interface i {
        void b(String str);

        void c(int i2, String str);
    }

    public interface j {
        void a(long j2, long j3);
    }

    public interface k {
        InputStream a();

        long length();
    }

    public static void O(String str, String str2, i iVar) {
        Q(new AsyncTaskC0040d(str, str2, iVar), new Void[0]);
    }

    public static void P(e.c cVar, i iVar) {
        if (iVar == null) {
            return;
        }
        int i2 = cVar == null ? -1 : cVar.f5348a;
        String str = cVar == null ? "empty response" : cVar.f5349b;
        try {
            if (i2 < 200 || i2 >= 300) {
                iVar.c(i2, str);
            } else {
                iVar.b(str);
            }
        } catch (Throwable th) {
            Log.e("HttpUtil", "callback crashed", th);
        }
    }

    public static void Q(AsyncTask asyncTask, Object... objArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objArr);
        } else {
            asyncTask.execute(objArr);
        }
    }

    public static void R(String str, String str2, i iVar) {
        Q(new c(str, str2, iVar), new Void[0]);
    }

    public static void S(String str, JSONObject jSONObject, String str2, i iVar) {
        Q(new b(str, jSONObject, str2, iVar), new Void[0]);
    }

    public static void T(String str, byte[] bArr, String str2, String str3, String str4, i iVar) {
        Q(new e(str, bArr, str2, str3, str4, iVar), new Void[0]);
    }

    public static void U(String str, k kVar, String str2, String str3, String str4, String str5, String str6, j jVar, i iVar) {
        Q(new h(str, kVar, str2, str3, str4, str5, str6, jVar, iVar), new Void[0]);
    }

    public static void V(String str, k kVar, String str2, String str3, byte[] bArr, String str4, String str5, String str6, j jVar, i iVar) {
        Q(new a(str, kVar, str2, str3, bArr, str4, str5, str6, jVar, iVar), new Void[0]);
    }

    public static void W(String str, byte[] bArr, String str2, String str3, byte[] bArr2, String str4, String str5, String str6, i iVar) {
        Q(new f(str, bArr, str2, str3, bArr2, str4, str5, str6, iVar), new Void[0]);
    }

    public static void X(String str, byte[] bArr, String str2, String str3, byte[] bArr2, String str4, String str5, String str6, j jVar, i iVar) {
        Q(new g(str, bArr, str2, str3, bArr2, str4, str5, str6, jVar, iVar), new Void[0]);
    }
}
