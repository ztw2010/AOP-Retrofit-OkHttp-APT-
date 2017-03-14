先来大体上介绍一下这个项目：
项目功能：本期功能比较简单，仅仅展示了新闻的列表页
项目API：使用开源中国的API，具体链接http://www.oschina.net/openapi/docs
项目架构：MVP+MD风格
项目技术：AOP，自定义注解处理器，APT等等

技术介绍：
APT：不会偷懒的程序员不是一个好程序员，Android开发者对ButterKnife依赖注解库一定耳熟能详，当我们UI布局中控件很多的时候ButterKnife无疑显著提高了开发效率。
作为一个注解库其实现的原理依然是Java Annotation的方式，有兴趣的可以去GitHub看下ButterKnife的源码。基于ButterKnife的启发于是自己实现了一套自定义注解处理器
