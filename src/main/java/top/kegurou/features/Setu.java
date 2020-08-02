package top.kegurou.features;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.dreamlu.mica.http.HttpRequest;
import top.kegurou.bean.LoliconImageBean;

public class Setu {

    private static final String LOLICONAPIURL = "https://api.lolicon.app/setu/";
    private static final String LOLICONAPIKEY = "***********";

    /**
     * @author qibao
     * @param r18
     * @return LoliconImageBean
     * @see 还是老规矩把Json中的文件放到Bean类中。
     */

    public static LoliconImageBean getLoliconImage(String r18) {
        // StringBuilder sb = getImageJson(r18); 使用mico-http
        String s = null;
        s = HttpRequest.get(LOLICONAPIURL).query("apikey", LOLICONAPIKEY).query("r18", r18).execute().asString();
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject jsonObjectImage = jsonObject.getJSONArray("data").getJSONObject(0);

        LoliconImageBean loliconImageBean = new LoliconImageBean();
        loliconImageBean = JSON.parseObject(jsonObjectImage.toJSONString(), LoliconImageBean.class);

        return loliconImageBean;
    }

}