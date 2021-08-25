package file.highway.demo;


import java.io.*;
import java.util.UUID;

public class FileUtils {


    public static void write(String target, InputStream src) throws IOException {
        OutputStream os = new FileOutputStream(target);
        byte[] buf = new byte[1024];
        int len;
        while (-1 != (len = src.read(buf))) {
            os.write(buf,0,len);
        }
        os.flush();
        os.close();
    }


    public static void writeWithBlok(String target,  InputStream src) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(target,"rw")){
            randomAccessFile.seek(0);
            byte[] buf = new byte[1024];
            int len;
            while (-1 != (len = src.read(buf))) {
                randomAccessFile.write(buf,0,len);
            }
            if(null != randomAccessFile){
                randomAccessFile.close();
            }
        }catch (Exception e){

        }

    }


    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
