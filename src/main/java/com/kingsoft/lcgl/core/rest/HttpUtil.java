package com.kingsoft.lcgl.core.rest;

/**
 * Created by hemingzhuang on 2016/8/3.
 */

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HTTP工具类
 * 当前只支持返回类型为JSON格式转换为Bean对象
 * @author ChenZhiguo<chenzhiguo@kingsoft.com>
 */
public class HttpUtil {
    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     * @param url  请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param clazz 返回对象的Class类型
     * @param argClasses 返回为集合类型时的集合类型 eg:List<String>中的String
     * @return
     * @throws Exception
     */
    public static <T> T doGet(String url, Map<String, String> params, Class<T> clazz, Class<?>... argClasses) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = "";
        try {
            RequestBuilder requestBuilder = RequestBuilder.get().setUri(new URI(url));

            if(params != null){
                for(Entry<String, String> entry : params.entrySet()){
                    requestBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            HttpUriRequest httpGet = requestBuilder.build();
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            try {
                HttpEntity entity1 = response1.getEntity();
                // 判断执行结果返回状态
                int status = response1.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response1.getEntity();
                    responseBody = entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

        } finally {
            httpclient.close();
        }
        if(argClasses.length > 0){
            JavaType javaType= objectMapper.getTypeFactory().constructParametricType(clazz, argClasses);
            return objectMapper.readValue(responseBody, javaType);
        }else{
            return objectMapper.readValue(responseBody, clazz);
        }
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     *
     * @param url  请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param clazz 返回对象的Class类型
     * @param argClasses 返回为集合类型时的集合类型 eg:List<String>中的String
     * @return
     * @throws Exception
     */
    public static <T> T doPost(String url, Map<String, String> params, Class<T> clazz, Class<?>... argClasses) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = "";
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(params != null){
            for(Entry<String, String> entry : params.entrySet()){
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            HttpEntity entity2 = response2.getEntity();
            // 判断执行结果返回状态
            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response2.getEntity();
                responseBody = entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
            httpclient.close();
        }
        if (clazz != null) {
            if(argClasses.length > 0){
                JavaType javaType= objectMapper.getTypeFactory().constructParametricType(clazz, argClasses);
                return objectMapper.readValue(responseBody, javaType);
            }else{
                return objectMapper.readValue(responseBody, clazz);
            }
        } else {
            return null;
        }
    }

    /**
     * 带文件的HTTP POST请求，返回请求响应的HTML
     *
     * @param url  请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param filePaths 文件列表<参数名， 文件路径>可以为null
     * @param clazz 返回对象的Class类型
     * @param argClasses 返回为集合类型时的集合类型 eg:List<String>中的String
     * @return
     * @throws Exception
     */
    public static <T> T doPost(String url, Map<String, String> params, Map<String, String> filePaths, Class<T> clazz, Class<?>... argClasses) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = "";
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        // 参数
        if(params != null){
            for(Entry<String, String> entry : params.entrySet()){
                StringBody strPart = new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8));
                entityBuilder.addPart(entry.getKey(), strPart);
            }
        }
        // 文件
        if (filePaths != null) {
            for (Entry<String, String> entry : filePaths.entrySet()) {
                FileBody filePart = new FileBody(new File(entry.getValue()));
                entityBuilder.addPart(entry.getKey(), filePart);
            }
        }

        httpPost.setEntity(entityBuilder.build());
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            HttpEntity entity2 = response2.getEntity();
            // 判断执行结果返回状态
            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response2.getEntity();
                responseBody = entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
            httpclient.close();
        }
        if (clazz != null) {
            if (argClasses.length > 0) {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(clazz, argClasses);
                return objectMapper.readValue(responseBody, javaType);
            } else {
                return objectMapper.readValue(responseBody, clazz);
            }
        } else {
            return null;
        }
    }

    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     *
     * @param url  请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param clazz 返回对象的Class类型
     * @param argClasses 返回为集合类型时的集合类型 eg:List<String>中的String
     * @return
     * @throws Exception
     */
    public static <T> T doGetAddHeader(String url, Map<String, String> params,Map<String, String> headParams, Class<T> clazz, Class<?>... argClasses) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = "";
        try {
            RequestBuilder requestBuilder = RequestBuilder.get().setUri(new URI(url));

            if(params != null){
                for(Entry<String, String> entry : params.entrySet()){
                    requestBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }

            HttpUriRequest httpGet = requestBuilder.build();
            if(headParams != null){
                for(Entry<String, String> entry : headParams.entrySet()){
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            httpGet.addHeader("timestamp", (System.currentTimeMillis()/1000) + "");

            CloseableHttpResponse response1 = httpclient.execute(httpGet);

            try {
                HttpEntity entity1 = response1.getEntity();
                // 判断执行结果返回状态
                int status = response1.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response1.getEntity();
                    responseBody = entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

        } finally {
            httpclient.close();
        }
        if(argClasses.length > 0){
            JavaType javaType= objectMapper.getTypeFactory().constructParametricType(clazz, argClasses);
            return objectMapper.readValue(responseBody, javaType);
        }else{
            return objectMapper.readValue(responseBody, clazz);
        }
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     *
     * @param url  请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param clazz 返回对象的Class类型
     * @param argClasses 返回为集合类型时的集合类型 eg:List<String>中的String
     * @return
     * @throws Exception
     */
    public static <T> T doPostAddHeader(String url, Map<String, String> params, Map<String, String> headParams, Class<T> clazz, Class<?>... argClasses) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = "";
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(params != null){
            for(Entry<String, String> entry : params.entrySet()){
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        if(headParams != null){
            for(Entry<String, String> entry : headParams.entrySet()){
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.addHeader("timestamp", (System.currentTimeMillis()/1000) + "");

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            HttpEntity entity2 = response2.getEntity();
            // 判断执行结果返回状态
            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response2.getEntity();
                responseBody = entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
            httpclient.close();
        }
        if (clazz != null) {
            if(argClasses.length > 0){
                JavaType javaType= objectMapper.getTypeFactory().constructParametricType(clazz, argClasses);
                return objectMapper.readValue(responseBody, javaType);
            }else{
                return objectMapper.readValue(responseBody, clazz);
            }
        } else {
            return null;
        }
    }

    /**
     * 带文件的HTTP POST请求，返回请求响应的HTML
     *
     * @param url  请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param filePaths 文件列表<参数名， 文件路径>可以为null
     * @param clazz 返回对象的Class类型
     * @param argClasses 返回为集合类型时的集合类型 eg:List<String>中的String
     * @return
     * @throws Exception
     */
    public static <T> T doPostFileAddHeader(String url, Map<String, String> params, Map<String, String> filePaths, Map<String, String> headParams,Class<T> clazz, Class<?>... argClasses) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = "";
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        // 参数
        if(params != null){
            for(Entry<String, String> entry : params.entrySet()){
                StringBody strPart = new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8));
                entityBuilder.addPart(entry.getKey(), strPart);
            }
        }

        if(headParams != null){
            for(Entry<String, String> entry : headParams.entrySet()){
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.addHeader("timestamp", (System.currentTimeMillis()/1000) + "");
        // 文件
        if (filePaths != null) {
            for (Entry<String, String> entry : filePaths.entrySet()) {
                FileBody filePart = new FileBody(new File(entry.getValue()));
                entityBuilder.addPart(entry.getKey(), filePart);
            }
        }

        httpPost.setEntity(entityBuilder.build());
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            HttpEntity entity2 = response2.getEntity();
            // 判断执行结果返回状态
            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response2.getEntity();
                responseBody = entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
            httpclient.close();
        }
        if (clazz != null) {
            if (argClasses.length > 0) {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(clazz, argClasses);
                return objectMapper.readValue(responseBody, javaType);
            } else {
                return objectMapper.readValue(responseBody, clazz);
            }
        } else {
            return null;
        }
    }
}
