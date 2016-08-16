package com.artecinnovaciones.aquarius.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.DetallesAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedades;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesFragment extends Fragment {

    RecyclerView recycler;

    public static String tipo_Clic,nombre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        detalles(view);

        return view;
    }

    public void detalles(View view) {
        recycler=ViewUtil.findViewById(view,R.id.recycler_peces_pacificos);

        GridLayoutManager layoutRecycler = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(layoutRecycler);
        recycler.setHasFixedSize(true);

        cargarBd();
    }

    public void cargarBd() {
       // if (ArrayListPeces == null && MainActivity.tipo_pez.equals("salada")) {

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
                    tipo_Clic="pez";
                    tipos(position);
                }
            });
            recycler.setAdapter(adapter);

        //}

    }

    private void tipos(int position) {
            nombre=mListpeces.get(position).getNombreCientifico();
            Intent i = new Intent(getActivity(), DetallesActivity.class);
            i.putExtra("info",mListpeces.get(position).getInformacion());
            i.putExtra("cuidados",mListpeces.get(position).getCuidados());
            i.putExtra("alimentacion",mListpeces.get(position).getAlimentacion());
            i.putExtra("img",mListpeces.get(position).getImagen());
            startActivity(i);
    }

    private ArrayList<PecesDulce> ArrayListPeces;
    private List<PecesDulce> mListpeces;
}
