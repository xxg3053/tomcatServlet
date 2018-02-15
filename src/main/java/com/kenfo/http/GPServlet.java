package com.kenfo.http;

/**
 * @author kenfo
 * @version V1.0
 * @Package java.com.kenfo.http
 * @Description: TODO
 * @date 2018/2/15 下午1:51
 */
public abstract class GPServlet {

    public void service(GPRequest gpRequest, GPResponse gpResponse) throws Exception{
        //由service方法来决定使用doget还是dopost
        if("GET".equalsIgnoreCase(gpRequest.getMethod())){
            doGet(gpRequest,gpResponse);
        }else {
            doPost(gpRequest, gpResponse);
        }
    }

    public abstract void doGet(GPRequest gpRequest, GPResponse gpResponse) throws Exception;
    public abstract void doPost(GPRequest gpRequest, GPResponse gpResponse) throws Exception;

}
