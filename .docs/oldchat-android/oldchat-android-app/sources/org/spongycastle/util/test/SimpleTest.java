package org.spongycastle.util.test;

import java.io.PrintStream;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleTest implements Test {
    public static void runTest(Test test) {
        runTest(test, System.out);
    }

    private TestResult success() {
        return SimpleTestResult.successful(this, "Okay");
    }

    public boolean areEqual(byte[][] bArr, byte[][] bArr2) {
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (!areEqual(bArr[i2], bArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public void fail(String str) {
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }

    @Override // org.spongycastle.util.test.Test
    public abstract String getName();

    public void isEquals(Object obj, Object obj2) {
        if (!obj.equals(obj2)) {
            throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
        }
    }

    public void isTrue(boolean z2) {
        if (!z2) {
            throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
        }
    }

    @Override // org.spongycastle.util.test.Test
    public TestResult perform() {
        try {
            performTest();
            return success();
        } catch (TestFailedException e2) {
            return e2.getResult();
        } catch (Exception e3) {
            return SimpleTestResult.failed(this, "Exception: " + e3, e3);
        }
    }

    public abstract void performTest();

    public static void runTest(Test test, PrintStream printStream) {
        TestResult testResultPerform = test.perform();
        printStream.println(testResultPerform.toString());
        if (testResultPerform.getException() != null) {
            testResultPerform.getException().printStackTrace(printStream);
        }
    }

    public void fail(String str, Throwable th) {
        throw new TestFailedException(SimpleTestResult.failed(this, str, th));
    }

    public void isTrue(String str, boolean z2) {
        if (!z2) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
    }

    public void fail(String str, Object obj, Object obj2) {
        throw new TestFailedException(SimpleTestResult.failed(this, str, obj, obj2));
    }

    public void isEquals(int i2, int i3) {
        if (i2 != i3) {
            throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
        }
    }

    public boolean areEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.areEqual(bArr, bArr2);
    }

    public void isEquals(long j2, long j3) {
        if (j2 != j3) {
            throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
        }
    }

    public void isEquals(String str, boolean z2, boolean z3) {
        if (z2 != z3) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
    }

    public void isEquals(String str, long j2, long j3) {
        if (j2 != j3) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
    }

    public void isEquals(String str, Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return;
        }
        if (obj == null) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
        if (obj2 != null) {
            if (!obj.equals(obj2)) {
                throw new TestFailedException(SimpleTestResult.failed(this, str));
            }
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }
}
