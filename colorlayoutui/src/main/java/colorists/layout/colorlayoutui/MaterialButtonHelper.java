//package colorists.layout.colorlayoutui;
//
//import android.annotation.TargetApi;
//import android.content.res.ColorStateList;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.GradientDrawable;
//import android.graphics.drawable.InsetDrawable;
//import android.graphics.drawable.LayerDrawable;
//import android.graphics.drawable.RippleDrawable;
//import android.os.Build;
//import android.support.annotation.Nullable;
//import android.support.annotation.RestrictTo;
//import android.support.design.button.MaterialButton;
//import android.support.design.internal.ViewUtils;
//import android.support.design.resources.MaterialResources;
//import android.support.design.ripple.RippleUtils;
//import android.support.v4.graphics.drawable.DrawableCompat;
//import android.support.v4.view.ViewCompat;
//
//@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
//class MaterialButtonHelper {
//    private static final float CORNER_RADIUS_ADJUSTMENT = 1.0E-5F;
//    private static final int DEFAULT_BACKGROUND_COLOR = -1;
//    private static final boolean IS_LOLLIPOP;
//    private final MaterialButton materialButton;
//    private int insetLeft;
//    private int insetRight;
//    private int insetTop;
//    private int insetBottom;
//    private int cornerRadius;
//    private int strokeWidth;
//    @Nullable
//    private PorterDuff.Mode backgroundTintMode;
//    @Nullable
//    private ColorStateList backgroundTint;
//    @Nullable
//    private ColorStateList strokeColor;
//    @Nullable
//    private ColorStateList rippleColor;
//    private final Paint buttonStrokePaint = new Paint(1);
//    private final Rect bounds = new Rect();
//    private final RectF rectF = new RectF();
//    @Nullable
//    private GradientDrawable colorableBackgroundDrawableCompat;
//    @Nullable
//    private Drawable tintableBackgroundDrawableCompat;
//    @Nullable
//    private GradientDrawable rippleDrawableCompat;
//    @Nullable
//    private Drawable tintableRippleDrawableCompat;
//    @Nullable
//    private GradientDrawable backgroundDrawableLollipop;
//    @Nullable
//    private GradientDrawable strokeDrawableLollipop;
//    @Nullable
//    private GradientDrawable maskDrawableLollipop;
//    private boolean backgroundOverwritten = false;
//
//    public MaterialButtonHelper(MaterialButton button) {
//        this.materialButton = button;
//    }
//
//    public void loadFromAttributes(TypedArray attributes) {
//        this.insetLeft = attributes.getDimensionPixelOffset(styleable.MaterialButton_android_insetLeft, 0);
//        this.insetRight = attributes.getDimensionPixelOffset(styleable.MaterialButton_android_insetRight, 0);
//        this.insetTop = attributes.getDimensionPixelOffset(styleable.MaterialButton_android_insetTop, 0);
//        this.insetBottom = attributes.getDimensionPixelOffset(styleable.MaterialButton_android_insetBottom, 0);
//        this.cornerRadius = attributes.getDimensionPixelSize(styleable.MaterialButton_cornerRadius, 0);
//        this.strokeWidth = attributes.getDimensionPixelSize(styleable.MaterialButton_strokeWidth, 0);
//        this.backgroundTintMode = ViewUtils.parseTintMode(attributes.getInt(styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
//        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), attributes, styleable.MaterialButton_backgroundTint);
//        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), attributes, styleable.MaterialButton_strokeColor);
//        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), attributes, styleable.MaterialButton_rippleColor);
//        this.buttonStrokePaint.setStyle(Paint.Style.STROKE);
//        this.buttonStrokePaint.setStrokeWidth((float)this.strokeWidth);
//        this.buttonStrokePaint.setColor(this.strokeColor != null ? this.strokeColor.getColorForState(this.materialButton.getDrawableState(), 0) : 0);
//        int paddingStart = ViewCompat.getPaddingStart(this.materialButton);
//        int paddingTop = this.materialButton.getPaddingTop();
//        int paddingEnd = ViewCompat.getPaddingEnd(this.materialButton);
//        int paddingBottom = this.materialButton.getPaddingBottom();
//        this.materialButton.setInternalBackground(IS_LOLLIPOP ? this.createBackgroundLollipop() : this.createBackgroundCompat());
//        ViewCompat.setPaddingRelative(this.materialButton, paddingStart + this.insetLeft, paddingTop + this.insetTop, paddingEnd + this.insetRight, paddingBottom + this.insetBottom);
//    }
//
//    void setBackgroundOverwritten() {
//        this.backgroundOverwritten = true;
//        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
//        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
//    }
//
//    boolean isBackgroundOverwritten() {
//        return this.backgroundOverwritten;
//    }
//
//    void drawStroke(@Nullable Canvas canvas) {
//        if (canvas != null && this.strokeColor != null && this.strokeWidth > 0) {
//            this.bounds.set(this.materialButton.getBackground().getBounds());
//            this.rectF.set((float)this.bounds.left + (float)this.strokeWidth / 2.0F + (float)this.insetLeft, (float)this.bounds.top + (float)this.strokeWidth / 2.0F + (float)this.insetTop, (float)this.bounds.right - (float)this.strokeWidth / 2.0F - (float)this.insetRight, (float)this.bounds.bottom - (float)this.strokeWidth / 2.0F - (float)this.insetBottom);
//            float strokeCornerRadius = (float)this.cornerRadius - (float)this.strokeWidth / 2.0F;
//            canvas.drawRoundRect(this.rectF, strokeCornerRadius, strokeCornerRadius, this.buttonStrokePaint);
//        }
//
//    }
//
//    private Drawable createBackgroundCompat() {
//        this.colorableBackgroundDrawableCompat = new GradientDrawable();
//        this.colorableBackgroundDrawableCompat.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
//        this.colorableBackgroundDrawableCompat.setColor(-1);
//        this.tintableBackgroundDrawableCompat = DrawableCompat.wrap(this.colorableBackgroundDrawableCompat);
//        DrawableCompat.setTintList(this.tintableBackgroundDrawableCompat, this.backgroundTint);
//        if (this.backgroundTintMode != null) {
//            DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, this.backgroundTintMode);
//        }
//
//        this.rippleDrawableCompat = new GradientDrawable();
//        this.rippleDrawableCompat.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
//        this.rippleDrawableCompat.setColor(-1);
//        this.tintableRippleDrawableCompat = DrawableCompat.wrap(this.rippleDrawableCompat);
//        DrawableCompat.setTintList(this.tintableRippleDrawableCompat, this.rippleColor);
//        return this.wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.tintableBackgroundDrawableCompat, this.tintableRippleDrawableCompat}));
//    }
//
//    private InsetDrawable wrapDrawableWithInset(Drawable drawable) {
//        return new InsetDrawable(drawable, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
//    }
//
//    void setSupportBackgroundTintList(@Nullable ColorStateList tintList) {
//        if (this.backgroundTint != tintList) {
//            this.backgroundTint = tintList;
//            if (IS_LOLLIPOP) {
//                this.updateTintAndTintModeLollipop();
//            } else if (this.tintableBackgroundDrawableCompat != null) {
//                DrawableCompat.setTintList(this.tintableBackgroundDrawableCompat, this.backgroundTint);
//            }
//        }
//
//    }
//
//    ColorStateList getSupportBackgroundTintList() {
//        return this.backgroundTint;
//    }
//
//    void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
//        if (this.backgroundTintMode != mode) {
//            this.backgroundTintMode = mode;
//            if (IS_LOLLIPOP) {
//                this.updateTintAndTintModeLollipop();
//            } else if (this.tintableBackgroundDrawableCompat != null && this.backgroundTintMode != null) {
//                DrawableCompat.setTintMode(this.tintableBackgroundDrawableCompat, this.backgroundTintMode);
//            }
//        }
//
//    }
//
//    PorterDuff.Mode getSupportBackgroundTintMode() {
//        return this.backgroundTintMode;
//    }
//
//    private void updateTintAndTintModeLollipop() {
//        if (this.backgroundDrawableLollipop != null) {
//            DrawableCompat.setTintList(this.backgroundDrawableLollipop, this.backgroundTint);
//            if (this.backgroundTintMode != null) {
//                DrawableCompat.setTintMode(this.backgroundDrawableLollipop, this.backgroundTintMode);
//            }
//        }
//
//    }
//
//    @TargetApi(21)
//    private Drawable createBackgroundLollipop() {
//        this.backgroundDrawableLollipop = new GradientDrawable();
//        this.backgroundDrawableLollipop.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
//        this.backgroundDrawableLollipop.setColor(-1);
//        this.updateTintAndTintModeLollipop();
//        this.strokeDrawableLollipop = new GradientDrawable();
//        this.strokeDrawableLollipop.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
//        this.strokeDrawableLollipop.setColor(0);
//        this.strokeDrawableLollipop.setStroke(this.strokeWidth, this.strokeColor);
//        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, this.strokeDrawableLollipop});
//        InsetDrawable bgInsetDrawable = this.wrapDrawableWithInset(layerDrawable);
//        this.maskDrawableLollipop = new GradientDrawable();
//        this.maskDrawableLollipop.setCornerRadius((float)this.cornerRadius + 1.0E-5F);
//        this.maskDrawableLollipop.setColor(-1);
//        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), bgInsetDrawable, this.maskDrawableLollipop);
//    }
//
//    void updateMaskBounds(int height, int width) {
//        if (this.maskDrawableLollipop != null) {
//            this.maskDrawableLollipop.setBounds(this.insetLeft, this.insetTop, width - this.insetRight, height - this.insetBottom);
//        }
//
//    }
//
//    void setBackgroundColor(int color) {
//        if (IS_LOLLIPOP && this.backgroundDrawableLollipop != null) {
//            this.backgroundDrawableLollipop.setColor(color);
//        } else if (!IS_LOLLIPOP && this.colorableBackgroundDrawableCompat != null) {
//            this.colorableBackgroundDrawableCompat.setColor(color);
//        }
//
//    }
//
//    void setRippleColor(@Nullable ColorStateList rippleColor) {
//        if (this.rippleColor != rippleColor) {
//            this.rippleColor = rippleColor;
//            if (IS_LOLLIPOP && this.materialButton.getBackground() instanceof RippleDrawable) {
//                ((RippleDrawable)this.materialButton.getBackground()).setColor(rippleColor);
//            } else if (!IS_LOLLIPOP && this.tintableRippleDrawableCompat != null) {
//                DrawableCompat.setTintList(this.tintableRippleDrawableCompat, rippleColor);
//            }
//        }
//
//    }
//
//    @Nullable
//    ColorStateList getRippleColor() {
//        return this.rippleColor;
//    }
//
//    void setStrokeColor(@Nullable ColorStateList strokeColor) {
//        if (this.strokeColor != strokeColor) {
//            this.strokeColor = strokeColor;
//            this.buttonStrokePaint.setColor(strokeColor != null ? strokeColor.getColorForState(this.materialButton.getDrawableState(), 0) : 0);
//            this.updateStroke();
//        }
//
//    }
//
//    @Nullable
//    ColorStateList getStrokeColor() {
//        return this.strokeColor;
//    }
//
//    void setStrokeWidth(int strokeWidth) {
//        if (this.strokeWidth != strokeWidth) {
//            this.strokeWidth = strokeWidth;
//            this.buttonStrokePaint.setStrokeWidth((float)strokeWidth);
//            this.updateStroke();
//        }
//
//    }
//
//    int getStrokeWidth() {
//        return this.strokeWidth;
//    }
//
//    private void updateStroke() {
//        if (IS_LOLLIPOP && this.strokeDrawableLollipop != null) {
//            this.materialButton.setInternalBackground(this.createBackgroundLollipop());
//        } else if (!IS_LOLLIPOP) {
//            this.materialButton.invalidate();
//        }
//
//    }
//
//    void setCornerRadius(int cornerRadius) {
//        if (this.cornerRadius != cornerRadius) {
//            this.cornerRadius = cornerRadius;
//            if (IS_LOLLIPOP && this.backgroundDrawableLollipop != null && this.strokeDrawableLollipop != null && this.maskDrawableLollipop != null) {
//                if (Build.VERSION.SDK_INT == 21) {
//                    this.unwrapBackgroundDrawable().setCornerRadius((float)cornerRadius + 1.0E-5F);
//                    this.unwrapStrokeDrawable().setCornerRadius((float)cornerRadius + 1.0E-5F);
//                }
//
//                this.backgroundDrawableLollipop.setCornerRadius((float)cornerRadius + 1.0E-5F);
//                this.strokeDrawableLollipop.setCornerRadius((float)cornerRadius + 1.0E-5F);
//                this.maskDrawableLollipop.setCornerRadius((float)cornerRadius + 1.0E-5F);
//            } else if (!IS_LOLLIPOP && this.colorableBackgroundDrawableCompat != null && this.rippleDrawableCompat != null) {
//                this.colorableBackgroundDrawableCompat.setCornerRadius((float)cornerRadius + 1.0E-5F);
//                this.rippleDrawableCompat.setCornerRadius((float)cornerRadius + 1.0E-5F);
//                this.materialButton.invalidate();
//            }
//        }
//
//    }
//
//    int getCornerRadius() {
//        return this.cornerRadius;
//    }
//
//    @Nullable
//    private GradientDrawable unwrapStrokeDrawable() {
//        if (IS_LOLLIPOP && this.materialButton.getBackground() != null) {
//            RippleDrawable background = (RippleDrawable)this.materialButton.getBackground();
//            InsetDrawable insetDrawable = (InsetDrawable)background.getDrawable(0);
//            LayerDrawable layerDrawable = (LayerDrawable)insetDrawable.getDrawable();
//            return (GradientDrawable)layerDrawable.getDrawable(1);
//        } else {
//            return null;
//        }
//    }
//
//    @Nullable
//    private GradientDrawable unwrapBackgroundDrawable() {
//        if (IS_LOLLIPOP && this.materialButton.getBackground() != null) {
//            RippleDrawable background = (RippleDrawable)this.materialButton.getBackground();
//            InsetDrawable insetDrawable = (InsetDrawable)background.getDrawable(0);
//            LayerDrawable layerDrawable = (LayerDrawable)insetDrawable.getDrawable();
//            return (GradientDrawable)layerDrawable.getDrawable(0);
//        } else {
//            return null;
//        }
//    }
//
//    static {
//        IS_LOLLIPOP = Build.VERSION.SDK_INT >= 21;
//    }
//}
