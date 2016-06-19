package com.titlezwc.moment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.enai.app.database.DataBaseOperator;
import com.titlezwc.moment.test.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private DataBaseOperator dbo;
    private static int i = 0;
    private ListView listView;
    private List<AppInfo> mList;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dbo = new DataBaseOperator(AppInfo.class, "path");
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        mList = dbo.qureyAll(AppInfo.class); //查询数据库
        adapter = new TestAdapter(this, mList);
        listView.setAdapter(adapter);
        btn.setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo appInfo = new AppInfo();
                appInfo.setId(mList.get(position).getId());
                appInfo.setAppName("修改");
                appInfo.setVersionCode(-1);
                dbo.update(appInfo); //修改数据库  注意：这个实体类必需要设置主键，并且这个主键是已将在数据库中存在的
                mList.clear();
                mList.addAll(dbo.qureyAll(AppInfo.class));
                adapter.reflush();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbo.delete(mList.get(position)); //删除数据库
                mList.clear();
                mList.addAll(dbo.qureyAll(AppInfo.class));
                adapter.reflush();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                AppInfo appInfo = new AppInfo();
                appInfo.setAppName("小恩爱" + i++);
                appInfo.setVersionCode(i++);
                dbo.insert(appInfo); //插入数据库
                break;
            case R.id.button1:
                dbo.deleteAll(AppInfo.class);
                break;
            case R.id.button2:
                if (mList.size() >= 3) {
                    dbo.deleteList(mList.subList(1, 3));
                }
                break;
            case R.id.button3:
                List<AppInfo> list = new ArrayList<>();
                AppInfo appInfo1 = new AppInfo();
                appInfo1.setAppName("插入数组1");
                appInfo1.setVersionCode(1);
                AppInfo appInfo2 = new AppInfo();
                appInfo2.setAppName("插入数组2");
                appInfo2.setVersionCode(2);
                list.add(appInfo1);
                list.add(appInfo2);
                dbo.insertList(list);
                break;
        }
        mList.clear();
        mList.addAll(dbo.qureyAll(AppInfo.class));
        adapter.reflush();
    }
}
