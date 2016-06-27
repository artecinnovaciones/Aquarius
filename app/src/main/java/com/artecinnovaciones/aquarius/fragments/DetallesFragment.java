package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.DetallesAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesFragment extends Fragment {

    String tipo;
    TextView titulo,info;
    RecyclerView recycler;

    public DetallesFragment(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        detalles(view);

        return view;
    }

    public void detalles(View view) {
        titulo=ViewUtil.findViewById(view,R.id.tipo);
        info=ViewUtil.findViewById(view,R.id.info_tipo);
        recycler=ViewUtil.findViewById(view,R.id.rv);

        GridLayoutManager gridRecycler = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(gridRecycler);
        recycler.setHasFixedSize(true);

        if (tipo.equals("salada")){
            titulo.setText("Peces de agua Salada");
            info.setText("Los peces de agua salada son una excelente opción para aquellas personas que no tienen mucho tiempo para dedicar a sus mascotas pero quieren disfrutar de la belleza de los peces.\n" +
                    "\n" +
                    "Se trata de animales poco complejos que habitan en un acuario aunque, eso si, necesitarás mucha información al respecto si eres un principiante en los peces de agua salada.");
        }else {
            titulo.setText("Peces de agua Dulce");
            info.setText("Existe una gran variedad de peces de agua dulce para elegir cuando estás construyendo y habitando un acuario; dependiendo del tamaño y del equipamiento que instale algunos peces se adaptarán mejores que otros.\n" +
                    "\n" +
                    "Por ejemplo, existen peces de agua dulce que no pueden vivir en un acuario demasiado pequeño, y  por raro que parezca, algunos no pueden estar sólos en un acuario de grandes dimensiones.");
        }

        cargarBd();
    }

    public void cargarBd() {
        if (ArrayListPeces == null) {
            try {
                PecesDulceDao mPeces = BdController.getInstance(getActivity()).pecesdulce();
                List listpeces = mPeces.queryBuilder().list();
                ArrayListPeces = new ArrayList<PecesDulce>();
                for (Object peces : listpeces) {
                    ArrayListPeces.add((PecesDulce) peces);
                }
                mListpeces = ArrayListPeces;
            } catch (Exception e) {
                e.getStackTrace();
            }

            DetallesAdapter adapter = new DetallesAdapter(mListpeces, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                }
            });
            recycler.setAdapter(adapter);
        }


    }

    private ArrayList<PecesDulce> ArrayListPeces;
    private List<PecesDulce> mListpeces;
}
