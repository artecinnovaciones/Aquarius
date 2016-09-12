package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artecinnovaciones.aquarius.R;

/**
 * Created by LAP-NIDIA on 12/09/2016.
 */
public class IdeasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ideas,container,false);
        return view;
    }
}
