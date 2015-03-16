package com.technicalmomins.restaurantdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class OrderActivity extends ActionBarActivity implements
        android.support.v7.app.ActionBar.TabListener, StartersFragment.OnFragmentInteractionListener {
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;


    ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"Starters", "Dishes", "Salads", "Desserts", "Beverages"};
    private String TAG = getClass().getSimpleName();
    private int tableId;
    private Button orderButton;
    private double orderValue;
    private TextView orderValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderButton = (Button) findViewById(R.id.order_button);
        orderValueText = (TextView) findViewById(R.id.order_value);
        orderValue=45.34;
        orderValueText.setText("$"+orderValue);
        Intent intent = getIntent();
        if (intent.hasExtra("table_id")) {
            tableId = intent.getIntExtra("table_id", 0);
        }

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        Log.d(TAG, "Table ID:" + tableId);
        if (tableId != 0) {
            actionBar.setTitle("Table " + tableId);
        }

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {

            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderActivity.this, "Order confirmed.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onFragmentInteraction(String id) {
        orderValueText.setText("$"+(++orderValue));
        Log.d(TAG, "ID:" + id);
    }


    public class TabsPagerAdapter extends FragmentPagerAdapter {

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:
                    return StartersFragment.newInstance("First Tab", "Starters");
                case 1:
                    return StartersFragment.newInstance("Second Tab", "Dishes");
                case 2:
                    return StartersFragment.newInstance("Third Tab", "Salads");
                case 3:
                    return StartersFragment.newInstance("Second Tab", "Desserts");
                case 4:
                    return StartersFragment.newInstance("Third Tab", "Beverages");
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return 5;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
