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


package com.xm.core.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xm.core.app.BaseApp;
import com.xm.core.components.BaseAppComponent;
import com.xm.core.utils.xmUtils;
import com.xm.core.viewmodel.BaseViewModel;


public abstract class BaseFragment extends Fragment {

    private BaseAppComponent mAppComponent;
    private ViewDataBinding mViewDataBinding;
    private BaseViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mAppComponent = ((BaseApp) getActivity().getApplication()).getAppComponent();
        initDagger();
        mViewDataBinding = getDataBinding(container, inflater);
        mViewModel = getViewModel();
        mViewModel.init(mAppComponent);
        initData(savedInstanceState);
        return mViewDataBinding.getRoot();
    }

    protected abstract int getLayoutId();

    protected abstract void initDagger();

    protected abstract BaseViewModel getViewModel();

    protected abstract void initData(Bundle savedInstanceState);

    protected ViewDataBinding getDataBinding(ViewGroup container, LayoutInflater inflater) {
        return DataBindingUtil.inflate(inflater, this.getLayoutId(), container, false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected <C> C getActivityComponent(Class<C> componentType) {
        return xmUtils.getComponent(componentType, getActivity());
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
    }
}
