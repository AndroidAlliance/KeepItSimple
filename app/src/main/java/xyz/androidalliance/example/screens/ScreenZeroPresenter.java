package xyz.androidalliance.example.screens;

import android.os.Bundle;
import android.os.SystemClock;

import xyz.androidalliance.base.Presenter;
import xyz.androidalliance.example.Models.Model;
import xyz.androidalliance.interfaces.View;

public class ScreenZeroPresenter extends Presenter<ScreenZeroPresenter.ViewContract> {

    private static final String STATE_PERSISTENT = "STATE_PERSISTENT";
    private static final String RANDOM_NUMBER = "RANDOM_NUMBER";

    private final Model model;
    private long randomNumber;
    private String aString;
    private ViewContract view;

    public static ScreenZeroPresenter newInstance(Model model) {
        return new ScreenZeroPresenter(model);
    }

    private ScreenZeroPresenter(Model model) {
        this.model = model;
    }

    @Override
    public void attachView(ViewContract view) {
        this.view = view;
        populateViews();
    }

    @Override
    public void detachView() {

    }

    public void restore(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_PERSISTENT)) {
            Bundle persistentState = savedInstanceState.getBundle(STATE_PERSISTENT);
            randomNumber = persistentState.getLong(RANDOM_NUMBER, -1L);
        } else {
            randomNumber = SystemClock.currentThreadTimeMillis();
        }
    }

    public void save(Bundle outState) {
        Bundle persistentState = new Bundle();
        persistentState.putLong(RANDOM_NUMBER, randomNumber);
        outState.putBundle(STATE_PERSISTENT, persistentState);
    }

    private void populateViews() {
        view.setMessage(model.getDefaultText(view.getPosition(), randomNumber));
    }

    public interface ViewContract extends View {
        void setMessage(String message);

        int getPosition();
    }
}