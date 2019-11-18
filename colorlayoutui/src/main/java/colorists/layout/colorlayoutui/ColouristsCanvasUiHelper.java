package colorists.layout.colorlayoutui;

import android.annotation.TargetApi;
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

public class ColouristsCanvasUiHelper {

    private static final boolean IS_LOLLIPOP;

    static {
        IS_LOLLIPOP = Build.VERSION.SDK_INT >= 21;
    }

    private ColorCanvasRelativeLayout colorRelativeLayout;
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
    private int measuredHeight;
    private int measuredWidth;

    public ColouristsCanvasUiHelper(ColorCanvasRelativeLayout colorRelativeLayout) {
        this.colorRelativeLayout = colorRelativeLayout;
    }

    public void loadFromAttributes(TypedArray typedArray) {

        circularCorner = typedArray.getBoolean(R.styleable.ColorRelativeLayout_circularCorner, false);
        cornerRadius = typedArray.getDimension(R.styleable.ColorRelativeLayout_cornerRadius, -1);
        leftTopCorner = typedArray.getDimension(R.styleable.ColorRelativeLayout_leftTopCorner, 0);
        rightTopCorner = typedArray.getDimension(R.styleable.ColorRelativeLayout_rightTopCorner, 0);
        rightBottomCorner = typedArray.getDimension(R.styleable.ColorRelativeLayout_rightBottomCorner, 0);
        leftBottomCorner = typedArray.getDimension(R.styleable.ColorRelativeLayout_leftBottomCorner, 0);

        backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.ColorRelativeLayout_mBackgroundTintMode, -1), PorterDuff.Mode.SRC_IN);

        backgroundTint = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.ColorRelativeLayout_mBackgroundTint);
        rippleColor = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.ColorRelativeLayout_rippleColor);
        strokeColor = MaterialResources.getColorStateList(colorRelativeLayout.getContext(), typedArray, R.styleable.ColorRelativeLayout_strokeColor);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.ColorRelativeLayout_strokeWidth, 0);
//        getSecondPro();
        Log.e("TAG", "loadFromAttributes: ");


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

//    public float[] getCornerRadius() {
//        return cornerRadiusAll;
//    }


    public void setDraw(Canvas canvas) {

    }


}
