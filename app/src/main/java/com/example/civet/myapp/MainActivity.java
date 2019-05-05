package com.example.civet.myapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.civet.myapp.bean.Consumption;
import com.example.civet.myapp.db.DBManager;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView consumptionList;
    TextView emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        checkList();
    }

    private void checkList() {
        if (DBManager.getConsumptionSize(MainActivity.this) == 0) {
            consumptionList.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        consumptionList = findViewById(R.id.consumption_list);
        emptyView = findViewById(R.id.empty_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager.insertConsumption(new Consumption("早餐", 2.5f, new Date().getTime(), "吃喝"), MainActivity.this);
            }
        });
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
