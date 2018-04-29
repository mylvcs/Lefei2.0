package com.example.wangmengyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.activity.MyFavouriteActivity;
import com.example.wangmengyun.https.presenter.GithubRepo;
import com.example.wangmengyun.lefei.R;

import java.util.List;

/**
 * Created by wangmengyun on 2018/4/28
 */

public class GithubRepoAdapter extends ArrayAdapter<Flight> {

    private Context context;
    private List<Flight> values;

    public GithubRepoAdapter(Context context, List<Flight> values) {
        super(context, R.layout.list_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);

        Flight item = values.get(position);
        String message = item.getDeparture();
        textView.setText(message);

        return row;
    }
}
