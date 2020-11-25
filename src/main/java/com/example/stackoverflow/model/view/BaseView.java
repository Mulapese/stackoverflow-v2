package com.example.stackoverflow.model.view;

import java.util.List;
import java.util.stream.Collectors;

public class BaseView<T> {
    private final T t;

    public BaseView(T t) {
        this.t = t;
    }

    public BaseView create() {
        return null;
    }

    public List<BaseView> getViewListFromEntityList(List<T> t) {
        return t.stream().map(question -> new BaseView(t).create()).collect(Collectors.toList());
    }
}
