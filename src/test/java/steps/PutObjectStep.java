package steps;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import common.BaseOperations;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

import java.io.IOException;

/**
 * Created by jellyfish on 2018/8/7.
 */
public class PutObjectStep extends BaseOperations {

    //个人比较喜欢且常用的日志工具
    private static final Log log = LogFactory.get();

    @Given("开始初始化S3客户端,bucketName:$bucketName")
    public void initS3Client(String bucketName) {

    }

    @Then("开始上传对象并验证对象上传成功,文件大小size(MB):$size")
    public void putObject(int size) throws IOException{


    }

}
