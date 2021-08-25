package file.highway.model.VO;

import com.alibaba.fastjson.annotation.JSONField;
import file.highway.enums.TransformStatusEnum;

/**
 * @Intro
 * @Author liutengfei
 */
public class RequestVO {
    private String originFileName;
    private String storagePath;
    private Long fileSize;

    private TransformStatusEnum transformStatusEnum;

    @JSONField(name = "status")
    public int getStatus() {
        return transformStatusEnum.getCode();
    }

    @JSONField(name = "status")
    public void setStatus(int code) {
        this.transformStatusEnum = TransformStatusEnum.getEnum(code);
    }

    @JSONField(serialize = false)
    public TransformStatusEnum getTransformStatusEnum() {
        return transformStatusEnum;
    }

    @JSONField(deserialize = false)
    public void setTransformStatusEnum(TransformStatusEnum transformStatusEnum) {
        this.transformStatusEnum = transformStatusEnum;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
