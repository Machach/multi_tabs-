package com.example.xiaomi.tabs;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomi.tabs.Fragment.Tab1Fragment;
import com.example.xiaomi.tabs.Fragment.Tab2Fragment;
import com.example.xiaomi.tabs.Fragment.Tab3Fragment;
import com.example.xiaomi.tabs.Fragment.Tab4Fragment;
import com.example.xiaomi.tabs.Fragment.Tab5Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = {"主页","群组","搜索","消息","更多"};
    private int images[] = {R.drawable.home_selector,R.drawable.group_select,R.drawable.search_select,R.drawable.mail_select,R.drawable.more_select};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        //页面，数据源
        list = new ArrayList<>();
        list.add(new Tab1Fragment());
        list.add(new Tab2Fragment());
        list.add(new Tab3Fragment());
        list.add(new Tab4Fragment());
        list.add(new Tab5Fragment());
        //ViewPager适配器
        adapter = new MyAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
        //自定义视图
        for(int i = 0; i < tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
    }


    class MyAdapter extends FragmentPagerAdapter{

        private Context context;

        public MyAdapter(FragmentManager fm,Context context){
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position){
            return list.get(position);
        }

        @Override
        public int getCount(){
            return list.size();
        }

        public View getTabView(int position){
            View v  = LayoutInflater.from(context).inflate(R.layout.tab_custom,null);
            TextView textView = (TextView) v.findViewById(R.id.tv_title);
            ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);

            textView.setTextColor(tabLayout.getTabTextColors());
            return v;
        }

    }




}




