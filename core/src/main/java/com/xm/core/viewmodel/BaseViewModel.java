/*
 * Copyright 2016 code_gg_boy, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xm.core.viewmodel;


import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.xm.core.model.BaseModel;
import com.xm.core.components.BaseAppComponent;

import javax.inject.Inject;

/**
 * The type Base view model.
 */
public abstract class BaseViewModel {

    private BaseModel mModel;
    private Bus bus;

    /**
     * Init dagger.
     *
     * @param mAppComponent the m app component
     */
    protected abstract void initDagger(BaseAppComponent mAppComponent);

    private void setBus(Bus bus) {
        this.bus = bus;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    protected abstract BaseModel getModel();

    /**
     * Init data.
     */
    protected abstract void initData();


    /**
     * Init.
     * 完成Dagger ，Model，Bus的初始化动作。这里Bus没有使用@Inject，原因是继承的类去Dagger有些麻烦，因此就
     * 直接传递过来了Bus。
     *
     * @param mAppComponent the m app component
     */
    public void init(BaseAppComponent mAppComponent) {
        initDagger(mAppComponent);
        mModel = getModel();
        setBus(mAppComponent.getBus());
        mModel.setBus(bus);
        initData();
    }

    /**
     * On resume.
     */
    @CallSuper
    public void onResume() {
        bus.register(this);
    }

    /**
     * On pause.
     */
    @CallSuper
    public void onPause() {
        bus.unregister(this);
    }

    /**
     * On destroy.
     */
    @CallSuper
    public void onDestroy() {
        mModel.getCompositeSubscription().unsubscribe();
    }

}
