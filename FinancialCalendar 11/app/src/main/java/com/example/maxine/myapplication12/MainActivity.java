package com.example.maxine.myapplication12;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxine.myapplication12.base.activity.BaseActivity;
import com.example.maxine.myapplication12.base.fragment.FragmentAdapter;
import com.example.maxine.myapplication12.group.GroupItemDecoration;
import com.example.maxine.myapplication12.pager.PagerFragment;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.example.maxine.myapplication12.group.GroupRecyclerView;
import com.example.maxine.myapplication12.ArticleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {


    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    GroupRecyclerView mRecyclerView;

    /**12/29_清瑜_側邊欄宣告元件________________________________________________________________________*/
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    NavigationView navigation;
    public static boolean event1, event2, event3, event4, event5, event6, event7, event8, event9, event10, event11 = false;
    FrameLayout current_icon;
    /**_______________________________________________________________________________________________*/

    FloatingActionButton fabPerson,fabMan,fabWoman;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout); //側滑選單

        initWindow();
        initView();
        initInstances();
        initData();
    }

    /**01/04_清瑜_左側欄checkbox勾選事件________________________________________________________________*/
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();
        switch ((view.getId())){
            case R.id.checkbox_1:
                if(checked){ event1 = true; initData(); }
                else{ event1 = false; initData(); } break;
            case R.id.checkbox_2:
                if(checked){ event2 = true; initData(); }
                else{ event2 = false; initData(); } break;
            case R.id.checkbox_3:
                if(checked){ event3 = true; initData(); }
                else{ event3 = false; initData(); } break;
            case R.id.checkbox_4:
                if(checked){ event4 = true; initData(); }
                else{ event4 = false; initData(); } break;
            case R.id.checkbox_5:
                if(checked){ event5 = true; initData(); }
                else{ event5 = false; initData(); } break;
            case R.id.checkbox_6:
                if(checked){ event6 = true; initData(); }
                else{ event6 = false; initData(); } break;
            case R.id.checkbox_7:
                if(checked){ event7 = true; initData(); }
                else{ event7 = false; initData(); } break;
            case R.id.checkbox_8:
                if(checked){ event8 = true; initData(); }
                else{ event8 = false; initData(); } break;
            case R.id.checkbox_9:
                if(checked){ event9 = true; initData();
                }else{ event9 = false; initData(); } break;
            case R.id.checkbox_10:
                if(checked){ event10 = true; initData(); }
                else{ event10 = false; initData(); } break;
            case R.id.checkbox_11:
                if(checked){ event11 = true; initData(); }
                else{ event11 = false; initData(); } break;
        }
    }
    /**______________________________________________________________________________________________*/

    /**01/03_清瑜_側欄點擊______________________________________________________________________________*/
    private void initInstances() {

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            setupDrawer();
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");

            //1/5_清瑜_修正_______________________________________________________________________________________
            //1/4_清瑜_左側欄點擊事件
            navigation = findViewById(R.id.nav_view);

            navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.nav_year:
                            mCalendarView.showOnlyYearSelectLayout(mYear);
                            mTextLunar.setVisibility(View.GONE);
                            mTextYear.setVisibility(View.GONE);
                            mTextMonthDay.setText(String.valueOf(mYear));
                            closeDrawer();
                            break;

                        case R.id.nav_month:
                            mCalendarView.closeYearSelectLayout();  //會回到上一次點擊的月份
                            closeDrawer();
                            break;
                    }
                    return false;
                }
            });
            //____________________________________________________________________________________________________
        }
    }
    /**_______________________________________________________________________________________________*/

    /**12/29_清瑜_側邊欄內部監聽(原)_____________________________________________________________________*/
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){  //R.String.drawer_open是用來表示選單目前是開啟或關閉，要寫在string.xml裡

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle("EDMTDev");
                invalidateOptionsMenu(); //重新載入新菜單
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                getSupportActionBar().setTitle("mActivityTitle");
                invalidateOptionsMenu();
            }

        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }
    /**_______________________________________________________________________________________________*/

    /**12/30_清瑜_側邊欄關閉(原)________________________________________________________________________*/
    public void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**_______________________________________________________________________________________________*/

    /**1/3_清瑜_廢棄_________________________________________________________________________________*/
    /**1/2_清瑜_右邊點點*/
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.drawer_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//
//        int selectedId = item.getItemId();
//
//        item.setChecked(true);
//        switch (selectedId){
//            case R.id.nav_event1:
//                initData();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    /**_______________________________________________________________________________________________*/

    /**1/2_清瑜_箭頭變漢堡加好了______________________________________________________________________________*/
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    /**_______________________________________________________________________________________________*/

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mTextLunar = findViewById(R.id.tv_lunar);
        mRelativeTool = findViewById(R.id.rl_tool);
        mCalendarView = findViewById(R.id.calendarView);
        mTextCurrentDay = findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });


        /**1/5_清瑜_當前日期___________________________________________________________________________*/
        current_icon = findViewById(R.id.fl_current);
        current_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.closeYearSelectLayout();
                mCalendarView.scrollToCurrent();
            }
        });
        /**___________________________________________________________________________________________*/

        mCalendarLayout = findViewById(R.id.calendarLayout);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PagerFragment.newInstance());
