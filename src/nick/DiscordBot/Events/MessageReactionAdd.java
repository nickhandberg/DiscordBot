package nick.DiscordBot.Events;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nick.DiscordBot.Games.ConnectFour;

import java.util.Objects;

public class MessageReactionAdd extends ListenerAdapter {

    public void onMessageReactionAdd(MessageReactionAddEvent event){
        String[] reactionArray = {"1️⃣","2️⃣","3️⃣","4️⃣","5️⃣","6️⃣","7️⃣"};
        for(int i=0; i<reactionArray.length; i++){
            if(event.getReactionEmote().getName().equals(reactionArray[i]) && !Objects.requireNonNull(event.getMember()).getUser().isBot()){
                ConnectFour.placePiece(i);
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }
    }
}
