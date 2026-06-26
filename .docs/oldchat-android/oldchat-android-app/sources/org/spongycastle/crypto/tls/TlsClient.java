package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public interface TlsClient extends TlsPeer {
    TlsAuthentication getAuthentication();

    int[] getCipherSuites();

    Hashtable getClientExtensions();

    ProtocolVersion getClientHelloRecordLayerVersion();

    Vector getClientSupplementalData();

    ProtocolVersion getClientVersion();

    short[] getCompressionMethods();

    TlsKeyExchange getKeyExchange();

    TlsSession getSessionToResume();

    void init(TlsClientContext tlsClientContext);

    boolean isFallback();

    void notifyNewSessionTicket(NewSessionTicket newSessionTicket);

    void notifySelectedCipherSuite(int i2);

    void notifySelectedCompressionMethod(short s2);

    void notifyServerVersion(ProtocolVersion protocolVersion);

    void notifySessionID(byte[] bArr);

    void processServerExtensions(Hashtable hashtable);

    void processServerSupplementalData(Vector vector);
}
