package colorists.layout.colorlayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
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
import android.view.View;
import android.widget.RelativeLayout;

import colorists.layout.colorlayoutui.ColorRelativeLayout;
import colorists.layout.colorlayoutui.RippleUtils;

public class MainActivity extends AppCompatActivity {

    private MaterialButton viewById;
    private ColorRelativeLayout re;
    private RelativeLayout s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.blocking);
        re = findViewById(R.id.re);
        s = findViewById(R.id.save_scale_type);
//        re.setBackgroundDrawable();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void OnClick(View view) {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(20);
//        gradientDrawable.setStroke(10, Color.GRAY);
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.colorAccent);

        DrawableCompat.setTintList(gradientDrawable, colorStateList);
        GradientDrawable gradientDrawable1 = new GradientDrawable();
        gradientDrawable1.setColor(-1);
        gradientDrawable1.setCornerRadius(20);
//        gradientDrawable1.setStroke(10, Color.GRAY);
//        ColorStateList colorStateList = getColorStateList();
        ColorStateList colorStateList1 = AppCompatResources.getColorStateList(this, R.color.colorAccent1);
        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(colorStateList1), gradientDrawable, gradientDrawable1);
        s.setBackground(rippleDrawable);
    }

}
