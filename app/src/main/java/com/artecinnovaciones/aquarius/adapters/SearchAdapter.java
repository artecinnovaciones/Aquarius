package com.artecinnovaciones.aquarius.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.filter.FilterPez;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geovany.Chin on 29/06/2016.
 */

public class SearchAdapter extends ArrayAdapter<PecesDulce> {

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