package xyz.androidalliance.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

abstract public class RichView extends FrameLayout  {

    private static final String VIEW_STATE = "VIEW_STATE";
    private static final String PROPERTIES = "PROPERTIES";

    protected Bundle properties;
    private boolean attachedToWindow = false;

    public RichView(Context context) {
        super(context);
    }

    public RichView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RichView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindViews();
    }

    abstract protected void inflateLayout(Context context);

    abstract protected void bindViews();

    /**
     * Pass in any properties that you want to set on the View,
     * or Pass into another Class
     *
     * @param props
     */
    public void setProperties(Bundle props) {
        properties = props;
    }

    public Bundle getProperties() {
        return properties;
    }

    @Override
    public void onRestoreInstanceState(Parcelable savedInstanceState) {
        Bundle bundle = (Bundle) savedInstanceState;

        Bundle props = bundle.getBundle(PROPERTIES);
        if (properties == null && props != null) {
            setProperties(properties);
        }
        super.onRestoreInstanceState(bundle.getParcelable(VIEW_STATE));
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle outState = new Bundle();

        outState.putBundle(PROPERTIES, getProperties());
        outState.putParcelable(VIEW_STATE, super.onSaveInstanceState());

        return outState;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachedToWindow = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        attachedToWindow = false;
        super.onDetachedFromWindow();
    }

    public boolean isAttachedToWindowCompat() {
        return attachedToWindow;
    }
}

