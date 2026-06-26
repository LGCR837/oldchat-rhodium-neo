package org.spongycastle.crypto.tls;

import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public final class ProtocolVersion {
    private String name;
    private int version;
    public static final ProtocolVersion SSLv3 = new ProtocolVersion(768, "SSL 3.0");
    public static final ProtocolVersion TLSv10 = new ProtocolVersion(769, "TLS 1.0");
    public static final ProtocolVersion TLSv11 = new ProtocolVersion(770, "TLS 1.1");
    public static final ProtocolVersion TLSv12 = new ProtocolVersion(771, "TLS 1.2");
    public static final ProtocolVersion DTLSv10 = new ProtocolVersion(65279, "DTLS 1.0");
    public static final ProtocolVersion DTLSv12 = new ProtocolVersion(65277, "DTLS 1.2");

    private ProtocolVersion(int i2, String str) {
        this.version = i2 & 65535;
        this.name = str;
    }

    public static ProtocolVersion get(int i2, int i3) throws TlsFatalAlert {
        if (i2 == 3) {
            return i3 != 0 ? i3 != 1 ? i3 != 2 ? i3 != 3 ? getUnknownVersion(i2, i3, "TLS") : TLSv12 : TLSv11 : TLSv10 : SSLv3;
        }
        if (i2 != 254) {
            throw new TlsFatalAlert((short) 47);
        }
        switch (i3) {
            case 253:
                return DTLSv12;
            case 254:
                throw new TlsFatalAlert((short) 47);
            case 255:
                return DTLSv10;
            default:
                return getUnknownVersion(i2, i3, "DTLS");
        }
    }

    private static ProtocolVersion getUnknownVersion(int i2, int i3, String str) throws TlsFatalAlert {
        TlsUtils.checkUint8(i2);
        TlsUtils.checkUint8(i3);
        int i4 = (i2 << 8) | i3;
        return new ProtocolVersion(i4, str + " 0x" + Strings.toUpperCase(Integer.toHexString(65536 | i4).substring(1)));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ProtocolVersion) && equals((ProtocolVersion) obj));
    }

    public ProtocolVersion getEquivalentTLSVersion() {
        return !isDTLS() ? this : this == DTLSv10 ? TLSv11 : TLSv12;
    }

    public int getFullVersion() {
        return this.version;
    }

    public int getMajorVersion() {
        return this.version >> 8;
    }

    public int getMinorVersion() {
        return this.version & 255;
    }

    public int hashCode() {
        return this.version;
    }

    public boolean isDTLS() {
        return getMajorVersion() == 254;
    }

    public boolean isEqualOrEarlierVersionOf(ProtocolVersion protocolVersion) {
        if (getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = protocolVersion.getMinorVersion() - getMinorVersion();
        if (isDTLS()) {
            if (minorVersion > 0) {
                return false;
            }
        } else if (minorVersion < 0) {
            return false;
        }
        return true;
    }

    public boolean isLaterVersionOf(ProtocolVersion protocolVersion) {
        if (getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = protocolVersion.getMinorVersion() - getMinorVersion();
        if (isDTLS()) {
            if (minorVersion <= 0) {
                return false;
            }
        } else if (minorVersion >= 0) {
            return false;
        }
        return true;
    }

    public boolean isSSL() {
        return this == SSLv3;
    }

    public boolean isTLS() {
        return getMajorVersion() == 3;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.version == protocolVersion.version;
    }
}
