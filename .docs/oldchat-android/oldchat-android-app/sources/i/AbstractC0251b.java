package i;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: i.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0251b {

    /* JADX INFO: renamed from: i.b$a */
    public static class a implements Parcelable.Creator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f5443a;

        public a(c cVar) {
            this.f5443a = cVar;
        }

        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel parcel) {
            return this.f5443a.createFromParcel(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int i2) {
            return this.f5443a.newArray(i2);
        }
    }

    public static Parcelable.Creator a(c cVar) {
        return Build.VERSION.SDK_INT >= 13 ? e.a(cVar) : new a(cVar);
    }
}
