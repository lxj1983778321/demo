package com.example.demo.AOP;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/7/3 14:47
 * @Version 1.0
 * 通过BeanFactory的后只处理器实现aop横切面
 **/
@Component
public class BeanFactoryPostProcessUtil implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("USE_INFO");
        System.out.println(beanDefinition.getRole());
        System.out.println(beanDefinition.getSource());

    }
}
