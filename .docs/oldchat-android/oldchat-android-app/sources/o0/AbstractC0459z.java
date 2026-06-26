package o0;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: o0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0459z {
    public static byte[] a(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[PKIFailureInfo.certConfirmed];
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 == -1) {
                fileInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    public static String b(String str) {
        String lowerCase = str == null ? "" : str.toLowerCase();
        return lowerCase.endsWith(".png") ? "image/png" : lowerCase.endsWith(".gif") ? "image/gif" : "image/jpeg";
    }
}
