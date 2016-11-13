package com.example.sth0409.gaodeguiji.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sth0409.gaodeguiji.Db.DbAdapter;
import com.example.sth0409.gaodeguiji.Entity.PathRecord;
import com.example.sth0409.gaodeguiji.R;
import com.example.sth0409.gaodeguiji.Util.Util;
import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.superrecycleview.superlibrary.recycleview.swipemenu.SuperSwipeMenuLayout;

import java.util.List;

/**
 * Created by STH0409 on 2016/10/27.
 */

public class Adapter_show extends SuperBaseAdapter<PathRecord> {

    public DbAdapter getDbAdapter() {
        return dbAdapter;
    }

    public void setDbAdapter(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    DbAdapter dbAdapter;
    private String TAG = "aa";

    public Adapter_show(Context context, List<PathRecord> data) {
        super(context, data);
    }

    public Adapter_show(Context context) {
        super(context);
        Log.i(TAG, "Adapter_show: ");
    }

    @Override
    protected void convert(BaseViewHolder holder, final PathRecord item, int position) {
        final SuperSwipeMenuLayout superSwipeMenuLayout = (SuperSwipeMenuLayout) holder.itemView;
        superSwipeMenuLayout.setSwipeEnable(true);//设置是否可以侧滑
        Log.i(TAG, "convert: " + item.getDistance() + "-----" + position);
        holder.setText(R.id.tv_index, position + 1 + "")
                .setText(R.id.tv_distance, item.getDistance()+"m")
                .setText(R.id.tv_duration, Util.toStringTime(item.getDuration()))
                .setText(R.id.tv_time, item.getDate())
                .setOnClickListener(R.id.tv_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick: "+item.getId()+mData.size());

                      //  mData.remove(item.getId()-2);
                        dbAdapter.delete(item.getId());
                        mData=dbAdapter.queryRecordAll();
                        Adapter_show.this.notifyDataSetChanged();
                    }
                });

    }

    @Override
    protected int getItemViewLayoutId(int position, PathRecord item) {
        Log.i(TAG, "getItemViewLayoutId: ");
        return R.layout.item_show;
    }

}
