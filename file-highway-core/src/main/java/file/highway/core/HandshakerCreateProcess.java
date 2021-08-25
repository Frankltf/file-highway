package file.highway.core;

import file.highway.exception.ConnectedProcessException;
import file.highway.monitor.StatsCounter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Intro
 * @Author liutengfei
 */
public class HandshakerCreateProcess implements ConnectedProcess {
    private Logger log = LoggerFactory.getLogger(HandshakerCreateProcess.class);

    @Autowired
    private StatsCounter statsCounter;

    @Override
    public void preHandle(ChannelHandlerContext ctx, Object msg) {
    }

    @Override
    public void afterHandle(ChannelHandlerContext ctx, Object msg) {
        handleHttpRequest(ctx, (FullHttpRequest) msg);
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        try {
            if (res.status().code() != 200) {
                ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
                res.content().writeBytes(buf);
                buf.release();
            }
            boolean keepAlive = HttpUtil.isKeepAlive(req);
            ChannelFuture f = ctx.channel().writeAndFlush(res);
            if (!keepAlive) {
                f.addListener(ChannelFutureListener.CLOSE);
            }
        }catch (Exception e){
            log.error("sendHttpResponse fai", e);

            throw new ConnectedProcessException("sendHttpResponse fail", e);
        }
    }


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        try {
            if (!req.decoderResult().isSuccess()) {
                sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
                statsCounter.recordConnectFailure();
                return;
            }
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                    "ws:/" + ctx.channel() + "/websocket",null,true,65536*100);
            WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(req);
            if (handshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshaker.handshake(ctx.channel(), req);
            }
            statsCounter.recordConnectSuccess();
        }catch (Exception e){
            log.error("handleHttpRequest fail", e);
            statsCounter.recordConnectFailure();
            throw new ConnectedProcessException("handleHttpRequest fail", e);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
