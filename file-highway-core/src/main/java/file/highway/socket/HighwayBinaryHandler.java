package file.highway.socket;

import com.alibaba.fastjson.JSON;
import file.highway.core.HighwayContext;
import file.highway.enums.TransformStatusEnum;
import file.highway.model.VO.ReponseVO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Intro
 * @Author liutengfei
 */
@ChannelHandler.Sharable
public class HighwayBinaryHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    private Logger log = LoggerFactory.getLogger(HighwayBinaryHandler.class);
    @Autowired
    private HighwayContext context;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        ByteBuf byteBuf = null;
        try {
            byteBuf = Unpooled.directBuffer(msg.content().capacity());
            byteBuf.writeBytes(msg.content());
            context.handle(byteBuf, context.getChannel(ctx.channel().id()));
        }catch (Exception e){
            ctx.channel().writeAndFlush(ReponseVO.fail(TransformStatusEnum.TRANSFERRING));
        }finally {
//            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("HighwayBinaryHandler exceptionCaught", cause);
    }
}
