package file.highway.socket;

import file.highway.core.HighwayContext;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Intro
 * @Author liutengfei
 */
@ChannelHandler.Sharable
public class HighwayCloseHandler  extends SimpleChannelInboundHandler<CloseWebSocketFrame> {
    @Autowired
    private HighwayContext context;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CloseWebSocketFrame msg) throws Exception {
        context.removeChannel(ctx.channel().id());
        ctx.channel().close();
        System.out.print("fefef");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.print("channelInactive");
    }
}
