package com.test.airbuzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.airbuzz.Activities.AeroActivity;
import com.test.airbuzz.models.NewsModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView navalNewsHead, navalNewsBody, aeroNewsHead, aeroNewsBody, railNewsHead, railNewsBody, roadNewsHead, roadNewsBody;
    RelativeLayout navalRL, aeroRL, railRL, roadRL;

    List<NewsModelClass> modelClassList;
    /*    RequestQueue queue = Volley.newRequestQueue(this);*/

    @Override
    public void onResume() {
        super.onResume();
        new GetDataTask().execute("http://192.168.43.250:8080/");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();


    }

    void initUI() {
        navalNewsHead = findViewById(R.id.naval_news_head);
        navalNewsBody = findViewById(R.id.naval_news_body);
        aeroNewsHead = findViewById(R.id.aero_news_head);
        aeroNewsBody = findViewById(R.id.aero_news_body);
        railNewsHead = findViewById(R.id.rail_news_head);
        railNewsBody = findViewById(R.id.rail_news_body);
        roadNewsHead = findViewById(R.id.road_news_head);
        roadNewsBody = findViewById(R.id.road_news_body);

        navalRL = findViewById(R.id.naval_rl);
        aeroRL = findViewById(R.id.aero_rl);
        railRL = findViewById(R.id.rail_rl);
        roadRL = findViewById(R.id.road_rl);
    }

    void initListeners() {

        aeroRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AeroActivity.class);
                startActivity(intent);
            }
        });

        navalRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetDataTask().execute("http://192.168.43.250:8080/");
            }
        });

    }

    /*

        final String url = "http://httpbin.org/get?param1=hello";

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", response);
                    }
                }
        );

    // add it to the RequestQueue
    queue.add(getRequest);

    */
    public void setTheData(String getResponse) throws JSONException {

        /*JSONArray jsonarray = new JSONArray(getResponse);

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String means = jsonobject.getString("means");
            String headLines = jsonobject.getString("headLines");
            String mainNews = jsonobject.getString("mainNews");
        }
*/
        /*          String str = "[{\"name\":\"name1\",\"url\":\"url1\"},{\"name\":\"name2\",\"url\":\"url2\"}]";
         */
        modelClassList = new ArrayList<>();
        JSONArray jsonarray = new JSONArray(getResponse);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String means = jsonobject.getString("Means");
            String headLines = jsonobject.getString("Headlines");
            String mainNews = jsonobject.getString("MainNews");
            modelClassList.add(new NewsModelClass(means, headLines, mainNews));
        }

        navalNewsHead.setText(modelClassList.get(0).getHeadLines());
        navalNewsBody.setText(modelClassList.get(0).getMainNews());
        aeroNewsHead.setText(modelClassList.get(1).getHeadLines());
        aeroNewsBody.setText(modelClassList.get(1).getMainNews());
        railNewsHead.setText(modelClassList.get(2).getHeadLines());
        railNewsBody.setText(modelClassList.get(2).getMainNews());
        roadNewsHead.setText(modelClassList.get(3).getHeadLines());
        roadNewsBody.setText(modelClassList.get(3).getMainNews());
    }


    class GetDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return getData(params[0]);
            } catch (IOException ex) {
                return "Network error !";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //set data response to textView

            try {
                setTheData(result);
            } catch (Exception e) {
                Log.e("TAG-REsponseCOde", "Exception  Caougt", e);
            }
            //mResult.setText(result);
            //cancel progress dialog

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String getData(String urlPath) throws IOException {
            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = null;

            try {
                //Initialize and config request, then connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(10000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

                int status = urlConnection.getResponseCode();

                Log.d("TAG-REsponseCOde", Integer.toString(status));

                //Read data response from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }

            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }

            Log.d("TAG this is result", result.toString());
            return result.toString();
        }
    }
}
