package file.highway.demo;

import file.highway.socket.HighwayBinaryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 大文件上传
 */
@RestController
@RequestMapping
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class BigFileUploadController {
    private Logger log = LoggerFactory.getLogger(BigFileUploadController.class);
    @Autowired
    private FileService fileService;

    @GetMapping("/ok")
    @CrossOrigin
    public Response ok(){
        return Response.Response("test",200);
    }

    @PostMapping("/upload")
    @CrossOrigin
    public Response upload(String fileName,
                       String md5,
                       Integer index,
                       MultipartFile file) throws IOException {
        try {
            fileService.uploadWithBlock(fileName, md5,file,index );
            log.info("request,filename:{},index:{},md5:{},filesize:{}",fileName,index,md5,file.getSize());
            return Response.Response("success",200);
        }catch (Exception e){
            log.info("request error,filename:{},index:{},md5:{},filesize:{}",fileName,index,md5,file.getSize());
            return Response.Response("error",1000);
        }
    }

    @PostMapping("/merge")
    @CrossOrigin
    public Response merge(String fileName) throws IOException {
        try {
            log.info("merge,filename:{}",fileName);
            fileService.merge(fileName);
            return Response.Response("success",200);
        }catch (Exception e){
            log.error("merge error,filename:{}",fileName);
            return Response.Response("error",1000);
        }
    }


}
