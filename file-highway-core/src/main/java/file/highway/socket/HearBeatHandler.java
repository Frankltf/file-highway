package file.highway.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Intro
 * @Author liutengfei
 */
public class HearBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent)evt;

            if (event.state()== IdleState.READER_IDLE){
                System.out.println("读空闲");
            }else if(event.state()==IdleState.WRITER_IDLE){
                System.out.println("写空闲");
            }else if(event.state()==IdleState.ALL_IDLE){
                System.out.println("读写都空闲你，关闭通道");
                ctx.channel().close();
            }
        }
    }
}

