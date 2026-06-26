package org.spongycastle.asn1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class ASN1GeneralizedTime extends ASN1Primitive {
    private byte[] time;

    public ASN1GeneralizedTime(String str) {
        this.time = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e2) {
            throw new IllegalArgumentException("invalid date string: " + e2.getMessage());
        }
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = "-";
        } else {
            str = "+";
        }
        int i2 = rawOffset / 3600000;
        int i3 = (rawOffset - (3600000 * i2)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i2 += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + convert(i2) + ":" + convert(i3);
    }

    private String convert(int i2) {
        if (i2 >= 10) {
            return Integer.toString(i2);
        }
        return "0" + i2;
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1GeneralizedTime) ASN1Primitive.fromByteArray((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    private boolean hasFractionalSeconds() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.time;
            if (i2 == bArr.length) {
                return false;
            }
            if (bArr[i2] == 46 && i2 == 14) {
                return true;
            }
            i2++;
        }
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            return Arrays.areEqual(this.time, ((ASN1GeneralizedTime) aSN1Primitive).time);
        }
        return false;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.writeEncoded(24, this.time);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    public Date getDate() {
        SimpleDateFormat simpleDateFormat;
        char cCharAt;
        String strFromByteArray = Strings.fromByteArray(this.time);
        if (strFromByteArray.endsWith("Z")) {
            simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'") : new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (strFromByteArray.indexOf(45) > 0 || strFromByteArray.indexOf(43) > 0) {
            strFromByteArray = getTime();
            simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSSz") : new SimpleDateFormat("yyyyMMddHHmmssz");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else {
            simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS") : new SimpleDateFormat("yyyyMMddHHmmss");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (hasFractionalSeconds()) {
            String strSubstring = strFromByteArray.substring(14);
            int i2 = 1;
            while (i2 < strSubstring.length() && '0' <= (cCharAt = strSubstring.charAt(i2)) && cCharAt <= '9') {
                i2++;
            }
            int i3 = i2 - 1;
            if (i3 > 3) {
                strFromByteArray = strFromByteArray.substring(0, 14) + (strSubstring.substring(0, 4) + strSubstring.substring(i2));
            } else if (i3 == 1) {
                strFromByteArray = strFromByteArray.substring(0, 14) + (strSubstring.substring(0, i2) + "00" + strSubstring.substring(i2));
            } else if (i3 == 2) {
                strFromByteArray = strFromByteArray.substring(0, 14) + (strSubstring.substring(0, i2) + "0" + strSubstring.substring(i2));
            }
        }
        return simpleDateFormat.parse(strFromByteArray);
    }

    public String getTime() {
        String strFromByteArray = Strings.fromByteArray(this.time);
        if (strFromByteArray.charAt(strFromByteArray.length() - 1) == 'Z') {
            return strFromByteArray.substring(0, strFromByteArray.length() - 1) + "GMT+00:00";
        }
        int length = strFromByteArray.length();
        int i2 = length - 5;
        char cCharAt = strFromByteArray.charAt(i2);
        if (cCharAt == '-' || cCharAt == '+') {
            StringBuilder sb = new StringBuilder();
            sb.append(strFromByteArray.substring(0, i2));
            sb.append("GMT");
            int i3 = length - 2;
            sb.append(strFromByteArray.substring(i2, i3));
            sb.append(":");
            sb.append(strFromByteArray.substring(i3));
            return sb.toString();
        }
        int length2 = strFromByteArray.length() - 3;
        char cCharAt2 = strFromByteArray.charAt(length2);
        if (cCharAt2 != '-' && cCharAt2 != '+') {
            return strFromByteArray + calculateGMTOffset();
        }
        return strFromByteArray.substring(0, length2) + "GMT" + strFromByteArray.substring(length2) + ":00";
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.time);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (!z2 && !(object instanceof ASN1GeneralizedTime)) {
            return new ASN1GeneralizedTime(((ASN1OctetString) object).getOctets());
        }
        return getInstance(object);
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(byte[] bArr) {
        this.time = bArr;
    }
}
