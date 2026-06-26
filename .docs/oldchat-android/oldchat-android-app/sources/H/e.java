package H;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public enum e {
    OTHER(Object.class),
    PURE_BARCODE(Void.class),
    POSSIBLE_FORMATS(List.class),
    TRY_HARDER(Void.class),
    CHARACTER_SET(String.class),
    ALLOWED_LENGTHS(int[].class),
    ASSUME_CODE_39_CHECK_DIGIT(Void.class),
    ASSUME_GS1(Void.class),
    RETURN_CODABAR_START_END(Void.class),
    NEED_RESULT_POINT_CALLBACK(q.class),
    ALLOWED_EAN_EXTENSIONS(int[].class);


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f272a;

    e(Class cls) {
        this.f272a = cls;
    }
}
