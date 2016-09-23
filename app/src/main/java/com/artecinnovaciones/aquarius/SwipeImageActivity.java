package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecerasGaleria;
import com.artecinnovaciones.aquarius.modelodao.PecerasGaleriaDao;
import com.artecinnovaciones.aquarius.utilidades.TouchImageView;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP-NIDIA on 15/09/2016.
 */
public class SwipeImageActivity extends Activity {

    TouchImageView imageView;

   /* public static Integer[] mImagesIds = {
            R.drawable.escondite, R.drawable.galeria,
            R.drawable.ideas_decoracion, R.drawable.ideas_fondo,
            R.drawable.ideas_iluminacion, R.drawable.escondite,
            R.drawable.escondite,R.drawable.fondo_splash,
            R.drawable.ideas_grava,R.drawable.ideas_organiza,
            R.drawable.ideas_plantas
    };

    private String[] imagesDescriptions = {
            "image1", "Image2", "image3", "Image4", "Image5", "Image6", "Image7", "image8", "Image9", "Image10", "Image11"
    };*/

    private ArrayList<PecerasGaleria> ArrayListGaleria;
    public static List<PecerasGaleria> mListGaleria;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_images_layout);

        try {
            PecerasGaleriaDao mGaleria = BdController.getInstance(SwipeImageActivity.this).pecerasgaleria();
            List listenfermedades = mGaleria.queryBuilder().list();
            ArrayListGaleria = new ArrayList<PecerasGaleria>();
            for (Object enfermedades : listenfermedades) {
                ArrayListGaleria.add((PecerasGaleria) enfermedades);
            }
            mListGaleria = ArrayListGaleria;
        } catch (Exception e) {
            e.getStackTrace();
        }

        String i = getIntent().getStringExtra("position");
        int index = Integer.parseInt(i);

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
            public void onPageSelected(int i) { }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
    }

    private class SwipeImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return GaleriaActivity.mListGaleria.size();
        }

        @Override
        public Object instantiateItem(ViewGroup collection, final int position) {

            LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.show_image, null);

            imageView = (TouchImageView) view.findViewById(R.id.gallery_image);
            Bitmap bMap = BitmapFactory.decodeFile(mListGaleria.get(position).getImg());
            imageView.setImageBitmap(bMap);
            //imageView.setImageResource(mImagesIds[position]);

            TextView imageDescription = (TextView) view.findViewById(R.id.image_description);
            imageDescription.setText(mListGaleria.get(position).getDescripcion().toString());

            collection.addView(view, 0);

            ImageView descargar = (ImageView) findViewById(R.id.descargar);
            descargar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    imageView.buildDrawingCache();
                    Bitmap imgBM = imageView.getDrawingCache();
                    new ViewUtil().guardarImg(getApplicationContext(),imgBM);
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
                        intent.putExtra(Intent.EXTRA_TEXT, "La mejor app para acuaristas... Aquarius, descárgala ya desde Google Play!!");
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

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public void finishUpdate(ViewGroup arg0) { }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) { }
        @Override
        public Parcelable saveState() {
            return null;
        }
        @Override
        public void startUpdate(ViewGroup arg0) { }
    }
}
