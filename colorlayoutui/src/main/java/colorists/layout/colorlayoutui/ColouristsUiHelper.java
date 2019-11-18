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
    private float[] cornerRadiusAll = new float[8];

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
    private int measuredHeight;
    private int measuredWidth;

    public ColouristsUiHelper(ColorRelativeLayout colorRelativeLayout) {
        this.colorRelativeLayout = colorRelativeLayout;
    }

    public void loadFromAttributes(TypedArray typedArray) {

        circularCorner = typedArray.getBoolean(R.styleable.ColorRelativeLayout_circularCorner, false);
        setcornerRadius(typedArray);

        backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.ColorRelativeLayout_mBackgroundTintMode, -1), PorterDuff.Mode.SRC_IN);

        backgroundTint = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.ColorRelativeLayout_mBackgroundTint);
        rippleColor = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.ColorRelativeLayout_rippleColor);
        strokeColor = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.ColorRelativeLayout_strokeColor);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.ColorRelativeLayout_strokeWidth, 0);
//        getSecondPro();
        colorRelativeLayout.setInternalBackground(IS_LOLLIPOP ? this.createBackgroundLollipop() : this.createBackgroundCompat());

        Log.e("TAG", "loadFromAttributes: ");


    }


    /**
     * 设置圆角
     *
     * @param typedArray
     */
    private void setcornerRadius(TypedArray typedArray) {
        cornerRadius = typedArray.getDimension(R.styleable.ColorRelativeLayout_cornerRadius, -1);
        if (cornerRadius >= 0) {
            cornerRadiusAll[0] = cornerRadius;
            cornerRadiusAll[1] = cornerRadius;
            cornerRadiusAll[2] = cornerRadius;
            cornerRadiusAll[3] = cornerRadius;
            cornerRadiusAll[4] = cornerRadius;
            cornerRadiusAll[5] = cornerRadius;
            cornerRadiusAll[6] = cornerRadius;
            cornerRadiusAll[7] = cornerRadius;
        } else {
            cornerRadiusAll[0] = typedArray.getDimension(R.styleable.ColorRelativeLayout_leftTopCorner, 0);
            cornerRadiusAll[1] = cornerRadiusAll[0];
            cornerRadiusAll[2] = typedArray.getDimension(R.styleable.ColorRelativeLayout_rightTopCorner, 0);
            cornerRadiusAll[3] = cornerRadiusAll[2];
            cornerRadiusAll[4] = typedArray.getDimension(R.styleable.ColorRelativeLayout_rightBottomCorner, 0);
            cornerRadiusAll[5] = cornerRadiusAll[4];
            cornerRadiusAll[6] = typedArray.getDimension(R.styleable.ColorRelativeLayout_leftBottomCorner, 0);
            cornerRadiusAll[7] = cornerRadiusAll[6];
        }
    }

    public ColorStateList getSecondPro() {
//        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[] colors = new int[]{android.R.color.background_dark, android.R.color.background_dark
                , android.R.color.background_dark, android.R.color.background_dark, android.R.color.background_dark
                , android.R.color.background_dark};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        return colorStateList;
    }

    public float[] getCornerRadius() {
        return cornerRadiusAll;
    }

    private int getShapeWidth() {
        Log.e("TAG", measuredWidth + "getShapeWidth: " + measuredHeight);
        return measuredWidth > measuredHeight ? measuredHeight : measuredWidth;
    }

    private Drawable createBackgroundCompat() {
        Log.e("TAG", "createBackgroundCompat: ");

        colorableBackgroundDrawableCompat = new GradientDrawable();
        rippleDrawableCompat = new GradientDrawable();
        if (circularCorner) {
            colorableBackgroundDrawableCompat.setShape(GradientDrawable.OVAL);
            colorableBackgroundDrawableCompat.setSize(getShapeWidth(), getShapeWidth());
            rippleDrawableCompat.setShape(GradientDrawable.OVAL);
            rippleDrawableCompat.setSize(getShapeWidth(), getShapeWidth());

        } else {
            colorableBackgroundDrawableCompat.setCornerRadii(cornerRadiusAll);
            rippleDrawableCompat.setCornerRadii(cornerRadiusAll);
        }

//        colorableBackgroundDrawableCompat.setCornerRadius((float) cornerRadius);
        colorableBackgroundDrawableCompat.setColor(-1);
        tintableBackgroundDrawableCompat = DrawableCompat.wrap(colorableBackgroundDrawableCompat);
        DrawableCompat.setTintList(this.tintableBackgroundDrawableCompat, backgroundTint);
//        if (backgroundTintMode != null) {
//            DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, this.backgroundTintMode);
//        }
//        rippleDrawableCompat.setCornerRadius((float) cornerRadius);
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
//        左上角，右上角，右下角，左下

        backgroundDrawableLollipop = new GradientDrawable();
        maskDrawableLollipop = new GradientDrawable();
        if (circularCorner) {
            backgroundDrawableLollipop.setShape(GradientDrawable.OVAL);
            Log.e("TAG", "createBackgroundLollipop: " + getShapeWidth());
            backgroundDrawableLollipop.setSize(getShapeWidth(), getShapeWidth());
            maskDrawableLollipop.setShape(GradientDrawable.OVAL);
            maskDrawableLollipop.setSize(getShapeWidth(), getShapeWidth());

        } else {
            backgroundDrawableLollipop.setCornerRadii(cornerRadiusAll);
            maskDrawableLollipop.setCornerRadii(cornerRadiusAll);
        }
//        backgroundDrawableLollipop.setCornerRadius((float) cornerRadius);


        backgroundDrawableLollipop.setColor(backgroundTint);
        backgroundDrawableLollipop.setStroke(strokeWidth, strokeColor);
        updateTintAndTintModeLollipop();
//        return backgroundDrawableLollipop;
//        strokeDrawableLollipop = new GradientDrawable();
//        strokeDrawableLollipop.setCornerRadius((float) this.cornerRadius );
//        strokeDrawableLollipop.setColor(0);
//        strokeDrawableLollipop.setStroke(strokeWidth, strokeColor);
//        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, strokeDrawableLollipop});
//        InsetDrawable bgInsetDrawable = wrapDrawableWithInset(layerDrawable);

//        maskDrawableLollipop.setCornerRadius((float) this.cornerRadius);

        maskDrawableLollipop.setColor(-1);
//        return backgroundDrawableLollipop;
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), backgroundDrawableLollipop, maskDrawableLollipop);
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
