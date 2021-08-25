package file.highway.core;

import file.highway.enums.ConnectEnum;
import file.highway.exception.ConnectedProcessException;
import file.highway.model.ConnectModel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Intro
 * @Author liutengfei
 */
public class RegistryConnectionProcess implements ConnectedProcess {
    private Logger log = LoggerFactory.getLogger(RegistryConnectionProcess.class);
    @Autowired
    private HighwayContext highwayContext;
    
    @Override
    public void preHandle(ChannelHandlerContext ctx, Object msg) {
    }

    @Override
    public void afterHandle(ChannelHandlerContext ctx, Object msg) {
        try {
            ConnectModel connectModel = new ConnectModel();
            connectModel.setChannel(ctx.channel());
            connectModel.setConnectEnum(ConnectEnum.CONNECT_SUCCESS);
            connectModel.setStartTime(System.currentTimeMillis());
            highwayContext.registryChannel(ctx.channel().id(),connectModel);
        }catch (Exception e){
            log.error("RegistryConnectionProcess afterHandle fail", e);
            throw new ConnectedProcessException("RegistryConnectionProcess afterHandle fail", e);
        }
    }

    @Override
    public int getOrder() {
        return 300;
    }
}
