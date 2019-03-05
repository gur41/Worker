package com.example.hurski_r.worker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.graphics.Color.*;


public class OrderActivity extends AppCompatActivity {

    private GetOrders task = null;

    ListView countriesList;

    String str = "https://3dlab.icdc.io/cleaning_api/public/index.php/work/getworkwithrelat/111";

    ArrayList<Order> orders = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new GetOrders().execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void getOrders(){
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {*/
                try {
                    String url1 = str;
                    URL url = new URL(url1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    //conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    //conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                    //Log.i("JSON", json.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());
                    Log.i("JSON", String.valueOf(response));

                    conn.disconnect();
                    //JSONArray myResponseArray = new JSONArray(response.toString());
                    //JSONObject myResponse = myResponseArray.getJSONObject(0);

                    JSONArray myResponseArrayFirst = new JSONArray(response.toString());
                    JSONArray myResponseArray = myResponseArrayFirst.getJSONArray(0);
                    //for(int i= 0; i < myResponseArrayFirst.length();i++){
                    for(int i= 0; i < 2;i++){
                        //System.out.println(myResponseArray.toString());
                        JSONObject myResponse = myResponseArrayFirst.getJSONArray(i).getJSONObject(0);
                        System.out.println(myResponse.toString());
                        Order order = new Order();
                        order.setNumber(myResponse.getString("ID"));
                        order.setDate(myResponse.getString("created_at"));
                        order.setAddress(new JSONArray(myResponse.getString("subject")).getJSONObject(0).getString("NAME")+" "+
                                new JSONArray(myResponse.getString("placement")).getJSONObject(0).getString("NAME")+" "+
                                new JSONArray(myResponse.getString("placement")).getJSONObject(0).getString("FLOOR")+" "+
                                new JSONArray(myResponse.getString("placement")).getJSONObject(0).getString("ROOM"));
                        order.setAdmin(new JSONArray(myResponse.getString("administrator")).getJSONObject(0).getString("NAME")+" "+
                                new JSONArray(myResponse.getString("administrator")).getJSONObject(0).getString("SURNAME"));
                        order.setStatus("Работает");
                        orders.add(order);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            /*}
        });

        thread.start();*/
    }

    public class GetOrders extends AsyncTask<Void, Void, Boolean> {

        public GetOrders() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            getOrders();

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            show();
            //showOrders();
        }

        @Override
        protected void onCancelled() {
            task = null;
        }
    }

    public void show(){
        countriesList = (ListView) findViewById(R.id.orderList);
        // создаем адаптер
        OrderAdapter stateAdapter = new OrderAdapter(this, R.layout.list_test_order, orders);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
    }

    @SuppressLint("ResourceType")
    public void showOrders(){
        /*TextView textView1 = (TextView)findViewById(R.id.numberOfOrder);
        //textView1.setVisibility(View.INVISIBLE);
        TextView textView2 = (TextView)findViewById(R.id.numberOfData);
        textView2.setVisibility(View.INVISIBLE);
        TextView textView3 = (TextView)findViewById(R.id.numberOfAdmin);
        textView3.setVisibility(View.INVISIBLE);
        TextView textView4 = (TextView)findViewById(R.id.numberOfAddress);
        textView4.setVisibility(View.INVISIBLE);
        View line = (View)findViewById(R.id.line1);
        line.setVisibility(View.INVISIBLE);*/
        int i=0;
        for (Order order: orders) {
            ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.layout_order);
            TextView textView5 = new TextView(this);
            textView5.setTextSize(20);
            textView5.setText(order.getNumber());
            textView5.setTextColor(BLACK);
            textView5.setId(100+i);
            ConstraintLayout.LayoutParams lp5 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lp5.setMargins(75,25,25,25);
            textView5.setLayoutParams(lp5);
            constraintLayout.addView(textView5);

            TextView textView6 = new TextView(this);
            textView6.setTextSize(18);
            textView6.setText(order.getDate());
            textView6.setTextColor(BLACK);
            textView6.setId(101+i);
            ConstraintLayout.LayoutParams lp6 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lp6.setMargins(50,25,25,25);
            textView6.setLayoutParams(lp6);
            constraintLayout.addView(textView6);

            TextView textView7 = new TextView(this);
            textView7.setTextSize(18);
            textView7.setText(order.getAdmin());
            textView7.setTextColor(BLACK);
            textView7.setId(102+i);
            ConstraintLayout.LayoutParams lp7 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lp7.setMargins(50,25,25,25);
            textView7.setLayoutParams(lp7);
            constraintLayout.addView(textView7);

            TextView textView8 = new TextView(this);
            textView8.setTextSize(20);
            textView8.setText(order.getAddress());
            textView8.setTextColor(BLACK);
            textView8.setId(103+i);
            ConstraintLayout.LayoutParams lp8 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lp8.setMargins(50,25,25,25);
            textView8.setLayoutParams(lp8);
            constraintLayout.addView(textView8);

            TextView textView9 = new TextView(this);
            textView9.setTextSize(20);
            textView9.setText(order.getStatus());
            textView9.setTextColor(BLACK);
            textView9.setId(104+i);
            ConstraintLayout.LayoutParams lp9 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lp9.setMargins(25,25,100,25);
            textView9.setLayoutParams(lp9);
            constraintLayout.addView(textView9);

            View view = new View(this);

            view.setBackgroundColor(GRAY);
            view.setId(105+i);
            ConstraintLayout.LayoutParams lp10 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 3);

            lp10.setMargins(0,25,0,25);
            view.setLayoutParams(lp10);
            constraintLayout.addView(view);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            if (i==0) {
                constraintSet.connect(textView5.getId(), ConstraintSet.TOP, R.id.toolbar, ConstraintSet.BOTTOM, 15);
                constraintSet.connect(textView9.getId(), ConstraintSet.TOP, R.id.toolbar, ConstraintSet.BOTTOM, 15);
            }
            else{
                constraintSet.connect(textView5.getId(), ConstraintSet.TOP, 105, ConstraintSet.BOTTOM, 15);
                constraintSet.connect(textView9.getId(), ConstraintSet.TOP, 105, ConstraintSet.BOTTOM, 15);
            }
            constraintSet.connect(textView5.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
            constraintSet.connect(textView6.getId(), ConstraintSet.TOP, textView5.getId(), ConstraintSet.BOTTOM, 15);
            constraintSet.connect(textView6.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
            constraintSet.connect(textView7.getId(), ConstraintSet.TOP, textView5.getId(), ConstraintSet.BOTTOM, 15);
            constraintSet.connect(textView7.getId(),ConstraintSet.LEFT,textView6.getId(),ConstraintSet.RIGHT,0);
            constraintSet.connect(textView8.getId(), ConstraintSet.TOP, textView6.getId(), ConstraintSet.BOTTOM, 15);
            constraintSet.connect(textView8.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
            constraintSet.connect(textView9.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
            constraintSet.connect(view.getId(), ConstraintSet.TOP, textView8.getId(), ConstraintSet.BOTTOM, 15);
            constraintSet.applyTo(constraintLayout);
            i=i+5;
        }
    }





}
