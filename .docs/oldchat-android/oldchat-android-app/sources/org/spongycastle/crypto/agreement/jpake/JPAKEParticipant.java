package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class JPAKEParticipant {
    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_KEY_CALCULATED = 50;
    public static final int STATE_ROUND_1_CREATED = 10;
    public static final int STATE_ROUND_1_VALIDATED = 20;
    public static final int STATE_ROUND_2_CREATED = 30;
    public static final int STATE_ROUND_2_VALIDATED = 40;
    public static final int STATE_ROUND_3_CREATED = 60;
    public static final int STATE_ROUND_3_VALIDATED = 70;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BigInteger f7238b;
    private final Digest digest;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final BigInteger f7239g;
    private BigInteger gx1;
    private BigInteger gx2;
    private BigInteger gx3;
    private BigInteger gx4;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final BigInteger f7240p;
    private final String participantId;
    private String partnerParticipantId;
    private char[] password;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final BigInteger f7241q;
    private final SecureRandom random;
    private int state;
    private BigInteger x1;
    private BigInteger x2;

    public JPAKEParticipant(String str, char[] cArr) {
        this(str, cArr, JPAKEPrimeOrderGroups.NIST_3072);
    }

    public BigInteger calculateKeyingMaterial() {
        int i2 = this.state;
        if (i2 >= 50) {
            throw new IllegalStateException("Key already calculated for " + this.participantId);
        }
        if (i2 < 40) {
            throw new IllegalStateException("Round2 payload must be validated prior to creating key for " + this.participantId);
        }
        BigInteger bigIntegerCalculateS = JPAKEUtil.calculateS(this.password);
        Arrays.fill(this.password, (char) 0);
        this.password = null;
        BigInteger bigIntegerCalculateKeyingMaterial = JPAKEUtil.calculateKeyingMaterial(this.f7240p, this.f7241q, this.gx4, this.x2, bigIntegerCalculateS, this.f7238b);
        this.x1 = null;
        this.x2 = null;
        this.f7238b = null;
        this.state = 50;
        return bigIntegerCalculateKeyingMaterial;
    }

    public JPAKERound1Payload createRound1PayloadToSend() {
        if (this.state >= 10) {
            throw new IllegalStateException("Round1 payload already created for " + this.participantId);
        }
        this.x1 = JPAKEUtil.generateX1(this.f7241q, this.random);
        this.x2 = JPAKEUtil.generateX2(this.f7241q, this.random);
        this.gx1 = JPAKEUtil.calculateGx(this.f7240p, this.f7239g, this.x1);
        this.gx2 = JPAKEUtil.calculateGx(this.f7240p, this.f7239g, this.x2);
        BigInteger[] bigIntegerArrCalculateZeroKnowledgeProof = JPAKEUtil.calculateZeroKnowledgeProof(this.f7240p, this.f7241q, this.f7239g, this.gx1, this.x1, this.participantId, this.digest, this.random);
        BigInteger[] bigIntegerArrCalculateZeroKnowledgeProof2 = JPAKEUtil.calculateZeroKnowledgeProof(this.f7240p, this.f7241q, this.f7239g, this.gx2, this.x2, this.participantId, this.digest, this.random);
        this.state = 10;
        return new JPAKERound1Payload(this.participantId, this.gx1, this.gx2, bigIntegerArrCalculateZeroKnowledgeProof, bigIntegerArrCalculateZeroKnowledgeProof2);
    }

    public JPAKERound2Payload createRound2PayloadToSend() {
        int i2 = this.state;
        if (i2 >= 30) {
            throw new IllegalStateException("Round2 payload already created for " + this.participantId);
        }
        if (i2 < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to creating Round2 payload for " + this.participantId);
        }
        BigInteger bigIntegerCalculateGA = JPAKEUtil.calculateGA(this.f7240p, this.gx1, this.gx3, this.gx4);
        BigInteger bigIntegerCalculateX2s = JPAKEUtil.calculateX2s(this.f7241q, this.x2, JPAKEUtil.calculateS(this.password));
        BigInteger bigIntegerCalculateA = JPAKEUtil.calculateA(this.f7240p, this.f7241q, bigIntegerCalculateGA, bigIntegerCalculateX2s);
        BigInteger[] bigIntegerArrCalculateZeroKnowledgeProof = JPAKEUtil.calculateZeroKnowledgeProof(this.f7240p, this.f7241q, bigIntegerCalculateGA, bigIntegerCalculateA, bigIntegerCalculateX2s, this.participantId, this.digest, this.random);
        this.state = 30;
        return new JPAKERound2Payload(this.participantId, bigIntegerCalculateA, bigIntegerArrCalculateZeroKnowledgeProof);
    }

    public JPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger) {
        int i2 = this.state;
        if (i2 >= 60) {
            throw new IllegalStateException("Round3 payload already created for " + this.participantId);
        }
        if (i2 >= 50) {
            BigInteger bigIntegerCalculateMacTag = JPAKEUtil.calculateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, bigInteger, this.digest);
            this.state = 60;
            return new JPAKERound3Payload(this.participantId, bigIntegerCalculateMacTag);
        }
        throw new IllegalStateException("Keying material must be calculated prior to creating Round3 payload for " + this.participantId);
    }

    public int getState() {
        return this.state;
    }

    public void validateRound1PayloadReceived(JPAKERound1Payload jPAKERound1Payload) throws CryptoException {
        if (this.state >= 20) {
            throw new IllegalStateException("Validation already attempted for round1 payload for" + this.participantId);
        }
        this.partnerParticipantId = jPAKERound1Payload.getParticipantId();
        this.gx3 = jPAKERound1Payload.getGx1();
        this.gx4 = jPAKERound1Payload.getGx2();
        BigInteger[] knowledgeProofForX1 = jPAKERound1Payload.getKnowledgeProofForX1();
        BigInteger[] knowledgeProofForX2 = jPAKERound1Payload.getKnowledgeProofForX2();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound1Payload.getParticipantId());
        JPAKEUtil.validateGx4(this.gx4);
        JPAKEUtil.validateZeroKnowledgeProof(this.f7240p, this.f7241q, this.f7239g, this.gx3, knowledgeProofForX1, jPAKERound1Payload.getParticipantId(), this.digest);
        JPAKEUtil.validateZeroKnowledgeProof(this.f7240p, this.f7241q, this.f7239g, this.gx4, knowledgeProofForX2, jPAKERound1Payload.getParticipantId(), this.digest);
        this.state = 20;
    }

    public void validateRound2PayloadReceived(JPAKERound2Payload jPAKERound2Payload) throws CryptoException {
        int i2 = this.state;
        if (i2 >= 40) {
            throw new IllegalStateException("Validation already attempted for round2 payload for" + this.participantId);
        }
        if (i2 < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to validating Round2 payload for " + this.participantId);
        }
        BigInteger bigIntegerCalculateGA = JPAKEUtil.calculateGA(this.f7240p, this.gx3, this.gx1, this.gx2);
        this.f7238b = jPAKERound2Payload.getA();
        BigInteger[] knowledgeProofForX2s = jPAKERound2Payload.getKnowledgeProofForX2s();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound2Payload.getParticipantId());
        JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, jPAKERound2Payload.getParticipantId());
        JPAKEUtil.validateGa(bigIntegerCalculateGA);
        JPAKEUtil.validateZeroKnowledgeProof(this.f7240p, this.f7241q, bigIntegerCalculateGA, this.f7238b, knowledgeProofForX2s, jPAKERound2Payload.getParticipantId(), this.digest);
        this.state = 40;
    }

    public void validateRound3PayloadReceived(JPAKERound3Payload jPAKERound3Payload, BigInteger bigInteger) throws CryptoException {
        int i2 = this.state;
        if (i2 >= 70) {
            throw new IllegalStateException("Validation already attempted for round3 payload for" + this.participantId);
        }
        if (i2 < 50) {
            throw new IllegalStateException("Keying material must be calculated validated prior to validating Round3 payload for " + this.participantId);
        }
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound3Payload.getParticipantId());
        JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, jPAKERound3Payload.getParticipantId());
        JPAKEUtil.validateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, bigInteger, this.digest, jPAKERound3Payload.getMacTag());
        this.gx1 = null;
        this.gx2 = null;
        this.gx3 = null;
        this.gx4 = null;
        this.state = 70;
    }

    public JPAKEParticipant(String str, char[] cArr, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup) {
        this(str, cArr, jPAKEPrimeOrderGroup, new SHA256Digest(), new SecureRandom());
    }

    public JPAKEParticipant(String str, char[] cArr, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup, Digest digest, SecureRandom secureRandom) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(cArr, "password");
        JPAKEUtil.validateNotNull(jPAKEPrimeOrderGroup, "p");
        JPAKEUtil.validateNotNull(digest, "digest");
        JPAKEUtil.validateNotNull(secureRandom, "random");
        if (cArr.length != 0) {
            this.participantId = str;
            this.password = Arrays.copyOf(cArr, cArr.length);
            this.f7240p = jPAKEPrimeOrderGroup.getP();
            this.f7241q = jPAKEPrimeOrderGroup.getQ();
            this.f7239g = jPAKEPrimeOrderGroup.getG();
            this.digest = digest;
            this.random = secureRandom;
            this.state = 0;
            return;
        }
        throw new IllegalArgumentException("Password must not be empty.");
    }
}
