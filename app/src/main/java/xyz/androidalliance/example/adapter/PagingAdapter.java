package xyz.androidalliance.example.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import xyz.androidalliance.base.RichView;
import xyz.androidalliance.example.screens.ScreenZeroRichView;

import static xyz.androidalliance.example.screens.ScreenZeroRichView.BACKGROUND_COLOR;
import static xyz.androidalliance.example.screens.ScreenZeroRichView.POSITION;

public class PagingAdapter extends PagerAdapter {

    private String VIEW_STATES = "VIEW_STATES";
    private View currentView;
    private int count = 4;
    private SparseArray<Parcelable> viewStates = new SparseArray<>(count);

    public PagingAdapter() {
    }

    public RichView getCurrentView() {
        return (RichView) currentView;
    }

    public View getItem(ViewGroup container, int position) {
        final Context context = container.getContext();
        View view = null;
        switch (position) {
            default:
                view = new ScreenZeroRichView(context);
                bindView((ScreenZeroRichView) view, position);
        }
        return view;
    }

    private void bindView(ScreenZeroRichView view, int position) {
        // example setting properties:
        Bundle properties = new Bundle();
        properties.putInt(POSITION, position);
        properties.putInt(BACKGROUND_COLOR, Color.GRAY);
        view.setProperties(properties);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getItem(container, position);
        if (view instanceof RichView) {
            ((RichView) view).onRestoreInstanceState(viewStates.get(position));
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentView = (View) object;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public Bundle saveState(ViewGroup container) {
        Bundle outState = new Bundle();
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = container.getChildAt(i);
            if (view instanceof RichView) {
                Parcelable viewState = ((RichView) view).onSaveInstanceState();
                viewStates.put(i, viewState);
            }

        }
        outState.putSparseParcelableArray(VIEW_STATES, viewStates);
        return outState;
    }

    public void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(VIEW_STATES)) {
            viewStates = savedInstanceState.getSparseParcelableArray(VIEW_STATES);
        } else {
            viewStates = new SparseArray<>(count);
        }
    }
}