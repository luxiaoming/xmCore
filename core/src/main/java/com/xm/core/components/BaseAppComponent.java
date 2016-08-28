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

package com.xm.core.components;

import android.content.Context;

import com.squareup.otto.Bus;
import com.xm.core.ViewModel.BaseViewModel;
import com.xm.core.activity.BaseActivity;
import com.xm.core.utils.rx.xmPref;

public interface BaseAppComponent {
    Context getContext();

    Bus getBus();

    xmPref getxmPref();

    void inject(BaseViewModel baseVM);

}