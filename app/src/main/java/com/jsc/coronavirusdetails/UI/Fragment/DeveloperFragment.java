package com.jsc.coronavirusdetails.UI.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jsc.coronavirusdetails.R;
import com.jsc.coronavirusdetails.Utils.TextViewUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperFragment extends Fragment {




    public DeveloperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_devloper, container, false);

        Toast.makeText(view.getContext(), "GlobalFragment", Toast.LENGTH_SHORT).show();

        TextView emailTextView  = view.findViewById(R.id.emailNumberTextView);
        TextView phoneTextView  = view.findViewById(R.id.phoneNumberTextView);

        if (emailTextView != null){
            TextViewUtils.removeUnderlines(emailTextView);
        }

        if (phoneTextView != null){
            TextViewUtils.removeUnderlines(phoneTextView);
        }


        return view;
    }

}
