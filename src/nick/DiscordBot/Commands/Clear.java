package nick.DiscordBot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nick.DiscordBot.DiscordBot;

import java.util.List;

// Command to clear chat messages
public class Clear extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event){
        // GETS USER COMMANDS
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // Checks for clear command
        if(args[0].equalsIgnoreCase(DiscordBot.prefix+"clear")){
            // Checks for missing arguments and sends embed to user
            if(args.length < 2){
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("❌ Specify amount to delete");
                embed.addField("Usage","~clear [# of messages]",false);
                embed.setColor(0xff0808);
                event.getChannel().sendMessageEmbeds(embed.build()).queue();
                embed.clear();
            }
            else{
                // Tries to delete messages and send embed to user
                try{
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])+1).complete();
                    for (Message message : messages) {
                        message.delete().queue();
                    }
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("✅ Deleted "+args[1]+" messages.");
                    embed.setColor(0x0dba3b);
                    event.getChannel().sendMessageEmbeds(embed.build()).queue();
                    embed.clear();
                // Catches on IllegalArgumentException
                }catch(IllegalArgumentException iae){
                    event.getChannel().sendMessage("Error clearing messages").queue();
                }
            }
        }
    }
}
