package top.kegurou.features;

import net.dreamlu.mica.http.HttpRequest;

public class Shadiao {

    private static final String NMSL_URL = "https://nmsl.shadiao.app/api.php?level=min";
    private static final String CHP_URL = "https://chp.shadiao.app/api.php";

    public static String getNMSL() {
        return HttpRequest.get(NMSL_URL).execute().asString();
    }

    public static String getCHP() {
        return HttpRequest.get(CHP_URL).execute().asString();
    }
}
