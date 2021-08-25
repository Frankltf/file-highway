package file.highway.model;

import file.highway.enums.ConnectEnum;
import io.netty.channel.Channel;

/**
 * @Intro
 * @Author liutengfei
 */
public class ConnectModel {
    private Long startTime;
    private Long endTime;
    private ConnectEnum connectEnum;
    private Channel channel;
    private FileTransformModel currentFileTransformModel;

    public FileTransformModel getCurrentFileTransformModel() {
        return currentFileTransformModel;
    }

    public void setCurrentFileTransformModel(FileTransformModel currentFileTransformModel) {
        this.currentFileTransformModel = currentFileTransformModel;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public ConnectEnum getConnectEnum() {
        return connectEnum;
    }

    public void setConnectEnum(ConnectEnum connectEnum) {
        this.connectEnum = connectEnum;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