//        fragments.add(PagerFragment.newInstance());
//        fragments.add(PagerFragment.newInstance());
        adapter.reset(fragments);
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        try {
            int year = mCalendarView.getCurYear();
            int month = mCalendarView.getCurMonth();

            //記事
            Map<String, Calendar> map = new HashMap<>();
            map.put(getSchemeCalendar(year, month, 3, "假").toString(),
                    getSchemeCalendar(year, month, 3, "假"));
            map.put(getSchemeCalendar(year, month, 6, "事").toString(),
                    getSchemeCalendar(year, month, 6, "事"));
            map.put(getSchemeCalendar(year, month, 9, "議").toString(),
                    getSchemeCalendar(year, month, 9, "議"));
            map.put(getSchemeCalendar(year, month, 13, "記").toString(),
                    getSchemeCalendar(year, month, 13, "記"));
            map.put(getSchemeCalendar(year, month, 14, "記").toString(),
                    getSchemeCalendar(year, month, 14, "記"));
            map.put(getSchemeCalendar(year, month, 15, "假").toString(),
                    getSchemeCalendar(year, month, 15, "假"));
            map.put(getSchemeCalendar(year, month, 18, "記").toString(),
                    getSchemeCalendar(year, month, 18, "記"));
            map.put(getSchemeCalendar(year, month, 25, "假").toString(),
                    getSchemeCalendar(year, month, 25, "假"));
            map.put(getSchemeCalendar(year, month, 27, "多").toString(),
                    getSchemeCalendar(year, month, 27, "多"));
            //此方法在巨大的数据量上不影响遍历性能，推荐使用
            mCalendarView.setSchemeDate(map);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debugB", e.toString());
        }
    }

    private Calendar getSchemeCalendar(int year, int month, int day, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(Color.WHITE);//white
        calendar.setScheme(text);

        calendar.addScheme(0xFFE88986, "0");
        calendar.addScheme(0xFFA1D69A, "1");
        calendar.addScheme(0xFFF5ee6a, "2");
        calendar.addScheme(0xFF85BDD3, "3");
        calendar.addScheme(0xFFD8D8D8, "4");
        calendar.addScheme(0xFF800080, "5");

        return calendar;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    /**1/4_尹邦_浮動按鈕事件___________________________________________________________________________*/
    public void onFABClick1(View view){

        switch (view.getId()){
            case R.id.fabPerson:
//                Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, Edit_Event.class);//告訴它從哪邊切換到哪邊
                startActivity(intent);
                break;}

    }
    /**_____________________________________________________________________________________________*/

}