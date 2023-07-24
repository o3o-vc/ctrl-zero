package com.onezero.security.access;

import com.onezero.security.access.annotation.CmdHandlerAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
/** 
* {@code} @description: 命令Handler自动注入处理类
* {@code} @author: voncho
*/
public class CmdAwareProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof Aware) {
            if (bean instanceof CmdHandlerAware cmdHandlerAware) {
                CmdHandler cmdHandler = applicationContext.getBean(CmdHandler.class);
                cmdHandlerAware.setCmdHandler(cmdHandler);
                return bean;
            }
        }
        return bean;
    }
}
