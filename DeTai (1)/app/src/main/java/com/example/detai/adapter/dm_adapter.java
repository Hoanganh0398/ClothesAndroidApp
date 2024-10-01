package com.example.detai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.detai.R;
import com.example.detai.model.Loaisp;
import com.example.detai.dm_sp.phanloai1_aosomi;

import java.util.List;

public class dm_adapter extends BaseAdapter {
    List<Loaisp> array;
    Context context;

    public dm_adapter(Context context, List<Loaisp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView texttendm;
    }
    //Xuất danh mục
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder  viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_danhmuc, null);
            viewHolder.texttendm = view.findViewById(R.id.item_tendm);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.texttendm.setText(array.get(i).getTendanhmuc());
        return view;
    }
}
