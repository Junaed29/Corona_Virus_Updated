package com.jsc.coronavirusdetails.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jsc.coronavirusdetails.R;
import com.jsc.coronavirusdetails.UI.Fragment.CountryFragment;
import com.jsc.coronavirusdetails.UI.Fragment.DeveloperFragment;
import com.jsc.coronavirusdetails.UI.Fragment.GlobalFragment;
import com.jsc.coronavirusdetails.Utils.MaintainNumber;
import com.jsc.coronavirusdetails.Utils.NetworkHelper;
import com.jsc.coronavirusdetails.ViewModel.CoronaViewModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    LinearLayout linearLayout, myselfLinearLayout;

    BottomNavigationView bottomNavigationView;
    TextView timeTextView;

    private boolean isNetworkConnected;

    CoronaViewModel coronaViewModel;
    
    SharedPreferences sharedPreferences;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coronaViewModel = new ViewModelProvider(this).get(CoronaViewModel.class);

        bottomNavigationView = findViewById(R.id.bottom_navigationId);
        bottomNavigationView.setItemIconTintList(null);

        timeTextView = findViewById(R.id.timeTextViewId);
        linearLayout = findViewById(R.id.linearLayoutId);
        myselfLinearLayout = findViewById(R.id.linearLayoutIdMyself);


        sharedPreferences = getSharedPreferences("sharedPreferences",MODE_PRIVATE);

        /******************************* if there is any "firstTime" then return it's value or return default value 'true' ********************************/
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
        isNetworkConnected = NetworkHelper.isNetworkAvailable(this);



        if (isFirstTime){
            if (isNetworkConnected) {
                coronaViewModel.updateCoronaDetails();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa (dd-MM-yyyy)");
                String datetime = "Last Update : " + sdf.format(c.getTime());


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("time", datetime);
                editor.putBoolean("firstTime", false);
                editor.apply();
            }else {
                Toast.makeText(this, "Please Connect to the internet and try again", Toast.LENGTH_LONG).show();
                finish();
            }
        }else if (isNetworkConnected){
            isNetworkConnected = NetworkHelper.isNetworkAvailable(this);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa (dd-MM-yyyy)");
            String datetime = "Last Update : "+sdf.format(c.getTime());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("time",datetime);
            editor.apply();

            coronaViewModel.updateCoronaDetails();
        }

        String time = sharedPreferences.getString("time",null);
        timeTextView.setText(time);

        if (savedInstanceState == null){
            fragment = new GlobalFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_ContainerId,fragment)
                    .commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

    }

    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.globalMenuId:
                    linearLayout.setVisibility(View.VISIBLE);
                    myselfLinearLayout.setVisibility(View.GONE);
                    fragment = new GlobalFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_ContainerId,fragment)
                            .commit();
                    break;
                case R.id.countryMenuId:
                    linearLayout.setVisibility(View.VISIBLE);
                    myselfLinearLayout.setVisibility(View.GONE);
                    fragment = new CountryFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_ContainerId,fragment)
                            .commit();
                    break;
                case R.id.developerMenuId:
                    linearLayout.setVisibility(View.GONE);
                    myselfLinearLayout.setVisibility(View.VISIBLE);
                    fragment = new DeveloperFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_ContainerId,fragment)
                            .commit();
                    break;
            }
            return true;
        }
    };



    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId()==R.id.globalMenuId){
            super.onBackPressed();
        }else {
            bottomNavigationView.setSelectedItemId(R.id.globalMenuId);
        }
    }

}
