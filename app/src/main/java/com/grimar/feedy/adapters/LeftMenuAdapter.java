package com.grimar.feedy.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roudj.feedy.R;

import java.util.ArrayList;

public class LeftMenuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StringListItem> articlesListItem;

    public LeftMenuAdapter(Context context, ArrayList<StringListItem> articlesListItem) {
        this.context = context;
        this.articlesListItem = articlesListItem;
    }

    @Override
    public int getCount() {
        return articlesListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return articlesListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        myViewHolder holder = null;
        // If is new row save to holder and set tag
        if (row == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = mInflater.inflate(R.layout.drawer_list_item, null);
            holder = new myViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (myViewHolder) row.getTag();
        }

        holder.txtTitle.setText(articlesListItem.get(position).getString());
        return row;
    }

    //ViewHolder for recycling
    class myViewHolder {
        TextView txtTitle;

        myViewHolder(View v) {
            txtTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}