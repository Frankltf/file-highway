package file.highway.socket;

import file.highway.core.HighwayContext;
import file.highway.monitor.StatsCounter;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Intro
 * @Author liutengfei
 */
@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<Object> {
    private Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);
    @Autowired
    private HighwayContext highwayContext;

    @Autowired
    private StatsCounter statsCounter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            connectedBeforeProcess(ctx,msg);
            connectedPostProcess(ctx, msg);
        } else if (msg instanceof WebSocketFrame) {
            ctx.fireChannelRead(((WebSocketFrame) msg).retain());
        }
    }

    private void connectedBeforeProcess(ChannelHandlerContext ctx, Object msg){
        highwayContext.doBeforeProcess(ctx, msg);
    }

    private void connectedPostProcess(ChannelHandlerContext ctx, Object msg){
        highwayContext.doPostProcess(ctx, msg);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        highwayContext.removeChannel(ctx.channel().id());
        log.info("channelInactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("channelInactive");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.print("channelRegistered");
    }
}
