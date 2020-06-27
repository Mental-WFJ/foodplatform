package com.frontEnd.entity;


import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Mental
 * @Date:
 */
public class AlipayConfig {
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    //public static String app_id = "2021001159691582";
    public static String app_id = "2016102400752957";

    // 商户私钥，您的PKCS8格式RSA2私钥
    //public static String merchant_private_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0P1QbvGYyLwWRRhH5g0rPjBiH+016RVfNh+54WSn9IO4wCwaG86cT+dqQRdNTNHcP9GNlDU8n/e0RxPdem+Ha2TvuJTs7gAnJp0ZHiP+VM8rxTIkyQsfxPKTCKtCWiJpinuqv9R+p4nTPweoaA3e9tgzLR7QQk3Rm94SKdgpIih2ZK/aGOLj7PBR1KcFTS5HemHBIIaMPz7FtsBlN0tY84RzRMDV5W/ZBqHcMdXxmwUXogc+GUbOiWEm6tr0wipnKcKOwRqLdBStPPJDwaUQp45+5dqqA35q6fp1WyIzTjv+oRKByEydZv5nOchBkC9pXRGRM/5rZOty3RvaS4QscQIDAQAB";
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC7Lvd+L+x+U18SG04M1yeiVd5UUKz1Q8sCiHR7qss40mFYE0vSiLLdFgUqR9JYcrRbu2O7yMwQQ2wMV5HXyUtl7y01g4eFPyMHHATW8nEoBoKRc4Ttv6GaKZ31pXanv2SH4g3Oiiw750ORpUp36svq/zkxS2P6mASlNR9yI8PTvlNhyk/LCXRVxdedgaI212NBoYN9yzxrDs0iLh/YaT9qmF+WmiXANMaL80X5y2LG51mbcFq6nCyjliUMcds5vOD3SdMciESrMkGBmq//LeWUxOHfHg0E4jHqIXYollT8T3kGTYgoms+WA9PIPtYWDiGs0CASBd5EbA5U5spLeO39AgMBAAECggEBALc1LBY9L9shHO9q/ETDHc7c/uRFplPUy9Gp32DfdVMjTxXWpSLmmzKbh+ivLWlWs8MJMX+t9FWCuDGIUH4FTp/xrq0vFKjNeCpQOUhtOWX0oT1d9OCC7NgoHaw+DFn5kZsSHXNneA4Vt+PJBY9Dp91VexQNd0jiG8HbsfCUouoOM5ZEObxyFGoGdFfqGh2XW2dvyMVjSVx5ZBrFISyyt7Vu5m2fc0htuqzzgOuTPJr6ZCgEP4WKfXg0arqwmQ9DZR141QSWe5jtJbD0Y62fJwfIKN5ZVuJvccDsOj61vKQVVSAhLO8vdZBc0qeFDoHbgdtc99mRBB6CYq3BuSCaLkECgYEA8YMGEV5oBe+X3BvXUduYYJDZUDBqTRdi0bqLlYqd1Rxx9miDH7T5y6OcqRLV5c/oiwQPKA58yhFX3ce/2DTvkoO3dNvtVmZjNSNUIUpdsiZONMB0eUXWH15srnjxppky+OBPnnFicsVVQP0NELNKjdRhdQIbx3MLtloltEGSk40CgYEAxmma0lm66wY8zas7u3iR3uymTvBfhegZuyhhnJAuPNwQ6oZf2NSukuR7e1yivSiVP+amhMuOKn/gmVFpydY81xo13iclFseEhc8IJfexxnVqQf4RQuPvLFRwgIYJoY6Ts6FCZcTqYl0BpzK4lN0z2CSqDorqkLg3H9J50/eRcDECgYEAzbbsITlH4958fNkeJDSna4GWCW7j7PyBiOGb6enjnNYY4GmPXx+ZMnMPixhnJ9RbYKY5fsYwVzJEpL1zVOdqNWOKjbiTRef6kxWfoKemfYuRS++uhmoNBDU637ZXJ7AQsdrnFq+/ysD5aFzjlxkQ5RRFWxRLsyz0igPpX8wG3TUCgYBdwJK2aE3JNeZPLGUKHVuX4WxoCm6ogFaAOAF/kCU4/qdDmgD6HOzsXef3D3uaM+3qBtuSDfj8HfaLm4uIIqIW5bykXwzIabg13fC0uAiZhOuTiTCw0bE22iNap3svioZCL6WHHve9SMM6NN5BARC5RgHQ41UY0uLBN5v3S5i+UQKBgQCXf0/zT5hlq496FMqWzcWYXPQO4y8UNema4fS5GQCvHeJXb3rv0qZW8czj953gE31Ndq3cRYWAEs3nC7A7igsCAk/L1ZOS7LKfT/BV6QdvLj4FRo74An/ll5cjeOBNbTLGc2heNshWbS+YXHUt3qcxn/p0LCxWMGaPcy7h86eGjA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    //public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4j+siSsq5SnFQ0BSYHDVB1d0GFB3jWoHefe8wXkVqH6qvU3I1QLfWXPodPFNj4VxDCfRBDlOdTodMllV7zYyApDfmQ1+XV56TDii+n9TYSkyrN3QVwXWfKNKU1BwViwzebkYiOE7sfhtDccDfGTdQNo6PdI1liq4tXfTQnKyxa15jxY/zzJdC2uzbXvEbBC+j24/vdQu55olWEFTHpPM0CMtEcH6KkQvDzF/LUI9H0dzeunw0weFdAgT0Ar8Z+M26XEWYNHR5M4AW8BMVyBUf1WF/SnzwSjf+8Fii5UvRAIzhspyeUHas0Sst4uGDM5CVyK7vNmXbnrzFyXQBvVSXwIDAQAB";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6B6A9NUfsIReP3DDqojoh6QwvzvR2TGbFsk/WckzarfFt/3NxFNcrpEvTe1Mx+yVnKrFzDZ6TdMf670WRed39eH6XRCKruN0DRKjo4JLrWibKZGaRVACB4WwtlneCySmCv118bpjFiFEeH63CGpUqYUlxx1J2kaOANzPnoyHTNQsmidOU7Sw2Plau3ThL+BU/B0lVGDyXCzvl6J8UjWkyWktklqg9uce3HYMXkOjkvOd3JAeoD70v5VFsAQh6Xp0i/r9RSZNlJihYWvxIEFPmjTSHh9apiam24Rxb83Q+lVveo7CCueNoN4ZI9CHoAaS3OZxV/tSStRtjvXGGAcxywIDAQAB";//MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuy73fi/sflNfEhtODNcnolXeVFCs9UPLAoh0e6rLONJhWBNL0oiy3RYFKkfSWHK0W7tju8jMEENsDFeR18lLZe8tNYOHhT8jBxwE1vJxKAaCkXOE7b+hmimd9aV2p79kh+INzoosO+dDkaVKd+rL6v85MUtj+pgEpTUfciPD075TYcpPywl0VcXXnYGiNtdjQaGDfcs8aw7NIi4f2Gk/aphflpolwDTGi/NF+ctixudZm3Baupwso5YlDHHbObzg90nTHIhEqzJBgZqv/y3llMTh3x4NBOIx6iF2KJZU/E95Bk2IKJrPlgPTyD7WFg4hrNAgEgXeRGwOVObKS3jt/QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8081/frontEnd/orders/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8081/frontEnd/orders/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    //public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
