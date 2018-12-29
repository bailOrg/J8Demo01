package com.fight.team.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * java - http请求与响应
 *
 * @author bail
 * @time 2018/3/15.11:14
 */
public class HttpClientDemo {
    /**
     * 服务器host
     */
    private static final String API_HOST = "https://bossbff.s2b.wanmi.com";

    /**
     * 查询基本信息
     */
    private static final String BASE_CONFIG = "/baseConfig";

    public static void main(String[] args) {
        HttpClientDemo demo = new HttpClientDemo();
        // 发送请求
        String data = demo.getBaseConfig();
        System.out.println("context ====>" + data);
    }

    /**
     * 获取基本配置接口
     *
     * @return 业务数据
     */
    public String getBaseConfig() {
        JSONObject result = getReq(BASE_CONFIG);
        if (result != null) {
            // 返回的业务数据
            return result.getString("context");
        }
        return null;
    }

    /**
     * 发送GET请求
     *
     * @param apiUrl api的url路径
     * @return 请求结果
     */
    public static JSONObject getReq(String apiUrl) {
        long begin = System.currentTimeMillis();
        JSONObject result = null;
        String resultStr = null;
        // 1.请求头文件
        HttpGet get = new HttpGet(API_HOST.concat(apiUrl));
        get.addHeader("Content-Type", "application/json;");
        try {
            // 2.httpClient执行请求,并等待响应结果
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse res = httpclient.execute(get);

            // 3.200响应状态码
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 4.返回业务数据
                resultStr = EntityUtils.toString(res.getEntity());
                result = JSONObject.parseObject(resultStr);
            } else {
                System.out.printf("发送GET请求回执异常, paramMap:%s, result:%s; costTime:%s", get.toString(), resultStr,
                        (System.currentTimeMillis() - begin));
            }
        } catch (Exception ex) {
            System.out.printf("发送GET请求失败, paramMap:%s, exMsg:%s; costTime:%s \n", get.toString(), ex.getMessage(), (System
                    .currentTimeMillis() - begin));
            ex.printStackTrace();
        } finally {
            System.out.printf("发送GET请求结束; paramMap:%s; result:%s; costTime:%s \n", get.toString(), resultStr, (System
                    .currentTimeMillis
                            () - begin));
        }
        return result;
    }

    /**
     * 发送POST请求
     *
     * @param apiUrl api的url路径
     * @param params 需要的参数
     * @return 请求结果
     */
    public static JSONObject postReq(String apiUrl, JSONObject params) {
        long begin = System.currentTimeMillis();
        JSONObject result = null;
        String resultStr = null;
        // 1.请求头文件 与 入参
        HttpPost post = new HttpPost(API_HOST.concat(apiUrl));
        post.addHeader("Content-Type", "application/json;");
        if (params != null) {
            post.setEntity(new StringEntity(params.toString(), Charset.forName("UTF-8")));
        }
        try {
            // 2.httpClient执行请求,并等待响应结果
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse res = httpclient.execute(post);

            // 3.200响应状态码
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 4.返回业务数据
                resultStr = EntityUtils.toString(res.getEntity());
                result = JSONObject.parseObject(resultStr);
            } else {
                System.out.printf("发送POST请求回执异常, paramMap:%s, result:%s; costTime:%s", post.toString(), resultStr,
                        (System.currentTimeMillis() - begin));
            }
        } catch (Exception ex) {
            System.out.printf("发送POST请求失败, paramMap:%s, exMsg:%s; costTime:%s \n", post.toString(), ex.getMessage(), (System
                    .currentTimeMillis() - begin));
            ex.printStackTrace();
        } finally {
            System.out.printf("发送POST请求结束; paramMap:%s; result:%s; costTime:%s \n", post.toString(), resultStr, (System
                    .currentTimeMillis
                            () - begin));
        }
        return result;
    }
}
