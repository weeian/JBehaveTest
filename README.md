#### 一个可用的Jbehave 框架 ####



###### 前提须知：本项目使用的编程语言为JAVA，测试框架为 JBehave ######



##### 1.JBehave是什么？ #####

>   JBehave是一个行为驱动开发(BDD:Behaviour-Driven Development)的开源测试框架。BDD是测试驱动开发(TDD:Behaviour-Driven Development)和验收测试驱动设计(acceptance-test driven design)的一个演变，BDD会使这些实践更容易被接受，无论是菜鸟还是专家。BDD将基于测试的驱动转变为基于行为的驱动，并且BDD本身也将作为一个设计的理念。

  BDD 目的是使开发过程更加接受，并且更加直观，能很快的使新人像专家一样工作。在BDD中, 行为代表规格说明和测试用例。我们可以在wiki上找出更多关于BDD开发的内容或者关于介绍BDD的内容。



##### 2.JBehave 实现五步骤： #####

 (1) 书写story文件，如图：

![1538018230148](C:\Users\lubyc\AppData\Local\Temp\1538018230148.png)

​    这里我的案例是一个上传对象的请求操作。其中，以story为后缀的文件即我所描述的场景文件。

​    在此文件中有，有比较重要的几个关键字：

​        Scenario：场景描述。实际上对应我们所想的用户操作场景，即测试用例。

​        Given: 一般用于描述准备类的工作，例如准备客户端、准备文件等。

​        Then: 一般用于正式的操作描述，比如开始上传文件、开始添加用户等等。

​    这些关键字没有使用次数的限制，并且也不限制排列顺序。只是出于使用习惯，我们常把Given这类准备工作放在Then前面，方便梳理场景逻辑。

​     

（2）匹配step到java类，如图：

![1538018813042](C:\Users\lubyc\AppData\Local\Temp\1538018813042.png)



从图中可以看到，我们通过注解的方式匹配到对应的story文件中的描述，这里有几点注意事项：

​    **注解后的文字描述必须与story文件中的描述保持一致

​    **如果需要引用story文件中的变量，则需要在值前面加上美元符号，且值应改为对应的变量名，例如：

​    story文件中描述：

```java
  Given 开始初始化S3客户端,bucketName:autobucket   

  //这里的bucketName为变量名，autobucket为变量值。    
```

   那么对应的java类配置的注解为：​        

```java
@Given("开始初始化S3客户端,bucketName:$bucketName")
public void initS3Client(String bucketName) {

}

//代码中对变量的引用比较简单，直接定义同名的变量名即可。这样即可实现 将story文件中的值传递给java类中的变量
```



（3）配置stories文件

![1538019704042](C:\Users\lubyc\AppData\Local\Temp\1538019704042.png)

这一步实现了去加载stroy文件，其实可以看到，此类继承了JUnitStory。（这个的实现多种多样，网上也很多，都可以找找看）

我这里用的两个类。运行入口如图：

![1538019943054](C:\Users\lubyc\AppData\Local\Temp\1538019943054.png)



（4）运行stories文件

有很多运行方式，eclipse，IDEA,maven等等，我使用的是idea+gradle





