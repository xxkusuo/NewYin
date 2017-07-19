package com.gxtc.yyj.newyin.mvp.dagger2.module;

import com.gxtc.yyj.newyin.mvp.presenter.ExplorePresenter;
import com.gxtc.yyj.newyin.mvp.ui.view.IExploreView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jam on 2017/7/19.
 */

@Module
public class ExploreModule {

    private IExploreView mExploreView;

    public ExploreModule(IExploreView exploreView) {
        mExploreView = exploreView;
    }

    @Provides
    public ExplorePresenter provideExplorePresenter(IExploreView exploreView){
        return new ExplorePresenter(exploreView);
    }

    @Provides
    public IExploreView provideExploreView(){
        return mExploreView;
    }
}
