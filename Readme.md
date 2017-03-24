# cmd
- mvn archetype:generate -DgroupId=com.xxx.sys -DpackageName=com.xxx.asystem -DartifactId=asystem -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  -DgroupId #组织标识（包名）
  -DartifactId #项目名称
  -DarchetypeArtifactId #指定ArchetypeId，maven-archetype-quickstart，创建一个Java Project；maven-archetype-webapp，创建一个Web Project
  -DinteractiveMode #是否使用交互模式
- mvn compile #编译源代码
- mvn test-compile #编译测试代码
- mvn clean #清空
- mvn test #运行测试
- mvn site-deploy #生产站点目录并打包
- mvn install #安装当前工程的输出文件到本地仓库
- mvn package #打包
- mvn jar:jar #打成jar包
- mvn eclipse:eclipse #生成eclipse项目
- mvn help:help #查看帮助信息
- mvn archetype:generate -DarchetypeCatalog=intrenal #查看maven有哪些项目类型分类

# instance
```
mvn clean install
java -cp target/asystem-1.0-SNAPSHOT.jar com.xxx.sys.App
```
# webapp
```
- mvn archetype:generate -DgroupId=com.xxx.webapp -DartifactId=asystem -DpackageName=com.xxx.webapp.asystem -DarchetypeArtifactId=maven-archetype-webapp -DinteractivMode=false
- mvn package
```
