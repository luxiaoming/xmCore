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

package com.xm.core.utils;

import android.app.Activity;
import android.content.Context;

import com.xm.core.R;
import com.xm.core.utils.sub.DoubleClick;


public class xmDCExit extends DoubleClick {

    private static xmDCExit doubleClickExitUtils;
    private static OnDoubleClick cb;

    private xmDCExit(Context context) {
        super(context);
    }

    public static synchronized xmDCExit get(Context context,
                                            OnDoubleClick cb) {
        if (doubleClickExitUtils == null) {
            doubleClickExitUtils = new xmDCExit(context);
        }
        xmDCExit.cb = cb;
        return doubleClickExitUtils;
    }

    private static void destroy() {
        doubleClickExitUtils = null;
    }

    @Override
    protected void afterDoubleClick() {
        if (cb != null) {
            cb.OnDoubleClickCb();
        }
        ((Activity) mContext).finish();
        destroy();
    }

    @Override
    public void doDoubleClick(int delayTime, String msg) {
        if (msg == null || msg.equals("")) {
            msg = mContext.getResources().getString(R.string.double_click_exit);
        }
        super.doDoubleClick(delayTime, msg);
    }

    static public interface OnDoubleClick {
        public void OnDoubleClickCb();

    }
}
