# xmCore
一个android架构,致力于快速开发app. 架构基于 Dagger2 + RxJava + Retrofit + Material Design + MVVM MVP 敬请期待，项目准备中，欢迎star fork

![](http://upload-images.jianshu.io/upload_images/1603789-d7bf28bd519bda65?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

 架构基于** Dagger2 + RxJava + Retrofit + Material Design + MVVM **

**目标：**简化开发app难度，轻松容易的实现自定义app1：封装BaseApp主要完成全局对象的构造，这里当前支持

**Bus **

**xmPreferences**

**xmCompositeSubscription**

**Logger**

各个部分的作用：

**Bus** 事件总线 ，为了相互之间传递消息使用

**xmPreferences** 存储简单数据

**xmCompositeSubscription **为了Rx系列使用，后面会隐藏到内部，对外不需要知道这个。

**Logger **输出log使用

****BaseApp在构造函数里面完成：

1：判断是否是测试版本，如果是，启动性能测试，UI和内存。默认开启2：初始化Logger3：初始化Component如何使用。
1：实现自定义的Component

![](http://upload-images.jianshu.io/upload_images/1603789-997af6b070c1fdb8?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

继承这个，然后组件里面连接 BaseAppModule.class ，将Base的功能提供上来。自己的app继承自BaseApp，带入自己的Component，然后复写几个方法。

![](http://upload-images.jianshu.io/upload_images/1603789-72ecffa803e455fd?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以复写的函数：

initCanaryOpen() 开启是否需要性能测试 （这个到时分测试版本和release版本）

 initAppComponent()  初始化全局Dagger2，可以选择不用Dagger2，则丧失本框架的设计意义了，不建议。因为我做的就是基于Dagger2来实现app的基础功能支持，后续要做**BaseViewModel** 和 **BaseActivity**来降低开发难度，直接继承去写即可。

暂时支持这些，BaseApp则写完了。各个功能后续会增加demo演示，可以直接拿来使用。

项目地址：

**https://github.com/luxiaoming/xmCore**

后续Baseapp添加 异常捕获功能。欢迎大家关注，支持。

![](http://upload-images.jianshu.io/upload_images/1603789-eded2dafda9686a3?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

封装之路 （二）BaseActivity
目标 ：作为封装，实现**BaseActivity**，基于**Dagger2+Databinding**的模式。

当前主要实现基础的框架，后期慢慢加入其他，像Toast之类的，直接疯转在外部控件之列，不在**BaseActivity**里面，使得**BaseActivity**尽可能的清晰一些。
自定义**Activity**，继承**BaseActivity**即可，实现它的三个抽象方法。

![](http://upload-images.jianshu.io/upload_images/1603789-5c23c67bc4923609?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**getLayoutId**，返回Layout值即可，比如R.layout.main这种。

**initDagger**不要返回值，主要是让初始化**Dagger2**，这里为什么没有帮忙实现，主要是这个情况太多，去做不太现实，所以放出去让自己去做吧。需要注意，这里提供了一个获取AppComponent的方法，目标便是让在依赖的时候，能够轻松拿到**app**的组件。

![](http://upload-images.jianshu.io/upload_images/1603789-d19ad6c6a98ceb5c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/1603789-6e8abacd52a7fe32?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**initData **初始化其他信息即可。

其他可以复写的方法

![](http://upload-images.jianshu.io/upload_images/1603789-5bf57b6f957d60e7?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**initDataBinding ** 默认帮助实现了Databinding的初始化，如果想自定义，直接复写，自己去实现即可。
如果需要双击退出，可以直接使用复写方法实现：
**getDoubleClickExit** 返回是否需要双击退出提示语，默认false
**getExitTxtId** 返回双击退出的提示语信息，默认提示，双击退出。

比起之前做的架构，这里**activity**简化了许多，原因是之前**view**很重，现在采用**mvvm**的思路，因此会将那些耦合的消息事件初始化之类的放入**vm**那边，到时会配合着这边做相关的对应处理。
项目地址：

https://github.com/luxiaoming/xmCore
路漫漫系其修远兮，吾将上下而求索。

封装之路（三）架构模型

![](http://upload-images.jianshu.io/upload_images/1603789-554abe1d6c41d392?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

前面写了BaseApp和BaseActivity的源码，有留言询问怎么使用，这里简单说下：
这个框架的形成原因是由于自己在学习开发android时候，重复以及代码重构太差，因此开始关注开发架构，而使用了一些开发架构，比如MVP+Dagger2的模型，其实完全可以解决当前问题，后续在关注MVVM时候，发现它的优势更大（当然也有利弊，总体来说，比MVP更好用），而当前流行的MVVM架构，使用的是三方实现的databinding（不符合主流google，果断放弃），因此自己想去实现一个基于MVVM的框架，同时将开源的三方比较成熟的库依赖进来，做出一些工具类，方便快速开发，后续完善代码的同时，去完善文档，使这套框架可以呈现生命特征，延续持久的存在下去。

有时需要权衡，Base到底是实现更多功能，还是保持清晰，只存留简单的架构的内容，其余的统统留给开发者。（这里我选择了后者，保持其单纯目标即可）

由于也是自己慢慢实践，必然有不成熟的地方，欢迎大家指出。

这节我们讲下整体架构，也是后续这边xmCore架构去完善的地方。首先，我们看下这张图：

![](http://upload-images.jianshu.io/upload_images/1603789-8e167e157d4bc370?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里我们依次讲解下各个模块的目的：
BaseApp  这个里面完成基础app的功能，比如存储，log之类的，性能之类。这个后面会慢慢修改的。

BaseActivity 这个里面完成初始化View和注入Vm，绑定Vm的过程，随后则将逻辑推给Vm，后续有个消息注册和Bus的绑定解绑，也在base内部处理掉，我们外面不需关注了，直接使用即可。

BaseViewModel 实现VM的代码逻辑，里面绑定Model，和设计好通知界面更新，通过Model去拿到数据，这边注册Bus，通过注解来获取到Model返回的数据，进行通知界面更新即可。

BaseBean 数据原型，让继承使用，主要目标是让所有数据都继承这个，方便后面变量传入，强转和判断使用的。

BaseModel model的基类，基本没啥动作，初始化即可。后续方便实现基础功能，作为所用Model的基类使用。

Retrogit +okhttp 完成网络数据请求动作。RxAndroid完成返回数据的初步处理过程，然后交给VM去处理即可。
数据库将会使用GreenDao ，可以初步去网上看看，之前一直没使用的原因是Greendao之前是需要手动完成table的生成的，不方便，最新的已经可以直接注解方式实现，非常方便，值得推荐。
号称比GSON快很多的json解析工具LoganSquare，有兴趣的可以看看。
图片缓存，解析，使用Glide即可。
Bus 我们选择otto，想了解更多，百度下即可。
缓存使用 ASimpleCache ，官网有详细教程。https://github.com/luxiaoming/ASimpleCache。
更多精彩，敬请期待。

![](http://upload-images.jianshu.io/upload_images/1603789-c40acf30b9028742?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
