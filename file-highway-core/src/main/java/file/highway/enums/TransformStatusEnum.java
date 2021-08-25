package file.highway.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Intro
 * @Author liutengfei
 */
public enum TransformStatusEnum {
    TRANSFORM_PRE(20,"准备好接收数据"),
    TRANSFERRING(30,"数据传输中"),
    TRANSFORM_FINISH(40,"文件传输完成"),
    TRANSFORM_MONITOR(50,"传输监控");
    private static final Map<Integer, TransformStatusEnum> CODE_MAP = new HashMap<Integer, TransformStatusEnum>();

    static {
        for (TransformStatusEnum typeEnum : TransformStatusEnum.values()) {
            CODE_MAP.put(typeEnum.getCode(), typeEnum);
        }
    }

    private Integer code;

    private String description;

    TransformStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public static TransformStatusEnum getEnum(int code){
        return CODE_MAP.get(code);
    }

    public String getDescription() {
        return description;
    }
}
