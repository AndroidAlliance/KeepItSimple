package xyz.androidalliance.example;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import xyz.androidalliance.example.adapter.PagingAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_ADAPTER = "STATE_ADAPTER";
    private ViewPager viewPager;
    private PagingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        configToolbar();
    }

    private void bindViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new PagingAdapter();
        viewPager.setAdapter(adapter);
    }

    private ActionBar configToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Keep it Simple");
        return ab;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(STATE_ADAPTER, adapter.saveState(viewPager));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.restoreState(savedInstanceState.getBundle(STATE_ADAPTER));
    }
}
