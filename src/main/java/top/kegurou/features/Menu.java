package top.kegurou.features;

public class Menu {

    public static String getMenu() {
        String menu = null;
        menu = "---功能---\n" + "[来张涩图]\t 随机一张涩图\n" + "[骂我]\t随机骂自己\n" + "[夸我]\t随机夸自己\n" + "[网抑云]\t随机抑郁\n"
                + "[点歌+歌名]\t网易云点歌/格式不对可能会有奇怪的东西\n" + "[-功能]\t查看目前功能\n" + "[-Mirai功能]\t查看计划中的功能\n";
        return menu;
    }

    public static String getFeature() {
        String feature;
        feature = "计划中的功能：a.报时功能\t b.重构代码框架，增加签到功能，丰富后端。";
        return feature;
    }
}