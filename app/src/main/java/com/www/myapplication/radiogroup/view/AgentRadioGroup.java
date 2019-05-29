package com.www.myapplication.radiogroup.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AgentRadioGroup extends RadioGroup {

    public AgentRadioGroup(Context context) {
        super(context);
    }

    public AgentRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onViewAdded(View child) {
        if (child instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) child;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View subChild = viewGroup.getChildAt(i);
                if (subChild instanceof ViewGroup) {
                    onViewAdded(subChild);
                } else {
                    if (subChild instanceof RadioButton) {
                        super.onViewAdded(subChild);
                    }
                }
            }
        }
        if (child instanceof RadioButton) {
            super.onViewAdded(child);
        }
    }
}