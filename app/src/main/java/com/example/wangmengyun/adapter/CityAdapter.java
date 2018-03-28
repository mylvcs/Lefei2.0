package com.example.wangmengyun.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangmengyun.Bean.CityContract;
import com.example.wangmengyun.lefei.R;

//package com.example.wangmengyun.activity;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//
//import com.example.wangmengyun.lefei.R;
//
//import java.util.List;
//
//public class CityAdapter extends ArrayAdapter<String> {
//    /**
//     * 需要渲染的item布局文件
//     */
//    private int resource;
//
//    public CityAdapter(Context context, int textViewResourceId, List<String> objects) {
//        super(context, textViewResourceId, objects);
//        resource = textViewResourceId;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LinearLayout layout = null;
//        if (convertView == null) {
//            layout = (LinearLayout) LayoutInflater.from(getContext()).inflate(resource, null);
//        } else {
//            layout = (LinearLayout) convertView;
//        }
//        Button name = (Button) layout.findViewById(R.id.tv_city);
//        name.setText(getItem(position));
//        return layout;
//    }
//}
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private static final String TAG = CityAdapter.class.getSimpleName();


    private Context mContext;

    private Cursor mCursor;

    private int mNumberCity;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public CityAdapter(Context context,Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdforListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdforListItem, viewGroup, false);

        CityViewHolder viewHolder = new CityViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CityAdapter.CityViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))

            return ;
        /**
         * 这里需要City的contrast类
         */
        String name = mCursor.getString(mCursor.getColumnIndex(CityContract.CityEntry.COLUMN_CITY_NAME));

      //  int = mCursor.getString(mCursor.getColumnIndex(CityContract.CityEntry.COLUMN_UUID;



        holder.listItemCityView.setText(name);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listItemCityView;

        TextView viewHolderIndex;
        private ListItemClickListener mOnClickListener;


        public CityViewHolder(View itemView) {
            super(itemView);

            listItemCityView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);

            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            viewHolderIndex.setText(String.valueOf(listIndex));
        }


        // COMPLETED (6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

//          Intent intent = new Intent(mContext,SearchFlightActivity.class);
//          String intentExtra = mCursor.getString(mCursor.getColumnIndex(CityContract.CityEntry.COLUMN_CITY_NAME));
//
//            intent.putExtra("Cityname",intentExtra);
//
//            mContext.startActivity(intent);

       //     Toast.makeText(2,"出发城市",Toast.LENGTH_SHORT).show();

        }
    }
}




