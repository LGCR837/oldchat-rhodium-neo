package org.spongycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.spongycastle.i18n.filter.Filter;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.i18n.filter.UntrustedUrlInput;

/* JADX INFO: loaded from: classes.dex */
public class LocalizedMessage {
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    protected FilteredArguments arguments;
    protected String encoding;
    protected FilteredArguments extraArgs;
    protected Filter filter;
    protected final String id;
    protected ClassLoader loader;
    protected final String resource;

    public class FilteredArguments {
        protected static final int FILTER = 1;
        protected static final int FILTER_URL = 2;
        protected static final int NO_FILTER = 0;
        protected int[] argFilterType;
        protected Object[] arguments;
        protected Filter filter;
        protected Object[] filteredArgs;
        protected boolean[] isLocaleSpecific;
        protected Object[] unpackedArgs;

        public FilteredArguments(LocalizedMessage localizedMessage) {
            this(new Object[0]);
        }

        private Object filter(int i2, Object obj) {
            Filter filter = this.filter;
            if (filter != null) {
                if (obj == null) {
                    obj = "null";
                }
                if (i2 != 0) {
                    if (i2 == 1) {
                        return filter.doFilter(obj.toString());
                    }
                    if (i2 != 2) {
                        return null;
                    }
                    return filter.doFilterUrl(obj.toString());
                }
            }
            return obj;
        }

        public Object[] getArguments() {
            return this.arguments;
        }

        public Filter getFilter() {
            return this.filter;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object[] objArr = new Object[this.unpackedArgs.length];
            int i2 = 0;
            while (true) {
                Object[] objArr2 = this.unpackedArgs;
                if (i2 >= objArr2.length) {
                    return objArr;
                }
                Object objFilter = this.filteredArgs[i2];
                if (objFilter == null) {
                    Object obj = objArr2[i2];
                    if (this.isLocaleSpecific[i2]) {
                        objFilter = filter(this.argFilterType[i2], ((LocaleString) obj).getLocaleString(locale));
                    } else {
                        objFilter = filter(this.argFilterType[i2], obj);
                        this.filteredArgs[i2] = objFilter;
                    }
                }
                objArr[i2] = objFilter;
                i2++;
            }
        }

        public boolean isEmpty() {
            return this.unpackedArgs.length == 0;
        }

        public void setFilter(Filter filter) {
            if (filter != this.filter) {
                for (int i2 = 0; i2 < this.unpackedArgs.length; i2++) {
                    this.filteredArgs[i2] = null;
                }
            }
            this.filter = filter;
        }

        public FilteredArguments(Object[] objArr) {
            this.filter = null;
            this.arguments = objArr;
            this.unpackedArgs = new Object[objArr.length];
            this.filteredArgs = new Object[objArr.length];
            this.isLocaleSpecific = new boolean[objArr.length];
            this.argFilterType = new int[objArr.length];
            for (int i2 = 0; i2 < objArr.length; i2++) {
                Object obj = objArr[i2];
                if (obj instanceof TrustedInput) {
                    this.unpackedArgs[i2] = ((TrustedInput) obj).getInput();
                    this.argFilterType[i2] = 0;
                } else if (obj instanceof UntrustedInput) {
                    this.unpackedArgs[i2] = ((UntrustedInput) obj).getInput();
                    if (objArr[i2] instanceof UntrustedUrlInput) {
                        this.argFilterType[i2] = 2;
                    } else {
                        this.argFilterType[i2] = 1;
                    }
                } else {
                    this.unpackedArgs[i2] = obj;
                    this.argFilterType[i2] = 1;
                }
                this.isLocaleSpecific[i2] = this.unpackedArgs[i2] instanceof LocaleString;
            }
        }
    }

