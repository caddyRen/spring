package com.mumu.springcloud.ymldata;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @ClassName YmldataApplicationTests
 * @Description TODO
 * @Author caddyR
 * @Date 2019-09-17 09:12
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class YmldataApplicationTests {

    @Test
    public void URLTest() {
        URL url = null;
        InputStream is = null;
        InputStream is2 = null;
        FileOutputStream fos = null;
        try {
            url = new URL("http://172.16.17.17/nexus/repository/public/c3p0/c3p0/0.9.1/c3p0-0.9.1.pom");
            is = url.openStream();
            byte[] b=new byte[20];
            int len;
            while((len=is.read(b))!=-1){
                System.err.print(new String(b,0,len));
            }
            URLConnection urlconn = url.openConnection();
            is2 = urlconn.getInputStream();
            fos = new FileOutputStream(new File("test.pom"));
            byte[] b1 = new byte[20];
            int len1;
            while ((len1 = is2.read(b1)) != -1) {
                fos.write(b1, 0, len1);
            }
            System.err.println(url.getProtocol());
//            System.err.println(url.getHost());
//            System.err.println(url.getPort());
//            System.err.println(url.getFile());
//            System.err.println(url.getRef());
//            System.err.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is2 != null) {
                try {
                    is2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void clientTCP() {
        Socket s = null;
        Scanner scanner = null;
        InputStream is = null;
        try {
            s = new Socket(InetAddress.getByName("127.0.0.1"), 3400);
            OutputStream os = s.getOutputStream();
            System.err.println("input");
            scanner = new Scanner(System.in);
            String str = scanner.next();
            os.write(str.getBytes());
            s.shutdownOutput();
            is = s.getInputStream();
            byte[] b = new byte[10];
            int len;
            String str2 = new String();
            while ((len =is.read(b)) != -1) {
                String str1 = new String(b, 0, len);
                str2 += str1;
            }
            System.err.println(str2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void serverTCP() {
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            ss = new ServerSocket(3400);
            s = ss.accept();
            is = s.getInputStream();
            byte[] b = new byte[10];
            int len;
            String str = new String();
            while ((len = is.read(b)) != -1) {
                String str1 = new String(b, 0, len);
                str += str1;
            }
            System.err.println(str.toUpperCase());
            os = s.getOutputStream();
            os.write(str.toUpperCase().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void send() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            byte[] b = "发送数据".getBytes();
            DatagramPacket pack = new DatagramPacket(b, 0, b.length, InetAddress.getByName("127.0.0.1"), 3400);
            ds.send(pack);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }

    @Test
    public void receive() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(3400);
            byte[] b = new byte[1024];
            DatagramPacket pack = new DatagramPacket(b, 0, b.length);
            ds.receive(pack);
            String str = new String(pack.getData(), 0, pack.getLength());
            System.err.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }

    }

    @Test
    public void serverFile() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(3400);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            fileOutputStream = new FileOutputStream(new File("C:\\Users\\renqiankun\\Pictures\\Camera Roll\\2.jpg"));
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }
            System.err.println("收到来自" + socket.getInetAddress().getHostAddress() + "的文件");
            outputStream = socket.getOutputStream();
            outputStream.write("你发送的图片我已接收成功".getBytes());
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void clientFile() {
        Socket socket = null;
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 3400);
            outputStream = socket.getOutputStream();
            fileInputStream = new FileInputStream(new File("C:\\Users\\renqiankun\\Pictures\\Camera Roll\\5bc4578d77880.jpg"));
            byte[] b = new byte[1024];
            int len;
            while ((len = fileInputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            socket.shutdownOutput();
            inputStream = socket.getInputStream();
            byte[] b1 = new byte[1024];
            int len1;
            while ((len1 = inputStream.read(b1)) != -1) {
                String str = new String(b1, 0, len1);
                System.err.println(str);
            }
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
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(3400);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[20];
            int len;
            //read()阻塞式，
            while ((len = inputStream.read(bytes)) != -1) {
                String str = new String(bytes, 0, len, "UTF-8");
                System.err.println(str);
            }
            outputStream = socket.getOutputStream();
            outputStream.write("地瓜收到".getBytes("UTF-8"));
            socket.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 3400);
            outputStream = socket.getOutputStream();
            outputStream.write("hello server".getBytes("UTF-8"));
            socket.shutdownOutput();
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[20];
            int len;
            //read()阻塞式
            while ((len = inputStream.read(bytes)) != -1) {
                String str = new String(bytes, 0, len, "UTF-8");
                System.err.println(str);
            }
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
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return a
     * @Author renqiankn
     * @Method a
     * @Description //TODO
     * @Date 2019-11-11 1104
     * @Param
     * @Version 1.0
     * @UpdateBy InetAddress 用来代表IP地址，一个InetAddress的对象就代表着一个IP地址
     * 端口号，标识正在计算机上运行的进程，16位的整数0~65535，0~1023预先定义的服务通信占用，
     * 端口+ip得到一个网络套接字
     **/
    @Test
    public void TestInetAddress() {
        try {
            //创建对象通过ip或域名
            InetAddress inetAddress = InetAddress.getByName("172.16.17.174");
            log.info(inetAddress.toString());
            log.info(inetAddress.getHostAddress());
            log.info(inetAddress.getHostName());
            //获取本机ip
            InetAddress inetAddress1 = InetAddress.getLocalHost();
            log.info(inetAddress1.toString());
            //获取ip
            log.info(inetAddress1.getHostAddress());
            //获取IP对应的域名
            log.info(inetAddress1.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
