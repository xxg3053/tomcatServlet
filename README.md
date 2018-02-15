# 手写tomcat透彻理解Servlet实现原理

Tomcat透彻理解Servlet实现原理：https://www.365yg.com/i6511939010108064264




web.xml   
告诉Web容器，浏览器的URL和业务对象进行关联，相当于一个注册的作用   

为什么要继承servlet   
Java语言就是一门面向接口编程语言，一旦继承了就解决了动态强制转换的问题，这也是一种设计模式   

为什么要重写doGet    
用于扩展我们自己的核心业务说白了，就是用来填空的   

HttpRequest和HttpResponse    
实际上是socket的封装http协议都是基于TCP/IP之上的   
InputStream Resquest  
OutPutStream Response   


url是如何被转换为调用java方法   
ServletMapping map键值对，键URL， 值servlet对象   

