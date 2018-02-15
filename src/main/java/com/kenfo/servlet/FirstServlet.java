package com.kenfo.servlet;

import com.kenfo.http.GPRequest;
import com.kenfo.http.GPResponse;
import com.kenfo.http.GPServlet;

/**
 * @author kenfo
 * @version V1.0
 * @Package java.com.kenfo.servlet
 * @Description: TODO
 * @date 2018/2/15 下午1:59
 */
public class FirstServlet extends GPServlet {

    public void doGet(GPRequest gpRequest, GPResponse gpResponse) throws Exception {
        this.doPost(gpRequest, gpResponse);
    }

    public void doPost(GPRequest gpRequest, GPResponse gpResponse) throws Exception {
        gpResponse.write("this is first servlet");
    }
}
