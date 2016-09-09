package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

public class TiposFragment extends Fragment {

    String informacion, cuidado, alimentar;
    int band;
    TextView info, cuidados, alimentacion;

    public TiposFragment(String informacion, String cuidado, String alimentar, int bandera) {
        this.informacion = informacion;
        this.cuidado = cuidado;
        this.alimentar = alimentar;
        this.band = bandera;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tipos, container, false);
        metodo(view);
        return view;
    }

    private void metodo(View view) {
        if (band == 0) {
            TextView titulo1 = ViewUtil.findViewById(view, R.id.informacionTitulo);
            TextView titulo2 = ViewUtil.findViewById(view, R.id.cuidadosTitulo);
            TextView titulo3 = ViewUtil.findViewById(view, R.id.alimentacionTitulo);
            titulo1.setText("Síntomas");
            titulo2.setText("Causas");
            titulo3.setText("Tratamiento");
        }
        info = ViewUtil.findViewById(view, R.id.informacionInfo);
        cuidados = ViewUtil.findViewById(view, R.id.cuidadosInfo);
        alimentacion = ViewUtil.findViewById(view, R.id.alimentacionInfo);

        info.setText(informacion);
        cuidados.setText(cuidado);
        alimentacion.setText(alimentar);
    }


}
