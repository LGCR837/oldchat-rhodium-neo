package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import i.AbstractC0251b;
import i.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Parcelable f863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f862b = new C0009a();
    public static final Parcelable.Creator<a> CREATOR = AbstractC0251b.a(new b());

    /* JADX INFO: renamed from: android.support.v4.view.a$a, reason: collision with other inner class name */
    public static class C0009a extends a {
        public C0009a() {
            super((C0009a) null);
        }
    }

    public static class b implements c {
        @Override // i.c
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return a.f862b;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // i.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public /* synthetic */ a(C0009a c0009a) {
        this();
    }

    public final Parcelable a() {
        return this.f863a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f863a, i2);
    }

    public a() {
        this.f863a = null;
    }

    public a(Parcelable parcelable) {
        if (parcelable != null) {
            this.f863a = parcelable == f862b ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public a(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.f863a = parcelable == null ? f862b : parcelable;
    }
}
