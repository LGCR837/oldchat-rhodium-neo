package c;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: renamed from: c.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0113E implements Parcelable {
    public static final Parcelable.Creator<C0113E> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1814c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1815d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1816e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1817f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1818g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f1819h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Bundle f1820i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f1821j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Bundle f1822k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public AbstractComponentCallbacksC0165t f1823l;

    /* JADX INFO: renamed from: c.E$a */
    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0113E createFromParcel(Parcel parcel) {
            return new C0113E(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0113E[] newArray(int i2) {
            return new C0113E[i2];
        }
    }

    public C0113E(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        this.f1812a = abstractComponentCallbacksC0165t.getClass().getName();
        this.f1813b = abstractComponentCallbacksC0165t.f2018f;
        this.f1814c = abstractComponentCallbacksC0165t.f2026n;
        this.f1815d = abstractComponentCallbacksC0165t.f2035w;
        this.f1816e = abstractComponentCallbacksC0165t.f2036x;
        this.f1817f = abstractComponentCallbacksC0165t.f2037y;
        this.f1818g = abstractComponentCallbacksC0165t.f1991B;
        this.f1819h = abstractComponentCallbacksC0165t.f1990A;
        this.f1820i = abstractComponentCallbacksC0165t.f2020h;
        this.f1821j = abstractComponentCallbacksC0165t.f2038z;
    }

    public AbstractComponentCallbacksC0165t a(AbstractC0173x abstractC0173x, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, C0110B c0110b) {
        if (this.f1823l == null) {
            Context contextH = abstractC0173x.h();
            Bundle bundle = this.f1820i;
            if (bundle != null) {
                bundle.setClassLoader(contextH.getClassLoader());
            }
            this.f1823l = AbstractComponentCallbacksC0165t.v(contextH, this.f1812a, this.f1820i);
            Bundle bundle2 = this.f1822k;
            if (bundle2 != null) {
                bundle2.setClassLoader(contextH.getClassLoader());
                this.f1823l.f2016d = this.f1822k;
            }
            this.f1823l.C0(this.f1813b, abstractComponentCallbacksC0165t);
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = this.f1823l;
            abstractComponentCallbacksC0165t2.f2026n = this.f1814c;
            abstractComponentCallbacksC0165t2.f2028p = true;
            abstractComponentCallbacksC0165t2.f2035w = this.f1815d;
            abstractComponentCallbacksC0165t2.f2036x = this.f1816e;
            abstractComponentCallbacksC0165t2.f2037y = this.f1817f;
            abstractComponentCallbacksC0165t2.f1991B = this.f1818g;
            abstractComponentCallbacksC0165t2.f1990A = this.f1819h;
            abstractComponentCallbacksC0165t2.f2038z = this.f1821j;
            abstractComponentCallbacksC0165t2.f2030r = abstractC0173x.f2061e;
            if (C0109A.f1765x) {
                Log.v("FragmentManager", "Instantiated fragment " + this.f1823l);
            }
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t3 = this.f1823l;
        abstractComponentCallbacksC0165t3.f2033u = c0110b;
        return abstractComponentCallbacksC0165t3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1812a);
        parcel.writeInt(this.f1813b);
        parcel.writeInt(this.f1814c ? 1 : 0);
        parcel.writeInt(this.f1815d);
        parcel.writeInt(this.f1816e);
        parcel.writeString(this.f1817f);
        parcel.writeInt(this.f1818g ? 1 : 0);
        parcel.writeInt(this.f1819h ? 1 : 0);
        parcel.writeBundle(this.f1820i);
        parcel.writeInt(this.f1821j ? 1 : 0);
        parcel.writeBundle(this.f1822k);
    }

    public C0113E(Parcel parcel) {
        this.f1812a = parcel.readString();
        this.f1813b = parcel.readInt();
        this.f1814c = parcel.readInt() != 0;
        this.f1815d = parcel.readInt();
        this.f1816e = parcel.readInt();
        this.f1817f = parcel.readString();
        this.f1818g = parcel.readInt() != 0;
        this.f1819h = parcel.readInt() != 0;
        this.f1820i = parcel.readBundle();
        this.f1821j = parcel.readInt() != 0;
        this.f1822k = parcel.readBundle();
    }
}
