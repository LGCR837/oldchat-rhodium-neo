package n;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.animation.Animation;
import android.widget.ImageView;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: n.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0419b extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animation.AnimationListener f6682a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6683b;

    /* JADX INFO: renamed from: n.b$a */
    public class a extends OvalShape {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RadialGradient f6684a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Paint f6685b = new Paint();

        public a(int i2) {
            C0419b.this.f6683b = i2;
            a((int) rect().width());
        }

        public final void a(int i2) {
            float f2 = i2 / 2;
            RadialGradient radialGradient = new RadialGradient(f2, f2, C0419b.this.f6683b, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.f6684a = radialGradient;
            this.f6685b.setShader(radialGradient);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            float width = C0419b.this.getWidth() / 2;
            float height = C0419b.this.getHeight() / 2;
            canvas.drawCircle(width, height, width, this.f6685b);
            canvas.drawCircle(width, height, r0 - C0419b.this.f6683b, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void onResize(float f2, float f3) {
            super.onResize(f2, f3);
            a((int) f2);
        }
    }

    public C0419b(Context context, int i2) {
        ShapeDrawable shapeDrawable;
        super(context);
        float f2 = getContext().getResources().getDisplayMetrics().density;
        int i3 = (int) (1.75f * f2);
        int i4 = (int) (0.0f * f2);
        this.f6683b = (int) (3.5f * f2);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            AbstractC0285k0.O(this, f2 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new a(this.f6683b));
            AbstractC0285k0.Q(this, 1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.f6683b, i4, i3, 503316480);
            int i5 = this.f6683b;
            setPadding(i5, i5, i5, i5);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i2);
        setBackgroundDrawable(shapeDrawable);
    }

    public final boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public void b(Animation.AnimationListener animationListener) {
        this.f6682a = animationListener;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.f6682a;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f6682a;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (a()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.f6683b * 2), getMeasuredHeight() + (this.f6683b * 2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i2);
        }
    }
}
