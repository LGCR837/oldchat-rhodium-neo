package c;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: c.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0111C implements Parcelable {
    public static final Parcelable.Creator<C0111C> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0113E[] f1806a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f1807b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0158p[] f1808c;

    /* JADX INFO: renamed from: c.C$a */
    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0111C createFromParcel(Parcel parcel) {
            return new C0111C(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0111C[] newArray(int i2) {
            return new C0111C[i2];
        }
    }

    public C0111C() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedArray(this.f1806a, i2);
        parcel.writeIntArray(this.f1807b);
        parcel.writeTypedArray(this.f1808c, i2);
    }

    public C0111C(Parcel parcel) {
        this.f1806a = (C0113E[]) parcel.createTypedArray(C0113E.CREATOR);
        this.f1807b = parcel.createIntArray();
        this.f1808c = (C0158p[]) parcel.createTypedArray(C0158p.CREATOR);
    }
}
