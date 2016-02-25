package edu.gatech.wguo64.module_ui01;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {
    public TextView textView;
    public Toolbar toolbar;
    public String searchFlag = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        inflateViews();
        setUIs();

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void inflateViews() {
        textView = (TextView)findViewById(R.id.textView);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
    }

    private void setUIs() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchResultsActivity.this.onBackPressed();
            }
        });
    }

    private void handleIntent(Intent intent) {
        if(intent.getStringExtra("searchFlag") != null) {
            searchFlag = intent.getStringExtra("searchFlag");
        }
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if("Fun1".equals(searchFlag)) {
                textView.setText("Fun1: " + query);
            } else if("Fun2".equals(searchFlag)) {
                textView.setText("Fun2: " + query);
            }
            //use the query to search
        }
    }
}
