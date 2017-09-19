package com.lebron.springboot.httpclient;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HttpAPIService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    /**
     * 不带参数的get请求
     */
    public HttpResult doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        return new HttpResult(response.getStatusLine().getStatusCode(),
                EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的get请求
     */
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }
    

    /**
     * 不带参数post请求
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    /**
     * 带参数的post表单请求
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(),
                EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的post json请求
     */
    public HttpResult doPostJson(String url, Object object) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (object != null) {
            // 构造StringEntity
            StringEntity stringEntity = new StringEntity(new ObjectMapper().writeValueAsString(object), "utf-8");
            stringEntity.setContentType("application/json"); 
            // 把表单放到post里
            httpPost.setEntity(stringEntity);
        }
        
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(),
                EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

}