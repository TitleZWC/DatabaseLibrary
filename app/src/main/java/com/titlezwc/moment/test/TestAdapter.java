package com.titlezwc.moment.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.titlezwc.moment.AppInfo;
import com.titlezwc.moment.R;

import java.util.List;

/**
 * Created by zhaowenchao on 16/6/18.
 */
public class TestAdapter extends BaseAdapter {

    private List<AppInfo> mList;
    private Context context;

    public TestAdapter(Context context, List<AppInfo> list) {
        mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.test_item, null);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mName.setText(mList.get(position).getAppName());
        mHolder.mVersion.setText(mList.get(position).getVersionCode()+"");
        return convertView;
    }

    private class ViewHolder {
        public ViewHolder(View v) {
            mName = (TextView) v.findViewById(R.id.appName);
            mVersion = (TextView) v.findViewById(R.id.appVersion);
        }

        private TextView mName;
        private TextView mVersion;

    }
    public void reflush(){
        notifyDataSetChanged();
    }

}
