package com.gxtc.yyj.newyin.mvp.greendao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gxtc.yyj.newyin.mvp.greendao.bean.Explore;

import com.gxtc.yyj.newyin.mvp.greendao.dao.ExploreDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig exploreDaoConfig;

    private final ExploreDao exploreDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        exploreDaoConfig = daoConfigMap.get(ExploreDao.class).clone();
        exploreDaoConfig.initIdentityScope(type);

        exploreDao = new ExploreDao(exploreDaoConfig, this);

        registerDao(Explore.class, exploreDao);
    }
    
    public void clear() {
        exploreDaoConfig.clearIdentityScope();
    }

    public ExploreDao getExploreDao() {
        return exploreDao;
    }

}