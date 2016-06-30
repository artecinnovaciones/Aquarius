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

    TextView info, cuidados, alimentacion;

    public TiposFragment (String informacion, String cuidado, String alimentar){
        this.informacion=informacion;
        this.cuidado=cuidado;
        this.alimentar=alimentar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tipos, container, false);
        metodo(view);
        return view;
    }

    private void metodo(View view) {
        info= ViewUtil.findViewById(view,R.id.informacionInfo);
        cuidados=ViewUtil.findViewById(view,R.id.cuidadosInfo);
        alimentacion=ViewUtil.findViewById(view,R.id.alimentacionInfo);

        info.setText(informacion);
        cuidados.setText(cuidado);
        alimentacion.setText(alimentar);
    }


}
