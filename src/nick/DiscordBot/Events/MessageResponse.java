package nick.DiscordBot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageResponse extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){
        String message = event.getMessage().toString().toLowerCase();
        String[] keywords = {"sponge", "bot", "pet","anime","weeb","perfect","mouse","yes","old","fat","chuck"};
        String[] imageLinks = {
        "https://cdn.discordapp.com/attachments/277294988469993472/807391035252146236/image0_1.gif",
        "https://cdn.discordapp.com/attachments/277294988469993472/807756800857735168/speed.gif",
        "https://cdn.discordapp.com/attachments/277294988469993472/807757804873318410/PETTHEPEEPO.gif",
        "https://cdn.discordapp.com/attachments/277294988469993472/924136869950791710/poop.gif",
        "https://cdn.discordapp.com/attachments/277294988469993472/924139390903681114/672493392395239425.gif",
        "https://cdn.discordapp.com/attachments/277294988469993472/924136985101230110/tenor.gif",
        "https://cdn.discordapp.com/attachments/277294988469993472/924138339555233792/test-removebg-preview.png",
        "https://cdn.discordapp.com/attachments/277294988469993472/924138595537809448/NO.png",
        "https://cdn.discordapp.com/attachments/277294988469993472/924138859439202334/FeelsCrustyMan.png",
        "https://cdn.discordapp.com/attachments/277294988469993472/924139134975635466/artworks-yrpn1tbtHr6qiRxh-tnzkkQ-t500x500.jpg",
        "https://cdn.discordapp.com/attachments/277294988469993472/924139884153798666/chuckie.PNG"};

        if(!event.getAuthor().isBot()) {
            // Sends images if keywords are found
            for (int i = 0; i < keywords.length; i++) {
                if (message.contains(keywords[i])) {
                    event.getChannel().sendMessage(imageLinks[i]).queue();
                }
            }
            // 5% chance to send nice to the the chat
            if (Math.random() <= 0.05) {
                event.getChannel().sendMessage("nice").queue();
            }
        }
    }
}
