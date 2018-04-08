package com.example.wangmengyun.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.activity.MainActivity;
import com.example.wangmengyun.activity.SearchFlightActivity;
import com.example.wangmengyun.lefei.R;

public class HotCityAdapter extends BaseAdapter {

	private List<City> mHotCityList;
	private LayoutInflater mInflater;
	private Context mContext;

	public HotCityAdapter(Context context, List<City> hotCityList) {
		this.mHotCityList = hotCityList;
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mHotCityList.size();
	}

	@Override
	public Object getItem(int position) {
		return mHotCityList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_city, null);
			viewHolder.tvCityName = (TextView) convertView
					.findViewById(R.id.tv_city_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvCityName.setText(mHotCityList.get(position).getName());
		viewHolder.tvCityName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

//				Intent intent2 =new Intent(mContext, SearchFlightActivity.class);
//
//				intent2.putExtra(Intent.EXTRA_TEXT, mHotCityList.get(position).getName());
//                Intent getDepartureintent = new Intent(mContext, SearchFlightActivity.class);
//
//                getDepartureintent.putExtra(Intent.EXTRA_TEXT,mHotCityList.get(position).getName());
//
//                mContext.startActivity(getDepartureintent);

//				setResult(Activity.RESULT_OK, intent2);
//
//
//				getActivity().finish();

            }
		});
		return convertView;
	}

	class ViewHolder {
		TextView tvCityName;
	}

}
