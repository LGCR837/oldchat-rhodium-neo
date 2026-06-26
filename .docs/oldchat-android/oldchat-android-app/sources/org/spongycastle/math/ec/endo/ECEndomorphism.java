package org.spongycastle.math.ec.endo;

import org.spongycastle.math.ec.ECPointMap;

/* JADX INFO: loaded from: classes.dex */
public interface ECEndomorphism {
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}
