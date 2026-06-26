package org.spongycastle.pqc.jcajce.provider.mceliece;

import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

/* JADX INFO: loaded from: classes.dex */
public final class McElieceCCA2Primitives {
    private McElieceCCA2Primitives() {
    }

    public static GF2Vector[] decryptionPrimitive(BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey, GF2Vector gF2Vector) {
        int k2 = bCMcElieceCCA2PrivateKey.getK();
        Permutation p2 = bCMcElieceCCA2PrivateKey.getP();
        GF2mField field = bCMcElieceCCA2PrivateKey.getField();
        PolynomialGF2mSmallM goppaPoly = bCMcElieceCCA2PrivateKey.getGoppaPoly();
        GF2Matrix h2 = bCMcElieceCCA2PrivateKey.getH();
        PolynomialGF2mSmallM[] qInv = bCMcElieceCCA2PrivateKey.getQInv();
        GF2Vector gF2Vector2 = (GF2Vector) gF2Vector.multiply(p2.computeInverse());
        GF2Vector gF2VectorSyndromeDecode = GoppaCode.syndromeDecode((GF2Vector) h2.rightMultiply(gF2Vector2), field, goppaPoly, qInv);
        GF2Vector gF2Vector3 = (GF2Vector) ((GF2Vector) gF2Vector2.add(gF2VectorSyndromeDecode)).multiply(p2);
        return new GF2Vector[]{gF2Vector3.extractRightVector(k2), (GF2Vector) gF2VectorSyndromeDecode.multiply(p2)};
    }

    public static GF2Vector encryptionPrimitive(BCMcElieceCCA2PublicKey bCMcElieceCCA2PublicKey, GF2Vector gF2Vector, GF2Vector gF2Vector2) {
        return (GF2Vector) bCMcElieceCCA2PublicKey.getG().leftMultiplyLeftCompactForm(gF2Vector).add(gF2Vector2);
    }

    public static GF2Vector encryptionPrimitive(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters, GF2Vector gF2Vector, GF2Vector gF2Vector2) {
        return (GF2Vector) mcElieceCCA2PublicKeyParameters.getG().leftMultiplyLeftCompactForm(gF2Vector).add(gF2Vector2);
    }

    public static GF2Vector[] decryptionPrimitive(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters, GF2Vector gF2Vector) {
        int k2 = mcElieceCCA2PrivateKeyParameters.getK();
        Permutation p2 = mcElieceCCA2PrivateKeyParameters.getP();
        GF2mField field = mcElieceCCA2PrivateKeyParameters.getField();
        PolynomialGF2mSmallM goppaPoly = mcElieceCCA2PrivateKeyParameters.getGoppaPoly();
        GF2Matrix h2 = mcElieceCCA2PrivateKeyParameters.getH();
        PolynomialGF2mSmallM[] qInv = mcElieceCCA2PrivateKeyParameters.getQInv();
        GF2Vector gF2Vector2 = (GF2Vector) gF2Vector.multiply(p2.computeInverse());
        GF2Vector gF2VectorSyndromeDecode = GoppaCode.syndromeDecode((GF2Vector) h2.rightMultiply(gF2Vector2), field, goppaPoly, qInv);
        GF2Vector gF2Vector3 = (GF2Vector) ((GF2Vector) gF2Vector2.add(gF2VectorSyndromeDecode)).multiply(p2);
        return new GF2Vector[]{gF2Vector3.extractRightVector(k2), (GF2Vector) gF2VectorSyndromeDecode.multiply(p2)};
    }
}
