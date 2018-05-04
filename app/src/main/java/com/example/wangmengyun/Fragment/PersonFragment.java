package com.example.wangmengyun.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangmengyun.lefei.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangmengyun on 2018/5/4. P145
 */

public class PersonFragment extends Fragment {

    @BindView(R.id.user_img_view)
    ImageView userImgView;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_level)
    TextView userLevel;

    @BindView(R.id.my_account)
    RelativeLayout myAccount;

    @BindView(R.id.person_logout_layout)
    RelativeLayout personLogoutLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);

        return view;


    }
    //TODO fragment的跳转逻辑
    @OnClick({R.id.person_login, R.id.person_my_order, R.id.my_collect, R.id.my_address, R.id.my_account, R.id.person_logout_layout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.person_login:
                break;


        }
    }


}
