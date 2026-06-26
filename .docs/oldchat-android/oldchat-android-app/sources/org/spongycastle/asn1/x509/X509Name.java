package org.spongycastle.asn1.x509;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.DERUniversalString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Encodable;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes.dex */
public class X509Name extends ASN1Object {
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final ASN1ObjectIdentifier f7217C;
    public static final ASN1ObjectIdentifier CN;
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP;
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE;
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH;
    public static final ASN1ObjectIdentifier DC;
    public static final ASN1ObjectIdentifier DMD_NAME;
    public static final ASN1ObjectIdentifier DN_QUALIFIER;
    public static final Hashtable DefaultLookUp;
    public static boolean DefaultReverse;
    public static final Hashtable DefaultSymbols;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final ASN1ObjectIdentifier f7218E;
    public static final ASN1ObjectIdentifier EmailAddress;
    private static final Boolean FALSE;
    public static final ASN1ObjectIdentifier GENDER;
    public static final ASN1ObjectIdentifier GENERATION;
    public static final ASN1ObjectIdentifier GIVENNAME;
    public static final ASN1ObjectIdentifier INITIALS;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final ASN1ObjectIdentifier f7219L;
    public static final ASN1ObjectIdentifier NAME;
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final ASN1ObjectIdentifier f7220O;
    public static final Hashtable OIDLookUp;
    public static final ASN1ObjectIdentifier OU;
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH;
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS;
    public static final ASN1ObjectIdentifier POSTAL_CODE;
    public static final ASN1ObjectIdentifier PSEUDONYM;
    public static final Hashtable RFC1779Symbols;
    public static final Hashtable RFC2253Symbols;
    public static final ASN1ObjectIdentifier SERIALNUMBER;
    public static final ASN1ObjectIdentifier SN;
    public static final ASN1ObjectIdentifier ST;
    public static final ASN1ObjectIdentifier STREET;
    public static final ASN1ObjectIdentifier SURNAME;
    public static final Hashtable SymbolLookUp;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public static final ASN1ObjectIdentifier f7221T;
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER;
    private static final Boolean TRUE;
    public static final ASN1ObjectIdentifier UID;
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER;
    public static final ASN1ObjectIdentifier UnstructuredAddress;
    public static final ASN1ObjectIdentifier UnstructuredName;
    private Vector added;
    private X509NameEntryConverter converter;
    private int hashCodeValue;
    private boolean isHashCodeCalculated;
    private Vector ordering;
    private ASN1Sequence seq;
    private Vector values;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("2.5.4.6");
        f7217C = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier("2.5.4.10");
        f7220O = aSN1ObjectIdentifier2;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = new ASN1ObjectIdentifier("2.5.4.11");
        OU = aSN1ObjectIdentifier3;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = new ASN1ObjectIdentifier("2.5.4.12");
        f7221T = aSN1ObjectIdentifier4;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = new ASN1ObjectIdentifier("2.5.4.3");
        CN = aSN1ObjectIdentifier5;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = new ASN1ObjectIdentifier("2.5.4.5");
        SN = aSN1ObjectIdentifier6;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = new ASN1ObjectIdentifier("2.5.4.9");
        STREET = aSN1ObjectIdentifier7;
        SERIALNUMBER = aSN1ObjectIdentifier6;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = new ASN1ObjectIdentifier("2.5.4.7");
        f7219L = aSN1ObjectIdentifier8;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = new ASN1ObjectIdentifier("2.5.4.8");
        ST = aSN1ObjectIdentifier9;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = new ASN1ObjectIdentifier("2.5.4.4");
        SURNAME = aSN1ObjectIdentifier10;
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = new ASN1ObjectIdentifier("2.5.4.42");
        GIVENNAME = aSN1ObjectIdentifier11;
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = new ASN1ObjectIdentifier("2.5.4.43");
        INITIALS = aSN1ObjectIdentifier12;
        ASN1ObjectIdentifier aSN1ObjectIdentifier13 = new ASN1ObjectIdentifier("2.5.4.44");
        GENERATION = aSN1ObjectIdentifier13;
        ASN1ObjectIdentifier aSN1ObjectIdentifier14 = new ASN1ObjectIdentifier("2.5.4.45");
        UNIQUE_IDENTIFIER = aSN1ObjectIdentifier14;
        ASN1ObjectIdentifier aSN1ObjectIdentifier15 = new ASN1ObjectIdentifier("2.5.4.15");
        BUSINESS_CATEGORY = aSN1ObjectIdentifier15;
        ASN1ObjectIdentifier aSN1ObjectIdentifier16 = new ASN1ObjectIdentifier("2.5.4.17");
        POSTAL_CODE = aSN1ObjectIdentifier16;
        ASN1ObjectIdentifier aSN1ObjectIdentifier17 = new ASN1ObjectIdentifier("2.5.4.46");
        DN_QUALIFIER = aSN1ObjectIdentifier17;
        ASN1ObjectIdentifier aSN1ObjectIdentifier18 = new ASN1ObjectIdentifier("2.5.4.65");
        PSEUDONYM = aSN1ObjectIdentifier18;
        ASN1ObjectIdentifier aSN1ObjectIdentifier19 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1");
        DATE_OF_BIRTH = aSN1ObjectIdentifier19;
        ASN1ObjectIdentifier aSN1ObjectIdentifier20 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2");
        PLACE_OF_BIRTH = aSN1ObjectIdentifier20;
        ASN1ObjectIdentifier aSN1ObjectIdentifier21 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3");
        GENDER = aSN1ObjectIdentifier21;
        ASN1ObjectIdentifier aSN1ObjectIdentifier22 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4");
        COUNTRY_OF_CITIZENSHIP = aSN1ObjectIdentifier22;
        ASN1ObjectIdentifier aSN1ObjectIdentifier23 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5");
        COUNTRY_OF_RESIDENCE = aSN1ObjectIdentifier23;
        ASN1ObjectIdentifier aSN1ObjectIdentifier24 = new ASN1ObjectIdentifier("1.3.36.8.3.14");
        NAME_AT_BIRTH = aSN1ObjectIdentifier24;
        ASN1ObjectIdentifier aSN1ObjectIdentifier25 = new ASN1ObjectIdentifier("2.5.4.16");
        POSTAL_ADDRESS = aSN1ObjectIdentifier25;
        DMD_NAME = new ASN1ObjectIdentifier("2.5.4.54");
        ASN1ObjectIdentifier aSN1ObjectIdentifier26 = X509ObjectIdentifiers.id_at_telephoneNumber;
        TELEPHONE_NUMBER = aSN1ObjectIdentifier26;
        ASN1ObjectIdentifier aSN1ObjectIdentifier27 = X509ObjectIdentifiers.id_at_name;
        NAME = aSN1ObjectIdentifier27;
        ASN1ObjectIdentifier aSN1ObjectIdentifier28 = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
        EmailAddress = aSN1ObjectIdentifier28;
        ASN1ObjectIdentifier aSN1ObjectIdentifier29 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
        UnstructuredName = aSN1ObjectIdentifier29;
        ASN1ObjectIdentifier aSN1ObjectIdentifier30 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
        UnstructuredAddress = aSN1ObjectIdentifier30;
        f7218E = aSN1ObjectIdentifier28;
        ASN1ObjectIdentifier aSN1ObjectIdentifier31 = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
        DC = aSN1ObjectIdentifier31;
        ASN1ObjectIdentifier aSN1ObjectIdentifier32 = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
        UID = aSN1ObjectIdentifier32;
        DefaultReverse = false;
        Hashtable hashtable = new Hashtable();
        DefaultSymbols = hashtable;
        Hashtable hashtable2 = new Hashtable();
        RFC2253Symbols = hashtable2;
        Hashtable hashtable3 = new Hashtable();
        RFC1779Symbols = hashtable3;
        Hashtable hashtable4 = new Hashtable();
        DefaultLookUp = hashtable4;
        OIDLookUp = hashtable;
        SymbolLookUp = hashtable4;
        TRUE = new Boolean(true);
        FALSE = new Boolean(false);
        hashtable.put(aSN1ObjectIdentifier, "C");
        hashtable.put(aSN1ObjectIdentifier2, "O");
        hashtable.put(aSN1ObjectIdentifier4, "T");
        hashtable.put(aSN1ObjectIdentifier3, "OU");
        hashtable.put(aSN1ObjectIdentifier5, "CN");
        hashtable.put(aSN1ObjectIdentifier8, "L");
        hashtable.put(aSN1ObjectIdentifier9, "ST");
        hashtable.put(aSN1ObjectIdentifier6, "SERIALNUMBER");
        hashtable.put(aSN1ObjectIdentifier28, "E");
        hashtable.put(aSN1ObjectIdentifier31, "DC");
        hashtable.put(aSN1ObjectIdentifier32, "UID");
        hashtable.put(aSN1ObjectIdentifier7, "STREET");
        hashtable.put(aSN1ObjectIdentifier10, "SURNAME");
        hashtable.put(aSN1ObjectIdentifier11, "GIVENNAME");
        hashtable.put(aSN1ObjectIdentifier12, "INITIALS");
        hashtable.put(aSN1ObjectIdentifier13, "GENERATION");
        hashtable.put(aSN1ObjectIdentifier30, "unstructuredAddress");
        hashtable.put(aSN1ObjectIdentifier29, "unstructuredName");
        hashtable.put(aSN1ObjectIdentifier14, "UniqueIdentifier");
        hashtable.put(aSN1ObjectIdentifier17, "DN");
        hashtable.put(aSN1ObjectIdentifier18, "Pseudonym");
        hashtable.put(aSN1ObjectIdentifier25, "PostalAddress");
        hashtable.put(aSN1ObjectIdentifier24, "NameAtBirth");
        hashtable.put(aSN1ObjectIdentifier22, "CountryOfCitizenship");
        hashtable.put(aSN1ObjectIdentifier23, "CountryOfResidence");
        hashtable.put(aSN1ObjectIdentifier21, "Gender");
        hashtable.put(aSN1ObjectIdentifier20, "PlaceOfBirth");
        hashtable.put(aSN1ObjectIdentifier19, "DateOfBirth");
        hashtable.put(aSN1ObjectIdentifier16, "PostalCode");
        hashtable.put(aSN1ObjectIdentifier15, "BusinessCategory");
        hashtable.put(aSN1ObjectIdentifier26, "TelephoneNumber");
        hashtable.put(aSN1ObjectIdentifier27, "Name");
        hashtable2.put(aSN1ObjectIdentifier, "C");
        hashtable2.put(aSN1ObjectIdentifier2, "O");
        hashtable2.put(aSN1ObjectIdentifier3, "OU");
        hashtable2.put(aSN1ObjectIdentifier5, "CN");
        hashtable2.put(aSN1ObjectIdentifier8, "L");
        hashtable2.put(aSN1ObjectIdentifier9, "ST");
        hashtable2.put(aSN1ObjectIdentifier7, "STREET");
        hashtable2.put(aSN1ObjectIdentifier31, "DC");
        hashtable2.put(aSN1ObjectIdentifier32, "UID");
        hashtable3.put(aSN1ObjectIdentifier, "C");
        hashtable3.put(aSN1ObjectIdentifier2, "O");
        hashtable3.put(aSN1ObjectIdentifier3, "OU");
        hashtable3.put(aSN1ObjectIdentifier5, "CN");
        hashtable3.put(aSN1ObjectIdentifier8, "L");
        hashtable3.put(aSN1ObjectIdentifier9, "ST");
        hashtable3.put(aSN1ObjectIdentifier7, "STREET");
        hashtable4.put("c", aSN1ObjectIdentifier);
        hashtable4.put("o", aSN1ObjectIdentifier2);
        hashtable4.put("t", aSN1ObjectIdentifier4);
        hashtable4.put("ou", aSN1ObjectIdentifier3);
        hashtable4.put("cn", aSN1ObjectIdentifier5);
        hashtable4.put("l", aSN1ObjectIdentifier8);
        hashtable4.put("st", aSN1ObjectIdentifier9);
        hashtable4.put("sn", aSN1ObjectIdentifier6);
        hashtable4.put("serialnumber", aSN1ObjectIdentifier6);
        hashtable4.put("street", aSN1ObjectIdentifier7);
        hashtable4.put("emailaddress", aSN1ObjectIdentifier28);
        hashtable4.put("dc", aSN1ObjectIdentifier31);
        hashtable4.put("e", aSN1ObjectIdentifier28);
        hashtable4.put("uid", aSN1ObjectIdentifier32);
        hashtable4.put("surname", aSN1ObjectIdentifier10);
        hashtable4.put("givenname", aSN1ObjectIdentifier11);
        hashtable4.put("initials", aSN1ObjectIdentifier12);
        hashtable4.put("generation", aSN1ObjectIdentifier13);
        hashtable4.put("unstructuredaddress", aSN1ObjectIdentifier30);
        hashtable4.put("unstructuredname", aSN1ObjectIdentifier29);
        hashtable4.put("uniqueidentifier", aSN1ObjectIdentifier14);
        hashtable4.put("dn", aSN1ObjectIdentifier17);
        hashtable4.put("pseudonym", aSN1ObjectIdentifier18);
        hashtable4.put("postaladdress", aSN1ObjectIdentifier25);
        hashtable4.put("nameofbirth", aSN1ObjectIdentifier24);
        hashtable4.put("countryofcitizenship", aSN1ObjectIdentifier22);
        hashtable4.put("countryofresidence", aSN1ObjectIdentifier23);
        hashtable4.put("gender", aSN1ObjectIdentifier21);
        hashtable4.put("placeofbirth", aSN1ObjectIdentifier20);
        hashtable4.put("dateofbirth", aSN1ObjectIdentifier19);
        hashtable4.put("postalcode", aSN1ObjectIdentifier16);
        hashtable4.put("businesscategory", aSN1ObjectIdentifier15);
        hashtable4.put("telephonenumber", aSN1ObjectIdentifier26);
        hashtable4.put("name", aSN1ObjectIdentifier27);
    }

    public X509Name() {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
    }

    private void addEntry(Hashtable hashtable, String str, Boolean bool) {
        X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(str, '=');
        String strNextToken = x509NameTokenizer.nextToken();
        if (!x509NameTokenizer.hasMoreTokens()) {
            throw new IllegalArgumentException("badly formatted directory string");
        }
        String strNextToken2 = x509NameTokenizer.nextToken();
        this.ordering.addElement(decodeOID(strNextToken, hashtable));
        this.values.addElement(unescape(strNextToken2));
        this.added.addElement(bool);
    }

    private void appendValue(StringBuffer stringBuffer, Hashtable hashtable, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        String str2 = (String) hashtable.get(aSN1ObjectIdentifier);
        if (str2 != null) {
            stringBuffer.append(str2);
        } else {
            stringBuffer.append(aSN1ObjectIdentifier.getId());
        }
        stringBuffer.append('=');
        int length = stringBuffer.length();
        stringBuffer.append(str);
        int length2 = stringBuffer.length();
        if (str.length() >= 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
            length += 2;
        }
        while (length < length2 && stringBuffer.charAt(length) == ' ') {
            stringBuffer.insert(length, "\\");
            length += 2;
            length2++;
        }
        while (true) {
            length2--;
            if (length2 <= length || stringBuffer.charAt(length2) != ' ') {
                break;
            } else {
                stringBuffer.insert(length2, '\\');
            }
        }
        while (length <= length2) {
            char cCharAt = stringBuffer.charAt(length);
            if (cCharAt != '\"' && cCharAt != '\\' && cCharAt != '+' && cCharAt != ',') {
                switch (cCharAt) {
                    case ';':
                    case '<':
                    case '=':
                    case CipherSuite.TLS_DH_DSS_WITH_AES_128_CBC_SHA256 /* 62 */:
                        break;
                    default:
                        length++;
                        break;
                }
            }
            stringBuffer.insert(length, "\\");
            length += 2;
            length2++;
        }
    }

    private String bytesToString(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return new String(cArr);
    }

    private String canonicalize(String str) {
        String lowerCase = Strings.toLowerCase(str.trim());
        if (lowerCase.length() <= 0 || lowerCase.charAt(0) != '#') {
            return lowerCase;
        }
        Encodable encodableDecodeObject = decodeObject(lowerCase);
        return encodableDecodeObject instanceof ASN1String ? Strings.toLowerCase(((ASN1String) encodableDecodeObject).getString().trim()) : lowerCase;
    }

    private ASN1ObjectIdentifier decodeOID(String str, Hashtable hashtable) {
        String strTrim = str.trim();
        if (Strings.toUpperCase(strTrim).startsWith("OID.")) {
            return new ASN1ObjectIdentifier(strTrim.substring(4));
        }
        if (strTrim.charAt(0) >= '0' && strTrim.charAt(0) <= '9') {
            return new ASN1ObjectIdentifier(strTrim);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) hashtable.get(Strings.toLowerCase(strTrim));
        if (aSN1ObjectIdentifier != null) {
            return aSN1ObjectIdentifier;
        }
        throw new IllegalArgumentException("Unknown object id - " + strTrim + " - passed to distinguished name");
    }

    private ASN1Primitive decodeObject(String str) {
        try {
            return ASN1Primitive.fromByteArray(Hex.decode(str.substring(1)));
        } catch (IOException e2) {
            throw new IllegalStateException("unknown encoding in name: " + e2);
        }
    }

    private boolean equivalentStrings(String str, String str2) {
        String strCanonicalize = canonicalize(str);
        String strCanonicalize2 = canonicalize(str2);
        return strCanonicalize.equals(strCanonicalize2) || stripInternalSpaces(strCanonicalize).equals(stripInternalSpaces(strCanonicalize2));
    }

    public static X509Name getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    private String stripInternalSpaces(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() != 0) {
            char cCharAt = str.charAt(0);
            stringBuffer.append(cCharAt);
            int i2 = 1;
            while (i2 < str.length()) {
                char cCharAt2 = str.charAt(i2);
                if (cCharAt != ' ' || cCharAt2 != ' ') {
                    stringBuffer.append(cCharAt2);
                }
                i2++;
                cCharAt = cCharAt2;
            }
        }
        return stringBuffer.toString();
    }

    private String unescape(String str) {
        int i2;
        if (str.length() == 0 || (str.indexOf(92) < 0 && str.indexOf(34) < 0)) {
            return str.trim();
        }
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer(str.length());
        if (charArray[0] == '\\' && charArray[1] == '#') {
            stringBuffer.append("\\#");
            i2 = 2;
        } else {
            i2 = 0;
        }
        boolean z2 = false;
        int length = 0;
        boolean z3 = false;
        boolean z4 = false;
        while (i2 != charArray.length) {
            char c2 = charArray[i2];
            if (c2 != ' ') {
                z4 = true;
            }
            if (c2 != '\"') {
                if (c2 == '\\' && !z2 && !z3) {
                    length = stringBuffer.length();
                    z2 = true;
                } else if (c2 != ' ' || z2 || z4) {
                    stringBuffer.append(c2);
                }
                i2++;
            } else if (z2) {
                stringBuffer.append(c2);
            } else {
                z3 = !z3;
            }
            z2 = false;
            i2++;
        }
        if (stringBuffer.length() > 0) {
            while (stringBuffer.charAt(stringBuffer.length() - 1) == ' ' && length != stringBuffer.length() - 1) {
                stringBuffer.setLength(stringBuffer.length() - 1);
            }
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj, boolean z2) {
        if (!z2) {
            return equals(obj);
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509Name) && !(obj instanceof ASN1Sequence)) {
            return false;
        }
        if (toASN1Primitive().equals(((ASN1Encodable) obj).toASN1Primitive())) {
            return true;
        }
        try {
            X509Name x509Name = getInstance(obj);
            int size = this.ordering.size();
            if (size != x509Name.ordering.size()) {
                return false;
            }
            for (int i2 = 0; i2 < size; i2++) {
                if (!((ASN1ObjectIdentifier) this.ordering.elementAt(i2)).equals((ASN1ObjectIdentifier) x509Name.ordering.elementAt(i2)) || !equivalentStrings((String) this.values.elementAt(i2), (String) x509Name.values.elementAt(i2))) {
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public Vector getOIDs() {
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.ordering.size(); i2++) {
            vector.addElement(this.ordering.elementAt(i2));
        }
        return vector;
    }

    public Vector getValues() {
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.values.size(); i2++) {
            vector.addElement(this.values.elementAt(i2));
        }
        return vector;
    }

    @Override // org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        if (this.isHashCodeCalculated) {
            return this.hashCodeValue;
        }
        this.isHashCodeCalculated = true;
        for (int i2 = 0; i2 != this.ordering.size(); i2++) {
            String strStripInternalSpaces = stripInternalSpaces(canonicalize((String) this.values.elementAt(i2)));
            int iHashCode = this.hashCodeValue ^ this.ordering.elementAt(i2).hashCode();
            this.hashCodeValue = iHashCode;
            this.hashCodeValue = strStripInternalSpaces.hashCode() ^ iHashCode;
        }
        return this.hashCodeValue;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.seq == null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = null;
            int i2 = 0;
            while (i2 != this.ordering.size()) {
                ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
                ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) this.ordering.elementAt(i2);
                aSN1EncodableVector3.add(aSN1ObjectIdentifier2);
                aSN1EncodableVector3.add(this.converter.getConvertedValue(aSN1ObjectIdentifier2, (String) this.values.elementAt(i2)));
                if (aSN1ObjectIdentifier == null || ((Boolean) this.added.elementAt(i2)).booleanValue()) {
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                } else {
                    aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
                    aSN1EncodableVector2 = new ASN1EncodableVector();
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                }
                i2++;
                aSN1ObjectIdentifier = aSN1ObjectIdentifier2;
            }
            aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
            this.seq = new DERSequence(aSN1EncodableVector);
        }
        return this.seq;
    }

    public String toString(boolean z2, Hashtable hashtable) {
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = new Vector();
        StringBuffer stringBuffer2 = null;
        for (int i2 = 0; i2 < this.ordering.size(); i2++) {
            if (((Boolean) this.added.elementAt(i2)).booleanValue()) {
                stringBuffer2.append('+');
                appendValue(stringBuffer2, hashtable, (ASN1ObjectIdentifier) this.ordering.elementAt(i2), (String) this.values.elementAt(i2));
            } else {
                stringBuffer2 = new StringBuffer();
                appendValue(stringBuffer2, hashtable, (ASN1ObjectIdentifier) this.ordering.elementAt(i2), (String) this.values.elementAt(i2));
                vector.addElement(stringBuffer2);
            }
        }
        boolean z3 = true;
        if (z2) {
            for (int size = vector.size() - 1; size >= 0; size--) {
                if (z3) {
                    z3 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(size).toString());
            }
        } else {
            for (int i3 = 0; i3 < vector.size(); i3++) {
                if (z3) {
                    z3 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(i3).toString());
            }
        }
        return stringBuffer.toString();
    }

    public static X509Name getInstance(Object obj) {
        return (obj == null || (obj instanceof X509Name)) ? (X509Name) obj : obj instanceof X500Name ? new X509Name(ASN1Sequence.getInstance(((X500Name) obj).toASN1Primitive())) : new X509Name(ASN1Sequence.getInstance(obj));
    }

    public Vector getValues(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.values.size(); i2++) {
            if (this.ordering.elementAt(i2).equals(aSN1ObjectIdentifier)) {
                String str = (String) this.values.elementAt(i2);
                if (str.length() > 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
                    vector.addElement(str.substring(1));
                } else {
                    vector.addElement(str);
                }
            }
        }
        return vector;
    }

    public X509Name(ASN1Sequence aSN1Sequence) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.seq = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Set aSN1Set = ASN1Set.getInstance(((ASN1Encodable) objects.nextElement()).toASN1Primitive());
            int i2 = 0;
            while (i2 < aSN1Set.size()) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Set.getObjectAt(i2).toASN1Primitive());
                if (aSN1Sequence2.size() == 2) {
                    this.ordering.addElement(ASN1ObjectIdentifier.getInstance(aSN1Sequence2.getObjectAt(0)));
                    ASN1Encodable objectAt = aSN1Sequence2.getObjectAt(1);
                    if ((objectAt instanceof ASN1String) && !(objectAt instanceof DERUniversalString)) {
                        String string = ((ASN1String) objectAt).getString();
                        if (string.length() > 0 && string.charAt(0) == '#') {
                            this.values.addElement("\\" + string);
                        } else {
                            this.values.addElement(string);
                        }
                    } else {
                        try {
                            this.values.addElement("#" + bytesToString(Hex.encode(objectAt.toASN1Primitive().getEncoded(ASN1Encoding.DER))));
                        } catch (IOException unused) {
                            throw new IllegalArgumentException("cannot encode value");
                        }
                    }
                    this.added.addElement(i2 != 0 ? TRUE : FALSE);
                    i2++;
                } else {
                    throw new IllegalArgumentException("badly sized pair");
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0085, code lost:
    
        r3[r9] = true;
        r4 = r4 + r6;
     */
    @Override // org.spongycastle.asn1.ASN1Object
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r12) {
        /*
            r11 = this;
            r0 = 1
            if (r12 != r11) goto L4
            return r0
        L4:
            boolean r1 = r12 instanceof org.spongycastle.asn1.x509.X509Name
            r2 = 0
            if (r1 != 0) goto Le
            boolean r1 = r12 instanceof org.spongycastle.asn1.ASN1Sequence
            if (r1 != 0) goto Le
            return r2
        Le:
            r1 = r12
            org.spongycastle.asn1.ASN1Encodable r1 = (org.spongycastle.asn1.ASN1Encodable) r1
            org.spongycastle.asn1.ASN1Primitive r1 = r1.toASN1Primitive()
            org.spongycastle.asn1.ASN1Primitive r3 = r11.toASN1Primitive()
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L20
            return r0
        L20:
            org.spongycastle.asn1.x509.X509Name r12 = getInstance(r12)     // Catch: java.lang.IllegalArgumentException -> L8e
            java.util.Vector r1 = r11.ordering
            int r1 = r1.size()
            java.util.Vector r3 = r12.ordering
            int r3 = r3.size()
            if (r1 == r3) goto L33
            return r2
        L33:
            boolean[] r3 = new boolean[r1]
            java.util.Vector r4 = r11.ordering
            java.lang.Object r4 = r4.elementAt(r2)
            java.util.Vector r5 = r12.ordering
            java.lang.Object r5 = r5.elementAt(r2)
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L4b
            r5 = r1
            r4 = 0
            r6 = 1
            goto L4f
        L4b:
            int r4 = r1 + (-1)
            r5 = -1
            r6 = -1
        L4f:
            if (r4 == r5) goto L8d
            java.util.Vector r7 = r11.ordering
            java.lang.Object r7 = r7.elementAt(r4)
            org.spongycastle.asn1.ASN1ObjectIdentifier r7 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r7
            java.util.Vector r8 = r11.values
            java.lang.Object r8 = r8.elementAt(r4)
            java.lang.String r8 = (java.lang.String) r8
            r9 = 0
        L62:
            if (r9 >= r1) goto L8c
            boolean r10 = r3[r9]
            if (r10 == 0) goto L69
            goto L89
        L69:
            java.util.Vector r10 = r12.ordering
            java.lang.Object r10 = r10.elementAt(r9)
            org.spongycastle.asn1.ASN1ObjectIdentifier r10 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r10
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L89
            java.util.Vector r10 = r12.values
            java.lang.Object r10 = r10.elementAt(r9)
            java.lang.String r10 = (java.lang.String) r10
            boolean r10 = r11.equivalentStrings(r8, r10)
            if (r10 == 0) goto L89
            r3[r9] = r0
            int r4 = r4 + r6
            goto L4f
        L89:
            int r9 = r9 + 1
            goto L62
        L8c:
            return r2
        L8d:
            return r0
        L8e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.X509Name.equals(java.lang.Object):boolean");
    }

    public String toString() {
        return toString(DefaultReverse, DefaultSymbols);
    }

    public X509Name(Hashtable hashtable) {
        this((Vector) null, hashtable);
    }

    public X509Name(Vector vector, Hashtable hashtable) {
        this(vector, hashtable, new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Hashtable hashtable, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        if (vector != null) {
            for (int i2 = 0; i2 != vector.size(); i2++) {
                this.ordering.addElement(vector.elementAt(i2));
                this.added.addElement(FALSE);
            }
        } else {
            Enumeration enumerationKeys = hashtable.keys();
            while (enumerationKeys.hasMoreElements()) {
                this.ordering.addElement(enumerationKeys.nextElement());
                this.added.addElement(FALSE);
            }
        }
        for (int i3 = 0; i3 != this.ordering.size(); i3++) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) this.ordering.elementAt(i3);
            if (hashtable.get(aSN1ObjectIdentifier) != null) {
                this.values.addElement(hashtable.get(aSN1ObjectIdentifier));
            } else {
                throw new IllegalArgumentException("No attribute for object id - " + aSN1ObjectIdentifier.getId() + " - passed to distinguished name");
            }
        }
    }

    public X509Name(Vector vector, Vector vector2) {
        this(vector, vector2, new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Vector vector2, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        if (vector.size() == vector2.size()) {
            for (int i2 = 0; i2 < vector.size(); i2++) {
                this.ordering.addElement(vector.elementAt(i2));
                this.values.addElement(vector2.elementAt(i2));
                this.added.addElement(FALSE);
            }
            return;
        }
        throw new IllegalArgumentException("oids vector must be same length as values.");
    }

    public X509Name(String str) {
        this(DefaultReverse, DefaultLookUp, str);
    }

    public X509Name(String str, X509NameEntryConverter x509NameEntryConverter) {
        this(DefaultReverse, DefaultLookUp, str, x509NameEntryConverter);
    }

    public X509Name(boolean z2, String str) {
        this(z2, DefaultLookUp, str);
    }

    public X509Name(boolean z2, String str, X509NameEntryConverter x509NameEntryConverter) {
        this(z2, DefaultLookUp, str, x509NameEntryConverter);
    }

    public X509Name(boolean z2, Hashtable hashtable, String str) {
        this(z2, hashtable, str, new X509DefaultEntryConverter());
    }

    public X509Name(boolean z2, Hashtable hashtable, String str, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(str);
        while (x509NameTokenizer.hasMoreTokens()) {
            String strNextToken = x509NameTokenizer.nextToken();
            if (strNextToken.indexOf(43) > 0) {
                X509NameTokenizer x509NameTokenizer2 = new X509NameTokenizer(strNextToken, '+');
                addEntry(hashtable, x509NameTokenizer2.nextToken(), FALSE);
                while (x509NameTokenizer2.hasMoreTokens()) {
                    addEntry(hashtable, x509NameTokenizer2.nextToken(), TRUE);
                }
            } else {
                addEntry(hashtable, strNextToken, FALSE);
            }
        }
        if (z2) {
            Vector vector = new Vector();
            Vector vector2 = new Vector();
            Vector vector3 = new Vector();
            int i2 = 1;
            for (int i3 = 0; i3 < this.ordering.size(); i3++) {
                if (((Boolean) this.added.elementAt(i3)).booleanValue()) {
                    vector.insertElementAt(this.ordering.elementAt(i3), i2);
                    vector2.insertElementAt(this.values.elementAt(i3), i2);
                    vector3.insertElementAt(this.added.elementAt(i3), i2);
                    i2++;
                } else {
                    vector.insertElementAt(this.ordering.elementAt(i3), 0);
                    vector2.insertElementAt(this.values.elementAt(i3), 0);
                    vector3.insertElementAt(this.added.elementAt(i3), 0);
                    i2 = 1;
                }
            }
            this.ordering = vector;
            this.values = vector2;
            this.added = vector3;
        }
    }
}
