package com.neurological.todd.utility;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by bhavdip on 8/21/16.
 */
public class AgeInputDetector implements InputFilter {

    private final int min;
    private final int max;

    public AgeInputDetector(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dstart, int dend) {
        try {
            int input = Integer.parseInt(spanned.toString() + charSequence.toString());
            if (isInDefineRange(min, max, input))
                return null;

        } catch (Exception e) {
        }
        return "";
    }

    private boolean isInDefineRange(int min, int max, int c) {
        return max > min ? c >= min && c <= max : c >= max && c <= min;
    }
}
