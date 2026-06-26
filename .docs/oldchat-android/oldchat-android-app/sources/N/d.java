package N;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f441b;

    public d(a aVar) {
        this.f440a = aVar;
        ArrayList arrayList = new ArrayList();
        this.f441b = arrayList;
        arrayList.add(new b(aVar, new int[]{1}));
    }

    public final b a(int i2) {
        if (i2 >= this.f441b.size()) {
            List list = this.f441b;
            b bVarI = (b) list.get(list.size() - 1);
            for (int size = this.f441b.size(); size <= i2; size++) {
                a aVar = this.f440a;
                bVarI = bVarI.i(new b(aVar, new int[]{1, aVar.c((size - 1) + aVar.d())}));
                this.f441b.add(bVarI);
            }
        }
        return (b) this.f441b.get(i2);
    }

    public void b(int[] iArr, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i2;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b bVarA = a(i2);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] iArrE = new b(this.f440a, iArr2).j(i2, 1).b(bVarA)[1].e();
        int length2 = i2 - iArrE.length;
        for (int i3 = 0; i3 < length2; i3++) {
            iArr[length + i3] = 0;
        }
        System.arraycopy(iArrE, 0, iArr, length + length2, iArrE.length);
    }
}
