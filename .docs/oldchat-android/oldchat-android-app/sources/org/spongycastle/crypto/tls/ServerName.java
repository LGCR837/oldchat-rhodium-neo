package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ServerName {
    protected Object name;
    protected short nameType;

    public ServerName(short s2, Object obj) {
        if (!isCorrectType(s2, obj)) {
            throw new IllegalArgumentException("'name' is not an instance of the correct type");
        }
        this.nameType = s2;
        this.name = obj;
    }

    public static boolean isCorrectType(short s2, Object obj) {
        if (s2 == 0) {
            return obj instanceof String;
        }
        throw new IllegalArgumentException("'nameType' is an unsupported NameType");
    }

    public static ServerName parse(InputStream inputStream) throws IOException {
        short uint8 = TlsUtils.readUint8(inputStream);
        if (uint8 != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        byte[] opaque16 = TlsUtils.readOpaque16(inputStream);
        if (opaque16.length >= 1) {
            return new ServerName(uint8, new String(opaque16, "ASCII"));
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.nameType, outputStream);
        if (this.nameType != 0) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] bytes = ((String) this.name).getBytes("ASCII");
        if (bytes.length < 1) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsUtils.writeOpaque16(bytes, outputStream);
    }

    public String getHostName() {
        if (isCorrectType((short) 0, this.name)) {
            return (String) this.name;
        }
        throw new IllegalStateException("'name' is not a HostName string");
    }

    public Object getName() {
        return this.name;
    }

    public short getNameType() {
        return this.nameType;
    }
}
