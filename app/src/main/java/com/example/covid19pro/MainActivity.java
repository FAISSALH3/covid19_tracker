package com.example.covid19pro;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<CoronaItem> coronaItemmArrayList;
    private RequestQueue requestQueue;
    private TextView dailyDeaths , dailyConfirm , dailyReco , dateHeader , totalConfirm , totalRecovered , totalDeath;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dailyConfirm=findViewById(R.id.dailyConfirm);
        dailyDeaths = findViewById(R.id.dailyDeath);
        dailyReco = findViewById(R.id.dailyRecovered);
        dateHeader = findViewById(R.id.dateHeader);
        totalRecovered = findViewById(R.id.totalRecovered);
        totalConfirm = findViewById(R.id.totalConfirm);
        totalDeath = findViewById(R.id.totalDeath);

        recyclerView =findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        coronaItemmArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();
        


    }

    private void jsonParse() {
        String url="https://api.covid19india.org/v4/min/timeseries.min.json";

        //HttpURLConnection conn = (HttpURLConnection) url.

        final JsonObjectRequest request = new
                JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    //first fetch the header and display it
                    //header consist Total and Today's statistics
                    //for today's details
                    //statewise hold data of today's cases at index 0
                    JSONArray todayAndTotalDataArray = response.getJSONArray("state_wise");
                    JSONObject todayAndTotalDataJsonObject = todayAndTotalDataArray.getJSONObject(0);

                    String dailyConfirmed = todayAndTotalDataJsonObject.getString("Daily Confirmed");
                    String dailyDeath = todayAndTotalDataJsonObject.getString("Daily Deceased");
                    String dailyRecovered = todayAndTotalDataJsonObject.getString("Daily Recovered");
                    String dataHeader = todayAndTotalDataJsonObject.getString("lastUpdatedTime").substring(0,5);
                    dataHeader = getFormattedDate(dataHeader);
                    dailyConfirm.setText(dailyConfirmed);
                    dailyReco.setText(dailyRecovered);
                    dailyDeaths.setText(dailyDeath);
                    dateHeader.setText(dataHeader);

                    String TotalDeathsFetched = todayAndTotalDataJsonObject.getString("deaths");
                    String TotalRecoveredFetched = todayAndTotalDataJsonObject.getString("recovered");
                    String TotalConfirmedFetched = todayAndTotalDataJsonObject.getString("confirmed");
                    totalConfirm.setText(TotalConfirmedFetched);
                    totalDeath.setText(TotalDeathsFetched);
                    totalRecovered.setText(TotalRecoveredFetched);
                        //now let's fetch the data for all the states
                    for(int i =1 ; i<todayAndTotalDataArray.length() ; i++){
                        JSONObject statewiseArrayJsonObject = todayAndTotalDataArray.getJSONObject(i);
                        String active = statewiseArrayJsonObject.getString("Active");
                        String deaths = statewiseArrayJsonObject.getString("Deaths");
                        String recovered = statewiseArrayJsonObject.getString("Recovered");
                        String state = statewiseArrayJsonObject.getString("State");
                        String confirmed = statewiseArrayJsonObject.getString("Confirmed");
                        String lastUpdated = statewiseArrayJsonObject.getString("Last_Updated_Time");


                        // today's data

                        String todayactive = statewiseArrayJsonObject.getString("Delta_Active");
                        String todaydeaths = statewiseArrayJsonObject.getString("Delta_Deaths");
                        String todayrecovered = statewiseArrayJsonObject.getString("Delta_Recovered");

                        String todayconfirmed = statewiseArrayJsonObject.getString("Delta_Confirmed");

                        CoronaItem coronaItem = new CoronaItem(state , deaths , active , recovered , confirmed , lastUpdated , todayactive ,todaydeaths , todayrecovered);
                        coronaItemmArrayList.add(coronaItem);
                    }
                    //now let's set up the recycler to display the
                    RecyclerViewAdapter recyclerViewAdapet = new  RecyclerViewAdapter(MainActivity.this,coronaItemmArrayList );

                }
                //try statement must have the catch
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) ;

    }
    private String getFormattedDate(String dateHeader){
        switch(dateHeader.substring(3,5)){
            case "01":
                return dateHeader.substring(0,2) + "Jan";
            case "02":
                return dateHeader.substring(0,2) + "Feb";
            case "03":
                return dateHeader.substring(0,2) + "Mar";
            case "04":
                return dateHeader.substring(0,2) + "Apr";
            case "05":
                return dateHeader.substring(0,2) + "May";
            case "06":
                    return dateHeader.substring(0,2) + "Jun";
            case "07":
                return dateHeader.substring(0,2) + "Jul";

            case "08":
                return dateHeader.substring(0,2) + "Aug";
            case "09":
                return dateHeader.substring(0,2) + "Sep";
            case "10":
                return dateHeader.substring(0,2) + "Oct";
            case "11":
                return dateHeader.substring(0,2) + "Nov";
            case "12":
                return dateHeader.substring(0,2) + "Dec";
            default:
                return null;

        }
    }
}