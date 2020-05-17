package com.jsc.coronavirusdetails.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;
import com.jsc.coronavirusdetails.R;
import com.jsc.coronavirusdetails.Utils.MaintainNumber;

import java.util.ArrayList;
import java.util.List;

/**************************Adapter Without ListAdapter**************************/

/**************************Adapter RecyclerView Adapter**************************/

public class CoronaRecyclerViewAdapter extends RecyclerView.Adapter<CoronaRecyclerViewAdapter.NoteHolder> {
    List<CoronaLDB_Details> coronaDetails = new ArrayList<>();
    List<String> indexNumber = new ArrayList<>();

    /****Same instance which interface created below****/
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sample_country_item, parent, false);
        return new NoteHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        CoronaLDB_Details currentCoronaDetails = coronaDetails.get(position);
        String index = indexNumber.get(position);
        String title = index+". "+currentCoronaDetails.getCountry();
        int totalInfected = currentCoronaDetails.getCases();
        int totalDeath = currentCoronaDetails.getDeaths();


        holder.countryNameTextView.setText(title);
        holder.totalInfectedTextView.setText(MaintainNumber.getRoundOffValue(totalInfected));
        holder.totalDeathTextView.setText(MaintainNumber.getRoundOffValue(totalDeath));

    }

    public void setCoronaDetails(List<CoronaLDB_Details> coronaDetails,List<String> indexNumber) {
        this.indexNumber = indexNumber;
        this.coronaDetails = coronaDetails;
        notifyDataSetChanged();
    }


    public CoronaLDB_Details getNoteAt(int position) {
        return coronaDetails.get(position);
    }


    @Override
    public int getItemCount() {
        return coronaDetails.size();
    }


    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView countryNameTextView;
        private TextView totalInfectedTextView;
        private TextView totalDeathTextView;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            countryNameTextView = itemView.findViewById(R.id.countryNameTextViewId);
            totalInfectedTextView = itemView.findViewById(R.id.countryTotalInfectedId);
            totalDeathTextView = itemView.findViewById(R.id.countryTotalDeathId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        /*******pass the specific note reference to the override method*******/
                        listener.onItemClick(coronaDetails.get(position));
                    }
                }
            });
        }
    }


    /******interface for click listener******/
    public interface OnItemClickListener {
        void onItemClick(CoronaLDB_Details coronaDetails);
    }


    /*******From other class setOnItemClickListener(OnItemClickListener listener) method will call*****/
    public void setOnItemClickListener(OnItemClickListener listener) {
        /********Connect this class listener with this method local parameter******/
        this.listener = listener;
    }
}

