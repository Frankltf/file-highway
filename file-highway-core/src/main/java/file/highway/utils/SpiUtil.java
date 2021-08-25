package file.highway.utils;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.ServiceLoader;

import static com.alibaba.spring.util.BeanRegistrar.registerInfrastructureBean;

/**
 * @Intro
 * @Author liutengfei
 */
public interface SpiUtil {
    static <T> ServiceLoader<T> spiRegisty(Class<T> type, BeanDefinitionRegistry registry){
        ServiceLoader<T> loader = ServiceLoader.load(type);
        for(T service : loader) {
            registerInfrastructureBean(registry, service.getClass().getName(),
                    service.getClass());
        }
        return loader;
    }


}
