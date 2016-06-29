package com.artecinnovaciones.aquarius.filter;

import android.widget.Filter;

import com.artecinnovaciones.aquarius.adapters.SearchAdapter;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geovany.Chin on 29/06/2016.
 */
public class FilterPez extends Filter {

    SearchAdapter adapter;
    List<PecesDulce> originalList;
    List<PecesDulce> filtradoList;

    public FilterPez(SearchAdapter adapter, List<PecesDulce> originalList) {
        super();
        this.adapter = adapter;
        this.originalList = originalList;
        this.filtradoList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filtradoList.clear();
        final FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() == 0) {
            filtradoList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            for (final PecesDulce pez : originalList) {
                if (pez.getNombreComun().toLowerCase().contains(filterPattern)) {
                    filtradoList.add(pez);
                }
            }
        }
        results.values = filtradoList;
        results.count = filtradoList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.values != null) {
            adapter.filterpeces.clear();
            adapter.filterpeces.addAll((List) results.values);
            adapter.notifyDataSetChanged();
        }

    }
}

