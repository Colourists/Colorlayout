package colorists.layout.colorlayoutui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;

public class ColouristsUiHelper {

    private static final boolean IS_LOLLIPOP;

    static {
        IS_LOLLIPOP = Build.VERSION.SDK_INT >= 21;
    }

    private ColorRelativeLayout colorRelativeLayout;
    //以下属性关于圆角 优先级如下 : circularCorner > cornerRadius > 其他
    // view为圆形
    private boolean circularCorner;
    //四个角都是这个弧度
    private float cornerRadius;
    //设置四个角的弧度
    private float leftBottomCorner;
    private float leftTopCorner;
    private float rightTopCorner;
    private float rightBottomCorner;
    private boolean backgroundOverwritten = false;


    private GradientDrawable colorableBackgroundDrawableCompat;
    private GradientDrawable backgroundDrawableLollipop;
    private GradientDrawable rippleDrawableCompat;
    private Drawable tintableRippleDrawableCompat;
    private Drawable tintableBackgroundDrawableCompat;
    private GradientDrawable maskDrawableLollipop;
    private PorterDuff.Mode backgroundTintMode;
    //颜色
    private ColorStateList backgroundTint;
    private ColorStateList rippleColor;
    private ColorStateList strokeColor;
    private GradientDrawable strokeDrawableLollipop;
    private int strokeWidth;

    public ColouristsUiHelper(ColorRelativeLayout colorRelativeLayout) {
        this.colorRelativeLayout = colorRelativeLayout;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        circularCorner = typedArray.getBoolean(R.styleable.CornerLayout_circularCorner, false);
        cornerRadius = typedArray.getDimension(R.styleable.CornerLayout_cornerRadius, 0);
        leftBottomCorner = typedArray.getDimension(R.styleable.CornerLayout_leftBottomCorner, 0);
        leftTopCorner = typedArray.getDimension(R.styleable.CornerLayout_leftTopCorner, 0);
        rightTopCorner = typedArray.getDimension(R.styleable.CornerLayout_rightTopCorner, 0);
        rightBottomCorner = typedArray.getDimension(R.styleable.CornerLayout_rightBottomCorner, 0);
        backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.CornerLayout_mBackgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        backgroundTint = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.CornerLayout_mBackgroundTint);
        rippleColor = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.CornerLayout_rippleColor);
        strokeColor = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.CornerLayout_strokeColor);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CornerLayout_strokeWidth, 0);

        Log.e("TAG", "loadFromAttributes: ");
        colorRelativeLayout.setInternalBackground(IS_LOLLIPOP ? this.createBackgroundLollipop() : this.createBackgroundCompat());

    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    private Drawable createBackgroundCompat() {
        Log.e("TAG", "createBackgroundCompat: ");

        colorableBackgroundDrawableCompat = new GradientDrawable();
        colorableBackgroundDrawableCompat.setCornerRadius((float) cornerRadius);
        colorableBackgroundDrawableCompat.setColor(-1);
        tintableBackgroundDrawableCompat = DrawableCompat.wrap(colorableBackgroundDrawableCompat);
        DrawableCompat.setTintList(this.tintableBackgroundDrawableCompat, backgroundTint);
//        if (backgroundTintMode != null) {
//            DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, this.backgroundTintMode);
//        }

        rippleDrawableCompat = new GradientDrawable();
        rippleDrawableCompat.setCornerRadius((float) cornerRadius);
        rippleDrawableCompat.setColor(-1);
        tintableRippleDrawableCompat = DrawableCompat.wrap(this.rippleDrawableCompat);
        DrawableCompat.setTintList(tintableRippleDrawableCompat, rippleColor);
        return new LayerDrawable(new Drawable[]{tintableBackgroundDrawableCompat, tintableRippleDrawableCompat});
    }

    private InsetDrawable wrapDrawableWithInset(Drawable drawable) {
        return new InsetDrawable(drawable, 0, 0, 0, 0);
    }

    @TargetApi(21)
    private Drawable createBackgroundLollipop() {
        Log.e("TAG", "createBackgroundLollipop: ");
        backgroundDrawableLollipop = new GradientDrawable();
        backgroundDrawableLollipop.setCornerRadius((float) cornerRadius);
        backgroundDrawableLollipop.setColor(backgroundTint);
        backgroundDrawableLollipop.setStroke(strokeWidth,strokeColor);
        updateTintAndTintModeLollipop();
//        return backgroundDrawableLollipop;
//        strokeDrawableLollipop = new GradientDrawable();
//        strokeDrawableLollipop.setCornerRadius((float) this.cornerRadius );
//        strokeDrawableLollipop.setColor(0);
//        strokeDrawableLollipop.setStroke(strokeWidth, strokeColor);
//        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, strokeDrawableLollipop});
//        InsetDrawable bgInsetDrawable = wrapDrawableWithInset(layerDrawable);
        maskDrawableLollipop = new GradientDrawable();
        maskDrawableLollipop.setCornerRadius((float) this.cornerRadius);
        maskDrawableLollipop.setColor(-1);
//        return backgroundDrawableLollipop;
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), backgroundDrawableLollipop, this.maskDrawableLollipop);
    }

    private void updateTintAndTintModeLollipop() {
        if (backgroundDrawableLollipop != null) {
//            DrawableCompat.setTintList(backgroundDrawableLollipop, backgroundTint);
            if (backgroundTintMode != null) {
                DrawableCompat.setTintMode(backgroundDrawableLollipop, backgroundTintMode);
            }
        }

    }

    void drawBackGroundCorner(@Nullable Canvas canvas) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.GREEN);
//        background.setCornerRadii();

    }

    void setBackgroundOverwritten() {
        backgroundOverwritten = true;
//        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
//        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

//        if (canvas != null && this.strokeColor != null && this.strokeWidth > 0) {
//            this.bounds.set(this.materialButton.getBackground().getBounds());
//            this.rectF.set((float)this.bounds.left + (float)this.strokeWidth / 2.0F + (float)this.insetLeft, (float)this.bounds.top + (float)this.strokeWidth / 2.0F + (float)this.insetTop, (float)this.bounds.right - (float)this.strokeWidth / 2.0F - (float)this.insetRight, (float)this.bounds.bottom - (float)this.strokeWidth / 2.0F - (float)this.insetBottom);
//            float strokeCornerRadius = (float)this.cornerRadius - (float)this.strokeWidth / 2.0F;
//            canvas.drawRoundRect(this.rectF, strokeCornerRadius, strokeCornerRadius, this.buttonStrokePaint);
//        }
//
//    }
}
