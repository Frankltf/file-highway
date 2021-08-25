package file.highway.spring;

import file.highway.core.ConnectedProcess;
import file.highway.core.FileProcess;
import file.highway.core.HighwayContext;
import file.highway.monitor.ConcurrentStatsCounter;
import file.highway.monitor.StatsCounter;
import file.highway.socket.*;
import file.highway.utils.SpiUtil;
import io.netty.channel.ChannelInitializer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import static com.alibaba.spring.util.BeanRegistrar.registerInfrastructureBean;

/**
 * @Intro
 * @Author liutengfei
 */
public class HighwayServiceClassPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registerCommonBeans(registry);
    }

    private void registerCommonBeans(BeanDefinitionRegistry registry) {
        registerInfrastructureBean(registry, HighwayApplicationListenerRegistrar.class.getName(), HighwayApplicationListenerRegistrar.class);
        registerInfrastructureBean(registry, FileHighwayServer.class.getName(), FileHighwayServer.class);
        registerInfrastructureBean(registry, ChannelInitializer.class.getName(), WebSocketChannelInitializer.class);
        registerInfrastructureBean(registry, "HttpRequestHandler", HttpRequestHandler.class);
        registerInfrastructureBean(registry, "HighwayHandler", HighwayHandler.class);
        registerInfrastructureBean(registry, "HighwayBinaryHandler", HighwayBinaryHandler.class);
        registerInfrastructureBean(registry, "HighwayCloseHandler", HighwayCloseHandler.class);
        registerInfrastructureBean(registry, StatsCounter.class.getName(), ConcurrentStatsCounter.class);
        registerInfrastructureBean(registry, HighwayContext.class.getName(), HighwayContext.class);
        SpiUtil.spiRegisty(ConnectedProcess.class, registry);
        SpiUtil.spiRegisty(FileProcess.class, registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
