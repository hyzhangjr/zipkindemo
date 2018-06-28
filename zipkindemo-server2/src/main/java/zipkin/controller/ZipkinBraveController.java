package zipkin.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipkinBraveController {
    @Autowired
    private CloseableHttpClient okHttpClient;

    @GetMapping("/test")
    public String myboot() throws Exception {
        Thread.sleep(100);//100ms
        HttpGet get = new HttpGet("http://localhost:8082/test");
        CloseableHttpResponse execute1 = okHttpClient.execute(get);
        /*
         * 1、执行execute()的前后，会执行相应的拦截器（cs,cr）
         * 2、请求在被调用方执行的前后，也会执行相应的拦截器（sr,ss）
         */
        HttpGet get2 = new HttpGet("http://localhost:8083/test");
        CloseableHttpResponse execute2 = okHttpClient.execute(get2);

        return EntityUtils.toString(execute1.getEntity(), "utf-8") + "-" +EntityUtils.toString(execute2.getEntity(), "utf-8");
    }
}