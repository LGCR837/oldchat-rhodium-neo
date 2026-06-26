package org.spongycastle.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Vector;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public final class Strings {
    private static String LINE_SEPARATOR;

    public static class StringListImpl extends ArrayList<String> implements StringList {
        private StringListImpl() {
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List, org.spongycastle.util.StringList
        public /* bridge */ /* synthetic */ String get(int i2) {
            return (String) super.get(i2);
        }

        @Override // org.spongycastle.util.StringList
        public String[] toStringArray() {
            int size = size();
            String[] strArr = new String[size];
            for (int i2 = 0; i2 != size; i2++) {
                strArr[i2] = get(i2);
            }
            return strArr;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public String set(int i2, String str) {
            return (String) super.set(i2, str);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(String str) {
            return super.add(str);
        }

        @Override // org.spongycastle.util.StringList
        public String[] toStringArray(int i2, int i3) {
            String[] strArr = new String[i3 - i2];
            for (int i4 = i2; i4 != size() && i4 != i3; i4++) {
                strArr[i4 - i2] = get(i4);
            }
            return strArr;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public void add(int i2, String str) {
            super.add(i2, str);
        }
    }

    static {
        try {
            try {
                LINE_SEPARATOR = (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.spongycastle.util.Strings.1
                    @Override // java.security.PrivilegedAction
                    public String run() {
                        return System.getProperty("line.separator");
                    }
                });
            } catch (Exception unused) {
                LINE_SEPARATOR = "\n";
            }
        } catch (Exception unused2) {
            LINE_SEPARATOR = String.format("%n", new Object[0]);
        }
    }

    public static char[] asCharArray(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return cArr;
    }

    public static String fromByteArray(byte[] bArr) {
        return new String(asCharArray(bArr));
    }

    public static String fromUTF8ByteArray(byte[] bArr) {
        char c2;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            int i5 = i4 + 1;
            byte b2 = bArr[i3];
            if ((b2 & 240) == 240) {
                i4 += 2;
                i3 += 4;
            } else {
                i3 = (b2 & 224) == 224 ? i3 + 3 : (b2 & 192) == 192 ? i3 + 2 : i3 + 1;
                i4 = i5;
            }
        }
        char[] cArr = new char[i4];
        int i6 = 0;
        while (i2 < bArr.length) {
            byte b3 = bArr[i2];
            if ((b3 & 240) == 240) {
                int i7 = (((((b3 & 3) << 18) | ((bArr[i2 + 1] & 63) << 12)) | ((bArr[i2 + 2] & 63) << 6)) | (bArr[i2 + 3] & 63)) - PKIFailureInfo.notAuthorized;
                char c3 = (char) ((i7 >> 10) | 55296);
                c2 = (char) ((i7 & 1023) | 56320);
                cArr[i6] = c3;
                i2 += 4;
                i6++;
            } else if ((b3 & 224) == 224) {
                c2 = (char) (((b3 & 15) << 12) | ((bArr[i2 + 1] & 63) << 6) | (bArr[i2 + 2] & 63));
                i2 += 3;
            } else if ((b3 & 208) == 208 || (b3 & 192) == 192) {
                int i8 = (b3 & 31) << 6;
                byte b4 = bArr[i2 + 1];
                c2 = (char) (i8 | (b4 & 63));
                i2 += 2;
            } else {
                c2 = (char) (b3 & 255);
                i2++;
            }
            cArr[i6] = c2;
            i6++;
        }
        return new String(cArr);
    }

    public static String lineSeparator() {
        return LINE_SEPARATOR;
    }

    public static StringList newList() {
        return new StringListImpl();
    }

    public static String[] split(String str, char c2) {
        int i2;
        Vector vector = new Vector();
        boolean z2 = true;
        while (true) {
            if (!z2) {
                break;
            }
            int iIndexOf = str.indexOf(c2);
            if (iIndexOf > 0) {
                vector.addElement(str.substring(0, iIndexOf));
                str = str.substring(iIndexOf + 1);
            } else {
                vector.addElement(str);
                z2 = false;
            }
        }
        int size = vector.size();
        String[] strArr = new String[size];
        for (i2 = 0; i2 != size; i2++) {
            strArr[i2] = (String) vector.elementAt(i2);
        }
        return strArr;
    }

    public static byte[] toByteArray(char[] cArr) {
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) cArr[i2];
        }
        return bArr;
    }

    public static String toLowerCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z2 = false;
        for (int i2 = 0; i2 != charArray.length; i2++) {
            char c2 = charArray[i2];
            if ('A' <= c2 && 'Z' >= c2) {
                charArray[i2] = (char) (c2 + ' ');
                z2 = true;
            }
        }
        return z2 ? new String(charArray) : str;
    }

    public static byte[] toUTF8ByteArray(String str) {
        return toUTF8ByteArray(str.toCharArray());
    }

    public static String toUpperCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z2 = false;
        for (int i2 = 0; i2 != charArray.length; i2++) {
            char c2 = charArray[i2];
            if ('a' <= c2 && 'z' >= c2) {
                charArray[i2] = (char) (c2 - ' ');
                z2 = true;
            }
        }
        return z2 ? new String(charArray) : str;
    }

    public static byte[] toUTF8ByteArray(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            toUTF8ByteArray(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static byte[] toByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) str.charAt(i2);
        }
        return bArr;
    }

    public static int toByteArray(String str, byte[] bArr, int i2) {
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i2 + i3] = (byte) str.charAt(i3);
        }
        return length;
    }

    public static void toUTF8ByteArray(char[] cArr, OutputStream outputStream) throws IOException {
        int i2 = 0;
        while (i2 < cArr.length) {
            char c2 = cArr[i2];
            if (c2 < 128) {
                outputStream.write(c2);
            } else if (c2 < 2048) {
                outputStream.write((c2 >> 6) | 192);
                outputStream.write((c2 & '?') | 128);
            } else if (c2 >= 55296 && c2 <= 57343) {
                i2++;
                if (i2 < cArr.length) {
                    char c3 = cArr[i2];
                    if (c2 <= 56319) {
                        int i3 = (((c2 & 1023) << 10) | (c3 & 1023)) + PKIFailureInfo.notAuthorized;
                        outputStream.write((i3 >> 18) | 240);
                        outputStream.write(((i3 >> 12) & 63) | 128);
                        outputStream.write(((i3 >> 6) & 63) | 128);
                        outputStream.write((i3 & 63) | 128);
                    } else {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                } else {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
            } else {
                outputStream.write((c2 >> '\f') | 224);
                outputStream.write(((c2 >> 6) & 63) | 128);
                outputStream.write((c2 & '?') | 128);
            }
            i2++;
        }
    }
}
