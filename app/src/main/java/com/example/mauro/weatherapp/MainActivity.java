package com.example.mauro.weatherapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mauro.weatherapp.data.MyList;
import com.example.mauro.weatherapp.data.ResultApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "b110dd5e91ba85397f2663b4b17d7161";
    private static final int ID_MEXICO = 3530597;
    private static final int ID_ATLANTA = 4180439;
    private static final int DAYS = 9;
    private static final String TAG = "MainActivityTAG_";

    private Observer<ResultApi> observer;
    private Consumer<ResultApi> consumer;
    private WeatherService weatherService;

    private RecyclerView recyclerView;
    private List<MyList> myList;
    private WeatherAdapter weatherAdapter;

    private DrawerLayout drawerLayout;
    private TextView textViewCity;
    private TextView textViewType;
    private TextView textViewDays;
    private Toolbar toolbar;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCity = (TextView) findViewById(R.id.a_main_textCity);
        textViewType = (TextView) findViewById(R.id.a_main_textType);
        textViewDays = (TextView) findViewById(R.id.a_main_textDays);

        textViewType.setText(getString(R.string.type_request));
        textViewDays.setText(getString(R.string.num_days, DAYS));


        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Set up the navigation drawer.
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        setUpRecyclerView();

        observer = new Observer<ResultApi>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: " + d);
            }

            @Override
            public void onNext(@NonNull ResultApi resultApi) {
                Log.d(TAG, "onNext: " + resultApi.getCity().getName());
                textViewCity.setText(resultApi.getCity().getName());
                List<MyList> myLists = resultApi.getMyList();
                for (MyList list : myLists) {
                    Log.d(TAG, "onNext: " + list.toString());
                }
                updateRecyclerView(myLists);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        weatherService = new WeatherService.Factory().createUserService();

        getData();
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.a_main_recyclerview);
        myList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(myList);

        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void updateRecyclerView(List<MyList> weatherList) {
        myList.clear();
        myList.addAll(weatherList);
        weatherAdapter.notifyDataSetChanged();
    }

    public void getData() {
        weatherService.getWeatherDaily(ID_ATLANTA, API_KEY, DAYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.list_navigation_menu_item:
                                // Do nothing, we're already on that screen
                                break;
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
