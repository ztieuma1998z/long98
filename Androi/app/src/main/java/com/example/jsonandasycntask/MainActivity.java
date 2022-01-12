package com.example.jsonandasycntask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnClick{

    RecyclerView recyclerView;
    JSONArray jsonArrayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new DownloadTask(this).execute("https://lebavui.github.io/jsons/users.json");

    }

    @Override
    public void userClick(UserModel user) {
        Intent intent = new Intent(MainActivity.this, UserDetail.class);
        intent.putExtra("key1", user);
        startActivity(intent);
    }

    class DownloadTask extends AsyncTask<String, Void, JSONArray> {
        ProgressDialog dialog;
        Context context;

        public DownloadTask (Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("loading data ...");
            dialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null)
                    builder.append(line);
                reader.close();

                String jsonString = builder.toString();
                Log.v("TAG", jsonString);

                return new JSONArray(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            dialog.dismiss();

            if(jsonArray != null) {
                ItemJSONAdapter adapter = new ItemJSONAdapter(jsonArray,MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}