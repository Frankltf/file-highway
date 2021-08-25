package file.highway.core;

import file.highway.model.ConnectModel;
import io.netty.buffer.ByteBuf;

/**
 * @Intro
 * @Author liutengfei
 */
public interface Context {
    void handle(ByteBuf byteBuf, ConnectModel connectModel);
}
