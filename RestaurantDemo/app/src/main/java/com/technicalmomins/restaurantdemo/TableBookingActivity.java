package com.technicalmomins.restaurantdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class TableBookingActivity extends ActionBarActivity {
    private GridView tableGridView;
    private TableGridAdapter tableGridAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_booking);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Select Table");

        tableGridView = (GridView) findViewById(R.id.table_grid);
        tableGridAdapter = new TableGridAdapter(TableBookingActivity.this);
        tableGridView.setAdapter(tableGridAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table_booking, menu);
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

    private class TableGridAdapter extends BaseAdapter {
        public String[] tableNumberArray;
        private Context mContext;

        private TableGridAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return 21;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View gridView = convertView;
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            gridView = layoutInflater.inflate(R.layout.item_table_grid, null);

            TextView textView = (TextView) gridView.findViewById(R.id.table_id);
            textView.setText(String.valueOf(position + 1));
            gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TableBookingActivity.this, OrderActivity.class);
                    intent.putExtra("table_id",position+1);
                    startActivity(intent);
                }
            });
            return gridView;
        }
    }
}
