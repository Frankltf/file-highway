package file.highway.enums;

/**
 * @Intro
 * @Author liutengfei
 */
public enum  ConnectEnum {
    CONNECT_SUCCESS(0,"连接成功"),
    CONNECT_FAIL(1,"连接失败"),
    CONNECT_BREAK(4,"连接断开");

    private Integer code;

    private String description;

    ConnectEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
