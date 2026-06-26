package c;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: renamed from: c.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0171w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0173x f2056a;

    public C0171w(AbstractC0173x abstractC0173x) {
        this.f2056a = abstractC0173x;
    }

    public static final C0171w b(AbstractC0173x abstractC0173x) {
        return new C0171w(abstractC0173x);
    }

    public void A() {
        this.f2056a.f2061e.X();
    }

    public View B(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f2056a.f2061e.onCreateView(view, str, context, attributeSet);
    }

    public void C() {
        this.f2056a.v();
    }

    public void D(Parcelable parcelable, C0110B c0110b) {
        this.f2056a.f2061e.d0(parcelable, c0110b);
    }

    public void E(j.j jVar) {
        this.f2056a.w(jVar);
    }

    public j.j F() {
        return this.f2056a.x();
    }

    public C0110B G() {
        return this.f2056a.f2061e.e0();
    }

    public Parcelable H() {
        return this.f2056a.f2061e.g0();
    }

    public void a(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        AbstractC0173x abstractC0173x = this.f2056a;
        abstractC0173x.f2061e.g(abstractC0173x, abstractC0173x, abstractComponentCallbacksC0165t);
    }

    public void c() {
        this.f2056a.f2061e.k();
    }

    public void d(Configuration configuration) {
        this.f2056a.f2061e.l(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f2056a.f2061e.m(menuItem);
    }

    public void f() {
        this.f2056a.f2061e.n();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f2056a.f2061e.o(menu, menuInflater);
    }

    public void h() {
        this.f2056a.f2061e.p();
    }

    public void i() {
        this.f2056a.f2061e.r();
    }

    public void j(boolean z2) {
        this.f2056a.f2061e.s(z2);
    }

    public boolean k(MenuItem menuItem) {
        return this.f2056a.f2061e.t(menuItem);
    }

    public void l(Menu menu) {
        this.f2056a.f2061e.u(menu);
    }

    public void m() {
        this.f2056a.f2061e.v();
    }

    public void n(boolean z2) {
        this.f2056a.f2061e.w(z2);
    }

    public boolean o(Menu menu) {
        return this.f2056a.f2061e.x(menu);
    }

    public void p() {
        this.f2056a.f2061e.y();
    }

    public void q() {
        this.f2056a.f2061e.z();
    }

    public void r() {
        this.f2056a.f2061e.A();
    }

    public void s() {
        this.f2056a.f2061e.B();
    }

    public void t() {
        this.f2056a.c();
    }

    public void u() {
        this.f2056a.d();
    }

    public void v(boolean z2) {
        this.f2056a.e(z2);
    }

    public void w(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f2056a.f(str, fileDescriptor, printWriter, strArr);
    }

    public boolean x() {
        return this.f2056a.f2061e.D();
    }

    public AbstractComponentCallbacksC0165t y(String str) {
        return this.f2056a.f2061e.H(str);
    }

    public AbstractC0175y z() {
        return this.f2056a.i();
    }
}
