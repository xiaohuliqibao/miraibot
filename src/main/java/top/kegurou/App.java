package top.kegurou;

import java.io.File;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kotlin.coroutines.CoroutineContext;
import net.dreamlu.mica.core.validation.GetGroup;
import net.dreamlu.mica.http.HttpRequest;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendEvent;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.LightApp;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.utils.BotConfiguration;
import top.kegurou.bean.LoliconImageBean;
import top.kegurou.features.Menu;
import top.kegurou.features.Setu;
import top.kegurou.features.Shadiao;
import top.kegurou.features.Wangyiyun;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(final String[] args) throws InterruptedException {
        // System.out.println( "Hello World!" );
        final Bot bot = BotFactoryJvm.newBot(114L, "****", new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
            }
        });

        bot.login();

        bot.getFriends().forEach(friend -> System.out.println(friend.getId() + ":" + friend.getNick()));

        bot.getGroups().forEach(group -> System.out.println(group.getId() + ":" + group.getName()));

        Events.registerEvents(bot, new SimpleListenerHost() {
            @EventHandler
            public ListeningStatus onFriendMessage(FriendMessageEvent event) {
                String friendMsg = event.getMessage().contentToString();
                return ListeningStatus.LISTENING;
            }

            @EventHandler
            public ListeningStatus onGroupMessage(GroupMessageEvent event) {
                String groupMsg = event.getMessage().contentToString();

                System.out.println(groupMsg);
                if (groupMsg.contains("-功能")) {
                    String sendMsg = Menu.getMenu();
                    event.getGroup().sendMessage(sendMsg);
                } else if (groupMsg.contains("-Mirai功能")) {
                    String sendMsg = Menu.getFeature();
                    event.getGroup().sendMessage(sendMsg);
                } else if (groupMsg.equals("骂我")) {
                    String sendMsg = Shadiao.getNMSL();
                    event.getGroup().sendMessage(MessageUtils.newChain(sendMsg).plus(new At(event.getSender())));
                } else if (groupMsg.equals("夸我")) {
                    String sendMsg = Shadiao.getCHP();
                    event.getGroup().sendMessage(MessageUtils.newChain(sendMsg).plus(new At(event.getSender())));
                } else if (groupMsg.equals("来张涩图")) {
                    String r18 = "0";
                    // 获取imageBean的基本信息
                    LoliconImageBean imageBean = Setu.getLoliconImage(r18);

                    String imageTitle = imageBean.getTitle();
                    String imageUrl = imageBean.getUrl();
                    String sendMsg = "神秘链接：" + imageUrl + "\n";

                    File imageFile = Setu.saveImageFile(imageUrl, imageTitle);
                    // 响应时间有点长，写一个文本消息来缓冲一下使用时的体验，如果处理时间不长可以不用。
                    // event.getGroup().sendMessage(MessageUtils.newChain(sendMsg).plus(new
                    // At(event.getSender())));
                    final Image image = event.getGroup().uploadImage(imageFile);
                    final String imageId = image.getImageId();
                    event.getGroup().sendMessage(MessageUtils.newImage(imageId));
                } else if (groupMsg.equals("网抑云")) {
                    String sendMsg = Wangyiyun.getWangyiyunComment();
                    event.getGroup().sendMessage(sendMsg);
                } else if (groupMsg.contains("点歌")) {
                    if (groupMsg.substring(0, 2).equals("点歌")) {
                        String songName = groupMsg.substring(2, groupMsg.length());
                        MessageChain ms = MessageUtils
                                .newChain(new LightApp(Wangyiyun.getCloudMusicJsonContent(songName)));
                        event.getGroup().sendMessage(ms);
                    }
                }
                return ListeningStatus.LISTENING;
            }

            @Override
            public void handleException(@NotNull final CoroutineContext context, @NotNull final Throwable exception) {
                throw new RuntimeException("在事件处理中发生异常", exception);
            }
        });
        bot.join();
    }
}
