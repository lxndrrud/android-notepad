package com.lxndrrud.lab1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.lxndrrud.lab1.R;
import com.lxndrrud.lab1.domain.Item;

import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {
    private ArrayList<Item> itemArrayList;
    private LayoutInflater layoutInflater;

    public ItemListAdapter(Context aContext, ArrayList<Item> listData) {
        this.itemArrayList = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itemArrayList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.titleView);
            holder.text = view.findViewById(R.id.textView);
            holder.createdAt = view.findViewById(R.id.createdAtView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(itemArrayList.get(i).getTitle());
        holder.text.setText(itemArrayList.get(i).getText());
        holder.createdAt.setText(itemArrayList.get(i).getCreatedAt().toString());
        return view;
    }


    static class ViewHolder {
        EditText id;
        TextView title;
        TextView text;
        TextView createdAt;
    }
}
