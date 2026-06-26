package org.spongycastle.crypto.tls;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
class DTLSReassembler {
    private byte[] body;
    private Vector missing;
    private short msg_type;

    public static class Range {
        private int end;
        private int start;

        public Range(int i2, int i3) {
            this.start = i2;
            this.end = i3;
        }

        public int getEnd() {
            return this.end;
        }

        public int getStart() {
            return this.start;
        }

        public void setEnd(int i2) {
            this.end = i2;
        }

        public void setStart(int i2) {
            this.start = i2;
        }
    }

    public DTLSReassembler(short s2, int i2) {
        Vector vector = new Vector();
        this.missing = vector;
        this.msg_type = s2;
        this.body = new byte[i2];
        vector.addElement(new Range(0, i2));
    }

    public void contributeFragment(short s2, int i2, byte[] bArr, int i3, int i4, int i5) {
        int i6 = i4 + i5;
        if (this.msg_type == s2 && this.body.length == i2 && i6 <= i2) {
            int i7 = 0;
            if (i5 == 0) {
                if (i4 == 0 && !this.missing.isEmpty() && ((Range) this.missing.firstElement()).getEnd() == 0) {
                    this.missing.removeElementAt(0);
                    return;
                }
                return;
            }
            while (i7 < this.missing.size()) {
                Range range = (Range) this.missing.elementAt(i7);
                if (range.getStart() >= i6) {
                    return;
                }
                if (range.getEnd() > i4) {
                    int iMax = Math.max(range.getStart(), i4);
                    int iMin = Math.min(range.getEnd(), i6);
                    System.arraycopy(bArr, (i3 + iMax) - i4, this.body, iMax, iMin - iMax);
                    if (iMax != range.getStart()) {
                        if (iMin != range.getEnd()) {
                            i7++;
                            this.missing.insertElementAt(new Range(iMin, range.getEnd()), i7);
                        }
                        range.setEnd(iMax);
                    } else if (iMin == range.getEnd()) {
                        this.missing.removeElementAt(i7);
                        i7--;
                    } else {
                        range.setStart(iMin);
                    }
                }
                i7++;
            }
        }
    }

    public byte[] getBodyIfComplete() {
        if (this.missing.isEmpty()) {
            return this.body;
        }
        return null;
    }

    public short getMsgType() {
        return this.msg_type;
    }

    public void reset() {
        this.missing.removeAllElements();
        this.missing.addElement(new Range(0, this.body.length));
    }
}
