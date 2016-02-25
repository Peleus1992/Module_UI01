package edu.gatech.wguo64.module_ui01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SearchSelectActivity extends AppCompatActivity implements View.OnClickListener{
    public Button searchFun1Btn, searchFun2Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_select);
        inflateViews();
        setUIs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_select, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fun1SearchBtn:
                startFun1SearchActivity();
                break;
            case R.id.fun2SearchBtn:
                startFun2SearchActivity();
                break;
        }
    }

    private void inflateViews() {
        searchFun1Btn = (Button)findViewById(R.id.fun1SearchBtn);
        searchFun2Btn = (Button)findViewById(R.id.fun2SearchBtn);
    }

    private void setUIs() {
        searchFun1Btn.setOnClickListener(this);
        searchFun2Btn.setOnClickListener(this);
    }

    private void startFun1SearchActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("searchFlag", "Fun1");
        startActivity(intent);
    }

    private void startFun2SearchActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("searchFlag", "Fun2");
        startActivity(intent);
    }

}
