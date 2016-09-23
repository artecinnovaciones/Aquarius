package com.artecinnovaciones.aquarius.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;

import java.util.ArrayList;

/**
 * Created by Geovany.Chin on 29/06/2016.
 */

public class SearchAdapter extends ArrayAdapter<PecesDulce> {

    final String TAG = "AutocompleteCustomArrayAdapter.java";

    Context mContext;
    int layoutResourceId;
    private ArrayList<PecesDulce> pecesList;

    public SearchAdapter(Activity mContext, int layoutResourceId, ArrayList<PecesDulce> arrayListPeces) {
        super(mContext, layoutResourceId, arrayListPeces);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.pecesList = arrayListPeces;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);
            }

            TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
            textViewItem.setText(pecesList.get(position).getNombreComun());

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;

    }
}



/*

        extends ArrayAdapter<PecesDulce> {

    private final List<PecesDulce> pecesList;
    public List<PecesDulce> filterpeces = new ArrayList<>(1);

    public SearchAdapter(Context context, List<PecesDulce> peces) {
        super(context, 0, peces);
        this.pecesList = peces;
    }

    @Override
    public Filter getFilter() {
        return new FilterPez(this, pecesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position < filterpeces.size()) {
            PecesDulce pez = filterpeces.get(position);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.searchadapterlayout, parent, false);

            TextView tvName = (TextView) convertView.findViewById(R.id.descripcionpez);
            tvName.setText(pez.getNombreComun());

        }
        return convertView;
    }
}
*/