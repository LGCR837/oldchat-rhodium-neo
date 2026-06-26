package c;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import c.RunnableC0156o;
import java.util.ArrayList;

/* JADX INFO: renamed from: c.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0158p implements Parcelable {
    public static final Parcelable.Creator<C0158p> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f1974a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1975b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1976c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1977d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1978e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1979f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CharSequence f1980g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1981h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CharSequence f1982i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f1983j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f1984k;

    /* JADX INFO: renamed from: c.p$a */
    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0158p createFromParcel(Parcel parcel) {
            return new C0158p(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0158p[] newArray(int i2) {
            return new C0158p[i2];
        }
    }

    public C0158p(RunnableC0156o runnableC0156o) {
        int size = 0;
        for (RunnableC0156o.d dVar = runnableC0156o.f1874b; dVar != null; dVar = dVar.f1911a) {
            ArrayList arrayList = dVar.f1919i;
            if (arrayList != null) {
                size += arrayList.size();
            }
        }
        this.f1974a = new int[(runnableC0156o.f1876d * 7) + size];
        if (!runnableC0156o.f1883k) {
            throw new IllegalStateException("Not on back stack");
        }
        int i2 = 0;
        for (RunnableC0156o.d dVar2 = runnableC0156o.f1874b; dVar2 != null; dVar2 = dVar2.f1911a) {
            int[] iArr = this.f1974a;
            int i3 = i2 + 1;
            iArr[i2] = dVar2.f1913c;
            int i4 = i2 + 2;
            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = dVar2.f1914d;
            iArr[i3] = abstractComponentCallbacksC0165t != null ? abstractComponentCallbacksC0165t.f2018f : -1;
            iArr[i4] = dVar2.f1915e;
            iArr[i2 + 3] = dVar2.f1916f;
            iArr[i2 + 4] = dVar2.f1917g;
            int i5 = i2 + 6;
            iArr[i2 + 5] = dVar2.f1918h;
            ArrayList arrayList2 = dVar2.f1919i;
            if (arrayList2 != null) {
                int size2 = arrayList2.size();
                i2 += 7;
                this.f1974a[i5] = size2;
                int i6 = 0;
                while (i6 < size2) {
                    this.f1974a[i2] = ((AbstractComponentCallbacksC0165t) dVar2.f1919i.get(i6)).f2018f;
                    i6++;
                    i2++;
                }
            } else {
                i2 += 7;
                iArr[i5] = 0;
            }
        }
        this.f1975b = runnableC0156o.f1881i;
        this.f1976c = runnableC0156o.f1882j;
        this.f1977d = runnableC0156o.f1885m;
        this.f1978e = runnableC0156o.f1887o;
        this.f1979f = runnableC0156o.f1888p;
        this.f1980g = runnableC0156o.f1889q;
        this.f1981h = runnableC0156o.f1890r;
        this.f1982i = runnableC0156o.f1891s;
        this.f1983j = runnableC0156o.f1892t;
        this.f1984k = runnableC0156o.f1893u;
    }

    public RunnableC0156o a(C0109A c0109a) {
        RunnableC0156o runnableC0156o = new RunnableC0156o(c0109a);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f1974a.length) {
            RunnableC0156o.d dVar = new RunnableC0156o.d();
            int i4 = i2 + 1;
            dVar.f1913c = this.f1974a[i2];
            if (C0109A.f1765x) {
                Log.v("FragmentManager", "Instantiate " + runnableC0156o + " op #" + i3 + " base fragment #" + this.f1974a[i4]);
            }
            int i5 = i2 + 2;
            int i6 = this.f1974a[i4];
            if (i6 >= 0) {
                dVar.f1914d = (AbstractComponentCallbacksC0165t) c0109a.f1771d.get(i6);
            } else {
                dVar.f1914d = null;
            }
            int[] iArr = this.f1974a;
            dVar.f1915e = iArr[i5];
            dVar.f1916f = iArr[i2 + 3];
            dVar.f1917g = iArr[i2 + 4];
            int i7 = i2 + 6;
            dVar.f1918h = iArr[i2 + 5];
            i2 += 7;
            int i8 = iArr[i7];
            if (i8 > 0) {
                dVar.f1919i = new ArrayList(i8);
                int i9 = 0;
                while (i9 < i8) {
                    if (C0109A.f1765x) {
                        Log.v("FragmentManager", "Instantiate " + runnableC0156o + " set remove fragment #" + this.f1974a[i2]);
                    }
                    dVar.f1919i.add((AbstractComponentCallbacksC0165t) c0109a.f1771d.get(this.f1974a[i2]));
                    i9++;
                    i2++;
                }
            }
            runnableC0156o.f1877e = dVar.f1915e;
            runnableC0156o.f1878f = dVar.f1916f;
            runnableC0156o.f1879g = dVar.f1917g;
            runnableC0156o.f1880h = dVar.f1918h;
            runnableC0156o.e(dVar);
            i3++;
        }
        runnableC0156o.f1881i = this.f1975b;
        runnableC0156o.f1882j = this.f1976c;
        runnableC0156o.f1885m = this.f1977d;
        runnableC0156o.f1887o = this.f1978e;
        runnableC0156o.f1883k = true;
        runnableC0156o.f1888p = this.f1979f;
        runnableC0156o.f1889q = this.f1980g;
        runnableC0156o.f1890r = this.f1981h;
        runnableC0156o.f1891s = this.f1982i;
        runnableC0156o.f1892t = this.f1983j;
        runnableC0156o.f1893u = this.f1984k;
        runnableC0156o.g(1);
        return runnableC0156o;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f1974a);
        parcel.writeInt(this.f1975b);
        parcel.writeInt(this.f1976c);
        parcel.writeString(this.f1977d);
        parcel.writeInt(this.f1978e);
        parcel.writeInt(this.f1979f);
        TextUtils.writeToParcel(this.f1980g, parcel, 0);
        parcel.writeInt(this.f1981h);
        TextUtils.writeToParcel(this.f1982i, parcel, 0);
        parcel.writeStringList(this.f1983j);
        parcel.writeStringList(this.f1984k);
    }

    public C0158p(Parcel parcel) {
        this.f1974a = parcel.createIntArray();
        this.f1975b = parcel.readInt();
        this.f1976c = parcel.readInt();
        this.f1977d = parcel.readString();
        this.f1978e = parcel.readInt();
        this.f1979f = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f1980g = (CharSequence) creator.createFromParcel(parcel);
        this.f1981h = parcel.readInt();
        this.f1982i = (CharSequence) creator.createFromParcel(parcel);
        this.f1983j = parcel.createStringArrayList();
        this.f1984k = parcel.createStringArrayList();
    }
}
