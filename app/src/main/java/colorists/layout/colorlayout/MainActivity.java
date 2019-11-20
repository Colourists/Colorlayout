package colorists.layout.colorlayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleableRes;
import android.support.design.button.MaterialButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;

import colorists.layout.colorlayoutui.ColorCanvasRelativeLayout;
import colorists.layout.colorlayoutui.ColorRelativeLayout;
import colorists.layout.colorlayoutui.RippleUtils;
import colorists.layout.colorlayoutui.RoundRectDrawableWithShadow;

public class MainActivity extends AppCompatActivity {

    private MaterialButton viewById;
    private ColorRelativeLayout re;
    private RelativeLayout s;
    private ColorCanvasRelativeLayout byId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.blocking);
        byId = findViewById(R.id.dfd);
//        viewById.setEnabled();
        re = findViewById(R.id.re);
//        re.setOnClickListener();
        s = findViewById(R.id.save_scale_type);
//        s.setOutlineSpotShadowColor();
        s.setElevation(10);
        s.setClipToOutline(true);
        s.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 50);
//                outline.
            }
        });
//        re.setBackgroundDrawable();

//        s.setBackgroundTintList(colorList);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void OnClick(View view) {

        byId.setEnabled(false);
        Drawable background = byId.getBackground();
        if (background instanceof RippleDrawable) {
            Log.e("TAG", "OnClick: ");
        }
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setColor(-1);
//        GradientDrawable gradientDrawable1 = new GradientDrawable();
//        gradientDrawable1.setColor(Color.YELLOW);
//        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(getColorStateList(R.color.colorAccent1)), gradientDrawable1, gradientDrawable);
//        byId.setBackground(rippleDrawable);
//        Drawable background = byId.getBackground();
//        RoundRectDrawableWithShadow roundRectDrawableWithShadow = new RoundRectDrawableWithShadow(getResources(), getColorStateList(R.color.colorAccent1), 20, 20, 20);
//
//        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {
//            public void drawRoundRect(Canvas canvas, RectF bounds, float cornerRadius, Paint paint) {
//                canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, paint);
//            }
//        };
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setColor(-1);
//        gradientDrawable.setCornerRadius(20);
////        gradientDrawable.setStroke(10, Color.GRAY);
//        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.colorAccent);
//
//        DrawableCompat.setTintList(gradientDrawable, colorStateList);
//        GradientDrawable gradientDrawable1 = new GradientDrawable();
//        gradientDrawable1.setColor(-1);
//        gradientDrawable1.setCornerRadius(20);
////        gradientDrawable1.setStroke(10, Color.GRAY);
////        ColorStateList colorStateList = getColorStateList();
//        ColorStateList colorStateList1 = AppCompatResources.getColorStateList(this, R.color.colorAccent1);
//        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(colorStateList1), gradientDrawable, gradientDrawable1);
//        s.setBackground(roundRectDrawableWithShadow);
    }

}
