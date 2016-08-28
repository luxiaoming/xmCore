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

package com.xm.core.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;

import com.xm.core.utils.rx.xmPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * The type App module.
 */
@Module
public class BaseAppModule {

    private Context mContext;

    /**
     * Instantiates a new App module.
     *
     * @param context the context
     */
    public BaseAppModule(Context context) {
        this.mContext = context;
    }

    /**
     * The constant DATA_FORMAT.
     */
    public static final String DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Provide bus bus.
     *
     * @return the bus
     */
    @Singleton
    @Provides
    Bus ProvideBus() {
        return new Bus();
    }

    /**
     * Provide context context.
     *
     * @return the context
     */
    @Singleton
    @Provides
    Context ProvideContext() {
        return mContext;
    }

    /**
     * Provide gson gson.
     *
     * @return the gson
     */
    @Singleton
    @Provides
    Gson ProvideGson() {
        return new GsonBuilder()
                .setDateFormat(DATA_FORMAT)
                .create();
    }

    /**
     * Provide preferences xm preferences.
     *
     * @param context the context
     * @return the xm preferences
     */
    @Singleton
    @Provides
    xmPref ProvidePreferences(Context context) {
        return xmPref.init(context);
    }

}
