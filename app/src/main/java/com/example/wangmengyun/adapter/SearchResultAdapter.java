package com.example.wangmengyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.lefei.R;

import java.util.List;


public class SearchResultAdapter extends BaseAdapter implements OnClickListener{

	private List<City> mSearchList;
	private Context mContext;
	private LayoutInflater mInflater;


//    private SubClickListener subClickListener;


//    public void setsubClickListener(HotCityAdapter.SubClickListener topicClickListener) {
//        this.subClickListener = (SubClickListener) topicClickListener;
//    }
//
//    public interface SubClickListener {
//        void OntopicClickListener(View v, City city, int position);
//    }

    public SearchResultAdapter(Context context, List<City> searchList, MyClickListener listener){
		this.mSearchList=searchList;
		this.mContext=context;
		mInflater=LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mSearchList.size();
	}

	@Override
	public Object getItem(int position) {
		return mSearchList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.item_search_list,null);
			viewHolder.tvCityName=(TextView) convertView.findViewById(R.id.tv_city_name);
			convertView.setTag(viewHolder);
		}else{
			viewHolder =(ViewHolder) convertView.getTag();
		}

		viewHolder.tvCityName.setText(mSearchList.get(position).getName());
		viewHolder.tvCityName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext,mSearchList.get(position).getName(),0).show();
			}
		});

		return convertView;
	}

	@Override
	public void onClick(View v) {

	}

	class ViewHolder{
		LinearLayout ll_item;
		TextView tvCityName;
	}

    public static abstract class MyClickListener implements OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }

        public abstract void myOnClick(int position, View v);
    }


}
