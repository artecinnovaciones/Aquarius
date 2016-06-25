package com.artecinnovaciones.aquarius.modelodao.ControladorBd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.artecinnovaciones.aquarius.modelodao.DaoMaster;
import com.artecinnovaciones.aquarius.modelodao.DaoSession;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;

/**
 * Created by Geovany.Chin on 24/06/2016.
 */
public class BdController {

    public static final String TAG = BdController.class.getSimpleName();

    private BdController(Context context) {
        mContext = context;
    }

    public static BdController getInstance(Context mcontext) {
        if (INSTANCE == null) {
            INSTANCE = new BdController(mcontext.getApplicationContext());
        }
        return INSTANCE;
    }

    public PecesDulceDao pecesdulce() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "pecesdulce-db", null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        return daoSession.getPecesDulceDao();
    }


    private static BdController INSTANCE;
    private Context mContext;
    private SQLiteDatabase db;

}
