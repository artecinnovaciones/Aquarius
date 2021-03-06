package com.artecinnovaciones.aquarius.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.R;

import com.artecinnovaciones.aquarius.adapters.CustomAutoCompleteView;
import com.artecinnovaciones.aquarius.adapters.PecesAdapter;
import com.artecinnovaciones.aquarius.adapters.SearchAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacificosFragment extends Fragment implements TextWatcher {

    RecyclerView recyclerPacificos;

    private ArrayList<PecesDulce> ArrayListPeces, buscadorlist;
    private List<PecesDulce> Listpeces;
    public CustomAutoCompleteView mCustomAutoCompleteView;
    public SearchAdapter mSearchAdapter;

    private InterstitialAd mInterstitialAd;
    static int p=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pacificos, container, false);
        pacificos(view);
        return view;
    }

    private void pacificos(View view) {

        AdView mAdView = ViewUtil.findViewById(view,R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        new ViewUtil().requestNewInterstitial(mInterstitialAd);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                new ViewUtil().requestNewInterstitial(mInterstitialAd);
                tipos(p);
            }
        });

        recyclerPacificos = ViewUtil.findViewById(view, R.id.recycler_peces_pacificos);
        mCustomAutoCompleteView = ViewUtil.findViewById(view, R.id.filtrobusqueda);
        mCustomAutoCompleteView.setOnItemClickListener(mOnItemClickListener);
        mCustomAutoCompleteView.addTextChangedListener(this);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
        recyclerPacificos.setLayoutManager(gridLayout);
        recyclerPacificos.setHasFixedSize(true);

        try {
            PecesDulceDao peces = BdController.getInstance(getActivity()).pecesdulce();
            List listaPeces = peces.queryBuilder()
                    .orderAsc(PecesDulceDao.Properties.NombreCientifico)
                    .list();

            ArrayListPeces = new ArrayList<PecesDulce>();

            for (Object Opeces : listaPeces) {
                ArrayListPeces.add((PecesDulce) Opeces);
            }

            Listpeces = ArrayListPeces;

        } catch (Exception e) {
            e.getStackTrace();
        }

        PecesAdapter adapter = new PecesAdapter(Listpeces, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                p=position;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }else {
                    tipos(position);
                }
            }
        });

        recyclerPacificos.setAdapter(adapter);

    }

    private void tipos(int position) {
        Intent i = new Intent(getActivity(), DetallesActivity.class);
        i.putExtra("info", Listpeces.get(position).getInformacion());
        i.putExtra("cuidados", Listpeces.get(position).getCuidados());
        i.putExtra("alimentacion", Listpeces.get(position).getAlimentacion());
        i.putExtra("img", Listpeces.get(position).getImagen());
        i.putExtra("bandera", 1);
        startActivity(i);
    }

    private void tipos(PecesDulce mPeces) {
        bandera = false;
        Intent i = new Intent(getActivity(), DetallesActivity.class);
        i.putExtra("info", mPeces.getInformacion());
        i.putExtra("cuidados", mPeces.getCuidados());
        i.putExtra("alimentacion", mPeces.getAlimentacion());
        i.putExtra("img", mPeces.getImagen());
        i.putExtra("bandera", 1);
        startActivity(i);
    }

    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bandera = true;
            mCustomAutoCompleteView.setText("");
            PecesDulce comparar= buscadorlist.get(position);
        //    for (PecesDulce comparar : buscadorlist) {
                if (comparar.getIsSearch()) {
                    tipos(comparar);
                }
         //   }
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        cargarBd(s.toString());
    }

    public void cargarBd(String buscar) {
        try {

            PecesDulceDao mPeces = BdController.getInstance(getActivity()).pecesdulce();
            List listpeces = mPeces.queryBuilder().whereOr(PecesDulceDao.Properties.NombreCientifico.like(buscar + "%"),
                    (PecesDulceDao.Properties.NombreComun.like(buscar + "%"))).list();
            if (listpeces.size() > 0 && !bandera) {
                buscadorlist = new ArrayList<PecesDulce>();
                for (Object peces : listpeces) {
                    buscadorlist.add((PecesDulce) peces);
                }
                for (PecesDulce mod : buscadorlist) {
                    mod.setIsSearch(true);
                }
            }
            mSearchAdapter = new SearchAdapter(getActivity(), R.layout.list_view_row, buscadorlist);
            mCustomAutoCompleteView.setAdapter(mSearchAdapter);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static boolean bandera = false;
}
