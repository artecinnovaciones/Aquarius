package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.DetallesAdapter;
import com.artecinnovaciones.aquarius.adapters.DetallesItem;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesFragment extends Fragment {

    ListView ld;
    ArrayList<DetallesItem> listD;

    public DetallesFragment(){ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        metodo(view);
        return view;
    }

    public void metodo(View view){
        ld= ViewUtil.findViewById(view, R.id.list_detalles);
        listD = agregar();

        DetallesAdapter adapter= new DetallesAdapter(getActivity(),listD);
        ld.setAdapter(adapter);
    }

    private ArrayList<DetallesItem> agregar(){
        ArrayList<DetallesItem> datos = new ArrayList<DetallesItem>();
        datos.add(new DetallesItem("Enfermedades","Diferentes tipos de enfermedades"));
        datos.add(new DetallesItem("Cuidados","Como cuidar a tu pez"));
        datos.add(new DetallesItem("Datos curiosos","Cosas que no sabías hacerca de los peces"));
        return  datos;
    }
}
