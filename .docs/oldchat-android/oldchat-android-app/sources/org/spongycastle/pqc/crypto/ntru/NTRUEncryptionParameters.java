package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class NTRUEncryptionParameters implements Cloneable {

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f7655N;
    public int bufferLenBits;
    int bufferLenTrits;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7656c;
    public int db;
    public int df;
    public int df1;
    public int df2;
    public int df3;
    public int dg;
    public int dm0;
    public int dr;
    public int dr1;
    public int dr2;
    public int dr3;
    public boolean fastFp;
    public Digest hashAlg;
    public boolean hashSeed;
    int llen;
    public int maxMsgLenBytes;
    public int minCallsMask;
    public int minCallsR;
    public byte[] oid;
    public int pkLen;
    public int polyType;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f7657q;
    public boolean sparse;

    public NTRUEncryptionParameters(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2, byte[] bArr, boolean z3, boolean z4, Digest digest) {
        this.f7655N = i2;
        this.f7657q = i3;
        this.df = i4;
        this.db = i6;
        this.dm0 = i5;
        this.f7656c = i7;
        this.minCallsR = i8;
        this.minCallsMask = i9;
        this.hashSeed = z2;
        this.oid = bArr;
        this.sparse = z3;
        this.fastFp = z4;
        this.polyType = 0;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        this.dr = this.df;
        this.dr1 = this.df1;
        this.dr2 = this.df2;
        this.dr3 = this.df3;
        int i2 = this.f7655N;
        this.dg = i2 / 3;
        this.llen = 1;
        int i3 = this.db;
        this.maxMsgLenBytes = (((((i2 * 3) / 2) / 8) - 1) - (i3 / 8)) - 1;
        this.bufferLenBits = (((((i2 * 3) / 2) + 7) / 8) * 8) + 1;
        this.bufferLenTrits = i2 - 1;
        this.pkLen = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionParameters nTRUEncryptionParameters = (NTRUEncryptionParameters) obj;
        if (this.f7655N != nTRUEncryptionParameters.f7655N || this.bufferLenBits != nTRUEncryptionParameters.bufferLenBits || this.bufferLenTrits != nTRUEncryptionParameters.bufferLenTrits || this.f7656c != nTRUEncryptionParameters.f7656c || this.db != nTRUEncryptionParameters.db || this.df != nTRUEncryptionParameters.df || this.df1 != nTRUEncryptionParameters.df1 || this.df2 != nTRUEncryptionParameters.df2 || this.df3 != nTRUEncryptionParameters.df3 || this.dg != nTRUEncryptionParameters.dg || this.dm0 != nTRUEncryptionParameters.dm0 || this.dr != nTRUEncryptionParameters.dr || this.dr1 != nTRUEncryptionParameters.dr1 || this.dr2 != nTRUEncryptionParameters.dr2 || this.dr3 != nTRUEncryptionParameters.dr3 || this.fastFp != nTRUEncryptionParameters.fastFp) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUEncryptionParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUEncryptionParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.hashSeed == nTRUEncryptionParameters.hashSeed && this.llen == nTRUEncryptionParameters.llen && this.maxMsgLenBytes == nTRUEncryptionParameters.maxMsgLenBytes && this.minCallsMask == nTRUEncryptionParameters.minCallsMask && this.minCallsR == nTRUEncryptionParameters.minCallsR && Arrays.equals(this.oid, nTRUEncryptionParameters.oid) && this.pkLen == nTRUEncryptionParameters.pkLen && this.polyType == nTRUEncryptionParameters.polyType && this.f7657q == nTRUEncryptionParameters.f7657q && this.sparse == nTRUEncryptionParameters.sparse;
    }

    public int getMaxMessageLength() {
        return this.maxMsgLenBytes;
    }

    public int hashCode() {
        int i2 = (((((((((((((((((((((((((((((((this.f7655N + 31) * 31) + this.bufferLenBits) * 31) + this.bufferLenTrits) * 31) + this.f7656c) * 31) + this.db) * 31) + this.df) * 31) + this.df1) * 31) + this.df2) * 31) + this.df3) * 31) + this.dg) * 31) + this.dm0) * 31) + this.dr) * 31) + this.dr1) * 31) + this.dr2) * 31) + this.dr3) * 31) + (this.fastFp ? 1231 : 1237)) * 31;
        Digest digest = this.hashAlg;
        return ((((((((((((((((((((i2 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + (this.hashSeed ? 1231 : 1237)) * 31) + this.llen) * 31) + this.maxMsgLenBytes) * 31) + this.minCallsMask) * 31) + this.minCallsR) * 31) + Arrays.hashCode(this.oid)) * 31) + this.pkLen) * 31) + this.polyType) * 31) + this.f7657q) * 31) + (this.sparse ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EncryptionParameters(N=" + this.f7655N + " q=" + this.f7657q);
        if (this.polyType == 0) {
            sb.append(" polyType=SIMPLE df=" + this.df);
        } else {
            sb.append(" polyType=PRODUCT df1=" + this.df1 + " df2=" + this.df2 + " df3=" + this.df3);
        }
        sb.append(" dm0=" + this.dm0 + " db=" + this.db + " c=" + this.f7656c + " minCallsR=" + this.minCallsR + " minCallsMask=" + this.minCallsMask + " hashSeed=" + this.hashSeed + " hashAlg=" + this.hashAlg + " oid=" + Arrays.toString(this.oid) + " sparse=" + this.sparse + ")");
        return sb.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f7655N);
        dataOutputStream.writeInt(this.f7657q);
        dataOutputStream.writeInt(this.df);
        dataOutputStream.writeInt(this.df1);
        dataOutputStream.writeInt(this.df2);
        dataOutputStream.writeInt(this.df3);
        dataOutputStream.writeInt(this.db);
        dataOutputStream.writeInt(this.dm0);
        dataOutputStream.writeInt(this.f7656c);
        dataOutputStream.writeInt(this.minCallsR);
        dataOutputStream.writeInt(this.minCallsMask);
        dataOutputStream.writeBoolean(this.hashSeed);
        dataOutputStream.write(this.oid);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeBoolean(this.fastFp);
        dataOutputStream.write(this.polyType);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public NTRUEncryptionParameters clone() {
        return this.polyType == 0 ? new NTRUEncryptionParameters(this.f7655N, this.f7657q, this.df, this.dm0, this.db, this.f7656c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg) : new NTRUEncryptionParameters(this.f7655N, this.f7657q, this.df1, this.df2, this.df3, this.dm0, this.db, this.f7656c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
    }

    public NTRUEncryptionParameters(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z2, byte[] bArr, boolean z3, boolean z4, Digest digest) {
        this.f7655N = i2;
        this.f7657q = i3;
        this.df1 = i4;
        this.df2 = i5;
        this.df3 = i6;
        this.db = i8;
        this.dm0 = i7;
        this.f7656c = i9;
        this.minCallsR = i10;
        this.minCallsMask = i11;
        this.hashSeed = z2;
        this.oid = bArr;
        this.sparse = z3;
        this.fastFp = z4;
        this.polyType = 1;
        this.hashAlg = digest;
        init();
    }

    public NTRUEncryptionParameters(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f7655N = dataInputStream.readInt();
        this.f7657q = dataInputStream.readInt();
        this.df = dataInputStream.readInt();
        this.df1 = dataInputStream.readInt();
        this.df2 = dataInputStream.readInt();
        this.df3 = dataInputStream.readInt();
        this.db = dataInputStream.readInt();
        this.dm0 = dataInputStream.readInt();
        this.f7656c = dataInputStream.readInt();
        this.minCallsR = dataInputStream.readInt();
        this.minCallsMask = dataInputStream.readInt();
        this.hashSeed = dataInputStream.readBoolean();
        byte[] bArr = new byte[3];
        this.oid = bArr;
        dataInputStream.read(bArr);
        this.sparse = dataInputStream.readBoolean();
        this.fastFp = dataInputStream.readBoolean();
        this.polyType = dataInputStream.read();
        String utf = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(utf)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(utf)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }
}
