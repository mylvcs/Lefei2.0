package com.example.wangmengyun.activity;//package com.example.wangmengyun.activity;
//
///**
// * Created by wangmengyun on 2018/3/6.
// */
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.wangmengyun.Utils.MD5Utils;
//
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;
//
//
//
//public class LoginActivity extends AppCompatActivity {
//    private TextView tv_main_title;
//    private TextView tv_back,tv_register,tv_find_psw;
//    private Button btn_login;
//    private String userName,psw,spPsw;
//    private EditText et_user_name,et_psw;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        //设置此界面为竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        init();
//
//            FacebookSdk.sdkInitialize(getApplicationContext());
//            AppEventsLogger.activateApp(this);
//        }
//    }
//    /**
//     * 获取界面控件
//     */
//    private void init(){
//        tv_back=(TextView) findViewById(R.id.tv_back);
//        tv_register=(TextView) findViewById(R.id.tv_register);
//        tv_find_psw= (TextView) findViewById(R.id.tv_find_psw);
//        btn_login=(Button) findViewById(R.id.btn_login);
//        et_user_name=(EditText) findViewById(R.id.et_user_name);
//        et_psw=(EditText) findViewById(R.id.et_psw);
//        tv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginActivity.this.finish();
//            }
//        });
//        //立即注册控件的点击事件
//        tv_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivityForResult(intent, 1);
//            }
//        });
//        //找回密码控件的点击事件
//        tv_find_psw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this,FindPswActivity.class);
//                startActivity(intent);
//            }
//        });
//        //登录按钮的点击事件
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userName=et_user_name.getText().toString().trim();
//                psw=et_psw.getText().toString().trim();
//                String md5Psw= MD5Utils.md5(psw);
//                spPsw=readPsw(userName);
//                if(TextUtils.isEmpty(userName)){
//                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if(TextUtils.isEmpty(psw)){
//                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if(md5Psw.equals(spPsw)){
//                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    //保存登录状态
//                    saveLoginStatus(true, userName);
//                    Intent data=new Intent();
//                    data.putExtra("isLogin",true);
//                    setResult(RESULT_OK,data);
//                    LoginActivity.this.finish();
//                    return;
//                }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
//                    Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
//                    return;
//                }else{
//                    Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//    /**
//     *从SharedPreferences中根据用户名读取密码
//     */
//    private String readPsw(String userName){
//        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
//        return sp.getString(userName, "");
//    }
//    /**
//     *保存登录状态和登录用户名到SharedPreferences中
//     */
//    private void saveLoginStatus(boolean status,String userName){
//        //loginInfo表示文件名
//        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
//        SharedPreferences.Editor editor=sp.edit();//获取编辑器
//        editor.putBoolean("isLogin", status);//存入boolean类型的登录状态
//        editor.putString("loginUserName", userName);//存入登录状态时的用户名
//        editor.commit();//提交修改
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(data!=null){
//            //从注册界面传递过来的用户名
//            String userName =data.getStringExtra("userName");
//            if(!TextUtils.isEmpty(userName)){
//                et_user_name.setText(userName);
//                //设置光标的位置
//                et_user_name.setSelection(userName.length());
//            }
//        }
//    }
//
//}

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.wangmengyun.Utils.NetworkUtil;
import com.example.wangmengyun.lefei.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private Button btn_login_user;
    private Button btn_login_manager;
    private Button btn_register;
    private Button btn_forgetpwd;

    private EditText edit_userTel;
    private EditText edit_userPwd;

    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0)
            {
                if(edit_userTel.getText().toString().equals(map.get("telephone")))
                {
                    if(edit_userPwd.getText().toString().equals(map.get("password")))
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("user_data",MODE_PRIVATE).edit();
                        editor.putString("id",map.get("id").toString());
                        editor.putString("telephone",map.get("telephone").toString());
                        editor.putString("password",map.get("password").toString());
                        editor.putString("userName",map.get("userName").toString());
                        editor.putString("imgSource",map.get("imgSource").toString());
                        editor.putString("payPassword",map.get("payPassword").toString());
                        editor.putString("taste",map.get("taste").toString());
                        editor.putString("job",map.get("job").toString());
                        editor.putString("pocket",map.get("pocket").toString());
                        editor.putString("sign",map.get("sign").toString());
                        editor.putString("home",map.get("home").toString());
                        editor.apply();
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(in);
                    }

                }
                else
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();

            }
        }
    };
    private Handler handler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0)
            {
                tel_map2.put("telephone",edit_userTel.getText().toString());
                tel_map2.put("password",edit_userPwd.getText().toString());


                if(edit_userTel.getText().toString().equals(map.get("telephone")))
                {
                    if(edit_userPwd.getText().toString().equals(map.get("password")))
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("manager_data",MODE_PRIVATE).edit();
                        editor.putString("id",map.get("id").toString());
                        editor.putString("telephone",map.get("telephone").toString());
                        editor.putString("password",map.get("password").toString());
                        editor.putString("userName",map.get("userName").toString());
                        editor.putString("name",map.get("name").toString());
                        editor.putString("sex",map.get("sex").toString());
                        editor.putString("canteenId",map.get("canteenId").toString());
                        editor.apply();

                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(in);
                    }

                }
                else
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        }

    };

    private  Map<String,String> tel_map1=new HashMap<>();
    private  Map<String,String> tel_map2=new HashMap<>();
    private Map<String,Object> map=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    Globals.init(this);
        setContentView(R.layout.activity_log_in2);
        btn_login_manager=(Button) findViewById(R.id.LogButton_manager);
        btn_login_user=(Button)findViewById(R.id.LogButton_user);
        btn_register=(Button)findViewById(R.id.login_register);
        btn_forgetpwd=(Button)findViewById(R.id.login_forgetpwd);
        edit_userTel=(EditText)findViewById(R.id.login_telephone);
        edit_userPwd=(EditText)findViewById(R.id.login_password);


        //用户登录界面
        btn_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel_map1.put("telephone",edit_userTel.getText().toString());
                tel_map1.put("password",edit_userPwd.getText().toString());

                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("信息提示");
                dialog.setMessage("正在登录....");
                dialog.show();

                Thread t=new Thread() {
                    @Override
                    public void run() {
                        loadData_stu();
                        handler1.sendEmptyMessage(0);
                        dialog.dismiss();
                    }
                }; t.start();
            }
        });

        btn_login_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel_map2.put("telephone",edit_userTel.getText().toString());
                tel_map2.put("password",edit_userPwd.getText().toString());
                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("信息提示");
                dialog.setMessage("正在登录....");
                dialog.show();

                Thread t=new Thread() {
                    @Override
                    public void run() {
                        loadData_manager();
                        handler2.sendEmptyMessage(0);
                        dialog.dismiss();
                    }
                }; t.start();
            }
        });

        //注册按钮触发

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(in);
            }
        });
        //忘记密码按钮触发

        btn_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(LoginActivity.this,FindPswActivity.class);
                startActivity(in);
            }
        });


    }
    public void loadData_stu()
    {
        try {
            JSONObject obj= new JSONObject(NetworkUtil.postDataByURL(NetworkUtil.URL_BASE,tel_map1));
            map.put("name",obj.getString("name"));              //昵称
            System.out.println(map.get("name")+"*******************userName");
            map.put("imgSource",obj.getString("imgSource"));   //头像
            map.put("id",obj.getInt("id"));
            map.put("sex",obj.getInt("sex"));
            // map.put("userName",obj.getString("userName"));
            map.put("userName",obj.getString("userName"));
            System.out.println(map.get("userName")+"*******************userName");
            map.put("password",obj.getString("password"));
            map.put("payPassword",obj.getString("payPassword"));
            map.put("taste",obj.getString("taste"));
            map.put("job",obj.getInt("job"));
            map.put("pocket",obj.getInt("pocket"));
            map.put("sign",obj.getString("sign"));
            map.put("home",obj.getString("home"));
            // map.put("updateTime",obj.getString("updateTime"));
            map.put("telephone",obj.getString("telephone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData_manager()
    {
        try {
            String str= NetworkUtil.postDataByURL(NetworkUtil.URL_BASE,tel_map2);
            JSONObject obj=new JSONObject(str);
            map.put("name",obj.getString("name"));              //昵称//头像
            map.put("id",obj.getInt("id"));
            map.put("sex",obj.getInt("sex"));
            map.put("userName",obj.getString("userName"));
            map.put("password",obj.getString("password"));
            map.put("telephone",obj.getString("telephone"));
            map.put("canteenId",obj.getInt("canteenId"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
