package com.kenfo.http;

import java.io.OutputStream;

/**
 * @author kenfo
 * @version V1.0
 * @Package java.com.kenfo.http
 * @Description: TODO
 * @date 2018/2/15 下午1:50
 */
public class GPResponse {

    private OutputStream os;

    public GPResponse(OutputStream out){
        this.os = out;
    }

    public void write(String outString) throws Exception{
        os.write(outString.getBytes());
    }
}
