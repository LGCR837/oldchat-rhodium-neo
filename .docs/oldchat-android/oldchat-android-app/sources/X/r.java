package x;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f8196c = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ProgressBar f8197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bitmap f8198b;

    public r(ProgressBar progressBar) {
        this.f8197a = progressBar;
    }

    public final Shape a() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    public Bitmap b() {
        return this.f8198b;
    }

    public void c(AttributeSet attributeSet, int i2) {
        f0 f0VarS = f0.s(this.f8197a.getContext(), attributeSet, f8196c, i2, 0);
        Drawable drawableG = f0VarS.g(0);
        if (drawableG != null) {
            this.f8197a.setIndeterminateDrawable(e(drawableG));
        }
        Drawable drawableG2 = f0VarS.g(1);
        if (drawableG2 != null) {
            this.f8197a.setProgressDrawable(d(drawableG2, false));
        }
        f0VarS.t();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Drawable d(Drawable drawable, boolean z2) {
        if (drawable instanceof g.w) {
            g.w wVar = (g.w) drawable;
            Drawable drawableA = wVar.a();
            if (drawableA != null) {
                wVar.b(d(drawableA, z2));
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    int id = layerDrawable.getId(i2);
                    drawableArr[i2] = d(layerDrawable.getDrawable(i2), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i3 = 0; i3 < numberOfLayers; i3++) {
                    layerDrawable2.setId(i3, layerDrawable.getId(i3));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.f8198b == null) {
                    this.f8198b = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(a());
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z2 ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    public final Drawable e(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i2 = 0; i2 < numberOfFrames; i2++) {
            Drawable drawableD = d(animationDrawable.getFrame(i2), true);
            drawableD.setLevel(10000);
            animationDrawable2.addFrame(drawableD, animationDrawable.getDuration(i2));
        }
        animationDrawable2.setLevel(10000);
        return animationDrawable2;
    }
}
