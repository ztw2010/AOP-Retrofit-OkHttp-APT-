package com.oschina.learn.apt.apt;


import com.google.auto.service.AutoService;
import com.oschina.learn.apt.apt.processor.ApiFactoryProcessor;
import com.oschina.learn.apt.apt.processor.InstanceProcessor;
import com.oschina.learn.apt.apt.processor.route.ParcelerCompiler;
import com.oschina.learn.apt.apt.processor.route.util.UtilMgr;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)//自动生成 javax.annotation.processing.IProcessor 文件
@SupportedSourceVersion(SourceVersion.RELEASE_8)//java版本支持
@SupportedAnnotationTypes({//标注注解处理器支持的注解类型
        "com.oschina.learn.lib.apt.InstanceFactory",
        "com.oschina.learn.lib.apt.route.Arg",
        "com.oschina.learn.lib.apt.route.Dispatcher",
        "com.oschina.learn.lib.apt,ApiFactory"
})
public class AnnotationProcessor extends AbstractProcessor {
    public Filer mFiler; //文件相关的辅助类
    public Elements mElements; //元素相关的辅助类
    public Messager mMessager; //日志相关的辅助类

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        UtilMgr.getMgr().init(processingEnv);//ParcelerCompiler相关初始化
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mFiler = processingEnv.getFiler();
        mElements = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        new InstanceProcessor().process(roundEnv, this,processingEnv);
        new ApiFactoryProcessor().process(roundEnv, this,processingEnv);
        new ParcelerCompiler().process(roundEnv,this,processingEnv);
        return true;
    }
}
