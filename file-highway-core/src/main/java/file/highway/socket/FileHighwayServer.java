package file.highway.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;

/**
 * @Intro
 * @Author liutengfei
 */
public class FileHighwayServer implements Lifecycle {
    @Autowired
    private WebSocketChannelInitializer webSocketChannelInitializer;

    @Override
    public void start() {
        initHighway();
    }

    private void initHighway(){
        NioEventLoopGroup mainGrp=new NioEventLoopGroup();
        NioEventLoopGroup subGrp=new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();

            serverBootstrap
                    .group(mainGrp,subGrp)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_RCVBUF, 100)
                    .childHandler(webSocketChannelInitializer);
            ChannelFuture future=serverBootstrap.bind(9090).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            mainGrp.shutdownGracefully();
            subGrp.shutdownGracefully();
        }

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
