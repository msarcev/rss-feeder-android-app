package com.grimar.feedy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roudj.feedy.R;

public class FragmentHiddenList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.hidden_list_layout, container, false);

        setRetainInstance(true);
        return view;
    }

}
