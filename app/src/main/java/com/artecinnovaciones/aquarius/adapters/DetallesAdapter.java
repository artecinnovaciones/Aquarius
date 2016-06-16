package com.artecinnovaciones.aquarius.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.R;

import java.util.ArrayList;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesAdapter extends ArrayAdapter<DetallesItem> {

    private Context context;
    private ArrayList<DetallesItem> datos;

    public DetallesAdapter(Context context, ArrayList<DetallesItem> datos) {
        super(context, R.layout.item_cardview,datos);

        this.datos=datos;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        Holder holder;

        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.item_cardview, null);

            holder = new Holder();
            holder.titulos = (TextView) item.findViewById(R.id.titulo);
            holder.descripciones = (TextView) item.findViewById(R.id.descrp);

            item.setTag(holder);
        }

        holder = (Holder) item.getTag();

        holder.titulos.setText(datos.get(position).getTitulo());
        holder.descripciones.setText(datos.get(position).getDescripcion());

        return item;
    }
}
