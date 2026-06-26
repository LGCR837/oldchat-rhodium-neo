package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public enum PasswordConverter implements CharToByteConverter {
    ASCII { // from class: org.spongycastle.crypto.PasswordConverter.1
        @Override // org.spongycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToBytes(cArr);
        }

        @Override // org.spongycastle.crypto.CharToByteConverter
        public String getType() {
            return "ASCII";
        }
    },
    UTF8 { // from class: org.spongycastle.crypto.PasswordConverter.2
        @Override // org.spongycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(cArr);
        }

        @Override // org.spongycastle.crypto.CharToByteConverter
        public String getType() {
            return "UTF8";
        }
    },
    PKCS12 { // from class: org.spongycastle.crypto.PasswordConverter.3
        @Override // org.spongycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
        }

        @Override // org.spongycastle.crypto.CharToByteConverter
        public String getType() {
            return "PKCS12";
        }
    }
}
