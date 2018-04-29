package com.example.wangmengyun.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wangmengyun.data.User;
import com.example.wangmengyun.lefei.R;

import java.util.List;

/**
 * Created by wangmengyun on 2018/4/29.
 */

public class CustomAdapter extends BaseAdapter{
    private Context mContext;

    private List<User> listUsers;

    public CustomAdapter(Context mContext, List<User> listUsers) {
        this.mContext = mContext;
        this.listUsers = listUsers;
    }

    @Override

    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsers.get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row, null);

        TextView textUser = view.findViewById(R.id.textUser);

        textUser.setText(listUsers.get(position).getUser());
        return view;
    }
}
