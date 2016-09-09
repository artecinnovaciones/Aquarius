package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.EnfermedadesAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedades;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedadesDao;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP-NIDIA on 09/08/2016.
 */
public class EnfermedadesFragment extends Fragment {

    RecyclerView recycler;
    private ArrayList<PecesEnfermedades> ArrayListEnfermedades;
    private List<PecesEnfermedades> mListEnfermedades;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enfermedades, container, false);
        metodo(view);

        return view;
    }

    public void metodo (View v){
        recycler= ViewUtil.findViewById(v,R.id.recycler_enfermedades);

        LinearLayoutManager linear= new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linear);
        recycler.setHasFixedSize(true);

        llenarRecycler();

    }

    private void llenarRecycler() {
        try {
            PecesEnfermedadesDao mEnfermedades = BdController.getInstance(getActivity()).pecesenfermedades();
            List listenfermedades = mEnfermedades.queryBuilder().list();
            ArrayListEnfermedades = new ArrayList<PecesEnfermedades>();
            for (Object enfermedades : listenfermedades) {
                ArrayListEnfermedades.add((PecesEnfermedades) enfermedades);
            }
            mListEnfermedades = ArrayListEnfermedades;
        } catch (Exception e) {
            e.getStackTrace();
        }

/*
        EnfermedadesAdapter adapter = new EnfermedadesAdapter(mListEnfermedades, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                AgresivosFragment.tipo_Clic="enfermedad";
                tipos(position);
            }
        });
        recycler.setAdapter(adapter);*/
    }

    private void tipos(int position) {
        Intent i = new Intent(getActivity(), DetallesActivity.class);
        i.putExtra("info",mListEnfermedades.get(position).getSintomas());
        i.putExtra("cuidados",mListEnfermedades.get(position).getCausas());
        i.putExtra("alimentacion",mListEnfermedades.get(position).getTratamiento());
        i.putExtra("img",mListEnfermedades.get(position).getImg());
        startActivity(i);
    }
}
