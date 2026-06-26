package i;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class d implements Parcelable.ClassLoaderCreator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f5444a;

    public d(c cVar) {
        this.f5444a = cVar;
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return this.f5444a.createFromParcel(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i2) {
        return this.f5444a.newArray(i2);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.f5444a.createFromParcel(parcel, classLoader);
    }
}
