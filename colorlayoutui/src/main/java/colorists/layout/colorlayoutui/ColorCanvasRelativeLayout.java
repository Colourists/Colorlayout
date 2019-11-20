package colorists.layout.colorlayoutui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import static android.graphics.BlurMaskFilter.Blur.OUTER;

public class ColorCanvasRelativeLayout extends RelativeLayout {

    private Path mClipPath;
    private Path mClipPathStro;

    public ColorCanvasRelativeLayout(Context context) {
        super(context);

    }

    public ColorCanvasRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorRelativeLayout);
//        // 颜色数组
//        int[] colors = new int[]{R.color.colorAccent, R.color.colorAccent1, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.design_default_color_primary_dark, R.color.red};
//
//// 颜色数组对应的状态
//        int[][] states = new int[6][];
//        states[0] = new int[]{android.R.attr.state_enabled};
//        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
//        states[2] = new int[]{android.R.attr.state_enabled};
//        states[3] = new int[]{android.R.attr.state_focused};
//        states[4] = new int[]{android.R.attr.state_window_focused};
//        states[5] = new int[]{};
//        ColorStateList colorList = new ColorStateList(states, colors);
////        gradientDrawable1.setColor(colorList);
//        StateListDrawable stateListDrawable = new StateListDrawable();
//        RippleDrawable rippleDrawable = getRippleDrawable(R.color.red, Color.BLUE);
//
//        stateListDrawable.addState(new int[]{android.R.attr.clickable}, rippleDrawable);
////        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, getRippleDrawable(R.color.red, Color.BLACK));
//        stateListDrawable.addState(new int[]{}, getRippleDrawable(R.color.red, Color.YELLOW));
//
//        setEnabled(true);
//        RippleDrawable rippleDrawable = getRippleDrawable(R.color.red, Color.YELLOW);
//        RippleDrawable rippleDrawable = new RippleDrawable(colorList, gradientDrawable1, gradientDrawable);
//        setBackground(stateListDrawable);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        GradientDrawable gradientDrawable1 = new GradientDrawable();
//        gradientDrawable1.setColor(backgrouncolor);
        setBackground(new RippleDrawable(RippleUtils.convertToRippleDrawableColor(AppCompatResources.getColorStateList(getContext(), R.color.red)), drawable, gradientDrawable));


//        setBackground(stateListDrawable);
        typedArray.recycle();


        init();

    }

    public ColorCanvasRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private RippleDrawable getRippleDrawable(int RippleColor, int backgrouncolor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);

        GradientDrawable gradientDrawable1 = new GradientDrawable();
        gradientDrawable1.setColor(backgrouncolor);
        return new RippleDrawable(RippleUtils.convertToRippleDrawableColor(AppCompatResources.getColorStateList(getContext(), RippleColor)), gradientDrawable1, gradientDrawable);
    }


    //
//    /**
//     * please set dp
//     */
//    public void setcornerRadius(float cornerRadius) {
//        float density = getResources().getDisplayMetrics().density;
//        this.cornerRadius = cornerRadius * density;
//        invalidate();
//    }
    private final RectF roundRect = new RectF();

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    private Paint paint = new Paint();
    private final Paint maskPaint = new Paint();
    private final Paint mPaint = new Paint();

    private void init() {


        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        setPadding(getPaddingLeft() + mStrokeWidth, getPaddingTop() + mStrokeWidth, getPaddingRight() - mStrokeWidth, getPaddingBottom() - mStrokeWidth);

//        zonePaint.setAntiAlias(true);

        mClipPath = new Path();
        mClipPathStro = new Path();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(200);
//        paint.setMaskFilter(new BlurMaskFilter(20, OUTER));
//        paint.setColor(Color.RED);
    }

    private int rad = 100;
    private float[] rids = {50.0f, 50.0f, 50.0f, 50.0f, 0.0f, 0.0f, 0.0f, 0.0f,};

    @Override
    public void draw(Canvas canvas) {
        mClipPath.addRoundRect(roundRect, rids, Path.Direction.CW);
        canvas.clipPath(mClipPath);
        super.draw(canvas);
//        mClipPathStro.addRoundRect(roundRect, rids, Path.Direction.CW);
//        mClipPathStro.

//        canvas.clipPath(mClipPathStro);
//        canvas.drawCircle(getWidth() / 2, getWidth() / 2, (getWidth() / 2) - 20, zonePaint);
//        super.draw(canvas);
//        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawRect(roundRect,zonePaint);
//        //哪个角不是圆角我再把你用矩形画出来
//        canvas.drawCircle(50, 50, 25, zonePaint);
//        canvas.drawRect(0, 0, rad, rad, zonePaint);

//        canvas.drawRect(roundRect.right - rad, 0, roundRect.right, rad, zonePaint);


//        canvas.drawRect(0, roundRect.bottom - rad, rad, roundRect.bottom, zonePaint);

//        canvas.drawRect(roundRect.right - 200, roundRect.bottom - 200, roundRect.right, roundRect.bottom, zonePaint);
//
//        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);

//        canvas.restore();

    }

    private int mStrokeWidth = 10;

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        onClipDraw(canvas);
    }

    public void onClipDraw(Canvas canvas) {
        if (mStrokeWidth > 0) {
            // 支持半透明描边，将与描边区域重叠的内容裁剪掉
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mPaint.setColor(Color.WHITE);
            mPaint.setStrokeWidth(mStrokeWidth * 2);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(mClipPath, mPaint);
            // 绘制描边
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(mClipPath, mPaint);
        }
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(mClipPath, mPaint);
        } else {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            final Path path = new Path();
            path.addRect(0, 0, (int) roundRect.width(), (int) roundRect.height(), Path.Direction.CW);
            path.op(mClipPath, Path.Op.DIFFERENCE);
            canvas.drawPath(path, mPaint);
        }
    }


}
