# miraibot
 基于[Mirai-core](https://github.com/mamoe/mirai)的Java机器人

 使用的包为mirai-core-1.1.3.jar和mirai-core-qqandroid-1.1.3.jar 仓库地址[mirai-repo](https://github.com/project-mirai/mirai-repo)

 本身是mvn项目，引入本地包，给包随便整个文件夹放着。

![binaryTree](images/20200722231520.png)

修改pom.xml给项目导入包，这里不要使用绝对地址，可能会引起mvn报错。

```xml
	<dependency>
      <groupId>net.mamoe</groupId>
      <artifactId>mirai-core</artifactId>
      <version>1.1.3</version>
      <scope>system</scope>
      <systemPath>${project.basedir}/src/main/java/resources/mirai-core-1.1.3.jar</systemPath>
    </dependency>
	<!-- 在线仓库的都试过了，有问题，等2.0的发版吧-->
    <dependency>
      <groupId>net.mamoe</groupId>
      <artifactId>mirai-core-qqandroid</artifactId>
      <version>1.1.3</version>
      <scope>system</scope>
      <systemPath>${project.basedir}/src/main/java/resources/mirai-core-qqandroid-1.1.3.jar</systemPath>
    </dependency>
```

哦，git不会pull jar文件，所以这个仓库里也就没有这两个包，所以写在这里说一下。