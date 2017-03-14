package com.oschina.learn.apt.apt.processor;

import com.oschina.learn.apt.apt.AnnotationProcessor;
import com.oschina.learn.apt.apt.inter.IProcessor;
import com.oschina.learn.apt.apt.util.Utils;
import com.oschina.learn.lib.apt.ApiFactory;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;

import javax.annotation.processing.FilerException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * API工厂处理器
 */
public class ApiFactoryProcessor implements IProcessor {
    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor,
                        ProcessingEnvironment processingEnv) {
        String CLASS_NAME = "ApiFactory";
        TypeSpec.Builder tb = classBuilder(CLASS_NAME).addModifiers(PUBLIC, FINAL).addJavadoc("@ API工厂 此类由apt自动生成");
        try {
            for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(ApiFactory.class))) {
                mAbstractProcessor.mMessager.printMessage(Diagnostic.Kind.NOTE, "正在处理: " + element.toString());
                for (Element e : element.getEnclosedElements()) {
                    ExecutableElement executableElement = (ExecutableElement) e;
                    TypeMirror returnMirror = executableElement.getReturnType();
                    MethodSpec.Builder methodBuilder =
                            MethodSpec.methodBuilder(e.getSimpleName().toString())
                                    .addJavadoc("@此方法由apt自动生成")
                                    .returns(TypeName.get(returnMirror)).addModifiers(PUBLIC, STATIC);
                    String paramsString = "";
                    for (VariableElement ep : executableElement.getParameters()) {
                        methodBuilder.addParameter(TypeName.get(ep.asType()), ep.getSimpleName().toString());
                        paramsString += ep.getSimpleName().toString() + ",";
                    }
                    List<TypeName> typeNames = ((ParameterizedTypeName)TypeName.get(returnMirror)).typeArguments;
                    TypeName typeName = typeNames.iterator().next();
                    methodBuilder.addStatement(
                            "return $T.getInstance()" +
                                    ".service.$L($L)" +
                                    ".compose($T.<$T>io_main())"
                            , ClassName.get("com.oschina.learn.api", "Api")
                            , e.getSimpleName().toString()
                            , paramsString.substring(0, paramsString.length() - 1)
                            , ClassName.get("com.oschina.learn.utils", "RxSchedulers")
                            , typeName);
                    tb.addMethod(methodBuilder.build());
                }
            }
            JavaFile javaFile = JavaFile.builder(Utils.PackageName, tb.build()).build();// 生成源代码
            javaFile.writeTo(mAbstractProcessor.mFiler);// 在 app module/build/generated/source/apt 生成一份源代码
        } catch (FilerException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
