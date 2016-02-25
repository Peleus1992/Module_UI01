package edu.gatech.wguo64.module_ui01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    public final static String TAG = MainActivity.class.getName();

    public final static String[] titles = {"Fun1" ,"Fun2"};

    public DrawerLayout drawerLayout;
    public Toolbar toolbar;
    public ViewPager viewPager;
    public NavigationView navigationView;

    public ActionBarDrawerToggle drawerToggle;

    public ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflateViews();
        setUIs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_search) {
            Intent intent = new Intent(this, SearchSelectActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.menu_item_fun1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.menu_item_fun2:
                viewPager.setCurrentItem(1);
                break;
        }
        return false;
    }

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setTitle(titles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private void inflateViews() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        navigationView = (NavigationView)findViewById(R.id.navigation);
    }

    private void setUIs() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        getSupportActionBar().setTitle(titles[0]);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, toolbar, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles.length);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);

        navigationView.setNavigationItemSelectedListener(this);
    }
}
