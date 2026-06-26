package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;

/* JADX INFO: loaded from: classes.dex */
public class ServerNameList {
    protected Vector serverNameList;

    public ServerNameList(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("'serverNameList' must not be null");
        }
        this.serverNameList = vector;
    }

    private static short[] checkNameType(short[] sArr, short s2) {
        if (!NameType.isValid(s2) || Arrays.contains(sArr, s2)) {
            return null;
        }
        return Arrays.append(sArr, s2);
    }

    public static ServerNameList parse(InputStream inputStream) {
        int uint16 = TlsUtils.readUint16(inputStream);
        if (uint16 < 1) {
            throw new TlsFatalAlert((short) 50);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(uint16, inputStream));
        short[] sArrCheckNameType = new short[0];
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            ServerName serverName = ServerName.parse(byteArrayInputStream);
            sArrCheckNameType = checkNameType(sArrCheckNameType, serverName.getNameType());
            if (sArrCheckNameType == null) {
                throw new TlsFatalAlert((short) 47);
            }
            vector.addElement(serverName);
        }
        return new ServerNameList(vector);
    }

    public void encode(OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        short[] sArrCheckNameType = new short[0];
        for (int i2 = 0; i2 < this.serverNameList.size(); i2++) {
            ServerName serverName = (ServerName) this.serverNameList.elementAt(i2);
            sArrCheckNameType = checkNameType(sArrCheckNameType, serverName.getNameType());
            if (sArrCheckNameType == null) {
                throw new TlsFatalAlert((short) 80);
            }
            serverName.encode(byteArrayOutputStream);
        }
        TlsUtils.checkUint16(byteArrayOutputStream.size());
        TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
        Streams.writeBufTo(byteArrayOutputStream, outputStream);
    }

    public Vector getServerNameList() {
        return this.serverNameList;
    }
}
