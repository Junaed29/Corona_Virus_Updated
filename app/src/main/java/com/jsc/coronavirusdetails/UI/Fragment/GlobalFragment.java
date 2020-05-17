package com.jsc.coronavirusdetails.UI.Fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;
import com.jsc.coronavirusdetails.R;
import com.jsc.coronavirusdetails.Utils.MaintainNumber;
import com.jsc.coronavirusdetails.Utils.NetworkHelper;
import com.jsc.coronavirusdetails.ViewModel.CoronaViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalFragment extends Fragment {
    private static final String TAG = "GlobalFragment";
    CoronaViewModel coronaViewModel;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout refreshLayout;
    TextView timeTextView;


    public GlobalFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coronaViewModel = new ViewModelProvider(this).get(CoronaViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        timeTextView = getActivity().findViewById(R.id.timeTextViewId);


        TextView totalInfected = view.findViewById(R.id.globalInfectedTextViewId);
        TextView activeInfected = view.findViewById(R.id.globalActiveInfectedTextViewId);
        TextView totalDeaths = view.findViewById(R.id.globalDeathTextViewId);
        TextView totalRecovered = view.findViewById(R.id.globalRecoveredTextViewId);
        TextView todayInfected = view.findViewById(R.id.globalTodayInfectedTextViewId);
        TextView todayDeaths = view.findViewById(R.id.globalTodayDeathTextViewId);
        TextView infectedPerMili = view.findViewById(R.id.globalInfectedPerMiTextViewId);
        TextView deathPerMili = view.findViewById(R.id.globalDeathsPerOneMilTextViewId);
        refreshLayout = view.findViewById(R.id.swipeRefreshLayoutId);

        progressDialog= ProgressDialog.show(view.getContext(), "Loading...", "Please wait...", true);

        coronaViewModel.getWorldCoronaDetails().observe(getActivity(), new Observer<CoronaLDB_Details>() {
            @Override
            public void onChanged(CoronaLDB_Details coronaLDB_details) {

                Log.d(TAG, "onChanged: "+coronaLDB_details);


                try {
                    totalInfected.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getCases()));
                    activeInfected.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getActive()));
                    totalDeaths.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getDeaths()));
                    totalRecovered.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getRecovered()));
                    todayInfected.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getTodayCases()));
                    todayDeaths.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getTodayDeaths()));
                    infectedPerMili.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getCasesPerOneMillion()));
                    deathPerMili.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getDeathsPerOneMillion()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }
        });


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshButtonClick(view);
            }
        });
        return view;
    }


    private void refreshButtonClick(View view) {
        boolean isNetworkConnected = NetworkHelper.isNetworkAvailable(view.getContext());
        if (isNetworkConnected){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa (dd-MM-yyyy)");
            String datetime = "Last Update : "+sdf.format(c.getTime());

            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("sharedPreferences",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("time",datetime);
            editor.apply();

            coronaViewModel.updateCoronaDetails();

            String time = sharedPreferences.getString("time",null);
            timeTextView.setText(time);
        }else{
            Toast.makeText(view.getContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
        }

        refreshLayout.setRefreshing(false);
    }

}
