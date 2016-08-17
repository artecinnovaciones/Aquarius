package com.artecinnovaciones.aquarius.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class PecesAdapter extends RecyclerView.Adapter<PecesAdapter.DatosViewHolder> {

    CustomItemClickListener listener;
    List<PecesDulce> datos;

    public static class DatosViewHolder extends RecyclerView.ViewHolder {

        //CardView cv;
        TextView titulo;
        TextView descrip;
        ImageView img;

        public DatosViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.cv);
            titulo = (TextView)itemView.findViewById(R.id.text_item);
            //descrip = (TextView)itemView.findViewById(R.id.descrp);
            img = (ImageView) itemView.findViewById(R.id.image_item);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public PecesAdapter(List<PecesDulce> datos, CustomItemClickListener listener){
        this.datos = datos;
        this.listener = listener;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peces, parent, false);
        final DatosViewHolder pvh = new DatosViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
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
        //holder.descrip.setText(datos.get(position).descripcion);
        Bitmap bMap = BitmapFactory.decodeFile(datos.get(position).getImagen());
        holder.img.setImageBitmap(bMap);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}
