package com.bawei.zby20191229.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zby20191229.R;
import com.bawei.zby20191229.model.bean.BaseBean;
import com.bawei.zby20191229.utile.NetUtile;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<BaseBean.DataBean> list = new ArrayList<>();

    public MyAdapter(List<BaseBean.DataBean> list) {
        this.list = list;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        BaseBean.DataBean dataBean = list.get(i);

        viewHolder.name.setText(dataBean.getGoods_name());
        viewHolder.price.setText(dataBean.getCurrency_price()+"");
        NetUtile.getInstance().getPhoto(dataBean.getGoods_thumb(),viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.text_name);
            price = itemView.findViewById(R.id.text_price);
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
