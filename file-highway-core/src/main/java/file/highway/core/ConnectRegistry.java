package file.highway.core;

import file.highway.model.ConnectModel;
import io.netty.channel.ChannelId;

/**
 * @Intro
 * @Author liutengfei
 */
public interface ConnectRegistry {
    void registryChannel(ChannelId id, ConnectModel connectModel);
    void removeChannel(ChannelId id);
    ConnectModel getChannel(ChannelId id);
}
