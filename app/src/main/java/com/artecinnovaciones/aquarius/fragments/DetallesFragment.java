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
import com.artecinnovaciones.aquarius.objetos.DetallesItem;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesFragment extends Fragment {

    String tipo;


    public DetallesFragment(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        cargarBd();

        return view;
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
           Toast.makeText(getActivity(), "s" + mListpeces.get(0).getInformacion(), Toast.LENGTH_LONG).show();
        }
    }


    private ArrayList<PecesDulce> ArrayListPeces;
    private List<PecesDulce> mListpeces;
}
