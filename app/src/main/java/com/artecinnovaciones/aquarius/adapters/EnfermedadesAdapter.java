package com.artecinnovaciones.aquarius.adapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.utilidades.CustomItemClickListener;

import java.util.List;

/**
 * Created by LAP-NIDIA on 29/06/2016.
 */
public class EnfermedadesAdapter extends RecyclerView.Adapter<EnfermedadesAdapter.DatosViewHolder> {

    CustomItemClickListener listener;
    List<PecesDulce> datos;

    public static class DatosViewHolder extends RecyclerView.ViewHolder {

        TextView titulo,descrip;
        ImageView img;

        public DatosViewHolder(View itemView) {
            super(itemView);
            titulo=(TextView)itemView.findViewById(R.id.titulo);
            descrip=(TextView)itemView.findViewById(R.id.descrp);
            img=(ImageView)itemView.findViewById(R.id.image_enfermedades);
        }

    }

    public EnfermedadesAdapter(List<PecesDulce> datos, CustomItemClickListener listener){
        this.datos = datos;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        final DatosViewHolder pvh = new DatosViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, pvh.getPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(DatosViewHolder holder, int position) {
        holder.titulo.setText(datos.get(position).getNombreCientifico());
        holder.descrip.setText(datos.get(position).getNombreComun());
        Bitmap bMap = BitmapFactory.decodeFile(datos.get(position).getImagen());
      /*  RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(Resources.getSystem(), bMap);
        roundedDrawable.setCornerRadius(bMap.getWidth()); */
        holder.img.setImageBitmap(bMap);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}
