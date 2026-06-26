package k;

import android.content.Context;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

/* JADX INFO: renamed from: k.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0270f0 {

    /* JADX INFO: renamed from: k.f0$a */
    public static class a extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Locale f5696a;

        public a(Context context) {
            this.f5696a = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f5696a);
            }
            return null;
        }
    }

    public static void a(TextView textView) {
        textView.setTransformationMethod(new a(textView.getContext()));
    }
}
