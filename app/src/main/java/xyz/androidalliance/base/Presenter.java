package xyz.androidalliance.base;

import xyz.androidalliance.interfaces.View;

public abstract class Presenter<T extends View> {

    abstract public void attachView(T view);

    abstract public void detachView();

}