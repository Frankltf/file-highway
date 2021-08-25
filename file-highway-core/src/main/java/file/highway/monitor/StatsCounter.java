package file.highway.monitor;

/**
 * @Intro
 * @Author liutengfei
 */
public interface StatsCounter {
    void recordConnectSuccess();
    void recordConnectFailure();
    void recordUploadSuccess();
    void recordUploadFailure();
    void recordUploadFileSize(long inc);
    HighwayStats snapshot();
}
