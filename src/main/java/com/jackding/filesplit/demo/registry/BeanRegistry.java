package com.jackding.filesplit.demo.registry;

import com.jackding.filesplit.demo.annotation.Tran;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class BeanRegistry implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware {
    private ResourceLoader resourceLoader;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
            List<Class> list=doScan();
            for (Class c:list){
                BeanDefinition beanDefinition=new RootBeanDefinition(c);
                beanDefinitionRegistry.registerBeanDefinition(c.getSimpleName(),beanDefinition);
            }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }

    public   List<Class> doScan()  {
        List<Class> list=new ArrayList<>();
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
       try {
           Resource[] resources = resolver.getResources("classpath*:com/jackding/filesplit/demo/trans/**/*.class");
           for (Resource r : resources
           ) {
               String clazzName=metaReader.getMetadataReader(r).getClassMetadata().getClassName();
               Class clazz=Thread.currentThread().getContextClassLoader().loadClass(clazzName);
               if (clazz.isAnnotationPresent(Tran.class)) {
                   list.add(clazz);
               }
           }
       }catch (IOException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
        return list;
    }
}
