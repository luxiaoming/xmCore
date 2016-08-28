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

package com.xm.core.demo.components;

import com.xm.core.components.BaseAppComponent;
import com.xm.core.demo.module.MyAppModule;
import com.xm.core.module.BaseAppModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 *   如果觉得内置的全局对象不够，在这里自己实现一个Module，然后加入进来，添加方法即可。
 *
 *    */


@Singleton
@Component(modules = {BaseAppModule.class,MyAppModule.class})
public interface MyAppComponent extends BaseAppComponent {

}