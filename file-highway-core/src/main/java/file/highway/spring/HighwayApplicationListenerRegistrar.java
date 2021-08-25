package file.highway.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Intro
 * @Author liutengfei
 */
public class HighwayApplicationListenerRegistrar implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        addApplicationListeners((ConfigurableApplicationContext) applicationContext);
    }

    private void addApplicationListeners(ConfigurableApplicationContext context) {
        context.addApplicationListener(createDubboBootstrapApplicationListener(context));
    }


    private ApplicationListener<?> createDubboBootstrapApplicationListener(ConfigurableApplicationContext context){
        return new HighwayBootstrapApplicationListener(context);
    }
}
