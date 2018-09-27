Meta:

Narrative:
put object

Scenario: 上传对象

Given 开始初始化S3客户端,bucketName:autobucket
Then 开始上传对象并验证对象上传成功,文件大小size(MB):2
