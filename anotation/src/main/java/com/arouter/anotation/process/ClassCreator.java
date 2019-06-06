package com.arouter.anotation.process;



import com.arouter.anotation.METHOD;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;


/**
 * java类的创建者
 */
public class ClassCreator {
    private String mBindingClassName;
    private String mPackageName;
    private TypeElement mTypeElement;
    private Messager mMessager;


    public ClassCreator(Elements elementUtils, TypeElement classElement, Messager messager) {
        this.mTypeElement = classElement;
        this.mMessager = messager;
        PackageElement packageElement = elementUtils.getPackageOf(mTypeElement);
        String packageName = packageElement.getQualifiedName().toString();
        String className = mTypeElement.getSimpleName().toString();
        this.mPackageName = packageName;
        this.mBindingClassName = className+"Proxy";
    }

    public String getProxyClassFullName() {
        return mPackageName + "." + mBindingClassName;
    }

    public TypeElement getTypeElement() {
        return mTypeElement;
    }

    //======================

    /**
     * 创建Java代码
     * javapoet
     *
     * @return
     */
    public TypeSpec generateProxy() {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(mBindingClassName);
        classBuilder.addJavadoc("这个是自动生成的代码")
                .addModifiers(Modifier.PUBLIC);

        List<? extends Element> elements = mTypeElement.getEnclosedElements();
        List<String> handlerMethodNames = new ArrayList<>(5);
        List<String> callHandlerMethodNames = new ArrayList<>(5);
        for (Element element : elements) {
            if (element.getKind() == ElementKind.METHOD) {
                ExecutableElement executableElement = (ExecutableElement) element;
                String methodName = executableElement.getSimpleName().toString();
                mMessager.printMessage(Diagnostic.Kind.NOTE, "executableElement " + methodName);
                //找到被Request注解的函数
                METHOD method = executableElement.getAnnotation(METHOD.class);
                if (method != null) {
                    handlerMethodNames.add(methodName);
                }
            }
        }
        classBuilder.addMethods(generateRequestMethod(callHandlerMethodNames));

        return classBuilder.build();
    }



    private List<MethodSpec> generateRequestMethod(List<String> callHandlerNames) {
        List<MethodSpec> methodSpecList = new ArrayList<>(5);
        ClassName BridgeWebView = ClassName.get("com.smarthome.hdjsbridge", "BridgeWebView");
        ClassName BridgeHandler = ClassName.get("com.smarthome.hdjsbridge", "BridgeHandler");
        ClassName CallBackFunction = ClassName.get("com.smarthome.hdjsbridge", "CallBackFunction");

        for (String handlerName : callHandlerNames) {

        }
        return methodSpecList;
    }




    public String getPackageName() {
        return mPackageName;
    }
}

