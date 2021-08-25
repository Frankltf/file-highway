package file.highway.demo;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @Intro
 * @Author liutengfei
 */
public class HttpUploadTest {
    public static void main(String[] args) {
        String sourceFile = "F:\\websocket\\test\\13结果图.jpg";
        String targetFile = "F:\\websocket\\test\\temp\\";
        FileService fileService = new FileService();
        Integer chunkSize = 1024*10;
        try {
            RandomAccessFile raf = new RandomAccessFile(sourceFile, "r");
            long length = raf.length();
            raf.seek(0);
            byte[] flush = new byte[chunkSize];
            int len = -1;
            Integer index = 0;
            while((len=raf.read(flush))!=-1) {
                String fileName = UUID.randomUUID().toString().replaceAll("-", "");
                RandomAccessFile os = new RandomAccessFile(targetFile + fileName,"rw");//写
                if(length>len) {
                    os.write(flush,0,len);
                    length -=len;
                    MultipartFile multipartFile = getMultipartFile(targetFile + fileName);
                    fileService.uploadWithBlock("13结果图.jpg",fileName,multipartFile,index);
                    index++;
                } else {
                    os.write(flush,0, (int) length);
                    os.close();
                    MultipartFile multipartFile = getMultipartFile(targetFile + fileName);
                    fileService.uploadWithBlock("13结果图.jpg",fileName,multipartFile,index);
                    break;
                }
                os.close();
            }
            raf.close();
            fileService.merge("13结果图.jpg");
            System.out.print("fefe");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static MultipartFile getMultipartFile(String strUrl) throws IOException {
        File file = new File(strUrl);
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        return multipartFile;

    }


}
