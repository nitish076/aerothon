package com.test.airbuzz.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.airbuzz.R;
import com.test.airbuzz.models.AircraftModelClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AeroInputActivity extends AppCompatActivity {

    TextView aircraftModel;
    EditText msnInfo,harLen,fuelCapL,fuelCapR,MaxAltReached, flightNo, destAir, takeoffAir, fuelQtyL, fuelQtyR, roomT, atmP, grossWt;

    EditText timeArr, timeDep, nBusiness, nEco;

    Button postBtn,msnBtn;

    String s;

    AircraftModelClass aircraftModelClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aero_input_fragment_input_page);
        initUI();
        setData();
        fetchfromUI();
        setListener();
    }

    void fetchfromUI(){
        aircraftModelClass = new AircraftModelClass(msnInfo.getText().toString(),MaxAltReached.getText().toString(),fuelQtyL.getText().toString(),fuelQtyR.getText().toString(),roomT.getText().toString(),
                atmP.getText().toString(),grossWt.getText().toString(),destAir.getText().toString(),takeoffAir.getText().toString(),timeArr.getText().toString(),timeDep.getText().toString(),nEco.getText().toString(),nBusiness.getText().toString());
    }

    void initUI(){
        aircraftModel = findViewById(R.id.tv_aircraftModel);
        msnInfo = findViewById(R.id.et_msn);
        harLen = findViewById(R.id.et_harness_len);
        fuelCapL = findViewById(R.id.et_fuel_cap_l);
        fuelCapR = findViewById(R.id.et_fuel_cap_r);
        MaxAltReached = findViewById(R.id.max_alt_reached);
        flightNo = findViewById(R.id.et_flight_no);
        destAir = findViewById(R.id.et_airport_arr);
        takeoffAir = findViewById(R.id.et_airport_dep);
        fuelQtyL = findViewById(R.id.et_fuel_qty_l);
        fuelQtyR = findViewById(R.id.et_fuel_qty_r);
        roomT = findViewById(R.id.et_room_temp);
        atmP = findViewById(R.id.et_atm_pr);
        grossWt = findViewById(R.id.et_gross_wt);

        timeArr = findViewById(R.id.time_arrival);
        timeDep = findViewById(R.id.time_dep);
        nBusiness = findViewById(R.id.et_business_seats);
        nEco = findViewById(R.id.et_eco_seats);

        msnBtn = findViewById(R.id.msn_fetch_btn);
        postBtn = findViewById(R.id.post_btn);
    }

    void setListener(){

        msnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAGBTNCLICK","BTN LISTENER");
                s = msnInfo.getText().toString();
                new msnPostData().execute("http://192.168.43.250:8080/check_MSN");

            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = msnInfo.getText().toString();
                new PostDataTask().execute("http://192.168.43.250:8080/flight_details_submit");

            }
        });
    }

    void setData(){
        //Log.d("TAGG",getIntent().getStringExtra("aircraftName"));
        aircraftModel.setText(getIntent().getStringExtra("aircraftName"));
    }


    class msnPostData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(AeroInputActivity.this);
            progressDialog.setMessage("Inserting data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return postData(params[0]);
            } catch (IOException ex) {
                Log.e("TAG","Exception:",ex);
                return "Network error !";
            } catch (JSONException ex) {
                return "Data Invalid !";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ///Result Point




            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String postData(String urlPath) throws IOException, JSONException {

            StringBuilder result = new StringBuilder();
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;

            try {
                //Create data to send to server
                JSONObject dataToSend = new JSONObject();
/*                dataToSend.put("username", fieldA.getText().toString());
                dataToSend.put("password", fieldB.getText().toString());*/

/*
                dataToSend.put("username", "unamefield");
                dataToSend.put("password", "passfield");
*/

                Log.d("MSNRAG",s);
                dataToSend.put("msnNo", s);



                //Initialize and config request, then connect to server.
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);  //enable output (body data)
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

/*
                int status = urlConnection.getResponseCode();

                Log.d("TAG",Integer.toString(status));
*/
                //Write data into server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                Log.d("DataBeingSent",dataToSend.toString());
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

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
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }

            return result.toString();
        }
    }




    class PostDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(AeroInputActivity.this);
            progressDialog.setMessage("Inserting data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return postData(params[0]);
            } catch (IOException ex) {
                Log.e("TAG","Exception:",ex);
                return "Network error !";
            } catch (JSONException ex) {
                return "Data Invalid !";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ///Result Point


          


            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String postData(String urlPath) throws IOException, JSONException {

            StringBuilder result = new StringBuilder();
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;

            try {
                //Create data to send to server
                JSONObject dataToSend = new JSONObject();
/*                dataToSend.put("username", fieldA.getText().toString());
                dataToSend.put("password", fieldB.getText().toString());*/

/*
                dataToSend.put("username", "unamefield");
                dataToSend.put("password", "passfield");
*/

                Log.d("MSNRAG",s);
                dataToSend.put("msnNo", s);

                String messageInJson = new Gson().toJson(aircraftModelClass);
                //Initialize and config request, then connect to server.
                Log.d("GSON SEND",messageInJson);
                URL url = new URL(urlPath);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);  //enable output (body data)
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

/*
                int status = urlConnection.getResponseCode();

                Log.d("TAG",Integer.toString(status));
*/
                //Write data into server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                Log.d("DataBeingSent",dataToSend.toString());
                bufferedWriter.write(messageInJson);
                bufferedWriter.flush();

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
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }

            return result.toString();
        }
    }


}