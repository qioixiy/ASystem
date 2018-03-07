```
Eclipse中高效的快捷键
当我知道了这些快捷键的使用方法之后，感觉真的很兴奋，没想到Eclipse中还有这么多令人惊喜的功能，真的可以提高效率。
        内容提示 Alt+/
        用于输入标准库或者关键字时的内容提示
        快速修复 Ctrl+1
        编辑器显示有错误的时候，不用想，先按Ctrl+1进行快速修复
        导包 Ctrl+Shift+o
        该快捷键可以进行快速导包，例如用到了集合类，但之前没有导入相应的包，可以通过该快捷键
        格式化代码块 Ctrl+Shift+f
        向前向后查看 Alt+前后方向键
        该功能在代码调试或者查看源代码的时候比较有用，
        添加注释 Ctrl+Shift+/
        去除注释 Ctrl+Shift+\
        查看方法说明 F2
        上下复制行 Ctrl+Alt+上下键
        可用于光标所在行的向上向下进行复制
        上下移动行 Alt+上下键
        可用于光标所在行的上下移动
        查看类的继承关系 Ctrl+t
        该方法用于查看指定类的继承树，特别是在学习框架查看源代码的时候会大量使用
        查看源代码 Ctrl+Shift+t
        在弹出框Open Type中输入要查看的源代码
        查看快捷键设置 Ctrl+Shift+l
        查看Eclipse中所有的快捷键
设置在Eclipse下查看源代码
按Ctrl+鼠标左键点击要查看到标准类库，但是如果没有正确配置，会出现错误
解决方法的步骤如下：
        点击窗口（window） --> “Java” --> 已安装JRE（“Installed JRES”）
        此时右边的列表框中显示了系统中的JRE环境，你可以自己制定你要的JRE，然后点击编辑（Edit），出现编辑窗口
        选中rt.jar文件一项：“..\jre\lib\rt.jar”并展开它
        展开后可以看到“源代码连接：（无）”或者“Source Attachment:(none)”，双击该项，选择你的JDK目录下的“src.zip”文件
        确定并完成配置
调试Debug
        跳入Step in -> F5
        跳过Step over -> F6
        跳出Step out -> F7
        拖放至帧Drop to frame
        到当前调试的方法的第一行去，这个方法比较实用，用于调试时当前步骤跳过了再回去
        跳至下一断点Resume ->F8
在断点（Breakpoints）视图中查看断点或者清除所有断点
```

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
- mvn archetype:generate -DgroupId=com.xxx.sys -DpackageName=com.xxx.asystem -DartifactId=asystem -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
- mvn clean install
- java -cp target/asystem-1.0-SNAPSHOT.jar com.xxx.sys.App
```
# webapp
```
- mvn archetype:generate -DgroupId=com.xxx.webapp -DartifactId=asystem -DpackageName=com.xxx.webapp.asystem -DarchetypeArtifactId=maven-archetype-webapp -DinteractivMode=false
- mvn package
- mvn tomcat:run
```

# deploy
```
cp target/asystem.war /var/lib/tomcat8/webapps/asystem.war
```
