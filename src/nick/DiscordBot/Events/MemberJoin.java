package nick.DiscordBot.Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.Random;

// Sends message when member joins the discord
public class MemberJoin extends ListenerAdapter {

    String[] messages = {
            "Welcome [member]",
            "Yo, [member]",
            "Hey [member]",
            "Hi [member]"
    };

    public void onMemberJoin(GuildMemberJoinEvent event){
        Random rand = new Random();
        int number = rand.nextInt(messages.length);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0xc9bc04);
        embed.setDescription(messages[number].replace("[member]",event.getMember().getAsMention()));
        Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessageEmbeds(embed.build()).queue();

        // Add role
        event.getGuild().addRoleToMember(event.getMember(), (Role) event.getGuild().getRolesByName("Normies", true)).complete();
    }
}
