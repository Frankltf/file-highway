package file.highway.core;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.core.Ordered;

/**
 * @Intro
 * @Author liutengfei
 */
public interface ConnectedProcess extends Ordered {
    void preHandle(ChannelHandlerContext ctx, Object msg);
    void afterHandle(ChannelHandlerContext ctx, Object msg);
}
