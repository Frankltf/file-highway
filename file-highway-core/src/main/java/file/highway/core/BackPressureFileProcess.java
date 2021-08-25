package file.highway.core;

import file.highway.enums.TransformStatusEnum;
import file.highway.model.ConnectModel;
import file.highway.model.VO.ReponseVO;
import io.netty.buffer.ByteBuf;
import org.springframework.core.annotation.Order;

/**
 * @Intro
 * @Author liutengfei
 */
@Order(10)
public class BackPressureFileProcess implements FileProcess {

    @Override
    public void handle(ByteBuf byteBuf, ConnectModel connectModel) {
        connectModel.getChannel().writeAndFlush(ReponseVO.success(TransformStatusEnum.TRANSFERRING));
    }
}
