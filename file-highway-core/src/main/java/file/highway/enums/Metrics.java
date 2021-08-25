package file.highway.enums;

/**
 * @Intro
 * @Author liutengfei
 */
public enum Metrics {
    APP_NAME("highway",  "app_name", "应用名"),
    SERVER("highway", "server", "服务ip"),
    CURRENT_SOCKET_CONNECT_COUNT("highway", "currentSocketCount", "当前连接数"),
    SOCKET_SUCCESS_CONNECT_COUNT("highway", "socketSuccessCount", "连接数成功"),
    SOCKET_FAIL_CONNECT_COUNT("highway", "socketFailCount", "失败连接数"),
    SOCKET_CONNECT_SUCCESS_RATE("highway", "socketSuccessRate", "连接成功率"),
    SOCKET_DURATION("highway", "socketDuration", "连接平均持续时间"),
    UPLOAD_SPEED_AVERAGE("highway", "uploadSpeedAverage", "平均上传速度"),
    UPLOAD_FILE_SUCCESS_COUNT("highway", "uploadFileSuccessCount", "上传成功总文件数"),
    UPLOAD_FILE_FAIL_COUNT("highway", "uploadFileFailCount", "上传失败总文件数"),
    UPLOAD_FILE_SIZE_COUNT("highway", "uploadFileSizeCount", "上传文件总大小");

    private String measurement;
    private String tag;
    private String desc;

    Metrics(String measurement, String field, String tag) {
        this.measurement = measurement;
        this.desc = field;
        this.tag = tag;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getTag() {
        return tag;
    }

    public String getDesc() {
        return desc;
    }}
