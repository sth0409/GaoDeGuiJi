package com.example.sth0409.gaodeguiji.Ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.annotation.TargetApi;

import com.example.sth0409.gaodeguiji.Adapter.Adapter_show;
import com.example.sth0409.gaodeguiji.Db.DbAdapter;
import com.example.sth0409.gaodeguiji.Entity.PathRecord;
import com.example.sth0409.gaodeguiji.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.superrecycleview.superlibrary.recycleview.swipemenu.SuperSwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements SuperBaseAdapter.OnItemClickListener {

    private SuperSwipeMenuRecyclerView superSwipemenuRecycleView;
    private Adapter_show adapter_show;
    private   List<PathRecord> pathRecords;
    private DbAdapter mDataBaseHelper;
    public static final String RECORD_ID = "record_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initBar();
        initView();
        initData();

        initSwipemenuRecycleView();
        initAdapter();
    }

    private void initAdapter() {
        adapter_show = new Adapter_show(this, pathRecords);
        adapter_show.setOnItemClickListener(this);
        adapter_show.setDbAdapter(mDataBaseHelper);
        superSwipemenuRecycleView.setAdapter(adapter_show);
    }

    private void initData() {
        mDataBaseHelper = new DbAdapter(this);
        pathRecords = new ArrayList<>();
        //  pathRecords.add(new PathRecord(null, null, null, "123", "123", "123", "123", 0));
        mDataBaseHelper.open();
        searchAllRecordFromDB();
    }

    private void initBar() {
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#24b7a4"));

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initSwipemenuRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        superSwipemenuRecycleView.setLayoutManager(layoutManager);
        superSwipemenuRecycleView.setRefreshEnabled(false);
        superSwipemenuRecycleView.setLoadMoreEnabled(false);
        // superSwipemenuRecycleView.setLoadingListener(this);
        superSwipemenuRecycleView.setSwipeDirection(SuperSwipeMenuRecyclerView.DIRECTION_LEFT);//左滑（默认）
        // superSwipeMenuRecyclerView.setSwipeDirection(SuperSwipeMenuRecyclerView.DIRECTION_LEFT);//右滑

    }

    private void initView() {
        superSwipemenuRecycleView = (SuperSwipeMenuRecyclerView) findViewById(R.id.super_swipemenu_recycle_view);
    }

    private void searchAllRecordFromDB() {
        pathRecords = mDataBaseHelper.queryRecordAll();
    }

    public void onBackClick(View view) {
        this.finish();
    }

    @Override
    public void onItemClick(View view, Object item, int position) {
        PathRecord recorditem = (PathRecord) item;
        Intent intent = new Intent(ListActivity.this,
                RecordShowActivity.class);
        intent.putExtra(RECORD_ID, recorditem.getId());
        startActivity(intent);
    }
}
