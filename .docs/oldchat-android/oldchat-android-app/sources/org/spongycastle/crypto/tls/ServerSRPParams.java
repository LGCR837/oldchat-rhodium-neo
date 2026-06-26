package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ServerSRPParams {

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    protected BigInteger f7439B;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    protected BigInteger f7440N;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected BigInteger f7441g;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    protected byte[] f7442s;

    public ServerSRPParams(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, BigInteger bigInteger3) {
        this.f7440N = bigInteger;
        this.f7441g = bigInteger2;
        this.f7442s = Arrays.clone(bArr);
        this.f7439B = bigInteger3;
    }

    public static ServerSRPParams parse(InputStream inputStream) {
        return new ServerSRPParams(TlsSRPUtils.readSRPParameter(inputStream), TlsSRPUtils.readSRPParameter(inputStream), TlsUtils.readOpaque8(inputStream), TlsSRPUtils.readSRPParameter(inputStream));
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.f7440N, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f7441g, outputStream);
        TlsUtils.writeOpaque8(this.f7442s, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f7439B, outputStream);
    }

    public BigInteger getB() {
        return this.f7439B;
    }

    public BigInteger getG() {
        return this.f7441g;
    }

    public BigInteger getN() {
        return this.f7440N;
    }

    public byte[] getS() {
        return this.f7442s;
    }
}
