package colorists.layout.colorlayoutui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import static android.graphics.BlurMaskFilter.Blur.OUTER;
import static android.graphics.BlurMaskFilter.Blur.SOLID;

public class ColorRelativeLayout extends RelativeLayout {

    private ColouristsUiHelper colouristsUiHelper;

    public ColorRelativeLayout(Context context) {
        super(context);

    }

    public ColorRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG", "ColorRelativeLayout: ");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorRelativeLayout);
        colouristsUiHelper = new ColouristsUiHelper(this);
        colouristsUiHelper.loadFromAttributes(typedArray);
        typedArray.recycle();
        init();

    }

    public ColorRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        Log.e("TAG", "setBackgroundDrawable: ");
        if (isUsingOriginalBackground()) {
            if (background != this.getBackground()) {
                Log.i("MaterialButton", "Setting a custom background is not supported.");
                this.colouristsUiHelper.setBackgroundOverwritten();
                super.setBackgroundDrawable(background);
            } else {
                this.getBackground().setState(background.getState());
            }
        } else {
            super.setBackgroundDrawable(background);
        }
//        setBackground(background);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }


    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
    }

    @Override
    public void setBackground(Drawable background) {
        setBackgroundDrawable(background);
    }

    void setInternalBackground(Drawable background) {
        setBackgroundDrawable(background);
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

    //
    private Paint paint = new Paint();

    protected void init() {
        paint.setMaskFilter(new BlurMaskFilter(20, OUTER));
        paint.setColor(Color.RED);
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.saveLayer(roundRect, paint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawRoundRect(roundRect, colouristsUiHelper.getCornerRadius(), colouristsUiHelper.getCornerRadius(), paint);
//        canvas.save();
        //哪个角不是圆角我再把你用矩形画出来
//        canvas.drawRect(0, 0, cornerRadius, cornerRadius, zonePaint);

//        canvas.saveLayer(roundRect, paint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
//        canvas.restore();
    }

    private boolean isUsingOriginalBackground() {
        return this.colouristsUiHelper != null && !this.colouristsUiHelper.isBackgroundOverwritten();
    }
}
