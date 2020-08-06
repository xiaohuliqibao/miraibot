package top.kegurou.features;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.dreamlu.mica.http.HttpRequest;
import top.kegurou.bean.LoliconImageBean;

public class Setu {

    private static final String LOLICONAPI_URL = "https://api.lolicon.app/setu/";
    private static final String LOLICONAPI_KEY = "****";
    private static final String IMAGE_PATH = "/home/qibao/file/images/";
    private static final String IMAGE_FORMAT = ".jpg";

    /**
     * @author qibao
     * @param r18
     * @return LoliconImageBean
     * @see 还是老规矩把Json中的文件放到Bean类中。
     */

    public static LoliconImageBean getLoliconImage(String r18) {
        // StringBuilder sb = getImageJson(r18); 使用mico-http
        String s = null;
        s = HttpRequest.get(LOLICONAPI_URL).query("apikey", LOLICONAPI_KEY).query("r18", r18).execute().asString();
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject jsonObjectImage = jsonObject.getJSONArray("data").getJSONObject(0);

        LoliconImageBean loliconImageBean = new LoliconImageBean();
        loliconImageBean = JSON.parseObject(jsonObjectImage.toJSONString(), LoliconImageBean.class);

        return loliconImageBean;
    }

    /**
     * 
     * @param imageUrl
     * @param imageTitle
     * @return
     */
    public static File saveImageFile(String imageUrl, String imageTitle) {
        File imageFile = new File(IMAGE_PATH + imageTitle + IMAGE_FORMAT);
        HttpRequest.get(imageUrl).execute().toFile(imageFile);
        return imageFile;
    }

}