package com.example.wangmengyun.Fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.activity.MainActivity;
import com.example.wangmengyun.adapter.GoodsListAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wangmengyun on 2018/5/4.
 */

public class CartFragment extends Fragment {

    @BindView(R.id.title_back)
    ImageView titleBack;

    @BindView(R.id.cart_checkout)
    TextView checkoutButton;
    @BindView(R.id.cart_forward_index)
    Button homepageButton;

    @BindView(R.id.recyclerView_cart)
    RecyclerView recyclerView;

    @BindView(R.id.cart_list_layout)
    LinearLayout cartListLayout;

    @BindView(R.id.cart_total)
    TextView cartTotal;

    private int memberId;

    private LinearLayout mLayoutManager;

    private List<Flight> listData = new ArrayList<>();

    private GoodsListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, null);
        init();
        getCartList(memberId);

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver();


    }

    public void init(){
        SharedPreferences sp= getActivity().getSharedPreferences("user" , 0);

        memberId = sp.getInt("memberId", -1);

        if(!(activity instanceof MainActivity)){

                titleBack.setVisibility();
        }
    }
}
