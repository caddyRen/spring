#循环依赖
##spring解决循环依赖的三种方式
###构造器参数循环依赖
- 通过构造器注入构成的循环依赖 
  - StudentA error code
    ```java
    @Component
    public class StudentA{
        private StudentB studentB;
        public void setStudentB(StudentB studentB){
            this.studentB=studentB;
        }
        public StudentA(){
            
        }
        @Autowired
        public StudentA(StudentB studentB){
            this.studentB=studentB;
        }
    }
    ```
  - StudentA @Lazy 
    ```java
    @Component
    public class StudentA{
        private StudentB studentB;
        public void setStudentB(StudentB studentB){
            this.studentB=studentB;
        }
        public StudentA(){
    
        }
        @Autowired
        public StudentA(@Lazy StudentB studentB){
            this.studentB=studentB;
        }
    
        public void print(){
            System.err.println("A");
        }
    }
    ```
  - StudentB error code
    ```java
    @Component
    public class StudentB{
        private StudentC studentC;
        public void setStudentC(StudentC studentC){
            this.studentC=studentC;
        }
        public StudentB(){
            
        }
        @Autowired
        public StudentB(StudentC studentC){
            this.studentC=studentC;
        }
    }
    ```
  - StudentB @Lazy
    ```java
    @Component
    public class StudentB{
        private StudentC studentC;
        public void setStudentC(StudentC studentC){
            this.studentC=studentC;
        }
        public StudentB(){
    
        }
        @Autowired
        public StudentB(@Lazy StudentC studentC){
            this.studentC=studentC;
        }
    
        public void print(){
            System.err.println("B");
        }
    }
    ```
  - StudentC error code
    ```java
    @Component
    public class StudentC{
        private StudentA studentA;
        public void setStudentA(StudentA studentA){
            this.studentA=studentA;
        }
        public StudentC(){
            
        }
        @Autowired
        public StudentC(StudentA studentA){
            this.studentA=studentA;
        }
    }
    ```
  - StudentC @Lazy
    ```java
    @Component
    public class StudentC{
        private StudentA studentA;
        public void setStudentA(StudentA studentA){
            this.studentA=studentA;
        }
        public StudentC(){
    
        }
        @Autowired
        public StudentC(@Lazy StudentA studentA){
            this.studentA=studentA;
        }
    
        public void print(){
            System.err.println("C");
        }
    }
    ```