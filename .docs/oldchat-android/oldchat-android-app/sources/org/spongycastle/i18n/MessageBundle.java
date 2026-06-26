package org.spongycastle.i18n;

import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class MessageBundle extends TextBundle {
    public static final String TITLE_ENTRY = "title";

    public MessageBundle(String str, String str2) {
        super(str, str2);
    }

    public String getTitle(Locale locale, TimeZone timeZone) {
        return getEntry(TITLE_ENTRY, locale, timeZone);
    }

    public MessageBundle(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public String getTitle(Locale locale) {
        return getEntry(TITLE_ENTRY, locale, TimeZone.getDefault());
    }

    public MessageBundle(String str, String str2, Object[] objArr) {
        super(str, str2, objArr);
    }

    public MessageBundle(String str, String str2, String str3, Object[] objArr) {
        super(str, str2, str3, objArr);
    }
}
