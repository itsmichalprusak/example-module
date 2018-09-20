package moe.kyokobot.example;

import com.google.common.eventbus.Subscribe;
import moe.kyokobot.bot.util.UserUtil;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventHandlerExample {
    private final Logger logger = LoggerFactory.getLogger(EventHandlerExample.class);

    @Subscribe
    public void onMessage(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().contains("i love testing")) {
            event.getMessage().getChannel().sendMessage("I love testing too.").queue();
        }
    }

    @Subscribe
    public void onJoin(GuildMemberJoinEvent event) {
        logger.info("Someone joined a guild {}: {}", event.getGuild().getName(), UserUtil.toDiscrim(event.getUser()));
    }
}
