#代理
  - ##思想模式
    - Proxy是一种设计模式，提供对目标对象另外的访问方式;即通过代理对象访问目标对象
    - 可以在目标对象实现的基础上，增强额外的功能操作，即扩展目标对象的功能
    - 编程思想：不要随意修改别人已经写好的代码或者方法，如果需要修改，可以通过代理的方式来扩展该方法
    - 代理模式的关键点：代理对象与目标对象，代理对象是对目标对象的扩展，并会调用目标对象

- #静态代理（类似于装饰者模式）
  - 例子 
    - 公共接口
    ```java
    public interface IUserServ {
        void save();
    }
    ```
    - 目标业务类
    ```java
    public class UserServiceImpl implements IUserServ {
  
      @Override
      public void save() {
          System.err.println("userServiceImpl");
      }
    }
    ```
    - 代理类
    ```java
    public class UserServiceProxy implements IUserServ {
    
        private IUserServ target;
    
        public UserServiceProxy(IUserServ iUserServ){
            this.target=iUserServ;
        }
    
        @Override
        public void save() {
            System.err.println("start...");
            target.save();
            System.err.println("end...");
    
        }
    }
    ```
    - 测试
    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class DemoApplicationTests {
      @Test
      public void testStaticProxy(){
          UserServiceImpl userService=new UserServiceImpl();
          UserServiceProxy userServiceProxy=new UserServiceProxy(userService);
          userServiceProxy.save();
      }
    }
    ```
  - 不修改目标对象，对目标的功能进行扩展
  - 代理对象需要与目标对象实现一样的接口，所以会有很多代理类，类太多。一旦接口增加，目标对象和代理对象都要维护
- #动态代理
  - 字节码对象级别的代理对象
  - jdk的API中存在一个Proxy，其中存在一个生成动态代理的方法newProxyInstance
    ```
      static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
      返回值Object就是代理对象
      参数
      loader:代表与目标对象相同的类加载器--目标对象.getClass().getClassLoader()
      interfaces:代表与目标对象实现的所有的接口字节码对象数组
      h：具体的代理的操作，InvocationHandler接口
    ```
    
    
  - 不需要手动编写一个代理对象，不需要编写与目标对象相同的方法，这个过程在运行时的内存中动态生成代理对象
  - ##JDK动态代理
    - JDK的Proxy方式实现的动态代理，目标对象必须有接口，没有接口不能实现jdk动态代理
    - 
    - 例子
      - 接口
      ```java
      public interface IUserServ {
              void save();
          }
      ```
      - 目标类
      ```java
      public class UserServiceImpl implements IUserServ {
        
            @Override
            public void save() {
                System.err.println("userServiceImpl");
            }
          }
      ```
      - 测试
      ```java
      @RunWith(SpringRunner.class)
      @SpringBootTest
      public class DemoApplicationTests {
      
          UserServiceImpl userService=new UserServiceImpl();
          @Test
          public void testDynamicProxy(){
              IUserServ proxy=(IUserServ) Proxy.newProxyInstance(
                      userService.getClass().getClassLoader(),
                      userService.getClass().getInterfaces(),
                      new InvocationHandler() {
                          @Override
                          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                              Object invoke=method.invoke(userService,args);
                              return null;
                          }
                      }
              );
              proxy.save();
      
          }
      }
      ```
  - ##cglib动态代理

