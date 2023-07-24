package com.onezero.security.access;

import com.onezero.security.access.annotation.CmdScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.lang.NonNull;
/** 
* {@code @description:} CmdScan注解实现类
* {@code @author:} voncho
* {@code @date:} 2022/12/27 11:55
 */
public class CmdScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(@NonNull ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata, @NonNull BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(CmdScan.class.getName()));
        if (annoAttrs != null) {
            String[] basePackages = annoAttrs.getStringArray("value");
            //获取到basePackage的值
            if (basePackages.length == 0) {
                basePackages = annoAttrs.getStringArray("basePackage");
            }
            //如果没有设置basePackage 扫描路径,就扫描对应包下面的值
            if(basePackages.length == 0 && importingClassMetadata instanceof StandardAnnotationMetadata standardAnnotationMetadata){
                basePackages = new String[]{standardAnnotationMetadata.getIntrospectedClass().getPackage().getName()};
            }

            CmdClassPathScanHandler cmdClassPathScanHandler = new CmdClassPathScanHandler(registry, false);
            cmdClassPathScanHandler.scan(basePackages);
        }
    }
}
