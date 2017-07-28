package com.gxtc.yyj.newyin.mvp.dagger2.component;

import com.gxtc.yyj.newyin.mvp.dagger2.module.ExploreModule;
import com.gxtc.yyj.newyin.mvp.ui.fragment.ExploreFragment;

import dagger.Component;

/**
 * Created by Jam on 2017/7/19.
 * dagger2 组件 弃用
 */

@Component(modules = ExploreModule.class)
public interface ExploreComponent {
    public void inject(ExploreFragment fragment);
}
