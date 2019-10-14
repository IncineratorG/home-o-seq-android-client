package com.touristskaya.homeoseqandroidclient.common.types.reactive;

/**
 * TODO: Add a class header comment
 */
public class ObservableProperty<T> extends Observable {
    private T mProperty;

    public ObservableProperty() {

    }

    public ObservableProperty(T value) {
        mProperty = value;
    }

    public void set(T value) {
        mProperty = value;
        notifySubscribers();
    }

    public T get() {
        return mProperty;
    }
}
