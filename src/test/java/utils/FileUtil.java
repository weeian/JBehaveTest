package utils;

import java.io.*;

/**
 * Created by jellyfish on 2018/8/7.
 */
public class FileUtil {
    public static void copyInputStreamToFile(InputStream in, File file) throws IOException {
        BufferedInputStream bin =new BufferedInputStream(in);
        byte[] buffer = new byte[256*1024];
        BufferedOutputStream bout =new BufferedOutputStream(new FileOutputStream(file));
        while(true){
            int read = bin.read(buffer, 0, buffer.length);
            if(read<=0){
                break;
            }
            bout.write(buffer,0,read);
            bout.flush();
        }

        bin.close();
        bout.close();
    }
}
