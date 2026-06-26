package Y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f621a = new HashMap();

    public int[] a() {
        ArrayList arrayList = new ArrayList();
        int iIntValue = -1;
        for (Map.Entry entry : this.f621a.entrySet()) {
            if (((Integer) entry.getValue()).intValue() > iIntValue) {
                iIntValue = ((Integer) entry.getValue()).intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (((Integer) entry.getValue()).intValue() == iIntValue) {
                arrayList.add(entry.getKey());
            }
        }
        return X.a.b(arrayList);
    }

    public void b(int i2) {
        Integer num = (Integer) this.f621a.get(Integer.valueOf(i2));
        if (num == null) {
            num = 0;
        }
        this.f621a.put(Integer.valueOf(i2), Integer.valueOf(num.intValue() + 1));
    }
}
