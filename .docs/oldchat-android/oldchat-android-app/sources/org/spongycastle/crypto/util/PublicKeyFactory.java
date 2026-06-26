package org.spongycastle.crypto.util;

import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAPublicKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.asn1.x9.DHPublicKey;
import org.spongycastle.asn1.x9.DomainParameters;
import org.spongycastle.asn1.x9.ECNamedCurveTable;
import org.spongycastle.asn1.x9.ValidationParams;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ECPoint;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.ec.CustomNamedCurves;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.DHValidationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECNamedDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

/* JADX INFO: loaded from: classes.dex */
public class PublicKeyFactory {
    public static AsymmetricKeyParameter createKey(byte[] bArr) {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inputStream) {
        return createKey(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        X9ECParameters x9ECParameters;
        ECDomainParameters eCDomainParameters;
        AlgorithmIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm();
        if (!algorithm.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption) && !algorithm.getAlgorithm().equals(X509ObjectIdentifiers.id_ea_rsa)) {
            DSAParameters dSAParameters = null;
            if (algorithm.getAlgorithm().equals(X9ObjectIdentifiers.dhpublicnumber)) {
                BigInteger y2 = DHPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey()).getY();
                DomainParameters domainParameters = DomainParameters.getInstance(algorithm.getParameters());
                BigInteger p2 = domainParameters.getP();
                BigInteger g2 = domainParameters.getG();
                BigInteger q2 = domainParameters.getQ();
                BigInteger j2 = domainParameters.getJ() != null ? domainParameters.getJ() : null;
                ValidationParams validationParams = domainParameters.getValidationParams();
                return new DHPublicKeyParameters(y2, new DHParameters(p2, g2, q2, j2, validationParams != null ? new DHValidationParameters(validationParams.getSeed(), validationParams.getPgenCounter().intValue()) : null));
            }
            if (algorithm.getAlgorithm().equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
                DHParameter dHParameter = DHParameter.getInstance(algorithm.getParameters());
                ASN1Integer aSN1Integer = (ASN1Integer) subjectPublicKeyInfo.parsePublicKey();
                BigInteger l2 = dHParameter.getL();
                return new DHPublicKeyParameters(aSN1Integer.getValue(), new DHParameters(dHParameter.getP(), dHParameter.getG(), null, l2 != null ? l2.intValue() : 0));
            }
            if (algorithm.getAlgorithm().equals(OIWObjectIdentifiers.elGamalAlgorithm)) {
                ElGamalParameter elGamalParameter = ElGamalParameter.getInstance(algorithm.getParameters());
                return new ElGamalPublicKeyParameters(((ASN1Integer) subjectPublicKeyInfo.parsePublicKey()).getValue(), new ElGamalParameters(elGamalParameter.getP(), elGamalParameter.getG()));
            }
            if (!algorithm.getAlgorithm().equals(X9ObjectIdentifiers.id_dsa) && !algorithm.getAlgorithm().equals(OIWObjectIdentifiers.dsaWithSHA1)) {
                if (algorithm.getAlgorithm().equals(X9ObjectIdentifiers.id_ecPublicKey)) {
                    X962Parameters x962Parameters = X962Parameters.getInstance(algorithm.getParameters());
                    if (x962Parameters.isNamedCurve()) {
                        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) x962Parameters.getParameters();
                        x9ECParameters = CustomNamedCurves.getByOID(aSN1ObjectIdentifier);
                        if (x9ECParameters == null) {
                            x9ECParameters = ECNamedCurveTable.getByOID(aSN1ObjectIdentifier);
                        }
                        eCDomainParameters = new ECNamedDomainParameters(aSN1ObjectIdentifier, x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
                    } else {
                        x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
                        eCDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
                    }
                    return new ECPublicKeyParameters(new X9ECPoint(x9ECParameters.getCurve(), new DEROctetString(subjectPublicKeyInfo.getPublicKeyData().getBytes())).getPoint(), eCDomainParameters);
                }
                throw new RuntimeException("algorithm identifier in key not recognised");
            }
            ASN1Integer aSN1Integer2 = (ASN1Integer) subjectPublicKeyInfo.parsePublicKey();
            ASN1Encodable parameters = algorithm.getParameters();
            if (parameters != null) {
                DSAParameter dSAParameter = DSAParameter.getInstance(parameters.toASN1Primitive());
                dSAParameters = new DSAParameters(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
            }
            return new DSAPublicKeyParameters(aSN1Integer2.getValue(), dSAParameters);
        }
        RSAPublicKey rSAPublicKey = RSAPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
        return new RSAKeyParameters(false, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
    }
}
