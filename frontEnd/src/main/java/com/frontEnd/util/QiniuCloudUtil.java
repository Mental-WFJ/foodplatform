package com.frontEnd.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * @Author: Mental
 * @Date:
 */
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "0GNeUR3P1ceewSBOuRQjINybkSTU_aPaqq9n_2TA";
    private static final String SECRET_KEY = "3Q9sqZVlS2sStXhCQWS__Z5pRoCtZlI6rV88dxS7";

    // 要上传的空间
    private static final String bucketname = "foodplatform";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final String DOMAIN = "q9j5eyjgf.bkt.clouddn.com";

    private static final String style = "自定义的图片样式";

    public String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }
    // 普通上传
    public String upload(String filePath, String fileName) throws IOException {

        //文件的外链地址
        StringBuffer fileUrl = new StringBuffer(DOMAIN);

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());

        // 创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            // 调用put方法上传
            String token = auth.uploadToken(bucketname);
            if(CommonUtil.isEmpty(token)) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            Response res = uploadManager.put(filePath, fileName, token);
            // 打印返回的信息
            System.out.println(res.bodyString());
            if (res.isOK()) {
                Ret ret = res.jsonToObject(Ret.class);
                //如果不需要对图片进行样式处理，则使用以下方式即可
                //return DOMAIN + ret.key;
                return DOMAIN + ret.key + "?" + style;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }


    //base64方式上传
    public String put64image(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        String url = "http://up-z2.qiniup.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        //如果不需要添加图片样式，使用以下方式
        return DOMAIN + "/" +key;
//        return DOMAIN + key + "?" + style;
    }

    /**
     *删除图片
     * @param key
     * @return
     * @throws QiniuException
     */
    public static boolean delete(String key)throws QiniuException{
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration cfg = new Configuration(Zone.zone2());
        BucketManager bucketMgr = new BucketManager(auth, cfg);
        try {
            bucketMgr.delete(bucketname,key);
        }catch (QiniuException Q){
            return false;
        }
        System.out.println("success");
        return true;
    }



    // 普通删除(暂未使用以下方法，未测试)
//    public void delete(String key) throws IOException {
//
//        //文件的外链地址
//        StringBuffer fileUrl = new StringBuffer(DOMAIN);
//
//        //构造一个带指定Zone对象的配置类
//        Configuration cfg = new Configuration(Zone.zone2());
//
//        // 实例化一个BucketManager对象
//        BucketManager bucketManager = new BucketManager(auth);
//        // 此处的33是去掉：http://ongsua0j7.bkt.clouddn.com/,剩下的key就是图片在七牛云的名称
//        key = key.substring(33);
//        try {
//            // 调用delete方法移动文件
//            bucketManager.delete(bucketname, key);
//        } catch (QiniuException e) {
//            // 捕获异常信息
//            Response r = e.response;
//            System.out.println(r.toString());
//        }
//    }

    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }
}
