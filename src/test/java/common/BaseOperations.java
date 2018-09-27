package common;

import com.xiaoleilu.hutool.setting.dialect.Props;

/**
 * Created by jellyfish on 2018/8/6.
 */
public class BaseOperations {

    protected String Rgw_ip(){
        Props props = new Props("config/yc-s3-sdk-test.properties");
        String Rgw_ip = props.getStr("Rgw_ip");
        return Rgw_ip;
    }

    protected String userAK(){
        Props props = new Props("config/yc-s3-sdk-test.properties");
        String userAK = props.getStr("userAK");
        return userAK;
    }

    protected String userSK(){
        Props props = new Props("config/yc-s3-sdk-test.properties");
        String userSK = props.getStr("userSK");
        return userSK;
    }

    protected String filePath(){
        Props props = new Props("config/yc-s3-sdk-test.properties");
        String filePath = props.getStr("filePath");
        return filePath;
    }

    protected int getFileSize(int size){
        return size*1023*1025;
    }
}
