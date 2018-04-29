//package com.example.wangmengyun.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.wangmengyun.database.FavoriteGoodsEntity;
//import com.example.wangmengyun.lefei.R;
//
//import java.util.List;
//
//import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
//
///**
// * Created by wangmengyun on 2018/4/28.
// */
//
//public class FavoriteGoodsAdapter extends RecyclerView.Adapter<FavoriteGoodsAdapter.ViewHolder> implements View.OnClickListener{
//    private Context mContext;
//    private List<FavoriteGoodsEntity> listData;
//
//
//    public FavoriteGoodsAdapter(Context context, List<FavoriteGoodsEntity> data) {
//
//        this.mContext= context;
//        this.listData = data;
//
//    }
//
//    @Override
//    public FavoriteGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_list, parent,false);
//
//        view.setOnClickListener(this);
//
//        ViewHolder holder = new ViewHolder(view);
//
//        return  holder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//
//        FavoriteGoodsEntity entity = listData.get(position);
//
//        holder.title.setText(entity.getClass().getName());
//
//        holder.price.setText(entity.getClass().getPrice());
//
//        holder.itemView.setTAG(entity);
//
//
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return 0;
//
//    }
//
//
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        private ImageView imageView;
//        private TextView title;
//        private TextView price;
//
//
//        public ViewHolder (View itemView){
//        super(itemView);
//
//        imageView = itemView.findViewById(R.id.goodlist_image);
//
//        title = itemView.findViewById(R.id.goodlist_name);
//
//        price = itemView.findViewById(R.id.goodlist_price);
//
//
//
//
//        }
//    }
//
//
//
//}
