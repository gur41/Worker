package com.example.hurski_r.worker.order;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.hurski_r.worker.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SingleOrderActivity extends AppCompatActivity {

    private OrderActivity.GetOrders task = null;

    ListView countriesList;

    String str = "https://3dlab.icdc.io/cleaning_api/public/index.php/work/withrel/byid/";
    String id;

    Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new GetOrders().execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_order);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void getOrders() {
        try {
            String url1 = str;
            URL url = new URL(url1+"2");
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

            JSONArray myResponseArrayFirst = new JSONArray(response.toString());
                JSONObject myResponse = myResponseArrayFirst.getJSONObject(0);
                Order order = new Order();
                order.setNumber(myResponse.getString("ID"));
                order.setDate(myResponse.getString("created_at"));
                order.setAddress(new JSONArray(myResponse.getString("subject")).getJSONObject(0).getString("NAME") + " " +
                        new JSONArray(myResponse.getString("placement")).getJSONObject(0).getString("NAME") + " " +
                        new JSONArray(myResponse.getString("placement")).getJSONObject(0).getString("FLOOR") + " " +
                        new JSONArray(myResponse.getString("placement")).getJSONObject(0).getString("ROOM"));
                order.setAdmin(new JSONArray(myResponse.getString("administrator")).getJSONObject(0).getString("NAME") + " " +
                        new JSONArray(myResponse.getString("administrator")).getJSONObject(0).getString("SURNAME"));
                order.setStatus(myResponse.getString("status"));
                this.order = order;


        } catch (Exception e) {
            e.printStackTrace();
        }
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
            showSingleOrder();

        }

        @Override
        protected void onCancelled() {
            task = null;
        }
    }

    public void showSingleOrder() {
        //countriesList = (ListView) findViewById(R.id.orderList);
        //OrderAdapter stateAdapter = new OrderAdapter(this, R.layout.list_test_order, orders);
        //countriesList.setAdapter(stateAdapter);
    }
}
