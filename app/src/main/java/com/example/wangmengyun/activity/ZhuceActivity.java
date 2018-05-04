package com.example.wangmengyun.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.EventHandler;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangmengyun.Utils.NetworkUtil;
import com.example.wangmengyun.lefei.R;
import com.mob.MobSDK;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.SMSSDK;

/**
 * Created by wangmengyun on 2018/5/3.
 */

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener {
    private TimerTask timerTask;
    private Timer timer;
    private EditText ed_phone;
    private Button get_code;
    private EditText ed_code;
    private Button check_code;
    private int TIME = 60;
    public String country = "86";//中国区号
    private String phone;
    private static final int CODE_REPEAT = 1; //重新发送


    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
        //    if (msg.obj.toString().equals("false"))
            if(msg.toString().equals("false"))
                toast("该号码已存在");
        }
    };

    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                get_code.setEnabled(true);
                timerTask.cancel();
                timer.cancel();
                TIME = 60;
                get_code.setText("重新发送验证码");
            } else {
                get_code.setText(TIME + "秒后重新发送验证码");
            }
        }
    };

    cn.smssdk.EventHandler eh = new cn.smssdk.EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    toast("验证成功");//处理验证成功之后的逻辑
                    Intent in = new Intent(ZhuceActivity.this, UserInfoActivity.class);
                    in.putExtra("telephone", phone);
                    startActivity(in);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    toast("获取验证码");//处理得到验证码之后的逻辑
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表，不用管
                }
            } else {
                //处理验证失败之后的逻辑
                ((Throwable) data).printStackTrace();
                String str = data.toString();
                Log.e("error", "afterEvent: " + str);
                toast("验证失败" + str);
                // ed_code.setText("");
            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuceuser);

        ed_phone = (EditText) findViewById(R.id.user_input_telephone);
        get_code = (Button) findViewById(R.id.user_getCheckpsd);
        check_code = (Button) findViewById(R.id.register_btn);
        ed_code = (EditText) findViewById(R.id.user_input_checkpsd);
        get_code.setOnClickListener(this);
        check_code.setOnClickListener(this);
        Log.e("onCreate", "发生了什么事");

        MobSDK.init(ZhuceActivity.this, "1f76acccc639f", "590f3f1283c1a357a32b8b8dfadd9c6f");
        SMSSDK.registerEventHandler(eh);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_getCheckpsd:
                phone = ed_phone.getText().toString().trim().replaceAll("/s", "");
                if (!TextUtils.isEmpty(phone)) {
                    //验证手机格式正不正确
                    String REGEX_MOBILE_SIMPLE = "[1][3578]\\d{9}";
                    Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);
                    Matcher matcher = pattern.matcher(phone);
                    if (matcher.find()) {
                        alertWarning(phone);

                        Thread t = new Thread() {
                            public void run() {
                                Message msg2 = new Message();
                                Map<String, String> map = new HashMap<>();
                                map.put("telephone", phone);
                                try {
                                    msg2.obj = NetworkUtil.postDataByURL(NetworkUtil.REGISTER_TELEPHONE_URL, map);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println(msg2.obj + "---------");
                                handler.sendMessage(msg2);
                            }
                        };
                        t.start();

                    } else {
                        ed_phone.setError("手机格式错误");
                    }
                } else {
                    ed_phone.setError("请先输入手机号");
                }
                break;

            case R.id.register_btn:
                String code = ed_code.getText().toString().trim().replaceAll("/s", "");
                if (TextUtils.isEmpty(code)) {
                    ed_code.setError("请输入验证码");
                } else {
                    SMSSDK.submitVerificationCode(country, phone, code);
                }
                break;
        }
    }

    public void alertWarning(String telephone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("将要给" + phone + "发送验证码");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SMSSDK.getVerificationCode(country, phone);
                Toast.makeText(ZhuceActivity.this, "已经发送了", Toast.LENGTH_SHORT).show();
                get_code.setEnabled(false);
                timer = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        hd.sendEmptyMessage(TIME--);
                    }
                };
                timer.schedule(timerTask, 0, 1000);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(ZhuceActivity.this, "已取消", Toast.LENGTH_SHORT);
            }

        });
        builder.create().show();
    }

    public void toast(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuceActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        // 注销回调接口registerEventHandler,否则可能造成内存泄漏。！！！！！！
        SMSSDK.unregisterEventHandler(eh);
    }
}

