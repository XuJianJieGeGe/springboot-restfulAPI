package com.xjj.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.UrlPattern;

/**
 * Created by jie on 2018/9/10.
 * 伪造restFul服务请求helloworld;
 *
 */
public class MockServer {

    public static void main(String[] args) {
        //配置端口
        WireMock.configureFor(8062);
        //移除所有的请求
        WireMock.removeAllMappings();

        //伪造一个请求装
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/order/1"))
                .willReturn(WireMock.aResponse()
                        .withBody("{\"id\":1}").withStatus(200)));
    }

}
