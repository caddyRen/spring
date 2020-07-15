#maven 知识点
- Java不仅是一门编程语言，还是一个平台通过JRuby和Jython我们可以在Java平台上编写和运行Ruby和Python程序。
- Maven不仅是构建工具，还是一个依赖管理工具和项目信息管理工具。提供了中央仓库，能帮助自动下载构件。
- Java应用都会借用一些第三方的开源类库，这些类库都可以通过依赖的方式引入到项目中来。随着依赖的增多，版本不一致，版本冲突，依赖臃肿等问题。maven提供了一个优秀的解决方案，它通过一个坐标系统准确地定位每一个构件（artifact），也就通过一组坐标maven能够找到任何一个java类库。Maven给这个类库世界引入了经纬，让他们变得有秩序，于是我们可以借助它来有序地管理依赖，轻松地解决那些繁杂的依赖问题
- Maven还能帮助我们管理原本分散在项目中各个角落的项目信息，包括项目描述，开发者列表，版本控制系统地址，许可证缺陷管理系统地址等，能帮助我们节省大量寻找这些信息的时间。通过Maven自动生成的站点，以及一些已有的插件，我们还能够轻松获得项目文档，测试报告，静态分析报告，源码版本日志报告等非常有价值的项目信息。
- Maven提供了免费的中央仓库，通过Maven的衍生工具（Nexus）我们还能进行快速地搜索，只要定位了坐标，Maven就能够帮我们自动下载，省去了手工劳动。
- Maven对于项目目录结构，测试用例命令方式等内容都有既定的规则，遵循这些规则，节约学习成本，约定优于配置（Convention Over Configuration）
##基础
- **安装目录**
    - 配置环境变量：\
        M2_HOME= 安装目录(bin的上级目录)\
		Path= %M2_HOME%\bin
    - bin：\
    包含了mvn运行的脚本，这些脚本用来配置Java命令，准备好classpath和相关的Java系统属性，然后执行java命令。其中mvn是基于unix平台的shell脚本，mvn.bat是基于Windows平台的bat脚本。在命令行输入任何一条mvn时实际上就是在调用这些脚本。该目录还包含了mvnDebug和mvnDebug.bat两个文件，mvn和mvnDebug基本一样，mvnDebug多了一条Maven_DEBUG_OPTS配置，其作用就是在运行Maven时开启debug，以便调试maven本身。此外，该目录还包含m2.conf文件，这是classworlds的配置文件。
    - boot：\
    包含一个文件，plexus-classworlds-2.2.3.jar。plexus-classworlds是一个类加载器框架，相对于默认的java类加载器，他提供了更丰富的语法以方便配置，Maven使用该框架加载自己的类库。[更多信息](http://classworlds.codehaus.org/)。一般用户不必关系该文件。
    - Conf：\
    该目录包含一个非常重要的文件settings.xml直接修改改文件，就能在机器上全局地定制maven的行为，建议复制该文件到 用户目录/.m2/目录下。
        - 用户目录/.m2/settings.xml\
            - Mvn help:system：\
            该命令打印出所有java系统属性和环境变量。
            - 默认情况下.m2文件夹放置了Maven本地仓库.m2/repository。所有的Maven构件都被存储到该仓库中，以方便重用。Maven根据一套规则来确定任何一个构件在仓库中的位置，由于maven仓库是通过简单文件系统透明地展示给Maven用户的，所以可以绕过Maven直接查看或修改仓库文件，遇到问题这种方式十分有用。
        	- 默认情况下不使用用户目录/.m2/repository作为本地仓库，而是复制M2_HOME/conf/settings.xml文件到用户目录/.m2/settings.xml 修改配置
        	 ```
        	 <localRepository>E:/maven/repository</localRepository>
             ```
    - Lib：\
    该目录包含了所有maven运行时需要的java类库，maven本身是分模块开发的，因此用户能看到诸如maven-core-3.0.jar、maven-model-3.0.jar之类的文件。此外这里还包含一些Maven用到的第三方依赖。可以说lib目录就是真正的Maven，用户可以在这个目录中找到Maven内置的超级pom。
    - LICENSE.txt：\
    记录Maven使用的软件许可证Apache License Version 2.0
    - **NOTICE.txt**： \
    记录了Maven包含的第三方软件
    - README.txt： \
    包含了Maven的简要介绍，包括安装需求以及如何安装简要指令  
- **生命周期\
    Maven有三套相互独立的生命周期，clean， default ，site\
    每个生命周期包含一些阶段（phase），这些阶段是有顺序的，并且后面的阶段依赖于前面的阶段\
    用户与maven最直接的交互方式就是调用这些生命周期\
    用户调用某个生命周期的某个阶段，不会影响其他的生命周期**
    - clean：生命周期目的是清理项目\
        包含pre-clean，clean，post-clean三个阶段，用户调用pre-clean的时候只有pre-clean阶段得以执行，调用clean，pre-clean和clean阶段会得以顺序执行，调用post-clean，pre-clean，clean，post-clean会顺序执行
        - Pre-clean：执行一些清理前需要完成的工作
        - Clean：清理上一次构建生成的文件
        - Post-clean：执行一些清理后需要完成的工作
    - default：生命周期的目的是构建项目\
        定义真正构建时所需要执行的所有步骤，它是所有生命周期中最核心的部分
        - Validate：
        - Initialize：
        - Generate-sources：
        - Process-sources：处理项目主资源文件，一般来说是对src/main/resources目录的内容进行变量替换等工作后，复制到项目输出的主classpath目录中
        - Generate-resources：
        - Process-resources：
        - Compile：编译项目的主源码，一般来说，是便宜src/main/java目录下的Java文件至项目输出的主classpath目录中
        - Process-classes：
        - Generate-test-sources：
        - Process-test-sources：处理项目测试资源文件，一般来说是对src/test/resources目录的内容进行变量替换等工作后，复制到项目输出的测试classpath目录中
        - Generate-test-resources：
        - Process-test-resources：
        - Test-compile：编译项目的测试代码，一般来说是编译src/test/java目录下的java文件至项目输出的测试classpath目录中，
        - Process-test-classes：
        - Test：
        - Prepare-package：
        - Package：接受编译好的代码，打包成可发布的格式，jar，war等
        - Pre-integration-test：
        - Integration-test：
        - Post-integration-test：
        - Verify：
        - Install：将安装包到maven本地仓库，供本地其他项目使用
        - Deploy：将最终的包复制到远程仓库，供其它开发人员和maven项目使用
    - site：生命周期的目的是建立项目站点\
        建立和发布项目站点，maven能够给予pom所包含的信息，自动生成一个友好的站点，方便团队交流和发布项目信息
        - Pre-site：执行一些在生成项目站点之前需要完成的工作
        - Site：生成项目站点文档
        - Post-site：执行一些在生成项目站点之后需要完成的工作
        - Site-deploy：将生成项目站点发布到服务器上
- **命令行与生命周期**\
    命令行执行maven任务的最主要方式就是调用maven的生命周期阶段，需要注意的是，各个生命周期是相互独立的，而一个生命周期的阶段是有前后依赖关系\
    maven中主要的生命周期阶段并不多，而常用的maven命令实际都是基于这些阶段简单组合而成的
    - mvn clean\
        该命令调用clean生命周期的clean阶段，实际执行的阶段为clean生命周期的pre-claen和clean阶段。
    - mvn test\
        default生命周期的test阶段，执行阶段为defaul生命周期的validate，initialize。。。直到test的所有阶段，所以执行测试的时候，项目的代码能够自动得以编译。
    - mvn clean install\
        调用clean的clean阶段，和default的install阶段，在执行真正的项目构建之前清理项目是一个很好的实践。
    - mvn clean deploy site-deploy\
        该命令调用clean生命周期的clean阶段，default生命周期的deploy阶段以及site生命周期的site-deploy阶段
- **20171017命令行 mvn clean compile报错**
    - 错误描述\
    使用JDK1.8安装后，会在Win10 系统path中新增 C:\ProgramData\Oracle\Java\javapath
    这样命令行mvn clean compile出现“No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?”
    - 解决方法\
    把“C:\ProgramData\Oracle\Java\javapath”从（系统）path中删除
    - 命令解析\
    Clean 告诉maven清理输出目录target/，compile 告诉maven编译项目主代码，从输出中看到maven首先执行了clean:clean任务，删除target/目录默认情况下maven构建的所有输出都在targe/目录中；接着执行resources:resources任务（未定义项目资源，暂且略过）；最后执行compile:compile任务，将项目主代码编译至target/classes
- **20171018命令行 mvn clean test**
   - 命令解析
     - mvn clean test\ 
     maven实际执行的还有clean:clean,resources:resources,compiler:compiler,resources:testResources,compiler:test:Compiler\
     在Maven执行测试test之前，会先自动执行项目主资源处理，主代码编译，测试资源处理，测试代码编译等工作，这是maven生命周期的一个特性。
     测试代码通过编译之后在target/test-classes下生成二进制文件，surefile:test任务运行测试，surefire是maven中 负责执行测试的插件，这里它运行测试用例HelloWorldTest，并且输出测试报告，显示一共运行了多少测试，失败多少，出错多少，跳过多少
     - mvn clean package\
     项目编译，测试之后一个重要步骤是打包，HelloWorld的POM中没有指定打包类型，使用默认打包类型jar\
     类似地maven会在打包之前执行编译，测试等操作。jar:jar任务负责打包，实际上是jar插件的jar目标将项目主代码打包成一个名为hello-world-1.0-SNAPSHOT.jar的文件，该文件也位于target/输出目录中，他是根据artifact-version.jar规则进行命名的，有需要还可以使用finalName来定义改文件的名称。\
     可以复制jar文件到其他项目的classpath中从而使用HelloWorld类；
     - mvn clean install\
     其他maven项目直接引用这个jar需要一个安装步骤\
     在打包之后又执行了安装任务install:install，从输出可以看到该项目输出的jar安装到了Maven本地仓库中，只有将HelloWorld的构件安装到本地仓库后，其他maven项目才能通过GAV坐标使用该jar
     - 运行jar\
     默认打包生成的jar不能够直接运行的，因为带有main方法的类信息不会添加到manifest中（打开jar文件中的META-INF/MANIFEST.MF文件，将无法看到Main-Class行）。\
     借助maven-shade-plugin生成可执行的jar文件，在pom中配置该插件
- **总结**
    - mvn clean compile\
     	编译
    - mvn clean test\
     	执行test之前会先执行compile
    - mvn clean package\
     	打包 执行package之前会先执行test
    - mvn clean install\
    	安装 执行install之前会执行package
    - 任何maven项目中都可以执行这些命令       
##插件
  - 插件目标\
    Maven的核心仅仅定义了抽象的生命周期，具体的任务是交由插件完成的，插件以独立的构件形式存在，因此，Maven核心的分发包只有不到3MB的大小，Maven会在需要的时候下载并使用插件。对于插件本身，为了能够复用代码，它往往能够完成多个任务。
    例如maven-dependency-plugin：
    	能够基于项目的依赖分析项目依赖，帮助找出潜在的无用依赖；它能够列出项目的依赖树，帮助分析来源，列出项目所有已解析的依赖。为每个这样的功能编写一个独立的插件显然是不可取的，因此这些任务背后有很多可以复用的代码，因此，这些功能聚集在一个插件里，每个功能就是一个插件目标。Maven-dependency-plugin有十多个目标，每个目标对应一个功能，
    Dependency：analyze分析依赖
    Dependency：tree列出依赖树
    Dependency：list列出所有已解析的依赖
    这是一种通用写法，冒号前是插件前缀，冒号后面是插件目标
  - 插件绑定\
    Maven的生命周期与插件相互绑定，用以完成实际的构建任务，具体而言是生命周期的阶段与插件的目标相互绑定，以完成某个具体的构建任务。
    例如项目编译对应default生命周期的compile阶段，maven-compile-plugin这一插件的compile目标能够完成该任务，因此他们绑定就能实现项目编译的目的
    - 内置绑定\
      为了能让用户不用任何配置就能构建maven项目，maven在核心为一些主要的生命周期阶段绑定了很多插件的目标，当用户通过命令行调用生命周期阶段的时候没对应的插件目标就会执行相应的任务
    - 自定义绑定\
      用户可以将某个插件目标绑定到生命周期的某个阶段上，这种自定义绑定方式能让Maven项目在构建过程中执行更多更丰富的特色的任务
      ```
        <build>
         <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-source-plugin</artifactId>
                <version>2.1.1</version>
                <executions>
                    <execution>
                        <id>attach-sources</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>jar-no-fork</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
          </plugins>
        </build>
       ```
      Mvn help：describe-Dplugin org.apache.maven.plugins:maven-source-plugin:2.1.1-Ddetails\
      该命令输出对应插件的详细信息\
      当插件目标被绑定到不同的生命周期阶段的时候，其执行顺序由生命周期阶段的先后顺序决定，如果多个目标被绑定到同一阶段，他们的执行顺序由插件声明的先后顺序决定\
      在POM文件的build元素下的plugins子元素中声明插件的使用，该例中用到的是maven-source-plugin，其groupid为org.apache.maven.plugins,是maven官方插件的groupId，紧接着artifactId为maven-source-plugin，version为2.1.1，对于自定义绑定的插件，用户总是应该声明一个非快照版本，这样可以避免由于插件版本变化造成构建不稳定。插件执行配置，executions下每个execution子元素可以用来配置执行一个任务，该例中配置了一个id为attach-sources的任务，通过phrase配置，将其绑定到verify生命周期阶段上，在通过goals配置指定要执行的插件目标，运行mvn verify，maven-source-plugin：jar-no-fork会得以执行，它会创建一个以-sources.jar结尾的源码文件包。有时候即使不通过phase元素配置生命周期阶段，插件目标也能绑定到生命周期中去，如删除phase这一行，再执行mvn verify仍然可以看到maven-source-plugin：jar-no-fork得以执行，这是因为有很多插件的目标在编写时已经定义了默认绑定阶段，可以使用maven-help-plugin查看插件详细信息，了解插件目标的默认绑定阶段
      - 插件配置\
      配置插件目标的参数，进一步调整插件目标所执行的任务以满足项目的需求，几乎所有maven插件的目标都有一些可配置的参数，用户可以通过命令行和POM配置等方式来配置这些参数
        - 命令行插件配置
          - mvn install –Dmaven.test.skip=true\
          Maven-surefire-plugin提供了一个maven.test.skip参数，当其值为true时会跳过执行测试。于是在运行命令的时候加上如下-D参数就能跳过测试\
          -D是java自带的，其功能是通过命令行设置一个java系统属性，maven简单地重用了该参数，在准备插件的时候检查系统属性，便实现了插件参数的配置\
          并不是所有插件参数都适合命令行配置，有些参数的值从项目创建到项目发布都不会改变，或者说很少改变，对于这种情况，在POM文件中一次性配置就显然比重复在命令行输入要方便
        - POM插件全局配置
        ```xml
        <build>
        	<plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>2.1</version>
                    <configuration>
                        <source>1.5</source>
                        <target>1.5</target>
                    </congiguration>
                </plugin>
        	</plugins>
        </build>
        ```
        配置maven-compiler-plugin告诉它编译JAVA1.5版本的源文件，生成与JVM1.5兼容的字节码文件\
        这样不管绑定到compile阶段的maven-compiler-plugin：testCompiler任务，就能够使用该配置，基于Java1.5版本进行编译
##传递性依赖和以来范围
依赖范围不仅可以控制依赖与三种classpath的关系，还对传递性依赖产生影响。\
A依赖于B，B依赖于C，我们说A对于B是第一直接依赖，B对于C是第二直接依赖，A对于C是传递性依赖。第一直接依赖的范围和第二直接依赖的范围决定了传递性依赖的范围

|第一直接依赖↓第二直接依赖→|compile|test|provided|runtime|
|---|---|---|---|---|
|compile|compile|***|***|runtime|
|test|test|***|***|test|
|provided|provided|***|provided|provided|
|runtime|runtime|***|***|runtime|
- 依赖调解\
引入传递性依赖机制，大大简化和方便依赖声明，大部分情况只需要关心项目的直接依赖是什么，而不用考虑这些直接依赖会引入什么传递性依赖。但是当传递性依赖造成问题（jar包版本冲突）的时候就需要清楚地知道传递性依赖是从那条依赖路径引入的。
如果有两个版本，maven依赖调解Dependency Mediation的第一原则：路径最近者优先。如果路径长度一样，maven2.0.9开始maven定义了依赖调解第二原则：第一声明者优先。在POM中依赖声明的顺序决定了谁会被解析使用，顺序最靠前的那个依赖优胜。但是还是要尽量避免版本冲突，因为高版本有的，低版本不一定有，造成不必要的错误
- 可选依赖\
A依赖B，B有两个互斥特性，分别依赖于X，Y，用户不可能同时使用两个特性，
    - B的依赖声明\
    使用optional元素表示mysql和postgresql这两个依赖为可选依赖，只会对B产生影响，当其他项目依赖B时这两个依赖不会被传递，
    ```
    <project>
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.juvenxu.mvnbook</groupId>
        <artifactId>project-b</artifactId>
        <version>1.0.0</version>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.10</version>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>postgresql</groupId>
                <artifactId>postgresql</artifactId>
                <version>8.4-701.jdbc3</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </project>
  ```
    - A的依赖声明\
    当项目A依赖B时，如果A使用mysql数据库，那么A需要显示地声明msql依赖
    ```
    <project>
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.juvenxu.mvnbook</groupId>
        <artifactId>project-a</artifactId>
        <version>1.0.0</version>
        <dependencies>
            <dependency>
                <groupId>com.juvenxu.mvnbook</groupId>
                <artifactId>project-b</artifactId>
                <version>1.0.0</version>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.10</version>
            </dependency>
        </dependencies>
    </project>
  ```
    在理想情况下，不应该使用可选依赖，在面向对象设计中，有单一职责性原则，意指一个类应该只有一项职责，而不是糅合太多的功能，这个原则在规划Maven项目的时候也同样适用，上述例子更好的处理方式是为mysql和postgesql分别创建一个maven项目，基于同样的groupId分配不同的artifactId\
    com.juvenxu.mvnbook:project-b-myslq\
    com.juvenxu.mvnbook:project-b-postgresql\
    在各自的pom中声明对应的JDBC驱动依赖，不适用可选依赖，用户则根据需要选择使用mysql或者postgresql。利用传递性依赖，就不用再次明JDBC驱动依赖
    - 排除依赖\
    传递性依赖会给项目隐式地引入很多依赖，这极大地简化了项目依赖的管理，但是有时候需要排除一些不需要的依赖\
    项目A依赖B，不想引入传递性依赖C，而是自己显示地声明C，1.1.0版本的依赖，使用<exclusions><exclusion>元素声明排除依赖，声明exclusion时只需要<groupId><artifactId>不需要version元素，因为只需要groupId和artifactId就能唯一定位依赖图中的某个依赖。Maven解析后的依赖中，不可能出现groupId和artifactId相同version不同的两个依赖
      ```
      <project>
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.juvenxu.mvnbook</groupId>
        <artifactId>project-a</artifactId>
        <version>1.0.0</version>
        <dependencies>
            <dependency>
                <groupId>com.juvenxu.mvnbook</groupId>
                <artifactId>project-b</artifactId>
                <version>1.0.0</version>
            <exclusions>
                <exclusion>
                <groupId> com.juvenxu.mvnbook </groupId>
                        <artifactId>project-c</artifactId>
                </exclusion>
            </exclusions>
            </dependency>
            <dependency>
                <groupId> com.juvenxu.mvnbook </groupId>
                <artifactId>project-c</artifactId>
                <version>1.1. 0</version>
            </dependency>
        </dependencies>
      </project>
      ```
    - 归类依赖\
    在邮箱验证模块有很多关于springframework的依赖
    ```
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>2.5.6</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>2.5.6</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>2.5.6</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>2.5.6</version>
    </dependency>
     ```
    可以优化为如下，更简洁\
    使用Maven属性，properties元素定义Maven属性，定义了springframework.version子元素其值为2.5.6，Maven运行时会将POM中所有的${springframework.version}替换为2.5.6美元符号加大括号环绕的方式引用Maven属性。
    ```
    <properties>
		<springframework.version>2.5.6</springframework.version>
	</properties>
	 <dependencies>
	 	<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${springframework.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>${springframework.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${springframework.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>${springframework.version}</version>
		</dependency>
		<dependency>
    ```
    - 优化依赖
    Maven会自动解析所有项目的直接依赖和传递性依赖，并且根据规则正确判断每一个依赖的范围，对于一些依赖冲突，也能进行调节，以确保任何一个构件只有唯一的版本在依赖中存在，在上面这些工作之后，最后的到的依赖称为已解析依赖
    - 查看依赖``` mvn dependency:list```
    - 查看依赖树```mvn dependency:tree```
    - 分析当前项目依赖```mvn dependency:analyze```
    
##多模块
- DepencyManagement
  - 在顶层的POM文件中，通过DepencyManagement元素来管理jar包的版本，让子项目中引用一个依赖而不用显示的列出版本号。Maven会沿着父子层次向上走，直到找到一个拥有dependencyManagement元素的项目，然后它就会使用在这个dependencyManagement元素中指定的版本号
  ![image](img/maven-parent-child.jpg)
  - 可以统一管理项目的版本号，确保应用的各个项目的依赖和版本一致，保证测试和发布是相同的成果，因此在顶层pom中定义共同的依赖关系，同时可以避免在每个使用的子项目中都声明一个版本号，这样想升级或者切换版本，只需在父类容器更新。如果子项目需要另外一个版本号时，只需要在自己的pom的dependencies中声明一个版本号，子项目就使用自己声明的版本号，不继承父类版本号。
- Dependencies
  - 相对于depencyManagement，所有声明在dependencies里的依赖都会自动引入，并默认被所有子项目继承
- 区别
  - dependencies即使在子项目中不写该依赖项，那么子项目仍然会从父项目中继承该依赖项（全部继承）
  - dependencyManagement里只是声明依赖，并不实际引入，因此子项目需要显示的声明需要用的依赖。如果不在子项目中声明依赖，是不会从父项目中继承下来的；只有在子项目中写了该依赖项，并且没有指定具体版本，才会从父项目中继承该项，并且version和scope都读取自父pom;另外如果子项目中指定了版本号，那么会使用子项目中指定的jar版本