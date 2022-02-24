package nick.DiscordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import nick.DiscordBot.Commands.Clear;
import nick.DiscordBot.Commands.Help;
import nick.DiscordBot.Events.*;
import nick.DiscordBot.Games.ConnectFour;
import nick.DiscordBot.Games.TicTacToe;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    public static JDA jda;
    // Prefix for command recognition
    public static String prefix = "~";
    // Token taken from system variables
    private static final String token = System.getenv("BOT_TOKEN");

    // MAIN METHOD
    public static void main(String[] args) throws LoginException {
        // Connects to discord bot using token
        jda = JDABuilder.createDefault(token).build();
        // Sets online status and activity
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.watching("~h for commands"));

        // Adds event listeners to the command and event classes
        jda.addEventListener(new Clear());
        jda.addEventListener(new Help());
        jda.addEventListener(new MemberJoin());
        jda.addEventListener(new MemberLeave());
        jda.addEventListener(new MediaVoteReaction());
        jda.addEventListener(new MessageResponse());
        jda.addEventListener(new ConnectFour());
        jda.addEventListener(new MessageReactionAdd());
        jda.addEventListener(new TicTacToe());

    }
}
