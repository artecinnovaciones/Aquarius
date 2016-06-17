package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.DetallesAdapter;
import com.artecinnovaciones.aquarius.objetos.DetallesItem;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesFragment extends Fragment {

    RecyclerView rv;
    ArrayList<DetallesItem> listD;

    public DetallesFragment(){ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        metodo(view);
        return view;
    }

    public void metodo(View view){
        listD = new ArrayList<DetallesItem>();
        listD.add(new DetallesItem("Enfermedades","Diferentes tipos de enfermedades"));
        listD.add(new DetallesItem("Cuidados", "Como cuidar a tu pez"));
        listD.add(new DetallesItem("Datos curiosos", "Cosas que no sabías hacerca de los peces"));

        rv = ViewUtil.findViewById(view, R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter();
    }

    /*private void agregar(){
        listD.add(new DetallesItem("Enfermedades","Diferentes tipos de enfermedades"));
        listD.add(new DetallesItem("Cuidados", "Como cuidar a tu pez"));
        listD.add(new DetallesItem("Datos curiosos", "Cosas que no sabías hacerca de los peces"));
    }*/

    private void initializeAdapter() {
        DetallesAdapter adapter = new DetallesAdapter(listD, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        rv.setAdapter(adapter);
    }
}
