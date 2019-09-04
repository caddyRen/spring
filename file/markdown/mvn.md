#maven 知识点

##多模块
- DepencyManagement
  - 在顶层的POM文件中，通过DepencyManagement元素来管理jar包的版本，让子项目中引用一个依赖而不用显示的列出版本号。Maven会沿着父子层次向上走，直到找到一个拥有dependencyManagement元素的项目，然后它就会使用在这个dependencyManagement元素中指定的版本号
  ![image](http://img.blog.csdn.net/20150721204949922?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
  - 可以统一管理项目的版本号，确保应用的各个项目的依赖和版本一致，保证测试和发布是相同的成果，因此在顶层pom中定义共同的依赖关系，同时可以避免在每个使用的子项目中都声明一个版本号，这样想升级或者切换版本，只需在父类容器更新。如果子项目需要另外一个版本号时，只需要在自己的pom的dependencies中声明一个版本号，子项目就使用自己声明的版本号，不继承父类版本号。
- Dependencies
  - 相对于depencyManagement，所有声明在dependencies里的依赖都会自动引入，并默认被所有子项目继承
- 区别
  - dependencies即使在子项目中不写该依赖项，那么子项目仍然会从父项目中继承该依赖项（全部继承）
  - dependencyManagement里只是声明依赖，并不实际引入，因此子项目需要显示的声明需要用的依赖。如果不在子项目中声明依赖，是不会从父项目中继承下来的；只有在子项目中写了该依赖项，并且没有指定具体版本，才会从父项目中继承该项，并且version和scope都读取自父pom;另外如果子项目中指定了版本号，那么会使用子项目中指定的jar版本