package com.artecinnovaciones.aquarius.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.PecesAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacificosFragment extends Fragment {

    RecyclerView recyclerPacificos;

    private ArrayList<PecesDulce> ArrayListPeces;
    private List<PecesDulce> Listpeces;

    public PacificosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pacificos, container, false);
        pacificos(view);
        return view;
    }

    private void pacificos(View view) {
        recyclerPacificos= ViewUtil.findViewById(view,R.id.recycler_peces_pacificos);

        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(),2);
        recyclerPacificos.setLayoutManager(gridLayout);
        recyclerPacificos.setHasFixedSize(true);

        try{
            PecesDulceDao peces = BdController.getInstance(getActivity()).pecesdulce();
            List listaPeces = peces.queryBuilder()
                    .where(PecesDulceDao.Properties.Tipo.eq(0))
                    .list();

            ArrayListPeces = new ArrayList<PecesDulce>();

            for (Object Opeces : listaPeces){
                ArrayListPeces.add((PecesDulce)Opeces);
            }

            Listpeces = ArrayListPeces;

        }catch (Exception e){
            e.getStackTrace();
        }

        PecesAdapter adapter = new PecesAdapter(Listpeces, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                AgresivosFragment.tipo_Clic="pez";
                tipos(position);
            }
        });

        recyclerPacificos.setAdapter(adapter);
    }

    private void tipos(int position) {
        AgresivosFragment.nombre=Listpeces.get(position).getNombreCientifico();
        Intent i = new Intent(getActivity(), DetallesActivity.class);
        i.putExtra("info",Listpeces.get(position).getInformacion());
        i.putExtra("cuidados",Listpeces.get(position).getCuidados());
        i.putExtra("alimentacion",Listpeces.get(position).getAlimentacion());
        i.putExtra("img",Listpeces.get(position).getImagen());
        startActivity(i);
    }

}
