package steps;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import common.BaseOperations;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;
import utils.CyclingInputStream;


import java.io.File;
import java.io.IOException;

/**
 * 以Amazon S3 putObject为例（上传对象）
 */

/**
 * Created by jellyfish on 2018/8/7.
 */
public class PutObjectStep extends BaseOperations {
    private String endPoint = Rgw_ip();
    private String userAK = userAK();
    private String userSK = userSK();
    private String filePath = filePath();
    public Bucket bucket;
    public AmazonS3 s3Client;

    private static final Log log = LogFactory.get();

    @Given("开始初始化S3客户端,bucketName:$bucketName")
    public void initS3Client(String bucketName) {

        //---使用租户创建bucket(桶)
        RGWPassport userPassport = new RGWPassport(endPoint,userAK,userSK);
        AWSCredentials credentials = new BasicAWSCredentials(userPassport.getAccessKey(), userPassport.getSecretKey());
        ClientConfiguration conf = new ClientConfiguration();
        conf.setProtocol(Protocol.HTTP);
        conf.setSignerOverride("S3SignerType");
        bucketName=bucketName+System.currentTimeMillis();
        s3Client = new AmazonS3Client(credentials, conf);
        s3Client.setEndpoint(userPassport.getEndPoint());
        bucket = s3Client.createBucket(bucketName);

        //--验证创建bucket成功
        Assert.assertTrue(!(s3Client.getBucketLocation(bucketName)).isEmpty());
        log.info("创建bucket成功,bucket名为:"+bucketName);
    }

    @Then("开始上传对象并验证对象上传成功,文件大小size(MB):$size")
    public void putObject(int size) throws IOException{

        //---在指定目录下准备一个临时文件
        String objectKey = SecureUtil.simpleUUID();
        File testFile =new File(filePath + File.separator + String.valueOf(System.currentTimeMillis()));
        FileUtil.writeFromStream(new CyclingInputStream(getFileSize(size)),testFile);

        //---开始上传文件
        PutObjectResult putObjectResult = s3Client.putObject(bucket.getName(),objectKey,testFile);

        //---验证文件上传成功
        Assert.assertTrue(putObjectResult.getETag()!=null);
        log.info("上传object成功！");

    }

}
