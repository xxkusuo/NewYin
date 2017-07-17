package com.gxtc.yyj.newyin.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.base.BaseActivity;
import com.gxtc.yyj.newyin.mvp.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRvMain;
    private FrameLayout mFlContent;
    private FragmentTabHost mTabContentBottom;
    private Toolbar mToolbar;
    private int offset;
    private AppBarLayout mAppBarLayout;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "来追我呀，嘿嘿嘿", Snackbar.LENGTH_LONG)
                        .setAction("追之", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "你追不到嘿嘿嘿", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mTabContentBottom = (FragmentTabHost) findViewById(R.id.ftb_main);
        initFragmentTabHost();
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_main);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                offset = verticalOffset;
            }

        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (offset!=0){
            mAppBarLayout.setExpanded(true,true);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //初始化FragmentTabHost
    private void initFragmentTabHost() {
        //初始化FragmentTabHost容器
        mTabContentBottom.setup(this, getSupportFragmentManager(), R.id.fl_content);

        //去掉FragmentTabHost的分割线
        if (android.os.Build.VERSION.SDK_INT > 10) {
            // 去除分割线
            mTabContentBottom.getTabWidget().setShowDividers(0);
        }

        /*使用枚举的方式 放入枚举常量 然后从中取出来 使用枚举常量的总数*/
        for (int i = 0; i < MainTab.values().length; i++) {
            MainTab mainTab = MainTab.values()[i];//得到对应的枚举对象
            String title = mainTab.title;//得到标题
            int resId = mainTab.topResID;//得到顶部资源id
            Class clazz = mainTab.clazz;//得到类

            //添加tab标签页详细 注意这里用法 title是作为一个tag传入用于区别页面的
            TabHost.TabSpec tabSpec = mTabContentBottom.newTabSpec(title);
            //设置指示的view 就是底部标签的名字的View
            View indicatorView = View.inflate(this,R.layout.view_tab_indicator,null);
            //找到指示view的控件
            TextView tvTitle = (TextView) indicatorView.findViewById(R.id.tab_title);
            //设置view中的标题
            tvTitle.setText(title);
            //设置标题顶部的图片
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(0,resId,0,0);
            //设置指示view到每个标签页
            tabSpec.setIndicator(indicatorView);

            //添加每个标签页
            /**
             * @param TabSpec
             * @param Class 类
             * @param Bundle 一些值传递
             */

            Bundle bundle = new Bundle();
            bundle.putString("title",title);//传递数据
            mTabContentBottom.addTab(tabSpec,clazz,bundle);


        }
    }

    /*使用枚举的方式 放入枚举常量 然后从中取出来*/
    enum MainTab {
        MUSIC("主页", R.drawable.tab_home_selector, HomeFragment.class),
        VIDEO("新闻", R.drawable.tab_news_selector, HomeFragment.class),
        EXPLORE("音乐", R.drawable.tab_music_selector, HomeFragment.class);
        private String title;
        private int topResID;
        private Class clazz;

        MainTab(String title, int drawableResId, Class clazz) {
            this.title = title;
            this.topResID = drawableResId;
            this.clazz = clazz;
        }
    }


}
