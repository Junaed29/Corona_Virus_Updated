package com.jsc.coronavirusdetails.UI.Fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;
import com.jsc.coronavirusdetails.R;
import com.jsc.coronavirusdetails.UI.Adapter.CoronaRecyclerViewAdapter;
import com.jsc.coronavirusdetails.UI.MainActivity;
import com.jsc.coronavirusdetails.Utils.MaintainNumber;
import com.jsc.coronavirusdetails.Utils.NetworkHelper;
import com.jsc.coronavirusdetails.ViewModel.CoronaViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {

    private CoronaRecyclerViewAdapter coronaRecyclerViewAdapter;
    private RecyclerView recyclerView;
    private CoronaViewModel coronaViewModel;
    private ProgressDialog progressDialog;
    private SearchView searchView;
    SwipeRefreshLayout refreshLayout;

    List<CoronaLDB_Details> coronaLDB_detail_local;
    List<String> indexNumber;

    TextView timeTextView;


    public CountryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coronaViewModel = new ViewModelProvider(this).get(CoronaViewModel.class);
        coronaRecyclerViewAdapter = new CoronaRecyclerViewAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewId);
        refreshLayout = view.findViewById(R.id.CountrySwipeRefreshLayoutId);
        timeTextView = getActivity().findViewById(R.id.timeTextViewId);

        indexNumber = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        /*****************Using General RecyclerView Adapter***************/
        recyclerView.setAdapter(coronaRecyclerViewAdapter);
        searchView = view.findViewById(R.id.searchViewId);



        /*****************Using General RecyclerView Adapter***************/
        coronaRecyclerViewAdapter = new CoronaRecyclerViewAdapter();
        recyclerView.setAdapter(coronaRecyclerViewAdapter);


        progressDialog= ProgressDialog.show(view.getContext(), "Loading...", "Please wait...", true);

        coronaViewModel.getAllCoronaDetails().observe(getActivity(), new Observer<List<CoronaLDB_Details>>() {
            @Override
            public void onChanged(List<CoronaLDB_Details> coronaLDB_details) {
                try {
                    setCoronaList(coronaLDB_details);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        /*****************Using General RecyclerView Adapter***************/
        coronaRecyclerViewAdapter.setOnItemClickListener(new CoronaRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CoronaLDB_Details coronaDetails) {
                setDialog(coronaDetails);
            }

        });


        searchView.setOnQueryTextListener(listener);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshButtonClick(view);
            }
        });

        return view;
    }

    private void setCoronaList(List<CoronaLDB_Details> coronaLDB_details){
        coronaLDB_details.remove(0);
        for (int i = 1; i <= coronaLDB_details.size();i++){
            indexNumber.add(String.valueOf(i));
        }

        this.coronaLDB_detail_local = coronaLDB_details;
        coronaRecyclerViewAdapter.setCoronaDetails(coronaLDB_details,indexNumber);
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    private void setDialog(CoronaLDB_Details coronaLDB_details){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.country_details_layout);

        dialog.setCanceledOnTouchOutside(false);

        ImageButton imageButton = dialog.findViewById(R.id.crossButtonId);
        TextView countryName = dialog.findViewById(R.id.CountryHeadingId);
        TextView totalInfected = dialog.findViewById(R.id.countryInfectedTextViewId);
        TextView activeInfected = dialog.findViewById(R.id.countryActiveInfectedTextViewId);
        TextView totalDeaths = dialog.findViewById(R.id.countryDeathTextViewId);
        TextView totalRecovered = dialog.findViewById(R.id.countryRecoveredTextViewId);
        TextView todayInfected = dialog.findViewById(R.id.countryTodayInfectedTextViewId);
        TextView todayDeaths = dialog.findViewById(R.id.countryTodayDeathTextViewId);
        TextView infectedPerMili = dialog.findViewById(R.id.countryInfectedPerMiTextViewId);
        TextView deathPerMili = dialog.findViewById(R.id.countryDeathsPerOneMilTextViewId);
        TextView totalTest = dialog.findViewById(R.id.countryTotalTestTextViewId);


        countryName.setText(coronaLDB_details.getCountry());
        totalInfected.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getCases()));

        activeInfected.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getActive()));

        totalDeaths.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getDeaths()));

        totalRecovered.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getRecovered()));

        if (coronaLDB_details.getTodayCases()==0){
            todayInfected.setText("Update not provided yet");
        }else {
            todayInfected.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getTodayCases()));
        }

        if (coronaLDB_details.getTodayDeaths()==0){
            todayDeaths.setText("Update not provided yet");
        }else {
            todayDeaths.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getTodayDeaths()));
        }

        infectedPerMili.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getCasesPerOneMillion()));

        deathPerMili.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getDeathsPerOneMillion()));

        totalTest.setText(MaintainNumber.getRoundOffValue(coronaLDB_details.getTotalTests()));

        imageButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
        Window window = dialog.getWindow();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    SearchView.OnQueryTextListener  listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            List<CoronaLDB_Details> coronaLDB_details_update = new ArrayList<>();
            List<String> indexNumber_update = new ArrayList<>();

            int i = 0;
            for(CoronaLDB_Details name : coronaLDB_detail_local){
                String countryName  = indexNumber.get(i)+" "+name.getCountry();
                //Get every letter into same order and find the match
                if (countryName.toLowerCase().contains(newText.toLowerCase())){
                    // Added to the new List
                    coronaLDB_details_update.add(name);
                    indexNumber_update.add(indexNumber.get(i));
                }
                i++;
            }
            coronaRecyclerViewAdapter.setCoronaDetails(coronaLDB_details_update,indexNumber_update);
            return true;
        }
    };


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
