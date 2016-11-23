package xyz.androidalliance.example.screens;

import android.os.Bundle;

import xyz.androidalliance.base.Presenter;
import xyz.androidalliance.interfaces.View;

public class ScreenZeroPresenter extends Presenter<ScreenZeroPresenter.ViewContract> {

    private static final String STATE_PERSISTENT = "STATE_PERSISTENT";
    private static final String A_STRING = "A_STRING";

    private String aString;
    private ViewContract view;

    public static ScreenZeroPresenter newInstance() {
        return new ScreenZeroPresenter();
    }

    private ScreenZeroPresenter() {
    }

    @Override
    public void attachView(ViewContract view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    public void restore(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_PERSISTENT)) {
            Bundle persistentState = savedInstanceState.getBundle(STATE_PERSISTENT);
            aString = persistentState.getString(A_STRING);
        }
    }

    public void save(Bundle outState) {
        Bundle persistentState = new Bundle();
        persistentState.putString(A_STRING, aString);
        outState.putBundle(STATE_PERSISTENT, persistentState);
    }

    public interface ViewContract extends View {

    }
}