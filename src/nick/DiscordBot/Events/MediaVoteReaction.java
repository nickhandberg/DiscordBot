package nick.DiscordBot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// Adds like dislike reactions to images/video links for user voting
public class MediaVoteReaction extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event){
        String[] keywords = {".mp4",".tv",".webm","https","youtu"};
        String message = event.getMessage().toString().toLowerCase();

        for (String keyword : keywords) {
            if (message.contains(keyword)) {
                event.getMessage().addReaction("⭐").queue();
                event.getMessage().addReaction("❌").queue();
            }
        }
    }
}
