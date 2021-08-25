package file.highway.core;

import io.netty.channel.ChannelHandlerContext;

import java.util.Set;

/**
 * @Intro
 * @Author liutengfei
 */
public interface ProcessRegistry {
    void doBeforeProcess(ChannelHandlerContext ctx, Object msg);
    void doPostProcess(ChannelHandlerContext ctx, Object msg);
}
