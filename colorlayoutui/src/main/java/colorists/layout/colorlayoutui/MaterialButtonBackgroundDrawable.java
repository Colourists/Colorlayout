package colorists.layout.colorlayoutui;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@TargetApi(21)
class MaterialButtonBackgroundDrawable extends RippleDrawable {

    MaterialButtonBackgroundDrawable(@NonNull ColorStateList color, @Nullable GradientDrawable content, @Nullable Drawable mask) {
        super(color, content, mask);
    }

//    public void setColorFilter(ColorFilter colorFilter) {
//        if (this.getDrawable(0) != null) {
//            GradientDrawable insetDrawable = (GradientDrawable) this.getDrawable(0);
////            LayerDrawable layerDrawable = (LayerDrawable) insetDrawable.getDrawable();
//            GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.getDrawable(0);
//            gradientDrawable.setColorFilter(colorFilter);
//        }
//
//    }
}
