package xyz.androidalliance.example.screens;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;

import xyz.androidalliance.base.RichView;
import xyz.androidalliance.example.R;

public class ScreenZeroRichView extends RichView implements ScreenZeroPresenter.ViewContract {

    public static final String BACKGROUND_COLOR = "BACKGROUND_COLOR";
    private static final String STATE_SUPER = "SUPER";

    private ScreenZeroPresenter presenter;

    public ScreenZeroRichView(Context context) {
        this(context, null);
    }

    public ScreenZeroRichView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenZeroRichView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflateLayout(context);
        presenter = ScreenZeroPresenter.newInstance();
    }

    @Override
    public void setProperties(Bundle props) {
        super.setProperties(props);
        // Example:
        setBackgroundColor(props.getInt(BACKGROUND_COLOR, Color.MAGENTA));
    }

    @Override
    protected void inflateLayout(Context context) {
        inflate(context, R.layout.richview_screen_zero, this);
        onFinishInflate();
    }

    @Override
    protected void bindViews() {

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle outState = new Bundle();
        presenter.save(outState);
        outState.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        return outState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle bundle = (Bundle) savedInstanceState;
            presenter.restore(bundle);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
        }
    }

}