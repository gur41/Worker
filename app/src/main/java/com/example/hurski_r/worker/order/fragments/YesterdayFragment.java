package com.example.hurski_r.worker.order.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hurski_r.worker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YesterdayFragment extends Fragment {


    public YesterdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yesterday, container, false);
    }

}
