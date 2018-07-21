package com.trial.mapdemo.facade.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.trial.mapdemo.facade.callback.GenericTextWatcherCallback;

import java.util.List;

public class GenericTextWatcher implements TextWatcher {
    private View view;
    private GenericTextWatcherCallback genericTextWatcherCallback;

    public GenericTextWatcher(View view, GenericTextWatcherCallback genericTextWatcherCallback) {
        this.view = view;
        this.genericTextWatcherCallback = genericTextWatcherCallback;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        genericTextWatcherCallback.getUpdate(s.toString(), view);
    }
}
