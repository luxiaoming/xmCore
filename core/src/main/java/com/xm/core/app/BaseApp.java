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

package com.xm.core.app;

import android.app.Application;
import android.os.Environment;
import android.support.annotation.StringRes;

import com.android.annotations.NonNull;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.xm.core.app.sub.AppBlockCanaryContext;
import com.xm.core.components.BaseAppComponent;

import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;

import de.mindpipe.android.logging.log4j.LogConfigurator;


public abstract class BaseApp extends Application {

    private BaseAppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = initAppComponent();
        if (initCanaryOpen()) {
            initCanary();
        }
        initLogger();

    }

    @NonNull
    protected abstract BaseAppComponent initAppComponent();


    public BaseAppComponent getAppComponent() {
        return mAppComponent;
    }

    private void initCanary() {
        BlockCanary.install(this, getBlockCanaryContext()).start();
        LeakCanary.install(this);
    }

    protected BlockCanaryContext getBlockCanaryContext() {
        return new AppBlockCanaryContext();
    }

    protected boolean initCanaryOpen() {
        return true;
    }

    private void initLogger() {
        String path = getLogPath();
        try {
            final LogConfigurator lc = new LogConfigurator();
            lc.setFileName(path);
            lc.setFilePattern("%d - [%-6p-%c] - %m%n");
            lc.setMaxBackupSize(2);
            lc.setMaxFileSize(1024 * 1024);
            lc.setRootLevel(Level.DEBUG);
            lc.setLevel("org.apache", Level.DEBUG);
            lc.configure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLogPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getPackageName() + "/log.txt";
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @SuppressWarnings("unchecked")
    protected <C> C getAppComponent(Class<C> componentType) {
        return componentType.cast(mAppComponent);
    }

}
