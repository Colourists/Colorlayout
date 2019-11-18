package colorists.layout.colorlayoutui;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import static android.graphics.BlurMaskFilter.Blur.SOLID;

public class Test1 extends RelativeLayout {

    private Paint paint;
    private Paint paint1;

    {
        // 需禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public Test1(Context context) {
        super(context);
        Log.e("TAG", "Test1: ");
    }


    public Test1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        Log.e("TAG", "Test4: ");

    }

    public Test1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.e("TAG", "Test6: ");

    }

    public Test1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e("TAG", "Test2: ");

    }

    private void init() {
        paint = new Paint();
        paint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));
//        paint.setShadowLayer(20, 0, 0, Color.GREEN);
        paint.setColor(Color.RED);

        paint1 = new Paint();
        paint1.setColor(Color.RED);

//        setPadding(getPaddingLeft() + 20, getPaddingTop() - 20, getPaddingRight() - 20, getPaddingBottom() + 20);

    }

    private final RectF roundRect = new RectF();
    private Path mClipPath = new Path();
    private float[] rids = {50.0f, 50.0f, 50.0f, 50.0f, 20.0f, 20.0f, 20.0f, 20.0f,};

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0+20, 0+20, w-20, h-20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mClipPath.addRoundRect(roundRect, rids, Path.Direction.CW);

//        canvas.clipPath(mClipPath);
        canvas.drawPath(mClipPath, paint);
        super.onDraw(canvas);
//getHeight()
//        canvas.drawRect(0, 0, 100, 100, paint);
//        canvas.save();
//        canvas.drawRect(0, 0, 100, 100, paint1);
//        canvas.drawCircle(50, 50, 50, paint1);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 20, paint);
    }
}
