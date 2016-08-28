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

package com.xm.core.demo.viewmodel;

import com.xm.core.components.BaseAppComponent;
import com.xm.core.demo.model.MainModel;
import com.xm.core.model.BaseModel;
import com.xm.core.viewmodel.BaseViewModel;

import javax.inject.Inject;


public class MainViewModel extends BaseViewModel {

    MainModel mainModel;

    @Inject
    public MainViewModel() {
    }

    @Override
    protected void initDagger(BaseAppComponent mAppComponent) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BaseModel getModel() {
        mainModel=new MainModel();
        return mainModel;
    }
}
