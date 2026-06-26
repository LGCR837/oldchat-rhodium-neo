package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class NewSessionTicket {
    protected byte[] ticket;
    protected long ticketLifetimeHint;

    public NewSessionTicket(long j2, byte[] bArr) {
        this.ticketLifetimeHint = j2;
        this.ticket = bArr;
    }

    public static NewSessionTicket parse(InputStream inputStream) {
        return new NewSessionTicket(TlsUtils.readUint32(inputStream), TlsUtils.readOpaque16(inputStream));
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint32(this.ticketLifetimeHint, outputStream);
        TlsUtils.writeOpaque16(this.ticket, outputStream);
    }

    public byte[] getTicket() {
        return this.ticket;
    }

    public long getTicketLifetimeHint() {
        return this.ticketLifetimeHint;
    }
}
