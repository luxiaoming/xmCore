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

/*
 * *
 *  * author: lxm
 *  * email : 332324956@qq.com
 *
 */

package com.xm.core.utils.sub;

import android.content.Context;

import com.github.johnpersano.supertoasts.library.SuperActivityToast;


public abstract class DoubleClick {
    protected Context mContext;

    private long mStartTime;

    public DoubleClick(Context context) {
        mContext = context;
        mStartTime = -1;
    }

    public void doDoubleClick(int delayTime, String msg) {
        if (!doInDelayTime(delayTime)) {
            SuperActivityToast.create(mContext, msg, delayTime).show();
        }
    }

    private boolean doInDelayTime(int delayTime) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mStartTime <= delayTime) {
            afterDoubleClick();
            return true;
        }
        mStartTime = nowTime;
        return false;
    }

    public void doDoubleClick(int delayTime, int msgResid) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mStartTime <= delayTime) {
            afterDoubleClick();
        } else {
            mStartTime = nowTime;
            SuperActivityToast.create(mContext, mContext.getResources().getString(msgResid), delayTime).show();

        }
    }

    abstract protected void afterDoubleClick();

}
