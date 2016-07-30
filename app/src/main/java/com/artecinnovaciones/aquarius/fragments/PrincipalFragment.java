package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

/**
 * Created by LAP-NIDIA on 28/07/2016.
 */
public class PrincipalFragment extends Fragment {

    public static String tipo_pez;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_main,container,false);
        principal(view);
        return view;
    }

    private void principal(View view) {
        LinearLayout dul = (LinearLayout) ViewUtil.findViewById(view,R.id.peces_dul);
        dul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_pez="dulce";
                DetallesFragment detFrag = new DetallesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frag_principal, detFrag).addToBackStack(null).commit();
            }
        });

        LinearLayout enfermedades = (LinearLayout) ViewUtil.findViewById(view, R.id.enfermedades_btn);
        enfermedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_pez="enfermedades";
                DetallesFragment detFrag = new DetallesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frag_principal,detFrag).addToBackStack(null).commit();
            }
        });
    }
}
