package file.highway.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;



@Service
public class FileService {
    private static final String path = "F:\\websocket\\test\\servicetemp\\";
    private Logger log = LoggerFactory.getLogger(FileService.class);
    private class ChunkFile{
        String chunkFileName;
        Integer index;

        public ChunkFile(String chunkFileName, Integer index) {
            this.chunkFileName = chunkFileName;
            this.index = index;
        }

        public String getChunkFileName() {
            return chunkFileName;
        }

        public Integer getIndex() {
            return index;
        }
    }
    Map<String , PriorityQueue<ChunkFile>> uploadFile = new ConcurrentHashMap<>();

    public static Comparator<ChunkFile> idComparator = new Comparator<ChunkFile>() {
        @Override
        public int compare(ChunkFile o1, ChunkFile o2) {
            return (int)(o1.getIndex() - o2.getIndex());
        }
    };


    public void addChunk(String fileName, String chunkFileName, Integer index) {
        if(uploadFile.containsKey(fileName)){
            PriorityQueue<ChunkFile> chunkFiles = uploadFile.get(fileName);
            chunkFiles.add(new ChunkFile(chunkFileName, index));
        }else{
            PriorityQueue<ChunkFile> chunkFiles = new PriorityQueue<ChunkFile>(idComparator);
            chunkFiles.add(new ChunkFile(chunkFileName, index));
            uploadFile.put(fileName, chunkFiles);
        }
    }


    public void uploadWithBlock(String name,
                                String md5,
                                MultipartFile file,
                                Integer index) throws IOException {
        String fileName = md5;
        if(containMd5(name, fileName)){
            log.warn("uploadWithBlock containMd5,filename:{},md5:{},index:{}",name,md5,index);
            return;
        }
        FileUtils.writeWithBlok(path + fileName, file.getInputStream());
        addChunk(name,fileName,index);
    }


    public boolean containMd5(String fileName, String chunkFileName) {
        if(!uploadFile.containsKey(fileName)){
            return false;
        }
        return uploadFile.get(fileName).contains(chunkFileName);
    }

    public static void mergeFile( RandomAccessFile out, String chunkFilePath) {
        RandomAccessFile in = null;
         try {
             in = new RandomAccessFile(chunkFilePath, "r");
            int len = 0;
            byte[] bt = new byte[1024];
            while (true) {
                if (!(-1 != (len = in.read(bt)))) {
                    break;
                };
                out.write(bt, 0, len);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
             if (in != null) {
                 try {
                     in.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
    }

    public void merge(String fileName){
        RandomAccessFile out = null;
        try{
            if(!uploadFile.containsKey(fileName)){
                return;
            }
            PriorityQueue<ChunkFile> queue = uploadFile.get(fileName);
            out = new RandomAccessFile(path + fileName, "rw");
            while(true){
                ChunkFile one = queue.poll();
                mergeFile(out, path + one.getChunkFileName());
                if(queue.isEmpty()){
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
