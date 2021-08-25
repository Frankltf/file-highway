package file.highway.socket;

import com.alibaba.fastjson.JSON;
import file.highway.core.HighwayContext;
import file.highway.enums.TransformStatusEnum;
import file.highway.model.FileTransformModel;
import file.highway.model.VO.ReponseVO;
import file.highway.model.VO.RequestVO;
import file.highway.monitor.StatsCounter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @Intro
 * @Author liutengfei
 */
@ChannelHandler.Sharable
public class HighwayHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private Logger log = LoggerFactory.getLogger(HighwayHandler.class);
    @Autowired
    private HighwayContext context;

    @Autowired
    private StatsCounter statsCounter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        try {
            RequestVO requestVO = JSON.parseObject(msg.text(), RequestVO.class);
            if(requestVO.getTransformStatusEnum().getCode().equals(TransformStatusEnum.TRANSFORM_PRE.getCode())){
                FileTransformModel fileTransformModel = new FileTransformModel();
                fileTransformModel.setEndTime(System.currentTimeMillis());
                fileTransformModel.setFileSize(requestVO.getFileSize());
                fileTransformModel.setOriginFileName(requestVO.getOriginFileName());
                fileTransformModel.setStoragePath(requestVO.getStoragePath());
                fileTransformModel.setTransformStatus(TransformStatusEnum.TRANSFORM_PRE);
                fileTransformModel.setPosition(0L);
                fileTransformModel.setTargetFileName(requestVO.getOriginFileName());
                File file = new File(requestVO.getStoragePath());
                if(!file.exists()){
                    file.mkdirs();
                }
                RandomAccessFile targetFile= new RandomAccessFile(requestVO.getStoragePath()+ fileTransformModel.getTargetFileName(), "rw");
                fileTransformModel.setTargetFile(targetFile);
                if(null == context.getChannel(ctx.channel().id()).getCurrentFileTransformModel()){
                    context.getChannel(ctx.channel().id()).setCurrentFileTransformModel(fileTransformModel);
                    ctx.channel().writeAndFlush(ReponseVO.success(TransformStatusEnum.TRANSFORM_PRE, 500));
                }else{
                    ctx.channel().writeAndFlush(ReponseVO.fail(TransformStatusEnum.TRANSFORM_PRE));
                }
            }else if(requestVO.getTransformStatusEnum().getCode().equals(TransformStatusEnum.TRANSFORM_FINISH.getCode())){
                ctx.channel().writeAndFlush(ReponseVO.success(TransformStatusEnum.TRANSFORM_FINISH));
                statsCounter.recordUploadSuccess();
                if(null != context.getChannel(ctx.channel().id()).getCurrentFileTransformModel().getTargetFile()){
                    context.getChannel(ctx.channel().id()).getCurrentFileTransformModel().getTargetFile().close();
                    context.getChannel(ctx.channel().id()).setCurrentFileTransformModel(null);
                }
            }
        }catch (Exception e){
            log.error("TextWebSocketFrame deal fail", e);
            ctx.channel().writeAndFlush(ReponseVO.fail(TransformStatusEnum.TRANSFORM_PRE));
            statsCounter.recordUploadFailure();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("HighwayHandler exceptionCaught", cause);
    }

}
