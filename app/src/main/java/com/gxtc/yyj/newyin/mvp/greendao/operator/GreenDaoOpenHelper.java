package com.gxtc.yyj.newyin.mvp.greendao.operator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.gxtc.yyj.newyin.mvp.greendao.dao.DaoMaster;
import com.gxtc.yyj.newyin.mvp.greendao.dao.ExploreDao;

import org.greenrobot.greendao.database.Database;

public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {
    public GreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(
                db,
                ExploreDao.class
        );
    }
}