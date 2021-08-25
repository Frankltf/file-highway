package file.highway.spring;

import file.highway.core.HighwayBootstrap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Intro
 * @Author liutengfei
 */
public class HighwayBootstrapApplicationListener implements ApplicationListener {
    private ConfigurableApplicationContext context;

    public HighwayBootstrapApplicationListener(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent){
            HighwayBootstrap homerBootstrap = new HighwayBootstrap(context);
            homerBootstrap.start();
        }
    }
}
