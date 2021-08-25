package file.highway.monitor;

/**
 * @Intro
 * @Author liutengfei
 */
public class HighwayStats {
    private final long socketSuccessCount;
    private final long socketFailCount;
    //连接持续时间
    private final long uploadFileSuccessCount;
    private final long uploadFileFailCount;
    private final long uploadFileSize;

    public HighwayStats(long socketSuccessCount, long socketFailCount,
                        long uploadFileSuccessCount, long uploadFileFailCount,
                        long uploadFileSize) {
        this.socketSuccessCount = socketSuccessCount;
        this.socketFailCount = socketFailCount;
        this.uploadFileSuccessCount = uploadFileSuccessCount;
        this.uploadFileFailCount = uploadFileFailCount;
        this.uploadFileSize = uploadFileSize;
    }



    public long getSocketSuccessCount() {
        return socketSuccessCount;
    }

    public long getSocketFailCount() {
        return socketFailCount;
    }


    public long getUploadFileSuccessCount() {
        return uploadFileSuccessCount;
    }

    public long getUploadFileFailCount() {
        return uploadFileFailCount;
    }

    public long getUploadFileSize() {
        return uploadFileSize;
    }
}
