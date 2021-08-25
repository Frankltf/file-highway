package file.highway.socket;

import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.annotation.Resource;

/**
 * @Intro
 * @Author liutengfei
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource(name = "HttpRequestHandler")
    private ChannelInboundHandler httpRequestHandler;

    @Resource(name = "HighwayHandler")
    private ChannelInboundHandler HighwayHandler;

    @Resource(name = "HighwayBinaryHandler")
    private ChannelInboundHandler HighwayBinaryHandler;

    @Resource(name = "HighwayCloseHandler")
    private HighwayCloseHandler highwayCloseHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new HttpObjectAggregator(1024*1024));
        pipeline.addLast(new WebSocketFrameAggregator(1024*1024));
        pipeline.addLast(httpRequestHandler);
        pipeline.addLast(HighwayHandler);
        pipeline.addLast(HighwayBinaryHandler);
        pipeline.addLast(highwayCloseHandler);
//        pipeline.addLast(new IdleStateHandler(4,8,12));
//        pipeline.addLast(new HearBeatHandler());
    }
}
