package file.highway.model;

import file.highway.enums.TransformStatusEnum;

import java.io.RandomAccessFile;

/**
 * @Intro
 * @Author liutengfei
 */
public class FileTransformModel {
    private String originFileName;
    private Long startTime;
    private Long endTime;
    private String targetFileName;
    private String storagePath;
    private TransformStatusEnum transformStatus;
    private Long fileSize;
    private Long position;
    private RandomAccessFile targetFile;

    public RandomAccessFile getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(RandomAccessFile targetFile) {
        this.targetFile = targetFile;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
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

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public TransformStatusEnum getTransformStatus() {
        return transformStatus;
    }

    public void setTransformStatus(TransformStatusEnum transformStatus) {
        this.transformStatus = transformStatus;
    }
}
