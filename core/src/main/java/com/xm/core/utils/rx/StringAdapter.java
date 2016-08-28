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

package com.xm.core.utils.rx;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

final class StringAdapter implements Preference.Adapter<String> {
    static final StringAdapter INSTANCE = new StringAdapter();

    @Override
    public String get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getString(key, null);
    }

    @Override
    public void set(@NonNull String key, @NonNull String value,
                    @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, value);
    }
}
