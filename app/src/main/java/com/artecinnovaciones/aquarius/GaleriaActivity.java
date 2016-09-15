package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GaleriaActivity extends Activity {

    public static int mSelected = 0;
    private GridView gridview;
    // Hacemos referencia a nuestras imagenes en miniatura de la carpeta de recursos
    public static Integer[] mImagesIds = {
            R.drawable.escondite, R.drawable.galeria,
            R.drawable.ideas_decoracion, R.drawable.ideas_fondo,
            R.drawable.ideas_iluminacion, R.drawable.escondite
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_gallery);

        //Hacemos referncia nuestro GridView
        gridview = (GridView) findViewById(R.id.gridview);
        //Asignamos el ImageAdapter que hemos creado en esta misma clase al GridView
        gridview.setAdapter(new ImageAdapter(this));
        //Nos movemos al elemento seleccionado del GridView
        gridview.setSelection(mSelected);
    }
    @Override
    protected void onStart() {
        super.onStart();
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setSelection(mSelected);
    }

    //ImageAdapter para almacenar las imagemes en el GridView
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        public ImageAdapter(Context c) {
            mContext = c;
        }
        public int getCount() {
            return mImagesIds.length;
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }

        // Creamos un ImageView por cada imagen miniatura referenciada en nuestro ImageAdapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 130));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setCropToPadding(true);
                } else {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                imageView.setPadding(2, 2, 2, 2);
            } else {
                imageView = (ImageView) convertView;
            }
            //Asignamos a cada ImageView una imagen de nuestro "Array" de recursos
            //utilizamos "setImageResource" ya que nuestras imagenes estan almacenadas en una
            //carpeta de recursos en nuestro proyecto.
            // "mImagesIds" es un Array de enteros, ya que almacena el Id del recurso, no la imagen en si.
            imageView.setImageResource(mImagesIds[position]);

            //En el evento click del ImageView obtenemos el indice de la imagen seleccionada
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSelected = (Integer) view.getTag();
                    notifyDataSetChanged();

                    String index = String.valueOf(position);
                    Bundle extras = new Bundle();

                    //Pasamos el indice de la imagen seleccionada a la activity que se encarga de mostrar la image Original
                    //y hacer el "swipe"
                    extras.putString("position", index);
                    startActivity(new Intent(GaleriaActivity.this, SwipeImageActivity.class).putExtras(extras).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
            try {
                imageView.setTag(position);
                //Ponemos un borde de color naranja a la imagen en miniatura seleccionada en el GrisView
                if (position == mSelected) {
                    imageView.setBackgroundColor(Color.parseColor("#ff6203"));
                } else {
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return imageView;
        }
    }
}