package top.kegurou;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kotlin.coroutines.CoroutineContext;
import net.dreamlu.mica.core.validation.GetGroup;
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
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.BotConfiguration;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(final String[] args) throws InterruptedException {
        // System.out.println( "Hello World!" );
        final Bot bot = BotFactoryJvm.newBot(123456789L, "QQ密码", new BotConfiguration() {
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
                System.out.println(event.getSenderName());
                System.out.println(event.getMessage().contentToString());
                return ListeningStatus.LISTENING;
            }

            @EventHandler
            public ListeningStatus onGroupMessage(GroupMessageEvent event) { 
                String groupMsg = event.getMessage().contentToString();
                System.out.println(groupMsg);
                if (groupMsg.contains("喷一下")) {
                    event.getGroup().sendMessage("喷不动了。");
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
