package com.kenfo.catalina;

import com.kenfo.http.GPRequest;
import com.kenfo.http.GPResponse;
import com.kenfo.http.GPServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author kenfo
 * @version V1.0
 * @Package java.com.kenfo.catalina
 * @Description: TODO
 * @date 2018/2/15 下午1:49
 */
public class GPTomcat {

    private int port = 8081;
    private ServerSocket serverSocket;

    private Map<String, GPServlet> servletMapping = new HashMap<String, GPServlet>();

    private Properties webxml = new Properties();

    //1. 加载配置文件，初始化servletMapping
    private void init(){
        //加载web.xml文件，初始化servletMapping对象
        try {
            String basePath = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(basePath + "web.properties");
            webxml.load(fis);

            for (Object k:webxml.keySet()){
                String key = k.toString();
                if(key.endsWith(".url")){
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    GPServlet obj = (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //2. 等待用户请求,使用死循环实现
     private void process(Socket client) throws Exception{
         //3. 获取到socket对象，将socket。getInputStream()封装成Request,将socket。getOutStream()封装成Response
         InputStream is = client.getInputStream();
         OutputStream out = client.getOutputStream();

         GPRequest request = new GPRequest(is);
         GPResponse response = new GPResponse(out);
         //4. 实现动态调用doGet/doPost方法，并且能够自定义返回结果
         //拿到用户请求的url
         String url = request.getUrl();
         if(servletMapping.containsKey(url)){
             servletMapping.get(url).service(request, response);
         }else {
             response.write("404 - Not Found");
         }


        //BIO版本
         out.flush();
         out.close();
         //因为http请求都是采用短链接，所以要关闭
         client.close();
    }


    public void start(){

        init();

        try {
            serverSocket = new ServerSocket(this.port);

            System.out.println("GP Tomcat 已启动，监听端口：" + this.port);
            //单线程
            //Socket socket = serverSocket.accept();
            //System.out.println(socket);

            //多线程
            while (true){
                Socket socket = serverSocket.accept();

                process(socket);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
