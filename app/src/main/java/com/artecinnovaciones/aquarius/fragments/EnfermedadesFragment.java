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
import com.artecinnovaciones.aquarius.MainActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.EnfermedadesAdapter;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedades;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedadesDao;
import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP-NIDIA on 09/08/2016.
 */
public class EnfermedadesFragment extends Fragment {

    RecyclerView recycler;
    private ArrayList<PecesEnfermedades> ArrayListEnfermedades;
    private List<PecesEnfermedades> mListEnfermedades;

    private InterstitialAd mInterstitialAd;
    static int p=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enfermedades, container, false);
        metodo(view);

        return view;
    }

    public void metodo (View v){

        AdView mAdView = ViewUtil.findViewById(v,R.id.adView2);
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


        EnfermedadesAdapter adapter = new EnfermedadesAdapter(mListEnfermedades, new CustomItemClickListener() {
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
        recycler.setAdapter(adapter);
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
