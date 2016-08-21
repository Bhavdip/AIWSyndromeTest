package com.neurological.todd.utility;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by bhavdip on 8/21/16.
 */
public class AdvanceTextWatcher implements TextWatcher {

    public interface onTextChangedListener {
        void onTextChanged(CharSequence charSequence);
    }

    public onTextChangedListener mOnTextChangedListener;

    public AdvanceTextWatcher(onTextChangedListener listener) {
        this.mOnTextChangedListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mOnTextChangedListener != null) {
            mOnTextChangedListener.onTextChanged(charSequence);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
