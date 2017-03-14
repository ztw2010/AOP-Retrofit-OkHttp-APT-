先来大体上介绍一下这个项目：

**项目功能**：本期功能比较简单，仅仅展示了新闻的列表页<br/>
**项目API**：使用开源中国的API，具体链接http://www.oschina.net/openapi/docs<br/>
**项目架构**：MVP+MD风格<br/>
**项目技术**：AOP，自定义注解处理器，APT等等<br/>

**技术介绍**：<br/>
APT：不会偷懒的程序员不是一个好程序员，Android开发者对ButterKnife依赖注解库一定耳熟能详，当我们UI布局中控件很多的时候ButterKnife无疑显著提高了开发效率。作为一个注解库其实现的原理依然是Java Annotation的方式，有兴趣的可以去GitHub看下ButterKnife的源码。该项目一共有2个自定义处理器：①传值注解处理器：相信大家很多时候在Activity进行页面传值时很苦恼，需要定义各种各样的KEY去传递数据。在传递时如果参数较多。还得需要到目标Activity中去寻找传递的数据应该是怎么传递才是符合逻辑的。源于此我就在思考是否可以设计一个自定义处理器让代码去自动生成参数bean呢？于是基于数据传递的APT就诞生了，传值无非一个@Dispatcher和@Arg就解决了，妈妈再也不用担心我传了一个错误的KEY哈哈。另外此插件使用编译时注解处理，绿色无反射，完全不用担心运行效率问题。②实例化工厂注解处理器：想要实现一个单例，在一个类上加上@InstanceFactory注解就OK了。<br/>

AOP：OOP的精髓是把功能或问题模块化，每个模块处理自己的家务事。但在现实世界中，并不是所有问题都能完美得划分到模块中。举个最简单而又常见的例子：现在想为每个模块加上日志功能，要求模块运行时候能输出日志。在不知道AOP的情况下，一般的处理都是：先设计一个日志输出模块，这个模块提供日志输出API，比如Android中的Log类。然后，其他模块需要输出日志的时候调用Log类的几个函数，比如e(TAG,...)，w(TAG,...)，d(TAG,...)，i(TAG,...)等。<br/>
在没有AOP之前，各个模块要打印日志，就是自己处理。反正日志模块的那几个API都已经写好了，你在其他模块的任何地方，任何时候都可以调用。功能是得到了满足，但是好像没有Oriented的感觉了。是的，随意加日志输出功能，使得其他模块的代码和日志模块耦合非常紧密。而且，将来要是日志模块修改了API，则使用它们的地方都得改。这种搞法，一点也不酷。于是乎网上各种了解基于Android的AOP，然后动手实现了自己的AOP结合自定义注解实现了类的单例，具体详见代码。<br/>
