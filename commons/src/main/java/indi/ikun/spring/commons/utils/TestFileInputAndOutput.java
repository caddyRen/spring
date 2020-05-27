package indi.ikun.spring.commons.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author renqiankun
 * @Description 读取dat大文件，5G左右,接数据中心数据，大文件处理方式
 * @Date  2018/5/15
 * @Modified By
 */
public class TestFileInputAndOutput {

    public static void main(String[] args){


        Integer i=Integer.valueOf(100);
        Integer j=Integer.valueOf(100);
        System.err.println(i==j);
        System.err.println(i>j);
        System.err.println(i<j);

        int a=10;
        Integer ii=new Integer(a);
        Integer jj=new Integer(a);
        System.err.println(ii==jj);

        ii=a;
        jj=a;
        System.err.println(ii==jj);
        ii=Integer.valueOf(a);
        jj=Integer.valueOf(a);
        System.err.println(ii==jj);
//        TestFileInputAndOutput test=new TestFileInputAndOutput();
//        //test.files();
//        //test.file();
//        long start1=System.currentTimeMillis();
//        //test.testChannelFileReader();
//        //test.testInputStream();
//        //test.testBufferedReader();
//        test.bufferReader("c://SF_PROD_PLAN_I.dat",5*1024*1024,"GBK");
//        long end1=System.currentTimeMillis();
//        System.err.println(end1-start1);

    }
    //抽出方法
    /*
    *读取数据中心提供的.dat文件,大小5GB+,事先已经使用UNIX换行符分好行（\n）
    * @param path 文件路径
    * @param size  处理文件时缓存大小，可以提高速度，字节
    *
    *抛出异常，外部方法try{}catch()
    * */
    public void bufferReader(String path,int size,String charserName) {
        File file=new File(path);//指向文件位置
        FileInputStream fileInputStream=null;
        BufferedInputStream bufferedInputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try{
            fileInputStream=new FileInputStream(file);//开启文件输入流
            bufferedInputStream=new BufferedInputStream(fileInputStream);//开启输入缓冲流
            inputStreamReader=new InputStreamReader(bufferedInputStream,charserName);//设置字符编码，开启字符流
            bufferedReader=new BufferedReader(inputStreamReader,size);//设置缓存大小，默认8192字节，
            String line=null;
            int i=0;
            while((line=bufferedReader.readLine())!=null){
            /*解析每行每个字段，进行匹配插入数据库，数据库io也会很慢*/
                //\u001B代表的是ESC键，
                // 对应的asc码
                // 27(十进制)
                // 0x1b(十六进制)
                //
                //
                //String[] datas=StringUtils.split(line,"\u001B");//多个分割符连续出现，null值被忽略
                String[] datas= StringUtils.splitPreserveAllTokens(line,"\u001B");//全部的分割符都算，\u001B代表的是ESC键，对应的asc码是0x1b
                for(String str:datas){
                    System.err.println(str);
//                    if(str==null||str.equals("")){
//                        System.err.println("111");
//                    }
                    if(i==1000){
                        System.err.println(str);
                    }
                }
                //System.err.println(line);
                i++;
            }
            System.err.println(i);//总行
            bufferedReader.close();
            inputStreamReader.close();
            bufferedInputStream.close();
            fileInputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(fileInputStream!=null)
                    fileInputStream.close();
                if(bufferedInputStream!=null)
                    bufferedInputStream.close();
                if(inputStreamReader!=null)
                    inputStreamReader.close();
                if(bufferedReader!=null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //BufferedReader
    public void testBufferedReader(){
        File file=new File("c://SF_PROD_PLAN_I.dat");
        BufferedInputStream bufferedInputStream=null;
        BufferedReader bufferedReader=null;
        try {
            bufferedInputStream=new BufferedInputStream(new FileInputStream(file));
            bufferedReader=new BufferedReader(new InputStreamReader(bufferedInputStream,"GB2312"),6*1024*1024);//6M缓存
            String line=null;
            int i=0;
//      ok
            while((line=bufferedReader.readLine())!=null){
                System.err.println(line);
                i++;
            }
//        会多一行null
//        do{
//            line=bufferedReader.readLine();
//            System.err.println(line);
//            i++;
//        }while(line!=null);

//        bufferedReader.read()会使下标移动一位，进而导致第一个字符打印不出来
//        while(-1!=bufferedReader.read()){
//            line=bufferedReader.readLine();
//            System.err.println(line);
//            i++;
//        }
            System.err.println(i);
            bufferedReader.close();
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedInputStream!=null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//byte[]
public void testChannelFileReader(){
    File file=new File("c://SF_PROD_PLAN_I.dat");
    FileInputStream fileInputStream=null;
    FileChannel fileChannel=null;
    ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024*6);
    int a;
    try {
        fileInputStream=new FileInputStream(file);
        do{
            fileChannel=fileInputStream.getChannel();
            a=fileChannel.read(byteBuffer);//将文件放到byteBuffer中
            if(a!=-1){
                byte[] bytes=new byte[a];
                byteBuffer.flip();
                byteBuffer.get(bytes);
                //System.err.println(new String(bytes,"GBK"));
                //String[] data= StringUtils.split(new String(bytes,"GB2312"),"\n");
                //System.err.println(data[0]);

                byteBuffer.clear();
            }
        } while(a!=-1);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if(fileInputStream!=null){
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(fileChannel!=null){
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

//Scanner读取文件流,稍慢
public void testInputStream(){
    FileInputStream inputStream = null;
    Scanner sc = null;
    BufferedInputStream bufferedInputStream=null;
    try {
        inputStream = new FileInputStream("c://SF_PROD_PLAN_I.dat");
        sc = new Scanner(inputStream, "GB2312");
        String line=null;
        int i=0;
        //一行一行扫描，
        while (sc.hasNextLine()) {
            i++;
            line=sc.nextLine();
            System.err.println(line);
//           String[] temp= StringUtils.split(sc.nextLine(),'\u001B');
//           for(int i=0;i<temp.length;i++){
//               System.err.println(temp[i]);
//           }
        }
        System.err.println(i);
        // note that Scanner suppresses exceptions
        if (sc.ioException() != null) {
            throw sc.ioException();
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(bufferedInputStream!=null){
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (sc != null) {
            sc.close();
        }
    }
}



}
