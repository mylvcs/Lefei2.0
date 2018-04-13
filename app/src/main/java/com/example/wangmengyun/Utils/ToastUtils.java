package com.example.wangmengyun.Utils;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.wangmengyun.adapter.CityListAdapter;

/**
 * author zaaach on 2016/1/26.
 */
public class ToastUtils {
    public static Toast mToast;

    /**
     * 显示吐司
     * @param context
     * @param message
     */
    public static void showToast(Context context, final String message){
        if (mToast == null){
            mToast = Toast.makeText( context, message, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 显示吐司
     * @param context
     * @param messageResId
     */
    public static void showToast(final AdapterView.OnItemClickListener context, final String messageResId){
        if (mToast == null){
            mToast = Toast.makeText((Context) context, messageResId, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(messageResId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
