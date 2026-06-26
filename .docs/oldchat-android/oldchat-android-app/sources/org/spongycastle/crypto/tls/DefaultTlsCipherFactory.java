package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.engines.CamelliaEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.RC4Engine;
import org.spongycastle.crypto.engines.SEEDEngine;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.OCBBlockCipher;

/* JADX INFO: loaded from: classes.dex */
public class DefaultTlsCipherFactory extends AbstractTlsCipherFactory {
    public AEADBlockCipher createAEADBlockCipher_AES_CCM() {
        return new CCMBlockCipher(createAESEngine());
    }

    public AEADBlockCipher createAEADBlockCipher_AES_GCM() {
        return new GCMBlockCipher(createAESEngine());
    }

    public AEADBlockCipher createAEADBlockCipher_AES_OCB() {
        return new OCBBlockCipher(createAESEngine(), createAESEngine());
    }

    public AEADBlockCipher createAEADBlockCipher_Camellia_GCM() {
        return new GCMBlockCipher(createCamelliaEngine());
    }

    public BlockCipher createAESBlockCipher() {
        return new CBCBlockCipher(createAESEngine());
    }

    public TlsBlockCipher createAESCipher(TlsContext tlsContext, int i2, int i3) {
        return new TlsBlockCipher(tlsContext, createAESBlockCipher(), createAESBlockCipher(), createHMACDigest(i3), createHMACDigest(i3), i2);
    }

    public BlockCipher createAESEngine() {
        return new AESEngine();
    }

    public BlockCipher createCamelliaBlockCipher() {
        return new CBCBlockCipher(createCamelliaEngine());
    }

    public TlsBlockCipher createCamelliaCipher(TlsContext tlsContext, int i2, int i3) {
        return new TlsBlockCipher(tlsContext, createCamelliaBlockCipher(), createCamelliaBlockCipher(), createHMACDigest(i3), createHMACDigest(i3), i2);
    }

    public BlockCipher createCamelliaEngine() {
        return new CamelliaEngine();
    }

    public TlsCipher createChaCha20Poly1305(TlsContext tlsContext) {
        return new Chacha20Poly1305(tlsContext);
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsCipherFactory, org.spongycastle.crypto.tls.TlsCipherFactory
    public TlsCipher createCipher(TlsContext tlsContext, int i2, int i3) throws TlsFatalAlert {
        if (i2 == 0) {
            return createNullCipher(tlsContext, i3);
        }
        if (i2 == 2) {
            return createRC4Cipher(tlsContext, 16, i3);
        }
        if (i2 == 103) {
            return createCipher_AES_OCB(tlsContext, 16, 12);
        }
        if (i2 == 104) {
            return createCipher_AES_OCB(tlsContext, 32, 12);
        }
        switch (i2) {
            case 7:
                return createDESedeCipher(tlsContext, i3);
            case 8:
                return createAESCipher(tlsContext, 16, i3);
            case 9:
                return createAESCipher(tlsContext, 32, i3);
            case 10:
                return createCipher_AES_GCM(tlsContext, 16, 16);
            case 11:
                return createCipher_AES_GCM(tlsContext, 32, 16);
            case 12:
                return createCamelliaCipher(tlsContext, 16, i3);
            case 13:
                return createCamelliaCipher(tlsContext, 32, i3);
            case 14:
                return createSEEDCipher(tlsContext, i3);
            case 15:
                return createCipher_AES_CCM(tlsContext, 16, 16);
            case 16:
                return createCipher_AES_CCM(tlsContext, 16, 8);
            case 17:
                return createCipher_AES_CCM(tlsContext, 32, 16);
            case 18:
                return createCipher_AES_CCM(tlsContext, 32, 8);
            case 19:
                return createCipher_Camellia_GCM(tlsContext, 16, 16);
            case 20:
                return createCipher_Camellia_GCM(tlsContext, 32, 16);
            case 21:
                return createChaCha20Poly1305(tlsContext);
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public TlsAEADCipher createCipher_AES_CCM(TlsContext tlsContext, int i2, int i3) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_CCM(), createAEADBlockCipher_AES_CCM(), i2, i3);
    }

    public TlsAEADCipher createCipher_AES_GCM(TlsContext tlsContext, int i2, int i3) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_GCM(), createAEADBlockCipher_AES_GCM(), i2, i3);
    }

    public TlsAEADCipher createCipher_AES_OCB(TlsContext tlsContext, int i2, int i3) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_OCB(), createAEADBlockCipher_AES_OCB(), i2, i3, 2);
    }

    public TlsAEADCipher createCipher_Camellia_GCM(TlsContext tlsContext, int i2, int i3) {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_Camellia_GCM(), createAEADBlockCipher_Camellia_GCM(), i2, i3);
    }

    public BlockCipher createDESedeBlockCipher() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    public TlsBlockCipher createDESedeCipher(TlsContext tlsContext, int i2) {
        return new TlsBlockCipher(tlsContext, createDESedeBlockCipher(), createDESedeBlockCipher(), createHMACDigest(i2), createHMACDigest(i2), 24);
    }

    public Digest createHMACDigest(int i2) throws TlsFatalAlert {
        if (i2 == 0) {
            return null;
        }
        if (i2 == 1) {
            return TlsUtils.createHash((short) 1);
        }
        if (i2 == 2) {
            return TlsUtils.createHash((short) 2);
        }
        if (i2 == 3) {
            return TlsUtils.createHash((short) 4);
        }
        if (i2 == 4) {
            return TlsUtils.createHash((short) 5);
        }
        if (i2 == 5) {
            return TlsUtils.createHash((short) 6);
        }
        throw new TlsFatalAlert((short) 80);
    }

    public TlsNullCipher createNullCipher(TlsContext tlsContext, int i2) {
        return new TlsNullCipher(tlsContext, createHMACDigest(i2), createHMACDigest(i2));
    }

    public TlsStreamCipher createRC4Cipher(TlsContext tlsContext, int i2, int i3) {
        return new TlsStreamCipher(tlsContext, createRC4StreamCipher(), createRC4StreamCipher(), createHMACDigest(i3), createHMACDigest(i3), i2, false);
    }

    public StreamCipher createRC4StreamCipher() {
        return new RC4Engine();
    }

    public BlockCipher createSEEDBlockCipher() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    public TlsBlockCipher createSEEDCipher(TlsContext tlsContext, int i2) {
        return new TlsBlockCipher(tlsContext, createSEEDBlockCipher(), createSEEDBlockCipher(), createHMACDigest(i2), createHMACDigest(i2), 16);
    }
}
