package file.highway.core;

import file.highway.exception.FileProcessException;
import file.highway.model.ConnectModel;
import file.highway.model.FileTransformModel;
import file.highway.monitor.StatsCounter;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Intro
 * @Author liutengfei
 */
@Order(5)
public class DefaultFileProcess implements FileProcess {
    private Logger log = LoggerFactory.getLogger(DefaultFileProcess.class);

    @Autowired
    private StatsCounter statsCounter;

    @Override
    public void handle(ByteBuf byteBuf, ConnectModel connectModel) {
        FileTransformModel currentFileTransformModel = connectModel.getCurrentFileTransformModel();
        try {
            RandomAccessFile targetFile= currentFileTransformModel.getTargetFile();
            targetFile.seek(currentFileTransformModel.getPosition());
            byte[] bys = new byte[byteBuf.readableBytes()];
            byteBuf.getBytes(0,bys);
            targetFile.write(bys);
            currentFileTransformModel.setPosition(currentFileTransformModel.getPosition() + bys.length);
            statsCounter.recordUploadFileSize(bys.length);
        } catch (FileNotFoundException e) {
            log.error("DefaultFileProcess FileNotFoundException fail",e);
            throw new FileProcessException("DefaultFileProcess FileNotFoundException fail",e);
        } catch (IOException e) {
            log.error("DefaultFileProcess IOException fail",e);
            throw new FileProcessException("DefaultFileProcess IOException fail",e);
        }
    }


}