    public LocalizedMessage(String str, String str2) {
        this.encoding = DEFAULT_ENCODING;
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str == null || str2 == null) {
            throw null;
        }
        this.id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(this);
    }

    public String addExtraArgs(String str, Locale locale) {
        if (this.extraArgs == null) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        Object[] filteredArgs = this.extraArgs.getFilteredArgs(locale);
        for (Object obj : filteredArgs) {
            stringBuffer.append(obj);
        }
        return stringBuffer.toString();
    }

    public String formatWithTimeZone(String str, Object[] objArr, Locale locale, TimeZone timeZone) {
        MessageFormat messageFormat = new MessageFormat(" ");
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(str);
        if (!timeZone.equals(TimeZone.getDefault())) {
            Format[] formats = messageFormat.getFormats();
            for (int i2 = 0; i2 < formats.length; i2++) {
                Format format = formats[i2];
                if (format instanceof DateFormat) {
                    DateFormat dateFormat = (DateFormat) format;
                    dateFormat.setTimeZone(timeZone);
                    messageFormat.setFormat(i2, dateFormat);
                }
            }
        }
        return messageFormat.format(objArr);
    }

    public Object[] getArguments() {
        return this.arguments.getArguments();
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public String getEntry(String str, Locale locale, TimeZone timeZone) {
        String str2 = this.id;
        if (str != null) {
            str2 = str2 + "." + str;
        }
        String str3 = str2;
        try {
            ClassLoader classLoader = this.loader;
            String string = (classLoader == null ? ResourceBundle.getBundle(this.resource, locale) : ResourceBundle.getBundle(this.resource, locale, classLoader)).getString(str3);
            if (!this.encoding.equals(DEFAULT_ENCODING)) {
                string = new String(string.getBytes(DEFAULT_ENCODING), this.encoding);
            }
            if (!this.arguments.isEmpty()) {
                string = formatWithTimeZone(string, this.arguments.getFilteredArgs(locale), locale, timeZone);
            }
            return addExtraArgs(string, locale);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        } catch (MissingResourceException unused) {
            String str4 = "Can't find entry " + str3 + " in resource file " + this.resource + ".";
            String str5 = this.resource;
            ClassLoader classLoader2 = this.loader;
            if (classLoader2 == null) {
                classLoader2 = getClassLoader();
            }
            throw new MissingEntryException(str4, str5, str3, locale, classLoader2);
        }
    }

    public Object[] getExtraArgs() {
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments == null) {
            return null;
        }
        return filteredArguments.getArguments();
    }

    public Filter getFilter() {
        return this.filter;
    }

    public String getId() {
        return this.id;
    }

    public String getResource() {
        return this.resource;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public void setExtraArgument(Object obj) {
        setExtraArguments(new Object[]{obj});
    }

    public void setExtraArguments(Object[] objArr) {
        if (objArr == null) {
            this.extraArgs = null;
            return;
        }
        FilteredArguments filteredArguments = new FilteredArguments(objArr);
        this.extraArgs = filteredArguments;
        filteredArguments.setFilter(this.filter);
    }

    public void setFilter(Filter filter) {
        this.arguments.setFilter(filter);
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null) {
            filteredArguments.setFilter(filter);
        }
        this.filter = filter;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource: \"");
        stringBuffer.append(this.resource);
        stringBuffer.append("\" Id: \"");
        stringBuffer.append(this.id);
        stringBuffer.append("\"");
        stringBuffer.append(" Arguments: ");
        stringBuffer.append(this.arguments.getArguments().length);
        stringBuffer.append(" normal");
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null && filteredArguments.getArguments().length > 0) {
            stringBuffer.append(", ");
            stringBuffer.append(this.extraArgs.getArguments().length);
            stringBuffer.append(" extra");
        }
        stringBuffer.append(" Encoding: ");
        stringBuffer.append(this.encoding);
        stringBuffer.append(" ClassLoader: ");
        stringBuffer.append(this.loader);
        return stringBuffer.toString();
    }

    public LocalizedMessage(String str, String str2, String str3) throws UnsupportedEncodingException {
        this.encoding = DEFAULT_ENCODING;
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str != null && str2 != null) {
            this.id = str2;
            this.resource = str;
            this.arguments = new FilteredArguments(this);
            if (Charset.isSupported(str3)) {
                this.encoding = str3;
                return;
            }
            throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
        }
        throw null;
    }

    public LocalizedMessage(String str, String str2, Object[] objArr) {
        this.encoding = DEFAULT_ENCODING;
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str != null && str2 != null && objArr != null) {
            this.id = str2;
            this.resource = str;
            this.arguments = new FilteredArguments(objArr);
            return;
        }
        throw null;
    }

    public LocalizedMessage(String str, String str2, String str3, Object[] objArr) throws UnsupportedEncodingException {
        this.encoding = DEFAULT_ENCODING;
        this.extraArgs = null;
        this.filter = null;
        this.loader = null;
        if (str != null && str2 != null && objArr != null) {
            this.id = str2;
            this.resource = str;
            this.arguments = new FilteredArguments(objArr);
            if (Charset.isSupported(str3)) {
                this.encoding = str3;
                return;
            }
            throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
        }
        throw null;
    }
}
