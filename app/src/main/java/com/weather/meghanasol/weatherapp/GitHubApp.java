package com.weather.meghanasol.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.meghanasol.weatherapp.model.Data;
import com.weather.meghanasol.weatherapp.model.GithubCalls;
import com.weather.meghanasol.weatherapp.model.GithubCallsCallback;
import com.weather.meghanasol.weatherapp.model.GithubServices;
import com.weather.meghanasol.weatherapp.model.ServerConfig;

public class GitHubApp extends AppCompatActivity implements GithubCallsCallback {

    private String TAG = this.getClass().getSimpleName();
    EditText edit_query;
    TextView tv_url, tv_url_result;
    private GithubServices service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.github_layout);

        edit_query = (EditText) findViewById(R.id.edit_query);
        tv_url = (TextView) findViewById(R.id.tv_url);
        tv_url_result = (TextView) findViewById(R.id.tv_url_result);
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
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Call Backs From GithubCalls
     *
     * @param
     */

    @Override
    public void datarecieved(Data data) {
        tv_url_result.append("Id ::" + data.getId() + "   Name :" + data.getName() + "(" + data.getFull_name() + ")");
        Log.w(TAG, "Id ::" + data.getId() + "   Login::" + data.getLogin() + "   Name :" + data.getName() + "(" + data.getFull_name() + ")");
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void urlPinged(String url) {
        tv_url.setText(url);
    }
}
