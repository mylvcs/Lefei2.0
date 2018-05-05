package com.example.wangmengyun.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.Utils.NetworkUtil;
import com.example.wangmengyun.Utils.NetworkUtils;
import com.example.wangmengyun.activity.MainActivity;
import com.example.wangmengyun.adapter.CartGoodsAdapter;
import com.example.wangmengyun.adapter.GoodsListAdapter;
import com.example.wangmengyun.https.ProgressDialogSubscriber;
import com.example.wangmengyun.https.presenter.GoodsPresenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

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

    @BindView(R.id.refresh_widget)
    SwipeRefreshLayout refreshWidget;

    private UpdateCart receiver;

    private int memberId;

    private LinearLayoutManager mLayoutManager;

    private List<Flight> listData = new ArrayList<>();

    private GoodsListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, null);
        ButterKnife.bind(this,view);

        init();
        getCartList(memberId);

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver();


    }

    private void registerReceiver() {
        activity = getActivity();

        UpdateCart receiver = new UpdateCart();
        IntentFilter filter = new IntentFilter();

        filter.addAction("");

        activity.registerReceiver(receiver,filter);
    }

    public void init(){
        SharedPreferences sp= getActivity().getSharedPreferences("user" , 0);

        memberId = sp.getInt("memberId", -1);

        if(!(activity instanceof MainActivity)) {

            titleBack.setVisibility(View.VISIBLE);
        }else{
            titleBack.setVisibility(View.GONE);
        }
        cartNoDataLayout.setVisibility(View.GONE);
        cartListLayout.setVisibility(View.VISIBLE);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        recyclerView.setLayoutManager(mLayoutManager);

        //设置刷新样式
        refreshWidget.setColorSchemeResources(android.R.color.holo_red_light);

        refreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(NetworkUtils.isNetworkAvailable(getActivity())){
                    getCartList(memberId);
                }else {
                    refreshWidget.setRefreshing(false);
                    Toast.makeText(getActivity(),"网络不可用",Toast.LENGTH_SHORT).show();
                }
            }
        });

        adapter = new CartGoodsAdapter(getActivity(),listData);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(),recyclerView, new RecyclerItemClickListener.onItemClickListener(){
                    public void onItemClick(View view,int position) {

                    }

                    public void onLongItemClick(View view, final int position){
                        new AlertDialog.Builder(activity).setTitle("删除预定订单").setMessage("您确定要删除吗？").setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                    }
                })
        );

    }
/**删除
 * @param cartid
 */

private void deleteCartGoods(int cartid) {
    GoodsPresenter.cartDelete(new ProgressDialogSubscriber<HttpResult>(getActivity()) {
        @Override
        public void onNext(HttpResult httpResult) {
            if (httpResult.getStatus() == 0) {
                Toast.makeText(activity, "删除成功", Toast.LENGTH_SHORT).show();

                refreshWidget.setRefreshing(true);
                getCartList(memberId);
            }
        }
    }, cartid);

}

    /**
     *获取购物车
     * 列表
     */

    private void getCartList(int id){
        if(memberId!= -1){
            GoodsPresenter.cartList(new Subscriber<List<Flight>>(){
                @Override
                public void onCompleted() {
                    refreshWidget.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    refreshWidget.setRefreshing(false);
                }

                @Override
                public void onNext(List<Flight> cartGoodsEntities) {
                    listData.clear();
                    listData.addAll(cartGoodsEntities);
                    adapter.notifyDataSetChanged();

                    double totalPrice = 0.0 ;
                    for (int i =0 ;i <cartGoodsEntities.size(); i++){
                        totalPrice+= cartGoodsEntities.get(i).getAmount();

                    }
                    cartTotal.setText("总价： ￥"+totalPrice);
                }
            }, id);
        }
    }


}
