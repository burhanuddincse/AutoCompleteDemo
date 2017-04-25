package com.autocompletedemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.autocompletedemo.adapter.RestaurantListAdapter;
import com.autocompletedemo.global.JSONfunctions;
import com.autocompletedemo.global.Ultils;
import com.autocompletedemo.model.RestaurantList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    AutoCompleteTextView autoCompleteTextView1;
    RestaurantListAdapter adapter;
    ActionBar actionbar;
    TextView tvRestuarantsCount;
    ListView list;
    ProgressDialog pDialog;
    String adminarea, jsonStr, cuisine_selected = "-1", cuisine_selected_name = "All", jsonStr1;
    JSONObject json, json1;
    JSONArray restaurant_data = null, res_categories = null;

    String categorystr = "";
    String[] available_categories;
    ArrayList<RestaurantList> restaurant_list;

    ArrayList<String> cuisineid_array;
    ArrayList<String> cuisinename_array;
    boolean isChecked = false;

    String[] restidarr, restnamearr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(true);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage(getResources().getString(
                R.string.loading_content));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);

        available_categories = new String[]{"Indian", "Caribbean", "Pizza", "Italian", "Desserts", "Pakistani", "Buffets", "American (Traditional)",
                "Burgers", "Vegetarian", "Thai", "Mexican", "Lounges", "Seafood"};

        list = (ListView)findViewById(R.id.list);
        tvRestuarantsCount = (TextView)findViewById(R.id.tvRestuarantsCount);
        autoCompleteTextView1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2, long id)
            {
                Toast.makeText(getApplicationContext(), String.valueOf(arg0.getItemAtPosition(arg2)), Toast.LENGTH_SHORT).show();
            }
        });

        if(Ultils.checkInternetConnection(MainActivity.this))
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new JSONParseRestaurants()
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else {
                new JSONParseRestaurants().execute();
            }
        }
        else
        {
            //setActionTextColor(getResources().getColor(android.R.color.holo_red_light ));
            Snackbar snack = Snackbar.make(getWindow().getDecorView().getRootView(), getResources().getString(R.string.internet), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null);
            ViewGroup group = (ViewGroup) snack.getView();
            group.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            snack.show();
        }
    }

    private class JSONParseRestaurants extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            JSONfunctions sh = new JSONfunctions();
            jsonStr = sh.makeServiceCall(getResources().getString(R.string.products), JSONfunctions.GET);
            Log.e("jsonStr","" + jsonStr);
            try
            {
                json = new JSONObject(jsonStr);
                restaurant_data = json.getJSONArray("products");
                if(restaurant_data.length() > 0)
                {
                    restnamearr = new String[restaurant_data.length()];
                    restidarr = new String[restaurant_data.length()];

                    restaurant_list = new ArrayList<RestaurantList>();
                    for(int i=0; i<restaurant_data.length(); i++)
                    {
                        categorystr = "";
                        JSONObject a = restaurant_data.getJSONObject(i);

                    //    String rest_id = a.getString("rest_id");
                        String rest_name = a.getString("name");
                        restnamearr[i] = rest_name;
                   //     restidarr[i] = rest_id;
                        //        Log.e("rest_name","" + rest_name);
                /*        String adress = a.getString("adress");
                        String city = a.getString("city");
                        String state = a.getString("state");
                        String country = a.getString("country");
                        String email_id = a.getString("email_id");
                        String is_pickup = a.getString("is_pickup");
                        String is_delivery = a.getString("is_delivery");
                        String sys_delivery = a.getString("sys_delivery");
                        String is_preorder = a.getString("is_preorder");
                        String rest_image = getResources().getString(R.string.images) + a.getString("image");
                        String newstring =	rest_image.replace("\"/", "/");
                        String rest_area = a.getString("rest_area");
                        String minimum_order = a.getString("minimum_order");

                        RestaurantList restaurant = new RestaurantList();


                            restaurant.setRestID(rest_id);
                            restaurant.setRestName(rest_name);
                            restaurant.setAddress(adress);
                            restaurant.setCity(city);
                            restaurant.setState(state);
                            restaurant.setCountry(country);
                            restaurant.setEmail(email_id);
                            restaurant.setIsPickup(is_pickup);
                            restaurant.setIsDelivery(is_delivery);
                            restaurant.setSysDelivery(sys_delivery);
                            restaurant.setIsPreorder(is_preorder);
                            restaurant.setRestImage(newstring);
                            restaurant.setRestArea(rest_area);
                            restaurant.setDelMinimumOrder(minimum_order);


                                restaurant.setRatingImage("");



                        restaurant_list.add(restaurant);
                        */
                    }
                }
            }
            catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            if(restaurant_data.length() > 0)
            {
                ArrayAdapter<String> adapterauto = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,restnamearr);
                autoCompleteTextView1.setAdapter(adapterauto);

                tvRestuarantsCount.setText("(" + restaurant_data.length() + ") Results");

          //      adapter = new RestaurantListAdapter(MainActivity.this,
          //              R.layout.hotels_v, restaurant_list);
          //      list.setAdapter(adapter);
                //      UtilityListView.setListViewHeightBasedOnChildren(list);
                //list.setOnItemClickListener(listViewOnClick);
            }
            else
            {
                Snackbar snack = Snackbar.make(getWindow().getDecorView().getRootView(),
                        "Data not available!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null);
                snack.show();
            }

            pDialog.dismiss();
            list.setVisibility(View.VISIBLE);
        }
    }
}
