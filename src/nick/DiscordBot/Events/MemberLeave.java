package nick.DiscordBot.Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.Random;

public class MemberLeave extends ListenerAdapter {
    String[] messages = {
            "Bye [member]",
            "RIP [member]",
            "Cya [member]",
            "Later [member]"
    };

    public void onMemberLeave(GuildMemberRemoveEvent event) {
        Random rand = new Random();
        int number = rand.nextInt(messages.length);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0xc9bc04);
        embed.setDescription(messages[number].replace("[member]", Objects.requireNonNull(event.getMember()).getAsMention()));
        Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessageEmbeds(embed.build()).queue();
    }
}
