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

package com.xm.core.demo;

import android.os.Bundle;


import com.xm.core.activity.BaseActivity;
import com.xm.core.demo.viewmodel.MainViewModel;
import com.xm.core.demo.components.DaggerMyActivityComponent;
import com.xm.core.demo.components.MyAppComponent;
import com.xm.core.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity{

    @Inject
    MainViewModel mViewmodel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BaseViewModel getViewModel() {
        return mViewmodel;
    }

    @Override
    protected void initDagger() {

        DaggerMyActivityComponent
                .builder()
                .myAppComponent(getAppComponent(MyAppComponent.class))
                .build()
                .inject(this);
    }

}
