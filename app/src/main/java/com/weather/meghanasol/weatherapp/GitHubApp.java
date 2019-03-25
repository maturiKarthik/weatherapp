package com.weather.meghanasol.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.meghanasol.weatherapp.model.Data;
import com.weather.meghanasol.weatherapp.model.GithubCalls;
import com.weather.meghanasol.weatherapp.model.GithubCallsCallback;
import com.weather.meghanasol.weatherapp.model.GithubServices;
import com.weather.meghanasol.weatherapp.model.ListItemClickListener;
import com.weather.meghanasol.weatherapp.model.MyAdapter;
import com.weather.meghanasol.weatherapp.model.ServerConfig;

import java.util.List;

public class GitHubApp extends AppCompatActivity implements GithubCallsCallback, ListItemClickListener {

    private String TAG = this.getClass().getSimpleName();
    EditText edit_query;
    TextView tv_url;
    private GithubServices service;
    RecyclerView recyclerView;
    //  private String [] myDataset = {"H","Welcome","Tuts","Normal","Gadget"};
    private List<Data> mdata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.github_layout);

        edit_query = (EditText) findViewById(R.id.edit_query);
        tv_url = (TextView) findViewById(R.id.tv_url);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /**
         * Server Config
         */
        service = new ServerConfig(Constant.GITHUB_BASE_URL).getGithubInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_button:
                Toast.makeText(this, "Run", Toast.LENGTH_SHORT).show();
                new GithubCalls(service, this, edit_query.getText().toString().replaceAll(" ", ""));
                break;
            case R.id.newActivity:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("key", "Data From Github");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Call Backs From GithubCalls
     *
     * @param
     */

    @Override
    public void datarecieved(List<Data> data) {
        mdata = data;
        recyclerView.setVisibility(View.VISIBLE);
        MyAdapter mAdapter = new MyAdapter(data, this);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onError(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void urlPinged(String url) {
        tv_url.setText(url);
    }

    /**
     * Call Back For OnItemClickListeners
     */
    @Override
    public void onClickListener(int position) {


        Toast toast = Toast.makeText(this, "" + mdata.get(position).getFull_name(), Toast.LENGTH_SHORT);
        toast.show();

    }
}
