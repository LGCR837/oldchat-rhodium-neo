package q;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static int[] a(int[] iArr, int i2, int i3) {
        if (i2 + 1 > iArr.length) {
            int[] iArr2 = new int[c(i2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr = iArr2;
        }
        iArr[i2] = i3;
        return iArr;
    }

    public static Object[] b(Object[] objArr, int i2, Object obj) {
        if (i2 + 1 > objArr.length) {
            Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), c(i2));
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            objArr = objArr2;
        }
        objArr[i2] = obj;
        return objArr;
    }

    public static int c(int i2) {
        if (i2 <= 4) {
            return 8;
        }
        return i2 * 2;
    }
}
