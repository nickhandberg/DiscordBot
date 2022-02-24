package nick.DiscordBot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nick.DiscordBot.DiscordBot;

public class Help extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event){
        // GETS USER COMMANDS
        String[] args = event.getMessage().getContentRaw().split(" ");

        // EMBED
        if(args[0].equalsIgnoreCase(DiscordBot.prefix+"h")){
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("COMMAND LIST");
            embed.addField("Chat commands","~clear",false);
            embed.addField("Games","~connect4\n~tictactoe",false);
            embed.setColor(0x0dba3b);
            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            embed.clear();

        }
    }
}
