package top.kegurou.util;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    // 请使用Mico-http
    // 此文件已未使用，仅存在于某些Test方法中。

    /**
     * @author qibao
     * @param url
     * @return InputStream
     * @see 没有关闭InputStream甚用
     */
    public static InputStream doGet(String url) {
        InputStream iStream = null;
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0 Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89Safari/537.36");
            connection.connect();
            iStream = connection.getInputStream();
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
            // TODO: handle exception
        }

        finally {
            try {
                if (iStream != null) {
                    // iStream.close();
                    // System.out.println("iStream 未关闭测试");
                }
            } catch (Exception e) {
                System.out.println("InputStream关闭失败" + e.getMessage());
                // TODO: handle exception
            }
        }
        return iStream;
    }

    /**
     * @author qibao
     * @param url
     * @return InputStream
     * @see 没有关闭InputStream
     */
    public static InputStream doPost(String url, String arg1) {
        OutputStreamWriter out = null;
        InputStream iStream = null;
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            // 添加访问配置
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
            // 发送Post请求
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // 添加参数
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.write(arg1);
            out.flush();
            // 获取InputStream
            iStream = connection.getInputStream();
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
            // TODO: handle exception
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (iStream != null) {
                    // iStream.close();
                }
            } catch (Exception e) {
                System.out.println("关闭时发生错误：" + e.getMessage());
                // TODO: handle exception
            }
        }
        return iStream;
    }

    public static InputStream doPost(String url) {
        return doPost(url, " ");
    }
}