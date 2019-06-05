package com.henley.smartadapter.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.text.util.LinkifyCompat;
import androidx.core.widget.ImageViewCompat;

/**
 * {@link View}辅助类(常用方法)
 *
 * @author Henley
 * @date 2017/8/1 15:59
 */
public final class ViewHolder {

    private View mItemView;
    private Context mContext;
    private SparseArray<View> mViews;

    /**
     * 创建{@link ViewHolder}
     *
     * @param itemView ItemView对象
     */
    public static ViewHolder creat(View itemView) {
        return new ViewHolder(itemView);
    }

    private ViewHolder(View itemView) {
        this.mItemView = itemView;
        this.mViews = new SparseArray<>();
        this.mContext = mItemView.getContext();
    }

    /**
     * 返回{@link Context}对象
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 返回{@link View}对象(ItemView)
     */
    public View getItemView() {
        return mItemView;
    }

    public SparseArray<View> getChildViews() {
        return mViews;
    }

    /**
     * 通过控件的资源ID获取控件
     *
     * @param viewId 控件的资源ID
     */
    public final <ViewType extends View> ViewType getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (ViewType) view;
    }


    /****以下为辅助方法*****/

    /**
     * 为{@link TextView}设置文本
     */
    public ViewHolder setText(@IdRes int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 为{@link TextView}设置文本
     */
    public ViewHolder setText(@IdRes int viewId, CharSequence text, TextView.BufferType type) {
        TextView textView = getView(viewId);
        textView.setText(text, type);
        return this;
    }

    /**
     * 为{@link TextView}设置文本
     */
    public ViewHolder setText(@IdRes int viewId, char[] text, int start, int len) {
        TextView textView = getView(viewId);
        textView.setText(text, start, len);
        return this;
    }

    /**
     * 为{@link TextView}设置文本
     */
    public ViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = getView(viewId);
        textView.setText(resId);
        return this;
    }

    /**
     * 为{@link TextView}设置文本
     */
    public ViewHolder setText(@IdRes int viewId, @StringRes int resId, TextView.BufferType type) {
        TextView textView = getView(viewId);
        textView.setText(resId, type);
        return this;
    }

    /**
     * 为{@link TextView}设置文本为空时显示的文本
     */
    public ViewHolder setHint(@IdRes int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setHint(text);
        return this;
    }

    /**
     * 为{@link TextView}设置文本为空时显示的文本
     */
    public ViewHolder setHint(@IdRes int viewId, @StringRes int resId) {
        TextView textView = getView(viewId);
        textView.setHint(resId);
        return this;
    }

    /**
     * 为{@link TextView}设置文本颜色
     */
    public ViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 为{@link TextView}设置文本颜色
     */
    public ViewHolder setTextColor(@IdRes int viewId, ColorStateList colors) {
        TextView view = getView(viewId);
        view.setTextColor(colors);
        return this;
    }

    /**
     * 为{@link TextView}设置文本颜色
     */
    public ViewHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext, textColorRes));
        return this;
    }

    /**
     * 为{@link TextView}设置输入类型
     */
    public ViewHolder setInputType(@IdRes int viewId, int type) {
        TextView textView = getView(viewId);
        textView.setInputType(type);
        return this;
    }

    /**
     * 为{@link TextView}设置输入法窗口中的回车键的功能
     */
    public ViewHolder setImeOptions(@IdRes int viewId, int imeOptions) {
        TextView textView = getView(viewId);
        textView.setImeOptions(imeOptions);
        return this;
    }

    /**
     * 为{@link TextView}设置超链接
     */
    public ViewHolder linkify(@IdRes int viewId, @LinkifyCompat.LinkifyMask int mask) {
        TextView textView = getView(viewId);
        Linkify.addLinks(textView, mask);
        return this;
    }

    /**
     * 为{@link TextView}设置文本字体
     */
    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        if (viewIds != null && viewIds.length > 0) {
            for (int viewId : viewIds) {
                TextView textView = getView(viewId);
                textView.setTypeface(typeface);
                textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
        }
        return this;
    }

    /**
     * 为{@link ImageView}设置图片
     */
    public ViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 为{@link ImageView}设置图片
     */
    public ViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 为{@link ImageView}设置图片
     */
    public ViewHolder setImageDrawable(@IdRes int viewId, @Nullable Drawable drawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     * 为{@link ImageView}设置图片
     */
    public ViewHolder setImageURI(@IdRes int viewId, @Nullable Uri uri) {
        ImageView imageView = getView(viewId);
        imageView.setImageURI(uri);
        return this;
    }

    /**
     * 为{@link ImageView}设置图片
     */
    @TargetApi(Build.VERSION_CODES.M)
    public ViewHolder setImageIcon(@IdRes int viewId, @Nullable Icon icon) {
        ImageView imageView = getView(viewId);
        imageView.setImageIcon(icon);
        return this;
    }

    /**
     * 为{@link ImageView}设置渲染颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewHolder setImageTintList(@IdRes int viewId, @Nullable ColorStateList tint) {
        ImageView imageView = getView(viewId);
        ImageViewCompat.setImageTintList(imageView, tint);
        return this;
    }

    /**
     * 为{@link ImageView}设置混合模式
     */
    public ViewHolder setImageTintMode(@IdRes int viewId, @Nullable PorterDuff.Mode tintMode) {
        ImageView imageView = getView(viewId);
        ImageViewCompat.setImageTintMode(imageView, tintMode);
        return this;
    }

    /**
     * 为{@link ImageView}设置背景颜色
     */
    public ViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为{@link ImageView}设置背景图片
     */
    public ViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 为{@link ImageView}设置背景图片
     */
    public ViewHolder setBackground(@IdRes int viewId, Drawable background) {
        View view = getView(viewId);
        view.setBackground(background);
        return this;
    }

    /**
     * 为{@link ImageView}设置背景图片
     */
    @Deprecated
    public ViewHolder setBackgroundDrawable(@IdRes int viewId, Drawable background) {
        View view = getView(viewId);
        view.setBackgroundDrawable(background);
        return this;
    }

    /**
     * 为{@link View}设置可视状态
     */
    public ViewHolder setVisibility(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 为{@link View}设置可视状态
     */
    public ViewHolder setVisibility(@IdRes int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 为{@link View}设置启用状态
     */
    public ViewHolder setEnabled(@IdRes int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 为{@link View}设置按下状态
     */
    public ViewHolder setPressed(@IdRes int viewId, boolean pressed) {
        View view = getView(viewId);
        view.setPressed(pressed);
        return this;
    }

    /**
     * 为{@link View}设置选择状态
     */
    public ViewHolder setSelected(@IdRes int viewId, boolean selected) {
        View view = getView(viewId);
        view.setSelected(selected);
        return this;
    }

    /**
     * 为{@link View}设置激活状态
     */
    public ViewHolder setActivated(@IdRes int viewId, boolean activated) {
        View view = getView(viewId);
        view.setActivated(activated);
        return this;
    }

    /**
     * 为{@link View}设置是否可以获得焦点
     */
    public ViewHolder setFocusable(@IdRes int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }

    /**
     * 为{@link View}设置在触摸模式下是否可以获得焦点
     */
    public ViewHolder setFocusableInTouchMode(@IdRes int viewId, boolean focusableInTouchMode) {
        View view = getView(viewId);
        view.setFocusableInTouchMode(focusableInTouchMode);
        return this;
    }

    /**
     * 为{@link View}设置是否允许点击事件
     */
    public ViewHolder setClickable(@IdRes int viewId, boolean clickable) {
        View view = getView(viewId);
        view.setClickable(clickable);
        return this;
    }

    /**
     * 为{@link View}设置是否允许长按事件
     */
    public ViewHolder setLongClickable(@IdRes int viewId, boolean longClickable) {
        View view = getView(viewId);
        view.setLongClickable(longClickable);
        return this;
    }

    /**
     * 为{@link ProgressBar}设置进度
     */
    public ViewHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar progressBar = getView(viewId);
        progressBar.setProgress(progress);
        return this;
    }

    /**
     * 为{@link ProgressBar}设置进度
     */
    @TargetApi(Build.VERSION_CODES.N)
    public ViewHolder setProgress(@IdRes int viewId, int progress, boolean animate) {
        ProgressBar progressBar = getView(viewId);
        progressBar.setProgress(progress, animate);
        return this;
    }

    /**
     * 为{@link ProgressBar}设置最大值
     */
    public ViewHolder setMax(@IdRes int viewId, int max) {
        ProgressBar progressBar = getView(viewId);
        progressBar.setMax(max);
        return this;
    }

    /**
     * 为{@link ProgressBar}设置进度和最大值
     */
    public ViewHolder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar progressBar = getView(viewId);
        progressBar.setMax(max);
        progressBar.setProgress(progress);
        return this;
    }

    /**
     * 为{@link RatingBar}设置等级
     */
    public ViewHolder setRating(@IdRes int viewId, float rating) {
        RatingBar ratingBar = getView(viewId);
        ratingBar.setRating(rating);
        return this;
    }

    /**
     * 为{@link RatingBar}设置等级和星星的数目
     */
    public ViewHolder setRating(@IdRes int viewId, float rating, int numStars) {
        RatingBar ratingBar = getView(viewId);
        ratingBar.setRating(rating);
        ratingBar.setNumStars(numStars);
        return this;
    }

    /**
     * 为{@link Checkable}设置选中状态
     */
    public ViewHolder setChecked(@IdRes int viewId, boolean checked) {
        Checkable checkable = getView(viewId);
        checkable.setChecked(checked);
        return this;
    }

    /**
     * 为{@link View}设置透明度
     */
    public ViewHolder setAlpha(@IdRes int viewId, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        View view = getView(viewId);
        view.setAlpha(alpha);
        return this;
    }

    /**
     * 为{@link View}设置TAG
     */
    public ViewHolder setTag(@IdRes int viewId, final Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 为{@link View}设置TAG
     */
    public ViewHolder setTag(@IdRes int viewId, int key, final Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * 为{@link View}设置点击事件
     */
    public ViewHolder setOnClickListener(@IdRes int viewId, @Nullable View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 为{@link View}设置长点击事件
     */
    public ViewHolder setOnLongClickListener(@IdRes int viewId, @Nullable View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 为{@link View}设置触摸事件
     */
    public ViewHolder setOnTouchListener(@IdRes int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

}
