package com.kenfo.http;

import java.io.InputStream;
import java.util.Map;

/**
 * @author kenfo
 * @version V1.0
 * @Package java.com.kenfo.http
 * @Description: TODO
 * @date 2018/2/15 下午1:50
 */
public class GPRequest {

    private String method;
    private String url;

    public GPRequest(InputStream is){
        //http协议就一串字符串
        try {
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if((len = is.read(buff)) > 0){
                content = new String(buff, 0, len);
                System.out.println(content);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public  String getMethod(){
        return this.method;
    }

    public String getUrl(){
        return this.url;
    }

    public Map<String, String> getParamters(){
        return null;
    }
}
