package file.highway.core;

import file.highway.model.ConnectModel;
import file.highway.utils.ProcessUitl;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Intro
 * @Author liutengfei
 */
public class HighwayContext implements Context, ConnectRegistry,ProcessRegistry, InitializingBean, ApplicationContextAware {

    private Map<ChannelId, ConnectModel> connectModelMap = new ConcurrentHashMap<>();
    private List<ConnectedProcess> connectedProcesses = new ArrayList<>();
    private List<FileProcess> fileProcesses = new ArrayList<>();
    private ApplicationContext applicationContext;


    @Override
    public void registryChannel(ChannelId id, ConnectModel connectModel) {
        connectModelMap.put(id,connectModel);
    }

    @Override
    public void removeChannel(ChannelId id) {
        ConnectModel connectModel = getChannel(id);
        if(null != connectModel){
            if(null != connectModel.getCurrentFileTransformModel()){
                if(null != connectModel.getCurrentFileTransformModel().getTargetFile()){
                    try {
                        connectModel.getCurrentFileTransformModel().getTargetFile().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        connectModelMap.remove(id);
    }

    @Override
    public ConnectModel getChannel(ChannelId id) {
        return connectModelMap.get(id);
    }

    @Override
    public void doBeforeProcess(ChannelHandlerContext ctx, Object msg) {
        connectedProcesses.forEach(value -> value.preHandle(ctx, msg));
    }

    @Override
    public void doPostProcess(ChannelHandlerContext ctx, Object msg) {
        connectedProcesses.forEach(value -> value.afterHandle(ctx, msg));
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.connectedProcesses = ProcessUitl.processOrder(applicationContext, ConnectedProcess.class);
        this.fileProcesses = ProcessUitl.processOrder(applicationContext, FileProcess.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void handle(ByteBuf byteBuf, ConnectModel connectModel) {
        fileProcesses.forEach(value -> value.handle(byteBuf, connectModel));
    }
}
