package top.kegurou.features;

public class Menu {

    public static String getMenu() {
        String menu = null;
        menu = "---功能---\n" + "*[骂他@qq]\t随机骂人\n" + "[骂我]\t随机骂自己\n" + "*[夸他@qq]\t随机夸人\n" + "[夸我]\t随机夸自己\n"
                + "[-功能]\t查看目前功能\n" + "[-Mirai功能]\t查看计划中的功能\n";
        return menu;
    }
}