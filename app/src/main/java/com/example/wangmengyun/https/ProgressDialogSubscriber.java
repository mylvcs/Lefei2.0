package com.example.wangmengyun.https;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.wangmengyun.activity.MyFavouriteActivity;
import com.example.wangmengyun.https.Entity.FavoriteGoodsEntity;

import java.net.SocketTimeoutException;
import java.util.List;

import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/5/2.
 */

public abstract class ProgressDialogSubscriber<T> extends Subscriber<T>{


        private Context mContext;
        private ProgressDialog mDialog;

        public ProgressDialogSubscriber(Context context){
            this.mContext = context;

    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();

    }

    private void dismissProgressDialog(){
            if(mDialog!= null&& mDialog.isShowing()){
                mDialog.dismiss();
                mDialog = null;
            }

    }


    @Override
    public void onStart() {
        showProgressDialog();
    }

    private void showProgressDialog() {

        if(mDialog==null){
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(true);

            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    ProgressDialogSubscriber.this.unsubscribe();
                }
            });
        }
        if(mDialog!= null&& !mDialog.isShowing()){
            mDialog.show();
        }
    }

    @Override
    public void onError(Throwable e) {

        if(e instanceof SocketTimeoutException){
            Toast.makeText(mContext,"Internet down", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext,"error",Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }


}
