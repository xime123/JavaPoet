package com.arouter.javapoet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InterceptActivity extends AppCompatActivity {
    private ListView lv;
    private ScrollView sv;
    private List datas=new ArrayList();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept);
        lv=findViewById(R.id.lv);
        sv=findViewById(R.id.sv);
        initData();
        adapter=new MyAdapter(datas);
        lv.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0;i<100;i++){
            datas.add("item数据"+i);
        }
    }

    class MyAdapter extends BaseAdapter{
        private List mDatas;

        public MyAdapter(List mDatas) {
            this.mDatas = mDatas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(getApplication());
            textView.setText(mDatas.get(position)+"");
            return textView;
        }
    }
}
