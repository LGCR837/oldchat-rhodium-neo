package org.spongycastle.crypto.tls;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DTLSTransport implements DatagramTransport {
    private final DTLSRecordLayer recordLayer;

    public DTLSTransport(DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public void close() throws TlsFatalAlert {
        this.recordLayer.close();
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int getReceiveLimit() {
        return this.recordLayer.getReceiveLimit();
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int getSendLimit() {
        return this.recordLayer.getSendLimit();
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int receive(byte[] bArr, int i2, int i3, int i4) throws IOException {
        try {
            return this.recordLayer.receive(bArr, i2, i3, i4);
        } catch (TlsFatalAlert e2) {
            this.recordLayer.fail(e2.getAlertDescription());
            throw e2;
        } catch (IOException e3) {
            this.recordLayer.fail((short) 80);
            throw e3;
        } catch (RuntimeException e4) {
            this.recordLayer.fail((short) 80);
            throw new TlsFatalAlert((short) 80, e4);
        }
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public void send(byte[] bArr, int i2, int i3) throws IOException {
        try {
            this.recordLayer.send(bArr, i2, i3);
        } catch (TlsFatalAlert e2) {
            this.recordLayer.fail(e2.getAlertDescription());
            throw e2;
        } catch (IOException e3) {
            this.recordLayer.fail((short) 80);
            throw e3;
        } catch (RuntimeException e4) {
            this.recordLayer.fail((short) 80);
            throw new TlsFatalAlert((short) 80, e4);
        }
    }
}
