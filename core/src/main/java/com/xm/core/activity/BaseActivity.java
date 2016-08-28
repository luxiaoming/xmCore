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

/*
 * *
 *  * author: lxm
 *  * email : 332324956@qq.com
 *
 */

package com.xm.core.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.xm.core.R;
import com.xm.core.app.BaseApp;
import com.xm.core.components.BaseAppComponent;
import com.xm.core.utils.xmDCExit;
import com.xm.core.utils.xmUtils;
import com.xm.core.viewmodel.BaseViewModel;

public abstract class BaseActivity extends AppCompatActivity {

    private BaseAppComponent mAppComponent;
    private ViewDataBinding mViewDataBinding;
    private BaseViewModel mViewModel;

    protected abstract int getLayoutId();

    protected abstract void initDagger();

    protected abstract BaseViewModel getViewModel();

    protected abstract void initData(Bundle savedInstanceState);

    protected ViewDataBinding getDataBinding() {
        return DataBindingUtil.setContentView(this, getLayoutId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppComponent = ((BaseApp) getApplication()).getAppComponent();
        initDagger();
        mViewDataBinding = getDataBinding();
        mViewModel = getViewModel();
        mViewModel.init(mAppComponent);
        initData(savedInstanceState);
    }

    protected boolean getDCExit() {
        return false;
    }

    @StringRes
    protected int getExitTxtId() {
        return R.string.double_click_exit;
    }

    @Override
    public void onBackPressed() {
        if (getDCExit()) {
            xmDCExit.get(this, null).doDoubleClick(getDCExitTime(), getExitTxtId());
        }
        super.onBackPressed();
    }

    protected int getDCExitTime() {
        return 2;
    }

    @SuppressWarnings("unchecked")
    protected <C> C getAppComponent(Class<C> componentType) {
        return xmUtils.getComponent(componentType, mAppComponent);
    }


    @SuppressWarnings("unchecked")
    protected <C> C getViewDataBinding(Class<C> componentType) {
        return xmUtils.getComponent(componentType, mViewDataBinding);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
    }
}
