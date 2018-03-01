package com.wangmengyun.lefei2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by wangmengyun on 2018/3/1.
 */

public class TitleButton extends LinearLayout {
    private RelativeLayout mRlay;
    private Button mLeft;
    private Button mRight;
    private OnLeftClickListener onLeftClickListener;
    private OnRightClickListener onRightClickListener;
    public TitleButton(Context context) {
        super(context);
        init();
    }
    public TitleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TitleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public void init() {
        View view = View.inflate(getContext(), R.layout.view_title_button, this);
        mLeft = (Button) view.findViewById(R.id.btn_left);
        mRight = (Button) view.findViewById(R.id.btn_right);
        mRlay = (RelativeLayout) view.findViewById(R.id.rl_title_button);

        mLeft.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (onLeftClickListener != null) {
                    onLeftClickListener.onLeftClickListener();
                }
                mRight.setBackground(getResources().getDrawable(R.drawable.right_button_null_click_shape));
         //       mRight.setTextColor(getResources().getColor(R.color.kuaixiu_blue));
                mLeft.setBackground(getResources().getDrawable(R.drawable.left_button_click_shape));
                mLeft.setTextColor(getResources().getColor(R.color.white));

            }
        });
        mRight.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (onRightClickListener != null) {
                    onRightClickListener.onRightClickListener();
                }
          //      mLeft.setBackground(getResources().getDrawable(R.drawable.left_button_no_click_shape));
          //      mLeft.setTextColor(getResources().getColor(R.color.kuaixiu_blue));
          //      mRight.setBackground(getResources().getDrawable(R.drawable.right_button_click_shape));
                mRight.setTextColor(getResources().getColor(R.color.white));
            }
        });
    }
    public Button getLeftButton() {
        return mLeft;
    }
    public Button getRightButton() {
        return mRight;
    }
    /**
     * 返回左边按钮点击回调
     *
     */
    public interface OnLeftClickListener {
        public void onLeftClickListener();
    }
    /**
     * 返回右边按钮点击回调
     *
     */
    public interface OnRightClickListener {
        public void onRightClickListener();
    }
    //回调接口
    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public void setOnRightClickListener(
            OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }
}