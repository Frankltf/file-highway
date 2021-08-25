package file.highway.core;

import file.highway.socket.FileHighwayServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;

/**
 * @Intro
 * @Author liutengfei
 */
public class HighwayBootstrap implements Lifecycle {
    private ApplicationContext context;

    public HighwayBootstrap(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void start() {
        FileHighwayServer server = context.getBean(FileHighwayServer.class);
        server.start();
    }


    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
