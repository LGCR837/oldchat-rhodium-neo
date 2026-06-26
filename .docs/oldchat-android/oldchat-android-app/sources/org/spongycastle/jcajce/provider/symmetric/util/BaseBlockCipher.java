package org.spongycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.engines.DSTU7624Engine;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.CTSBlockCipher;
import org.spongycastle.crypto.modes.EAXBlockCipher;
import org.spongycastle.crypto.modes.GCFBBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.GOFBBlockCipher;
import org.spongycastle.crypto.modes.KCCMBlockCipher;
import org.spongycastle.crypto.modes.KCTRBlockCipher;
import org.spongycastle.crypto.modes.KGCMBlockCipher;
import org.spongycastle.crypto.modes.OCBBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.spongycastle.crypto.modes.PGPCFBBlockCipher;
import org.spongycastle.crypto.modes.SICBlockCipher;
import org.spongycastle.crypto.paddings.BlockCipherPadding;
import org.spongycastle.crypto.paddings.ISO10126d2Padding;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.paddings.TBCPadding;
import org.spongycastle.crypto.paddings.X923Padding;
import org.spongycastle.crypto.paddings.ZeroBytePadding;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.params.RC2Parameters;
import org.spongycastle.crypto.params.RC5Parameters;
import org.spongycastle.jcajce.PBKDF1Key;
import org.spongycastle.jcajce.PBKDF1KeyWithParameters;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.PKCS12KeyWithParameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBE;
import org.spongycastle.jcajce.spec.AEADParameterSpec;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.jcajce.spec.RepeatedSecretKeySpec;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class BaseBlockCipher extends BaseWrapCipher implements PBE {
    private static final Class gcmSpecClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.spec.GCMParameterSpec");
    private AEADParameters aeadParams;
    private Class[] availableSpecs;
    private BlockCipher baseEngine;
    private GenericBlockCipher cipher;
    private int digest;
    private BlockCipherProvider engineProvider;
    private boolean fixedIv;
    private int ivLength;
    private ParametersWithIV ivParam;
    private int keySizeInBits;
    private String modeName;
    private boolean padded;
    private String pbeAlgorithm;
    private PBEParameterSpec pbeSpec;
    private int scheme;

    public static class AEADGenericBlockCipher implements GenericBlockCipher {
        private static final Constructor aeadBadTagConstructor;
        private AEADBlockCipher cipher;

        static {
            Class clsLoadClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.AEADBadTagException");
            if (clsLoadClass != null) {
                aeadBadTagConstructor = findExceptionConstructor(clsLoadClass);
            } else {
                aeadBadTagConstructor = null;
            }
        }

        public AEADGenericBlockCipher(AEADBlockCipher aEADBlockCipher) {
            this.cipher = aEADBlockCipher;
        }

        private static Constructor findExceptionConstructor(Class cls) {
            try {
                return cls.getConstructor(String.class);
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int doFinal(byte[] bArr, int i2) throws BadPaddingException {
            BadPaddingException badPaddingException;
            try {
                return this.cipher.doFinal(bArr, i2);
            } catch (InvalidCipherTextException e2) {
                Constructor constructor = aeadBadTagConstructor;
                if (constructor != null) {
                    try {
                        badPaddingException = (BadPaddingException) constructor.newInstance(e2.getMessage());
                    } catch (Exception unused) {
                        badPaddingException = null;
                    }
                    if (badPaddingException != null) {
                        throw badPaddingException;
                    }
                }
                throw new BadPaddingException(e2.getMessage());
            }
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getOutputSize(int i2) {
            return this.cipher.getOutputSize(i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getUpdateOutputSize(int i2) {
            return this.cipher.getUpdateOutputSize(i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void init(boolean z2, CipherParameters cipherParameters) {
            this.cipher.init(z2, cipherParameters);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processByte(byte b2, byte[] bArr, int i2) {
            return this.cipher.processByte(b2, bArr, i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
            return this.cipher.processBytes(bArr, i2, i3, bArr2, i4);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void updateAAD(byte[] bArr, int i2, int i3) {
            this.cipher.processAADBytes(bArr, i2, i3);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public boolean wrapOnNoPadding() {
            return false;
        }
    }

    public interface GenericBlockCipher {
        int doFinal(byte[] bArr, int i2);

        String getAlgorithmName();

        int getOutputSize(int i2);

        BlockCipher getUnderlyingCipher();

        int getUpdateOutputSize(int i2);

        void init(boolean z2, CipherParameters cipherParameters);

        int processByte(byte b2, byte[] bArr, int i2);

        int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

        void updateAAD(byte[] bArr, int i2, int i3);

        boolean wrapOnNoPadding();
    }

    public static class InvalidKeyOrParametersException extends InvalidKeyException {
        private final Throwable cause;

        public InvalidKeyOrParametersException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public BaseBlockCipher(BlockCipher blockCipher) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    private CipherParameters adjustParameters(AlgorithmParameterSpec algorithmParameterSpec, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                ParametersWithIV parametersWithIV = new ParametersWithIV(cipherParameters, ((IvParameterSpec) algorithmParameterSpec).getIV());
                this.ivParam = parametersWithIV;
                cipherParameters2 = parametersWithIV;
            } else {
                if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                    return cipherParameters;
                }
                GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec) algorithmParameterSpec;
                ParametersWithSBox parametersWithSBox = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec.getSbox());
                cipherParameters2 = parametersWithSBox;
                if (gOST28147ParameterSpec.getIV() != null) {
                    cipherParameters2 = parametersWithSBox;
                    if (this.ivLength != 0) {
                        return new ParametersWithIV(parametersWithSBox, gOST28147ParameterSpec.getIV());
                    }
                }
            }
            return cipherParameters2;
        }
        CipherParameters parameters = ((ParametersWithIV) cipherParameters).getParameters();
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            ParametersWithIV parametersWithIV2 = new ParametersWithIV(parameters, ((IvParameterSpec) algorithmParameterSpec).getIV());
            this.ivParam = parametersWithIV2;
            return parametersWithIV2;
        }
        if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
            return cipherParameters;
        }
        GOST28147ParameterSpec gOST28147ParameterSpec2 = (GOST28147ParameterSpec) algorithmParameterSpec;
        ParametersWithSBox parametersWithSBox2 = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec2.getSbox());
        if (gOST28147ParameterSpec2.getIV() == null || this.ivLength == 0) {
            return parametersWithSBox2;
        }
        ParametersWithIV parametersWithIV3 = new ParametersWithIV(parameters, gOST28147ParameterSpec2.getIV());
        this.ivParam = parametersWithIV3;
        return parametersWithIV3;
    }

    private boolean isAEADModeName(String str) {
        return "CCM".equals(str) || "EAX".equals(str) || "GCM".equals(str) || "OCB".equals(str);
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i2, int i3) throws IllegalBlockSizeException {
        int iEngineGetOutputSize = engineGetOutputSize(i3);
        byte[] bArr2 = new byte[iEngineGetOutputSize];
        int iProcessBytes = i3 != 0 ? this.cipher.processBytes(bArr, i2, i3, bArr2, 0) : 0;
        try {
            int iDoFinal = iProcessBytes + this.cipher.doFinal(bArr2, iProcessBytes);
            if (iDoFinal == iEngineGetOutputSize) {
                return bArr2;
            }
            byte[] bArr3 = new byte[iDoFinal];
            System.arraycopy(bArr2, 0, bArr3, 0, iDoFinal);
            return bArr3;
        } catch (DataLengthException e2) {
            throw new IllegalBlockSizeException(e2.getMessage());
        }
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        return this.baseEngine.getBlockSize();
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        AEADParameters aEADParameters = this.aeadParams;
        if (aEADParameters != null) {
            return aEADParameters.getNonce();
        }
        ParametersWithIV parametersWithIV = this.ivParam;
        if (parametersWithIV != null) {
            return parametersWithIV.getIV();
        }
        return null;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineGetOutputSize(int i2) {
        return this.cipher.getOutputSize(i2);
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            if (this.pbeSpec != null) {
                try {
                    AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance(this.pbeAlgorithm);
                    this.engineParams = algorithmParametersCreateParametersInstance;
                    algorithmParametersCreateParametersInstance.init(this.pbeSpec);
                } catch (Exception unused) {
                    return null;
                }
            } else if (this.aeadParams != null) {
                try {
                    AlgorithmParameters algorithmParametersCreateParametersInstance2 = createParametersInstance("GCM");
                    this.engineParams = algorithmParametersCreateParametersInstance2;
                    algorithmParametersCreateParametersInstance2.init(new GCMParameters(this.aeadParams.getNonce(), this.aeadParams.getMacSize() / 8).getEncoded());
                } catch (Exception e2) {
                    throw new RuntimeException(e2.toString());
                }
            } else if (this.ivParam != null) {
                String algorithmName = this.cipher.getUnderlyingCipher().getAlgorithmName();
                if (algorithmName.indexOf(47) >= 0) {
                    algorithmName = algorithmName.substring(0, algorithmName.indexOf(47));
                }
                try {
                    AlgorithmParameters algorithmParametersCreateParametersInstance3 = createParametersInstance(algorithmName);
                    this.engineParams = algorithmParametersCreateParametersInstance3;
                    algorithmParametersCreateParametersInstance3.init(new IvParameterSpec(this.ivParam.getIV()));
                } catch (Exception e3) {
                    throw new RuntimeException(e3.toString());
                }
            }
        }
        return this.engineParams;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher] */
    /* JADX WARN: Type inference failed for: r0v21, types: [org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher$GenericBlockCipher] */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARN: Type inference failed for: r7v24, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r7v25, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r7v26, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29, types: [org.spongycastle.crypto.params.AEADParameters] */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v50 */
    /* JADX WARN: Type inference failed for: r7v51 */
    /* JADX WARN: Type inference failed for: r7v52 */
    /* JADX WARN: Type inference failed for: r7v53 */
    /* JADX WARN: Type inference failed for: r7v54 */
    /* JADX WARN: Type inference failed for: r7v55 */
    /* JADX WARN: Type inference failed for: r7v56 */
    /* JADX WARN: Type inference failed for: r7v57 */
    /* JADX WARN: Type inference failed for: r7v58 */
    /* JADX WARN: Type inference failed for: r7v59 */
    /* JADX WARN: Type inference failed for: r7v60 */
    /* JADX WARN: Type inference failed for: r7v61 */
    /* JADX WARN: Type inference failed for: r7v62 */
    /* JADX WARN: Type inference failed for: r7v9, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters cipherParametersMakePBEParameters;
        ?? parametersWithIV;
        CipherParameters rC5Parameters;
        ParametersWithIV parametersWithIV2;
        CipherParameters cipherParametersMakePBEParameters2;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.engineParams = null;
        this.aeadParams = null;
        if (!(key instanceof SecretKey)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Key for algorithm ");
            sb.append(key != null ? key.getAlgorithm() : null);
            sb.append(" not suitable for symmetric enryption.");
            throw new InvalidKeyException(sb.toString());
        }
        if (algorithmParameterSpec == null && this.baseEngine.getAlgorithmName().startsWith("RC5-64")) {
            throw new InvalidAlgorithmParameterException("RC5 requires an RC5ParametersSpec to be passed in.");
        }
        int i3 = this.scheme;
        if (i3 == 2 || (key instanceof PKCS12Key)) {
            try {
                SecretKey secretKey = (SecretKey) key;
                if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
                }
                boolean z2 = secretKey instanceof PBEKey;
                if (z2 && this.pbeSpec == null) {
                    PBEKey pBEKey = (PBEKey) secretKey;
                    if (pBEKey.getSalt() == null) {
                        throw new InvalidAlgorithmParameterException("PBEKey requires parameters to specify salt");
                    }
                    this.pbeSpec = new PBEParameterSpec(pBEKey.getSalt(), pBEKey.getIterationCount());
                }
                if (this.pbeSpec == null && !z2) {
                    throw new InvalidKeyException("Algorithm requires a PBE key");
                }
                if (key instanceof BCPBEKey) {
                    CipherParameters param = ((BCPBEKey) key).getParam();
                    if (!(param instanceof ParametersWithIV)) {
                        if (param != null) {
                            throw new InvalidKeyException("Algorithm requires a PBE key suitable for PKCS12");
                        }
                        param = PBE.Util.makePBEParameters(secretKey.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
                    }
                    cipherParametersMakePBEParameters = param;
                } else {
                    cipherParametersMakePBEParameters = PBE.Util.makePBEParameters(secretKey.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
                }
                boolean z3 = cipherParametersMakePBEParameters instanceof ParametersWithIV;
                parametersWithIV = cipherParametersMakePBEParameters;
                if (z3) {
                    this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters;
                    parametersWithIV = cipherParametersMakePBEParameters;
                }
            } catch (Exception unused) {
                throw new InvalidKeyException("PKCS12 requires a SecretKey/PBEKey");
            }
        } else if (key instanceof PBKDF1Key) {
            PBKDF1Key pBKDF1Key = (PBKDF1Key) key;
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
            }
            if ((pBKDF1Key instanceof PBKDF1KeyWithParameters) && this.pbeSpec == null) {
                PBKDF1KeyWithParameters pBKDF1KeyWithParameters = (PBKDF1KeyWithParameters) pBKDF1Key;
                this.pbeSpec = new PBEParameterSpec(pBKDF1KeyWithParameters.getSalt(), pBKDF1KeyWithParameters.getIterationCount());
            }
            CipherParameters cipherParametersMakePBEParameters3 = PBE.Util.makePBEParameters(pBKDF1Key.getEncoded(), 0, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
            boolean z4 = cipherParametersMakePBEParameters3 instanceof ParametersWithIV;
            parametersWithIV = cipherParametersMakePBEParameters3;
            if (z4) {
                this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters3;
                parametersWithIV = cipherParametersMakePBEParameters3;
            }
        } else if (key instanceof BCPBEKey) {
            BCPBEKey bCPBEKey = (BCPBEKey) key;
            if (bCPBEKey.getOID() != null) {
                this.pbeAlgorithm = bCPBEKey.getOID().getId();
            } else {
                this.pbeAlgorithm = bCPBEKey.getAlgorithm();
            }
            if (bCPBEKey.getParam() != null) {
                cipherParametersMakePBEParameters2 = adjustParameters(algorithmParameterSpec, bCPBEKey.getParam());
            } else {
                if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                    throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                }
                this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
                cipherParametersMakePBEParameters2 = PBE.Util.makePBEParameters(bCPBEKey, algorithmParameterSpec, this.cipher.getUnderlyingCipher().getAlgorithmName());
            }
            boolean z5 = cipherParametersMakePBEParameters2 instanceof ParametersWithIV;
            parametersWithIV = cipherParametersMakePBEParameters2;
            if (z5) {
                this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters2;
                parametersWithIV = cipherParametersMakePBEParameters2;
            }
        } else if (key instanceof PBEKey) {
            PBEKey pBEKey2 = (PBEKey) key;
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            this.pbeSpec = pBEParameterSpec;
            if ((pBEKey2 instanceof PKCS12KeyWithParameters) && pBEParameterSpec == null) {
                this.pbeSpec = new PBEParameterSpec(pBEKey2.getSalt(), pBEKey2.getIterationCount());
            }
            CipherParameters cipherParametersMakePBEParameters4 = PBE.Util.makePBEParameters(pBEKey2.getEncoded(), this.scheme, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
            boolean z6 = cipherParametersMakePBEParameters4 instanceof ParametersWithIV;
            parametersWithIV = cipherParametersMakePBEParameters4;
            if (z6) {
                this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters4;
                parametersWithIV = cipherParametersMakePBEParameters4;
            }
        } else if (key instanceof RepeatedSecretKeySpec) {
            parametersWithIV = 0;
        } else {
            if (i3 == 0 || i3 == 4 || i3 == 1 || i3 == 5) {
                throw new InvalidKeyException("Algorithm requires a PBE key");
            }
            parametersWithIV = new KeyParameter(key.getEncoded());
        }
        if (algorithmParameterSpec instanceof AEADParameterSpec) {
            if (!isAEADModeName(this.modeName) && !(this.cipher instanceof AEADGenericBlockCipher)) {
                throw new InvalidAlgorithmParameterException("AEADParameterSpec can only be used with AEAD modes.");
            }
            AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
            parametersWithIV = new AEADParameters(parametersWithIV instanceof ParametersWithIV ? (KeyParameter) ((ParametersWithIV) parametersWithIV).getParameters() : (KeyParameter) parametersWithIV, aEADParameterSpec.getMacSizeInBits(), aEADParameterSpec.getNonce(), aEADParameterSpec.getAssociatedData());
            this.aeadParams = parametersWithIV;
        } else if (algorithmParameterSpec instanceof IvParameterSpec) {
            if (this.ivLength != 0) {
                IvParameterSpec ivParameterSpec = (IvParameterSpec) algorithmParameterSpec;
                if (ivParameterSpec.getIV().length != this.ivLength && !(this.cipher instanceof AEADGenericBlockCipher) && this.fixedIv) {
                    throw new InvalidAlgorithmParameterException("IV must be " + this.ivLength + " bytes long.");
                }
                parametersWithIV = parametersWithIV instanceof ParametersWithIV ? new ParametersWithIV(((ParametersWithIV) parametersWithIV).getParameters(), ivParameterSpec.getIV()) : new ParametersWithIV(parametersWithIV, ivParameterSpec.getIV());
                this.ivParam = parametersWithIV;
            } else {
                String str = this.modeName;
                if (str != null && str.equals("ECB")) {
                    throw new InvalidAlgorithmParameterException("ECB mode does not use an IV");
                }
            }
        } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
            GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec) algorithmParameterSpec;
            rC5Parameters = new ParametersWithSBox(new KeyParameter(key.getEncoded()), gOST28147ParameterSpec.getSbox());
            if (gOST28147ParameterSpec.getIV() != null && this.ivLength != 0) {
                parametersWithIV2 = new ParametersWithIV(rC5Parameters, gOST28147ParameterSpec.getIV());
                this.ivParam = parametersWithIV2;
                parametersWithIV = parametersWithIV2;
            }
            parametersWithIV = rC5Parameters;
        } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
            RC2ParameterSpec rC2ParameterSpec = (RC2ParameterSpec) algorithmParameterSpec;
            rC5Parameters = new RC2Parameters(key.getEncoded(), rC2ParameterSpec.getEffectiveKeyBits());
            if (rC2ParameterSpec.getIV() != null && this.ivLength != 0) {
                parametersWithIV2 = new ParametersWithIV(rC5Parameters, rC2ParameterSpec.getIV());
                this.ivParam = parametersWithIV2;
                parametersWithIV = parametersWithIV2;
            }
            parametersWithIV = rC5Parameters;
        } else if (algorithmParameterSpec instanceof RC5ParameterSpec) {
            RC5ParameterSpec rC5ParameterSpec = (RC5ParameterSpec) algorithmParameterSpec;
            rC5Parameters = new RC5Parameters(key.getEncoded(), rC5ParameterSpec.getRounds());
            if (!this.baseEngine.getAlgorithmName().startsWith("RC5")) {
                throw new InvalidAlgorithmParameterException("RC5 parameters passed to a cipher that is not RC5.");
            }
            if (this.baseEngine.getAlgorithmName().equals("RC5-32")) {
                if (rC5ParameterSpec.getWordSize() != 32) {
                    throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 32 not " + rC5ParameterSpec.getWordSize() + ".");
                }
            } else if (this.baseEngine.getAlgorithmName().equals("RC5-64") && rC5ParameterSpec.getWordSize() != 64) {
                throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 64 not " + rC5ParameterSpec.getWordSize() + ".");
            }
            if (rC5ParameterSpec.getIV() != null && this.ivLength != 0) {
                parametersWithIV2 = new ParametersWithIV(rC5Parameters, rC5ParameterSpec.getIV());
                this.ivParam = parametersWithIV2;
                parametersWithIV = parametersWithIV2;
            }
            parametersWithIV = rC5Parameters;
        } else {
            Class cls = gcmSpecClass;
            if (cls == null || !cls.isInstance(algorithmParameterSpec)) {
                if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                    throw new InvalidAlgorithmParameterException("unknown parameter type.");
                }
            } else {
                if (!isAEADModeName(this.modeName) && !(this.cipher instanceof AEADGenericBlockCipher)) {
                    throw new InvalidAlgorithmParameterException("GCMParameterSpec can only be used with AEAD modes.");
                }
                try {
                    AEADParameters aEADParameters = new AEADParameters(parametersWithIV instanceof ParametersWithIV ? (KeyParameter) ((ParametersWithIV) parametersWithIV).getParameters() : (KeyParameter) parametersWithIV, ((Integer) cls.getDeclaredMethod("getTLen", null).invoke(algorithmParameterSpec, null)).intValue(), (byte[]) cls.getDeclaredMethod("getIV", null).invoke(algorithmParameterSpec, null));
                    this.aeadParams = aEADParameters;
                    parametersWithIV = aEADParameters;
                } catch (Exception unused2) {
                    throw new InvalidAlgorithmParameterException("Cannot process GCMParameterSpec.");
                }
            }
        }
        ?? parametersWithRandom = parametersWithIV;
        if (this.ivLength != 0) {
            boolean z7 = parametersWithIV instanceof ParametersWithIV;
            parametersWithRandom = parametersWithIV;
            if (!z7) {
                boolean z8 = parametersWithIV instanceof AEADParameters;
                parametersWithRandom = parametersWithIV;
                if (!z8) {
                    SecureRandom secureRandom2 = secureRandom == null ? new SecureRandom() : secureRandom;
                    if (i2 == 1 || i2 == 3) {
                        byte[] bArr = new byte[this.ivLength];
                        secureRandom2.nextBytes(bArr);
                        ParametersWithIV parametersWithIV3 = new ParametersWithIV(parametersWithIV, bArr);
                        this.ivParam = parametersWithIV3;
                        parametersWithRandom = parametersWithIV3;
                    } else {
                        parametersWithRandom = parametersWithIV;
                        if (this.cipher.getUnderlyingCipher().getAlgorithmName().indexOf("PGPCFB") < 0) {
                            throw new InvalidAlgorithmParameterException("no IV set when one expected");
                        }
                    }
                }
            }
        }
        if (secureRandom != null && this.padded) {
            parametersWithRandom = new ParametersWithRandom(parametersWithRandom, secureRandom);
        }
        try {
            if (i2 == 1) {
                this.cipher.init(true, parametersWithRandom);
            } else {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            throw new InvalidParameterException("unknown opmode " + i2 + " passed");
                        }
                    }
                    this.cipher.init(true, parametersWithRandom);
                }
                this.cipher.init(false, parametersWithRandom);
            }
            GenericBlockCipher genericBlockCipher = this.cipher;
            if ((genericBlockCipher instanceof AEADGenericBlockCipher) && this.aeadParams == null) {
                this.aeadParams = new AEADParameters((KeyParameter) this.ivParam.getParameters(), ((AEADGenericBlockCipher) genericBlockCipher).cipher.getMac().length * 8, this.ivParam.getIV());
            }
        } catch (Exception e2) {
            throw new InvalidKeyOrParametersException(e2.getMessage(), e2);
        }
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        String upperCase = Strings.toUpperCase(str);
        this.modeName = upperCase;
        if (upperCase.equals("ECB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(this.baseEngine);
            return;
        }
        if (this.modeName.equals("CBC")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new CBCBlockCipher(this.baseEngine));
            return;
        }
        if (this.modeName.startsWith("OFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                this.cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                return;
            } else {
                BlockCipher blockCipher = this.baseEngine;
                this.cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8));
                return;
            }
        }
        if (this.modeName.startsWith("CFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                this.cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                return;
            } else {
                BlockCipher blockCipher2 = this.baseEngine;
                this.cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(blockCipher2, blockCipher2.getBlockSize() * 8));
                return;
            }
        }
        if (this.modeName.startsWith("PGP")) {
            boolean zEqualsIgnoreCase = this.modeName.equalsIgnoreCase("PGPCFBwithIV");
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new PGPCFBBlockCipher(this.baseEngine, zEqualsIgnoreCase));
            return;
        }
        if (this.modeName.equalsIgnoreCase("OpenPGPCFB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(new OpenPGPCFBBlockCipher(this.baseEngine));
            return;
        }
        if (this.modeName.startsWith("SIC")) {
            int blockSize = this.baseEngine.getBlockSize();
            this.ivLength = blockSize;
            if (blockSize < 16) {
                throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
            }
            this.fixedIv = false;
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("CTR")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.fixedIv = false;
            BlockCipher blockCipher3 = this.baseEngine;
            if (blockCipher3 instanceof DSTU7624Engine) {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new KCTRBlockCipher(blockCipher3)));
                return;
            } else {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(blockCipher3)));
                return;
            }
        }
        if (this.modeName.startsWith("GOFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GOFBBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("GCFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GCFBBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("CTS")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(new CBCBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("CCM")) {
            this.ivLength = 13;
            if (this.baseEngine instanceof DSTU7624Engine) {
                this.cipher = new AEADGenericBlockCipher(new KCCMBlockCipher(this.baseEngine));
                return;
            } else {
                this.cipher = new AEADGenericBlockCipher(new CCMBlockCipher(this.baseEngine));
                return;
            }
        }
        if (this.modeName.startsWith("OCB")) {
            if (this.engineProvider != null) {
                this.ivLength = 15;
                this.cipher = new AEADGenericBlockCipher(new OCBBlockCipher(this.baseEngine, this.engineProvider.get()));
                return;
            } else {
                throw new NoSuchAlgorithmException("can't support mode " + str);
            }
        }
        if (this.modeName.startsWith("EAX")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new AEADGenericBlockCipher(new EAXBlockCipher(this.baseEngine));
        } else {
            if (!this.modeName.startsWith("GCM")) {
                throw new NoSuchAlgorithmException("can't support mode " + str);
            }
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.baseEngine instanceof DSTU7624Engine) {
                this.cipher = new AEADGenericBlockCipher(new KGCMBlockCipher(this.baseEngine));
            } else {
                this.cipher = new AEADGenericBlockCipher(new GCMBlockCipher(this.baseEngine));
            }
        }
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NOPADDING")) {
            if (this.cipher.wrapOnNoPadding()) {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(this.cipher.getUnderlyingCipher()));
                return;
            }
            return;
        }
        if (upperCase.equals("WITHCTS")) {
            this.cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(this.cipher.getUnderlyingCipher()));
            return;
        }
        this.padded = true;
        if (isAEADModeName(this.modeName)) {
            throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
        }
        if (upperCase.equals("PKCS5PADDING") || upperCase.equals("PKCS7PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher());
            return;
        }
        if (upperCase.equals("ZEROBYTEPADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ZeroBytePadding());
            return;
        }
        if (upperCase.equals("ISO10126PADDING") || upperCase.equals("ISO10126-2PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO10126d2Padding());
            return;
        }
        if (upperCase.equals("X9.23PADDING") || upperCase.equals("X923PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new X923Padding());
            return;
        }
        if (upperCase.equals("ISO7816-4PADDING") || upperCase.equals("ISO9797-1PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO7816d4Padding());
            return;
        }
        if (upperCase.equals("TBCPADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new TBCPadding());
            return;
        }
        throw new NoSuchPaddingException("Padding " + str + " unknown.");
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i2, int i3) {
        int updateOutputSize = this.cipher.getUpdateOutputSize(i3);
        if (updateOutputSize <= 0) {
            this.cipher.processBytes(bArr, i2, i3, null, 0);
            return null;
        }
        byte[] bArr2 = new byte[updateOutputSize];
        int iProcessBytes = this.cipher.processBytes(bArr, i2, i3, bArr2, 0);
        if (iProcessBytes == 0) {
            return null;
        }
        if (iProcessBytes == updateOutputSize) {
            return bArr2;
        }
        byte[] bArr3 = new byte[iProcessBytes];
        System.arraycopy(bArr2, 0, bArr3, 0, iProcessBytes);
        return bArr3;
    }

    @Override // javax.crypto.CipherSpi
    public void engineUpdateAAD(byte[] bArr, int i2, int i3) {
        this.cipher.updateAAD(bArr, i2, i3);
    }

    public static class BufferedGenericBlockCipher implements GenericBlockCipher {
        private BufferedBlockCipher cipher;

        public BufferedGenericBlockCipher(BufferedBlockCipher bufferedBlockCipher) {
            this.cipher = bufferedBlockCipher;
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int doFinal(byte[] bArr, int i2) throws BadPaddingException {
            try {
                return this.cipher.doFinal(bArr, i2);
            } catch (InvalidCipherTextException e2) {
                throw new BadPaddingException(e2.getMessage());
            }
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getOutputSize(int i2) {
            return this.cipher.getOutputSize(i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getUpdateOutputSize(int i2) {
            return this.cipher.getUpdateOutputSize(i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void init(boolean z2, CipherParameters cipherParameters) {
            this.cipher.init(z2, cipherParameters);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processByte(byte b2, byte[] bArr, int i2) {
            return this.cipher.processByte(b2, bArr, i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
            return this.cipher.processBytes(bArr, i2, i3, bArr2, i4);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void updateAAD(byte[] bArr, int i2, int i3) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public boolean wrapOnNoPadding() {
            return !(this.cipher instanceof CTSBlockCipher);
        }

        public BufferedGenericBlockCipher(BlockCipher blockCipher) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher);
        }

        public BufferedGenericBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher, blockCipherPadding);
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineUpdateAAD(ByteBuffer byteBuffer) {
        engineUpdateAAD(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.limit() - byteBuffer.position());
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalBlockSizeException, ShortBufferException {
        int iProcessBytes;
        if (engineGetOutputSize(i3) + i4 <= bArr2.length) {
            if (i3 != 0) {
                try {
                    iProcessBytes = this.cipher.processBytes(bArr, i2, i3, bArr2, i4);
                } catch (OutputLengthException e2) {
                    throw new IllegalBlockSizeException(e2.getMessage());
                } catch (DataLengthException e3) {
                    throw new IllegalBlockSizeException(e3.getMessage());
                }
            } else {
                iProcessBytes = 0;
            }
            return iProcessBytes + this.cipher.doFinal(bArr2, i4 + iProcessBytes);
        }
        throw new ShortBufferException("output buffer too short for input.");
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws ShortBufferException {
        if (this.cipher.getUpdateOutputSize(i3) + i4 <= bArr2.length) {
            try {
                return this.cipher.processBytes(bArr, i2, i3, bArr2, i4);
            } catch (DataLengthException e2) {
                throw new IllegalStateException(e2.toString());
            }
        }
        throw new ShortBufferException("output buffer too short for input.");
    }

    public BaseBlockCipher(BlockCipher blockCipher, int i2, int i3, int i4, int i5) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.scheme = i2;
        this.digest = i3;
        this.keySizeInBits = i4;
        this.ivLength = i5;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    public BaseBlockCipher(BlockCipherProvider blockCipherProvider) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipherProvider.get();
        this.engineProvider = blockCipherProvider;
        this.cipher = new BufferedGenericBlockCipher(blockCipherProvider.get());
    }

    public BaseBlockCipher(AEADBlockCipher aEADBlockCipher) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        BlockCipher underlyingCipher = aEADBlockCipher.getUnderlyingCipher();
        this.baseEngine = underlyingCipher;
        this.ivLength = underlyingCipher.getBlockSize();
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    public BaseBlockCipher(AEADBlockCipher aEADBlockCipher, boolean z2, int i2) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = aEADBlockCipher.getUnderlyingCipher();
        this.fixedIv = z2;
        this.ivLength = i2;
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    public BaseBlockCipher(BlockCipher blockCipher, int i2) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
        this.ivLength = i2 / 8;
    }

    public BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, int i2) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = bufferedBlockCipher.getUnderlyingCipher();
        this.cipher = new BufferedGenericBlockCipher(bufferedBlockCipher);
        this.ivLength = i2 / 8;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec = null;
        if (algorithmParameters != null) {
            int i3 = 0;
            while (true) {
                Class[] clsArr = this.availableSpecs;
                if (i3 == clsArr.length) {
                    break;
                }
                Class cls = clsArr[i3];
                if (cls != null) {
                    try {
                        parameterSpec = algorithmParameters.getParameterSpec(cls);
                        break;
                    } catch (Exception unused) {
                        continue;
                        i3++;
                    }
                }
                i3++;
            }
            if (parameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        engineInit(i2, key, parameterSpec, secureRandom);
        this.engineParams = algorithmParameters;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i2, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new InvalidKeyException(e2.getMessage());
        }
    }
}
