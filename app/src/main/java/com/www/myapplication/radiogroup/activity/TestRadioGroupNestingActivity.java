package com.www.myapplication.radiogroup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.flexbox.FlexLine;
import com.google.android.flexbox.FlexboxLayout;
import com.www.myapplication.R;
import com.www.myapplication.radiogroup.utils.RadioGroupUtil;
import com.www.myapplication.radiogroup.view.AgentRadioGroup;
import com.www.myapplication.radiogroup.view.RecursiveRadioGroup;

import java.util.List;

public class TestRadioGroupNestingActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "TestRadioGroupNesting";
    private Button btn_test_click;
    private FlexboxLayout fl_id;
    private RecursiveRadioGroup rb_test;
    private RadioGroup rg_test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_radio_group_nexting);

        initUI();
        initData();
        initControl();
    }

    private void initUI() {
        btn_test_click = findViewById(R.id.btn_test_click);
        fl_id = findViewById(R.id.fl_id);
        rb_test = findViewById(R.id.rb_test);
        rg_test2 = findViewById(R.id.rg_test2);
    }

    private void initData() {

    }

    private void initControl() {
        btn_test_click.setOnClickListener(this);

        rg_test2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton childAt = (RadioButton) group.getChildAt(checkedId - 1);
                Log.e(TAG, "点击的     " + childAt.getText().toString());
            }
        });
        rb_test.setOnCheckedChangeListener(new RecursiveRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RecursiveRadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                int totalChild = childCount;
                RadioButton radioButton = (RadioButton) group.getCheckedItem();
                Log.e(TAG, "点击的     " + radioButton.getText().toString());
//                for (int i = 0; i < childCount; i++) {
//                    FlexboxLayout flexboxLayout = (FlexboxLayout) group.getChildAt(i);
//                    int childIndex = checkedId - totalChild - 1;
//                    totalChild += flexboxLayout.getChildCount();
//                    if (totalChild >= checkedId) {
//                        RadioButton radioButton = (RadioButton) flexboxLayout.getChildAt(childIndex);
//                        Log.e(TAG, "点击的     " + radioButton.getText().toString());
//                    }
//                }
//                Log.e(TAG, "点击的     " + totalChild);
            }
        });
    }

    int hideId = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_click:
                int sumOfCrossSize = fl_id.getSumOfCrossSize();
                Log.e(TAG, "伸缩线的大小" + sumOfCrossSize);
                List<FlexLine> flexLines = fl_id.getFlexLines();
                int flexItemCount = fl_id.getFlexItemCount();
                if (flexLines.size() > 1) {
                    hideId = flexLines.get(1).getFirstIndex();
                    for (int i = hideId; i < flexItemCount; i++) {
                        fl_id.getFlexItemAt(i).setVisibility(View.GONE);
                    }
                } else {
                    for (int i = hideId; i < flexItemCount; i++) {
                        fl_id.getFlexItemAt(i).setVisibility(View.VISIBLE);
                    }

                }

                Log.e(TAG, "行数" + flexLines.size());
                for (int i = 1; i < flexLines.size(); i++) {

                }
                for (FlexLine flexLine : flexLines) {
                    int itemCount = flexLine.getItemCount();
                    Log.e(TAG, "返回组中每一行子元素的数目" + itemCount);
                    int firstIndex = flexLine.getFirstIndex();
                    Log.e(TAG, "每一行首个视图索引" + firstIndex);
                }


                Log.e(TAG, "返回组中子元素的数目" + flexItemCount);
                View flexItemAt = fl_id.getFlexItemAt(flexItemCount);
                Log.e(TAG, "返回组中指定位置的视图" + flexItemCount);
                break;
        }
    }
}
