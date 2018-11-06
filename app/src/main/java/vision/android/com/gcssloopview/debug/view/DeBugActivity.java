package vision.android.com.gcssloopview.debug.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vision.android.com.gcssloopview.R;
import vision.android.com.gcssloopview.basis.csys.CSYSActivity;
import vision.android.com.gcssloopview.debug.adapter.DeBugAdapter;
import vision.android.com.gcssloopview.debug.bean.DeBugBean;

public class DeBugActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private final String TAG = DeBugActivity.class.getSimpleName();
    private ListView main_listview;
    private DeBugAdapter mAdapter;
    private List<DeBugBean> mMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_bug);
        init();
    }

    private void init() {
        main_listview = (ListView) findViewById(R.id.activity_debug_list_view);
        //加载按钮数据
        mMainList = new ArrayList<>();
        initData();
        //初始化适配器
        mAdapter = new DeBugAdapter(this, mMainList);
        main_listview.setAdapter(mAdapter);
        main_listview.setOnItemClickListener(this);
    }

    /**
     * 在此添加调试功能
     */
    private void initData() {
        mMainList.add(getMainBean("安卓自定义View基础-坐标系" , CSYSActivity.class));
    }

    /**
     * 描述：返回Bean
     *
     * @param name
     * @param cls
     * @return
     */
    private DeBugBean getMainBean(String name, Class<? extends Activity> cls) {
        DeBugBean bean = new DeBugBean();
        bean.setActivityClass(cls);
        bean.setNameItem(name);
        return bean;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到相应Activity界面
        startActivity(new Intent(DeBugActivity.this, mMainList.get(position).getActivityClass()));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放引用
        mMainList.clear();
    }
}
