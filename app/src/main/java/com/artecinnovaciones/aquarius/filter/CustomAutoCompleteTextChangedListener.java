package com.artecinnovaciones.aquarius.filter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;

import com.artecinnovaciones.aquarius.DetallesActivity;
import com.artecinnovaciones.aquarius.R;
import com.artecinnovaciones.aquarius.adapters.SearchAdapter;
import com.artecinnovaciones.aquarius.modelodao.DaoMaster;
import com.artecinnovaciones.aquarius.modelodao.DaoSession;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geovany.Chin on 15/07/2016.
 */
public class CustomAutoCompleteTextChangedListener implements TextWatcher {

    public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
    Context context;

    public CustomAutoCompleteTextChangedListener(Context context) {
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        try {
        /*    DetallesActivity mainActivity = ((DetallesActivity) context);
            mainActivity.mSearchAdapter.notifyDataSetChanged();
            mainActivity.mSearchAdapter = new SearchAdapter(mainActivity, R.layout.list_view_row, read(userInput));


            mainActivity.mCustomAutoCompleteView.setAdapter(mainActivity.mSearchAdapter);*/

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<PecesDulce> read(CharSequence userInput) {
        PecesDulceDao mPeces = pecesdulce();

        List sql = mPeces.queryBuilder()
                .where(PecesDulceDao.Properties.NombreComun.like("%" + userInput + "%")
                ).orderDesc(PecesDulceDao.Properties.NombreComun)
                .list();
        mPecesDulces = new ArrayList<PecesDulce>();
        for (Object peces : sql) {
            mPecesDulces.add((PecesDulce) peces);
        }
        return mPecesDulces;

    }

    public PecesDulceDao pecesdulce() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "pecesdulce-db", null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        return daoSession.getPecesDulceDao();
    }

    private SQLiteDatabase db;
    ArrayList<PecesDulce> mPecesDulces = null;
}