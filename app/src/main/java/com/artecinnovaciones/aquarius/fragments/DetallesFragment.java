package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.MainActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.DetallesAdapter;
import com.artecinnovaciones.aquarius.adapters.EnfermedadesAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedades;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedadesDao;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesFragment extends Fragment {

    RecyclerView recycler;
    CardView Cardagresivos,Cardpacificos;

    Animation aparecer;

    public static String tipo_Clic,nombre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        detalles(view);

        return view;
    }

    public void detalles(View view) {
        recycler=ViewUtil.findViewById(view,R.id.recycler_peces);
        Cardagresivos=ViewUtil.findViewById(view,R.id.card_agresivos);
        Cardpacificos=ViewUtil.findViewById(view, R.id.card_pacificos);

        aparecer= AnimationUtils.loadAnimation(this.getActivity().getApplicationContext(), R.anim.trasladar);

        if(PrincipalFragment.tipo_pez.equals("dulce")){
            GridLayoutManager layoutRecycler = new GridLayoutManager(getActivity(),2);
            recycler.setLayoutManager(layoutRecycler);
        }else{
            LinearLayoutManager layoutRecycler = new LinearLayoutManager(getActivity());
            recycler.setLayoutManager(layoutRecycler);
        }

        recycler.setHasFixedSize(true);

        cargarBd();
    }

    public void cargarBd() {
       // if (ArrayListPeces == null && MainActivity.tipo_pez.equals("salada")) {

        if(PrincipalFragment.tipo_pez.equals("dulce")){

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

            Cardagresivos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MainActivity.Agresivosvisible == 0){
                        MainActivity.Agresivosvisible=1;
                        recycler.setVisibility(View.VISIBLE);
                        recycler.startAnimation(aparecer);
                    }else{
                        MainActivity.Agresivosvisible=0;
                        recycler.setVisibility(View.GONE);
                    }
                }
            });
        }else{

            recycler.setVisibility(View.VISIBLE);
            Cardagresivos.setVisibility(View.GONE);
            Cardpacificos.setVisibility(View.GONE);

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

            EnfermedadesAdapter adapter = new EnfermedadesAdapter(mListEnfermedades, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    tipo_Clic="enfermedad";
                    tipos(position);
                }
            });
            recycler.setAdapter(adapter);
        }

        //}

    }

    private void tipos(int position) {
        if (tipo_Clic.equalsIgnoreCase("pez")){
            nombre=mListpeces.get(position).getNombreCientifico();
            Intent i = new Intent(getActivity(), DetallesActivity.class);
            i.putExtra("info",mListpeces.get(position).getInformacion());
            i.putExtra("cuidados",mListpeces.get(position).getCuidados());
            i.putExtra("alimentacion",mListpeces.get(position).getAlimentacion());
            i.putExtra("img",mListpeces.get(position).getImagen());
            startActivity(i);
            /*TiposFragment tiposFragment = new TiposFragment(mListpeces.get(position).getInformacion()
                    ,mListpeces.get(position).getCuidados()
                    ,mListpeces.get(position).getAlimentacion());
            getFragmentManager().beginTransaction()
                    .replace(R.id.frag_l,tiposFragment).addToBackStack(null).commit(); */
        }else{
            Intent i = new Intent(getActivity(), DetallesActivity.class);
            i.putExtra("info",mListEnfermedades.get(position).getSintomas());
            i.putExtra("cuidados",mListEnfermedades.get(position).getCausas());
            i.putExtra("alimentacion",mListEnfermedades.get(position).getTratamiento());
            i.putExtra("img",mListEnfermedades.get(position).getImg());
            startActivity(i);
            /*TiposFragment tiposFragment = new TiposFragment(mListEnfermedades.get(position).getSintomas()
                    ,mListEnfermedades.get(position).getCausas()
                    ,mListEnfermedades.get(position).getTratamiento());
            getFragmentManager().beginTransaction()
                    .replace(R.id.frag_l,tiposFragment).addToBackStack(null).commit(); */
        }

    }

    private ArrayList<PecesDulce> ArrayListPeces;
    private ArrayList<PecesEnfermedades> ArrayListEnfermedades;
    private List<PecesDulce> mListpeces;
    private List<PecesEnfermedades> mListEnfermedades;
}
