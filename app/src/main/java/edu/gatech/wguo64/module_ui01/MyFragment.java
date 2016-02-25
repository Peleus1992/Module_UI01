package edu.gatech.wguo64.module_ui01;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;


public class MyFragment extends android.support.v4.app.Fragment implements SwipyRefreshLayout.OnRefreshListener {
    public RecyclerView recyclerView;
    public SwipyRefreshLayout swipyRefreshLayout;
    public ProgressBar progressBar;

    public RecyclerViewAdapter rvAdapter;

    private String cursor = "0";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        inflateViews(view);

        setUIs();

        updateObjects();

        return view;
    }

    /**
     * Called when the {@link com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout}
     * is in refresh mode. Just for example purpose.
     */
    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if(direction == SwipyRefreshLayoutDirection.TOP) {
            updateObjects();
        } else {
            appendObejcts();
        }
    }


    public void updateObjects() {
        new InitializeObjectsTask().execute();
    }

    public void appendObejcts() {
        new AppendObjectsTask().execute(cursor);
    }

    private class InitializeObjectsTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            rvAdapter.clearObjects();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add("Hello" + i);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            //handle visibility
            super.onPostExecute(list);

            cursor = "10";
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            //set data for list
            rvAdapter.addObjects(list);
            swipyRefreshLayout.setRefreshing(false);
        }

    }

    private class AppendObjectsTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            int counter = Integer.parseInt(params[0]);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                list.add("Hello" + (counter++));
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            //handle visibility
            super.onPostExecute(list);
            cursor = "" + (Integer.parseInt(cursor) + 5);

            //set data for list
            rvAdapter.addObjects(list);
            swipyRefreshLayout.setRefreshing(false);
        }

    }

    private void inflateViews(View view) {
        swipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id
                .swipeContainer);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    private void setUIs() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAdapter = new RecyclerViewAdapter(new
                ArrayList<String>(), R.layout.cardview,
                this);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setVisibility(View.GONE);

        swipyRefreshLayout.setOnRefreshListener(this);

        progressBar.setVisibility(View.VISIBLE);
    }
}
