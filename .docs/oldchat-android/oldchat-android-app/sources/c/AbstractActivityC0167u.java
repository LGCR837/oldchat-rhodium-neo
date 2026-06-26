package c;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import c.AbstractC0121a;
import c.AbstractC0130d;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: renamed from: c.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0167u extends AbstractActivityC0163s implements AbstractC0121a.b, AbstractC0130d.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f2040c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0171w f2041d = C0171w.b(new b());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2042e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2043f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2044g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2045h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2046i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2047j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2048k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2049l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j.k f2050m;

    /* JADX INFO: renamed from: c.u$a */
    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                AbstractActivityC0167u abstractActivityC0167u = AbstractActivityC0167u.this;
                if (abstractActivityC0167u.f2044g) {
                    abstractActivityC0167u.w(false);
                    return;
                }
                return;
            }
            if (i2 != 2) {
                super.handleMessage(message);
            } else {
                AbstractActivityC0167u.this.C();
                AbstractActivityC0167u.this.f2041d.x();
            }
        }
    }

    /* JADX INFO: renamed from: c.u$b */
    public class b extends AbstractC0173x {
        public b() {
            super(AbstractActivityC0167u.this);
        }

        @Override // c.AbstractC0169v
        public View a(int i2) {
            return AbstractActivityC0167u.this.findViewById(i2);
        }

        @Override // c.AbstractC0169v
        public boolean b() {
            Window window = AbstractActivityC0167u.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // c.AbstractC0173x
        public void n(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
            AbstractActivityC0167u.this.z(abstractComponentCallbacksC0165t);
        }

        @Override // c.AbstractC0173x
        public void o(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            AbstractActivityC0167u.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // c.AbstractC0173x
        public LayoutInflater p() {
            return AbstractActivityC0167u.this.getLayoutInflater().cloneInContext(AbstractActivityC0167u.this);
        }

        @Override // c.AbstractC0173x
        public int q() {
            Window window = AbstractActivityC0167u.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // c.AbstractC0173x
        public boolean r() {
            return AbstractActivityC0167u.this.getWindow() != null;
        }

        @Override // c.AbstractC0173x
        public boolean s(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
            return !AbstractActivityC0167u.this.isFinishing();
        }

        @Override // c.AbstractC0173x
        public void t(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, Intent intent, int i2, Bundle bundle) {
            AbstractActivityC0167u.this.E(abstractComponentCallbacksC0165t, intent, i2, bundle);
        }

        @Override // c.AbstractC0173x
        public void u() {
            AbstractActivityC0167u.this.F();
        }
    }

    /* JADX INFO: renamed from: c.u$c */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f2053a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public C0110B f2054b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public j.j f2055c;
    }

    public static String G(View view) {
        String resourcePackageName;
        StringBuilder sb = new StringBuilder(128);
        sb.append(view.getClass().getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(view)));
        sb.append(' ');
        int visibility = view.getVisibility();
        if (visibility == 0) {
            sb.append('V');
        } else if (visibility == 4) {
            sb.append('I');
        } else if (visibility != 8) {
            sb.append('.');
        } else {
            sb.append('G');
        }
        sb.append(view.isFocusable() ? 'F' : '.');
        sb.append(view.isEnabled() ? 'E' : '.');
        sb.append(view.willNotDraw() ? '.' : 'D');
        sb.append(view.isHorizontalScrollBarEnabled() ? 'H' : '.');
        sb.append(view.isVerticalScrollBarEnabled() ? 'V' : '.');
        sb.append(view.isClickable() ? 'C' : '.');
        sb.append(view.isLongClickable() ? Matrix.MATRIX_TYPE_RANDOM_LT : '.');
        sb.append(' ');
        sb.append(view.isFocused() ? 'F' : '.');
        sb.append(view.isSelected() ? 'S' : '.');
        sb.append(view.isPressed() ? 'P' : '.');
        sb.append(' ');
        sb.append(view.getLeft());
        sb.append(',');
        sb.append(view.getTop());
        sb.append('-');
        sb.append(view.getRight());
        sb.append(',');
        sb.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            sb.append(" #");
            sb.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (id != 0 && resources != null) {
                int i2 = (-16777216) & id;
                if (i2 == 16777216) {
                    resourcePackageName = "android";
                } else if (i2 != 2130706432) {
                    try {
                        resourcePackageName = resources.getResourcePackageName(id);
                    } catch (Resources.NotFoundException unused) {
                    }
                } else {
                    resourcePackageName = "app";
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                sb.append(" ");
                sb.append(resourcePackageName);
                sb.append(":");
                sb.append(resourceTypeName);
                sb.append("/");
                sb.append(resourceEntryName);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean A(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public void B() {
        this.f2041d.v(this.f2046i);
        this.f2041d.p();
    }

    public void C() {
        this.f2041d.q();
    }

    public Object D() {
        return null;
    }

    public void E(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, Intent intent, int i2, Bundle bundle) {
        this.f1987b = true;
        try {
            if (i2 == -1) {
                AbstractC0121a.k(this, intent, -1, bundle);
            } else {
                AbstractActivityC0160q.t(i2);
                AbstractC0121a.k(this, intent, ((v(abstractComponentCallbacksC0165t) + 1) << 16) + (i2 & 65535), bundle);
            }
        } finally {
            this.f1987b = false;
        }
    }

    public abstract void F();

    @Override // c.AbstractC0130d.a
    public final void a(int i2) {
        if (this.f2048k || i2 == -1) {
            return;
        }
        AbstractActivityC0160q.t(i2);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.f2042e);
        printWriter.print("mResumed=");
        printWriter.print(this.f2043f);
        printWriter.print(" mStopped=");
        printWriter.print(this.f2044g);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.f2045h);
        this.f2041d.w(str2, fileDescriptor, printWriter, strArr);
        this.f2041d.z().b(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        x(str + "  ", printWriter, getWindow().getDecorView());
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        this.f2041d.A();
        int i4 = i2 >> 16;
        if (i4 == 0) {
            super.onActivityResult(i2, i3, intent);
            return;
        }
        int i5 = i4 - 1;
        String str = (String) this.f2050m.e(i5);
        this.f2050m.j(i5);
        if (str == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tY = this.f2041d.y(str);
        if (abstractComponentCallbacksC0165tY != null) {
            abstractComponentCallbacksC0165tY.C(i2 & 65535, i3, intent);
            return;
        }
        Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f2041d.z().d()) {
            return;
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2041d.d(configuration);
    }

    @Override // c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.f2041d.a(null);
        super.onCreate(bundle);
        c cVar = (c) getLastNonConfigurationInstance();
        if (cVar != null) {
            this.f2041d.E(cVar.f2055c);
        }
        if (bundle != null) {
            this.f2041d.D(bundle.getParcelable("android:support:fragments"), cVar != null ? cVar.f2054b : null);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.f2049l = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.f2050m = new j.k(intArray.length);
                    for (int i2 = 0; i2 < intArray.length; i2++) {
                        this.f2050m.i(intArray[i2], stringArray[i2]);
                    }
                }
            }
        }
        if (this.f2050m == null) {
            this.f2050m = new j.k();
            this.f2049l = 0;
        }
        this.f2041d.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 != 0) {
            return super.onCreatePanelMenu(i2, menu);
        }
        boolean zOnCreatePanelMenu = super.onCreatePanelMenu(i2, menu) | this.f2041d.g(menu, getMenuInflater());
        if (Build.VERSION.SDK_INT >= 11) {
            return zOnCreatePanelMenu;
        }
        return true;
    }

    @Override // c.r, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        w(false);
        this.f2041d.h();
        this.f2041d.t();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.f2041d.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0) {
            return this.f2041d.k(menuItem);
        }
        if (i2 != 6) {
            return false;
        }
        return this.f2041d.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z2) {
        this.f2041d.j(z2);
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f2041d.A();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        if (i2 == 0) {
            this.f2041d.l(menu);
        }
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.f2043f = false;
        if (this.f2040c.hasMessages(2)) {
            this.f2040c.removeMessages(2);
            C();
        }
        this.f2041d.m();
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z2) {
        this.f2041d.n(z2);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.f2040c.removeMessages(2);
        C();
        this.f2041d.x();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        if (i2 != 0 || menu == null) {
            return super.onPreparePanel(i2, view, menu);
        }
        if (this.f2047j) {
            this.f2047j = false;
            menu.clear();
            onCreatePanelMenu(i2, menu);
        }
        return A(view, menu) | this.f2041d.o(menu);
    }

    @Override // android.app.Activity, c.AbstractC0121a.b
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        int i3 = (i2 >> 16) & 65535;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String str = (String) this.f2050m.e(i4);
            this.f2050m.j(i4);
            if (str == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tY = this.f2041d.y(str);
            if (abstractComponentCallbacksC0165tY != null) {
                abstractComponentCallbacksC0165tY.Y(i2 & 65535, strArr, iArr);
                return;
            }
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.f2040c.sendEmptyMessage(2);
        this.f2043f = true;
        this.f2041d.x();
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        if (this.f2044g) {
            w(true);
        }
        Object objD = D();
        C0110B c0110bG = this.f2041d.G();
        j.j jVarF = this.f2041d.F();
        if (c0110bG == null && jVarF == null && objD == null) {
            return null;
        }
        c cVar = new c();
        cVar.f2053a = objD;
        cVar.f2054b = c0110bG;
        cVar.f2055c = jVarF;
        return cVar;
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable parcelableH = this.f2041d.H();
        if (parcelableH != null) {
            bundle.putParcelable("android:support:fragments", parcelableH);
        }
        if (this.f2050m.k() > 0) {
            bundle.putInt("android:support:next_request_index", this.f2049l);
            int[] iArr = new int[this.f2050m.k()];
            String[] strArr = new String[this.f2050m.k()];
            for (int i2 = 0; i2 < this.f2050m.k(); i2++) {
                iArr[i2] = this.f2050m.h(i2);
                strArr[i2] = (String) this.f2050m.l(i2);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.f2044g = false;
        this.f2045h = false;
        this.f2040c.removeMessages(1);
        if (!this.f2042e) {
            this.f2042e = true;
            this.f2041d.c();
        }
        this.f2041d.A();
        this.f2041d.x();
        this.f2041d.u();
        this.f2041d.r();
        this.f2041d.C();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.f2041d.A();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.f2044g = true;
        this.f2040c.sendEmptyMessage(1);
        this.f2041d.s();
    }

    @Override // c.AbstractActivityC0163s, android.app.Activity
    public /* bridge */ /* synthetic */ void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // c.AbstractActivityC0160q, android.app.Activity
    public /* bridge */ /* synthetic */ void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    @Override // c.AbstractActivityC0160q
    public final View u(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f2041d.B(view, str, context, attributeSet);
    }

    public final int v(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (this.f2050m.k() >= 65534) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (this.f2050m.g(this.f2049l) >= 0) {
            this.f2049l = (this.f2049l + 1) % 65534;
        }
        int i2 = this.f2049l;
        this.f2050m.i(i2, abstractComponentCallbacksC0165t.f2019g);
        this.f2049l = (this.f2049l + 1) % 65534;
        return i2;
    }

    public void w(boolean z2) {
        if (this.f2045h) {
            if (z2) {
                this.f2041d.u();
                this.f2041d.v(true);
                return;
            }
            return;
        }
        this.f2045h = true;
        this.f2046i = z2;
        this.f2040c.removeMessages(1);
        B();
    }

    public final void x(String str, PrintWriter printWriter, View view) {
        ViewGroup viewGroup;
        int childCount;
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(G(view));
        if ((view instanceof ViewGroup) && (childCount = (viewGroup = (ViewGroup) view).getChildCount()) > 0) {
            String str2 = str + "  ";
            for (int i2 = 0; i2 < childCount; i2++) {
                x(str2, printWriter, viewGroup.getChildAt(i2));
            }
        }
    }

    public AbstractC0175y y() {
        return this.f2041d.z();
    }

    @Override // c.AbstractActivityC0160q, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2) {
        if (!this.f1987b && i2 != -1) {
            AbstractActivityC0160q.t(i2);
        }
        super.startActivityForResult(intent, i2);
    }

    @Override // c.AbstractActivityC0163s, android.app.Activity
    public /* bridge */ /* synthetic */ void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void z(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
    }
}
