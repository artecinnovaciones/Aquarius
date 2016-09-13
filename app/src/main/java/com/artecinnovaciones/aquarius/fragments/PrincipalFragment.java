package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.artecinnovaciones.aquarius.PecerasActivity;
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
              //  startActivity(new Intent(getActivity(), PecesActivity.class));
                PacificosFragment detFrag = new PacificosFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frag_principal,detFrag).addToBackStack(null).commit();
            }
        });

        LinearLayout enfermedades = (LinearLayout) ViewUtil.findViewById(view, R.id.enfermedades_btn);
        enfermedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_pez="enfermedades";
                EnfermedadesFragment detFrag = new EnfermedadesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frag_principal,detFrag).addToBackStack(null).commit();
            }
        });

        LinearLayout pecera = (LinearLayout) ViewUtil.findViewById(view, R.id.peceras_btn);
        pecera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PecerasActivity.class));
            }
        });
    }
}
