package file.highway.model.VO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import file.highway.enums.TransformStatusEnum;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Intro
 * @Author liutengfei
 */
public class ReponseVO {
    private Boolean handle;
    private TransformStatusEnum transformStatusEnum;
    private int chunkSize;

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Boolean getHandle() {
        return handle;
    }

    public void setHandle(Boolean handle) {
        this.handle = handle;
    }

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

    public static TextWebSocketFrame success(TransformStatusEnum transformStatusEnum){
        return success(transformStatusEnum, 0);
    }

    public static TextWebSocketFrame success(TransformStatusEnum transformStatusEnum, int chunkSize){
        ReponseVO res = new ReponseVO();
        res.setTransformStatusEnum(transformStatusEnum);
        res.setHandle(true);
        res.setChunkSize(chunkSize);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JSON.toJSONString(res));
        return textWebSocketFrame;
    }

    public static TextWebSocketFrame fail(TransformStatusEnum transformStatusEnum){
        ReponseVO res = new ReponseVO();
        res.setTransformStatusEnum(transformStatusEnum);
        res.setHandle(false);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JSON.toJSONString(res));
        return textWebSocketFrame;
    }
}
