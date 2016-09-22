package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.utilidades.GuardarImagen;
import com.artecinnovaciones.aquarius.utilidades.TouchImageView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by LAP-NIDIA on 15/09/2016.
 */
public class SwipeImageActivity extends Activity {

    TouchImageView imageView;

    //Hacemos referencia a nuestras imagenes originales de la carpeta de recursos
    public static Integer[] mImagesIds = {
            R.drawable.escondite, R.drawable.galeria,
            R.drawable.ideas_decoracion, R.drawable.ideas_fondo,
            R.drawable.ideas_iluminacion, R.drawable.escondite,
            R.drawable.escondite,R.drawable.fondo_splash,
            R.drawable.ideas_grava,R.drawable.ideas_organiza,
            R.drawable.ideas_plantas
    };

    private String[] imagesDescriptions = {
            "image1", "Image2", "image3", "Image4", "Image5", "Image6", "Image7", "image8", "Image9", "Image10", "Image11"
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_images_layout);

        //Optenenos del GridView de la activity anterior el indice de la imagen que hemos
        //seleccionado
        String i = getIntent().getStringExtra("position");
        //Lo convertimos a entero
        int index = Integer.parseInt(i);

        //Obtenemos la decripcion de la imagen seleccionada de una lista en la carpeta values\arrays.xml
       // imagesDescriptions = getResources().getStringArray(R.array.images_descriptions);

        SwipeImagePagerAdapter swipeNewsAdapter = new SwipeImagePagerAdapter();
        ViewPager newsPager = (ViewPager) findViewById(R.id.swipe_pager);
        newsPager.setAdapter(swipeNewsAdapter);
        newsPager.setCurrentItem(index);

        newsPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                GaleriaActivity.mSelected = i;
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    //Creamos el SwipeImagePagerAdapter. Donde utilizaremos el layout "show_images"
    //para cargar la imagen original y los textos de descripcion de la imagen
    private class SwipeImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return GaleriaActivity.mImagesIds.length;
        }
        /**
         * Create the page for the given position.  The adapter is responsible
         * for adding the view to the container given here, although it only
         * must ensure this is done by the time it returns from
         * {@link #finishUpdate(android.view.ViewGroup)}.
         *
         * @param collection The containing View in which the page will be shown.
         * @param position   The page position to be instantiated.
         * @return Returns an Object representing the new page.  This does not
         * need to be a View, but can be some other container of the page.
         */
        @Override
        public Object instantiateItem(ViewGroup collection, final int position) {

            LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Refereciamos el show_images layout
            View view = inflater.inflate(R.layout.show_image, null);

            //Refereciamos el objeto ImageView del "show_images" layout
            imageView = (TouchImageView) view.findViewById(R.id.gallery_image);

            //Asignamos a cada ImageView una imagen de nuestro "Array" de recursos
            //utilizamos "setImageResource" ya que nuestras imagenes estan almacenadas en una
            //carpeta de recursos en nuestro proyecto.
            // "mImagesIds" es un Array de enteros, ya que almacena el Id del recurso, no la imagen en si.
            imageView.setImageResource(mImagesIds[position]);

            //Refereciamos el objeto TextView del "show_images" layout, para colocar la descripcion de la imagen
            TextView imageDescription = (TextView) view.findViewById(R.id.image_description);

            //Asignamos el test descriptivo de la imagen al objecto TextView.
            imageDescription.setText(imagesDescriptions[position].toString());

            //Adicionamos el "view" que hemos creado con los objectos ImageView y TextView a la coleccion ViewGroup
            collection.addView(view, 0);

            ImageView descargar = (ImageView) findViewById(R.id.descargar);
            descargar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    imageView.buildDrawingCache();
                    Bitmap imgBM = imageView.getDrawingCache();
                    GuardarImagen guardar = new GuardarImagen();
                    guardar.guardarImg(getApplicationContext(),imgBM);
                }
            });

            ImageView compartir = (ImageView) findViewById(R.id.compartir);
            compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.buildDrawingCache();
                    Bitmap imgBM = imageView.getDrawingCache();

                    try {

                        File file = new File(imageView.getContext().getCacheDir(), imgBM + ".png");
                        FileOutputStream fOut = null;
                        fOut = new FileOutputStream(file);
                        imgBM.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                        file.setReadable(true, false);

                        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, "La mejor app para acuaristas... Aquarius, descárgala ya!!");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                        intent.setType("image/png");
                        startActivity(Intent.createChooser(intent, "Share with"));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            return view;
        }

        /**
         * Remove a page for the given position.  The adapter is responsible
         * for removing the view from its container, although it only must ensure
         * this is done by the time it returns from {@link #finishUpdate(android.view.ViewGroup)}.
         *
         * @param collection The containing View from which the page will be removed.
         * @param position   The page position to be removed.
         * @param view       The same object that was returned by
         *                   {@link #instantiateItem(android.view.View, int)}.
         */
        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by instantiateItem(ViewGroup, int). This method is required
         * for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with object
         * @param object Object to check for association with view
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
        /**
         * Called when the a change in the shown pages has been completed.  At this
         * point you must ensure that all of the pages have actually been added or
         * removed from the container as appropriate.
         *
         * @param arg0 The containing View which is displaying this adapter's
         *             page views.
         */
        @Override
        public void finishUpdate(ViewGroup arg0) {
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }
        @Override
        public Parcelable saveState() {
            return null;
        }
        @Override
        public void startUpdate(ViewGroup arg0) {
        }
    }
}
