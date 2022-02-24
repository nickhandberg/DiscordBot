package nick.DiscordBot.Games;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nick.DiscordBot.DiscordBot;

import java.util.Arrays;
import java.util.Objects;

public class ConnectFour extends ListenerAdapter {
    private int[][] board;
    private int turn;
    private MessageReceivedEvent event;
    private final EmbedBuilder embed = new EmbedBuilder();

    public void onMessageReceived(MessageReceivedEvent event) {

        // GETS USER COMMANDS
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(event.getAuthor().isBot()){
            this.event = event;
        }

        // Checks for clear command
        if (args[0].equalsIgnoreCase(DiscordBot.prefix + "connect4")) {
            turn = 1;
            board = new int[6][7];
            for (int[] ints : board) {
                Arrays.fill(ints, 0);
            }
            displayBoard(event);
        }
    }

    public void onMessageReactionAdd(MessageReactionAddEvent event){
        String[] reactionArray = {"1️⃣","2️⃣","3️⃣","4️⃣","5️⃣","6️⃣","7️⃣"};
        for(int i=0; i<reactionArray.length; i++){
            if(event.getReactionEmote().getName().equals(reactionArray[i]) && !Objects.requireNonNull(event.getMember()).getUser().isBot()){
                placePiece(i);
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }
    }


    public void placePiece(int choice){
        for(int i=board.length-1; i>=0; i--){
            if(board[i][choice] == 0){
                board[i][choice] = turn;
                break;
            }
        }
        updateBoard(checkWin());
    }

    public String getBoardString(){
        String[] icons = {"⚫","🔴","🟡"};
        StringBuilder boardString = new StringBuilder();
        for (int[] ints : board) {
            for (int anInt : ints) {
                boardString.append(icons[anInt]);
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    public void updateBoard(boolean win){
        String boardString = getBoardString();
        embed.setTitle("Connect4");
        if(win){
            embed.addField("PLAYER "+ turn +" WINS", String.valueOf(boardString),true);
            event.getMessage().clearReactions().queue();
        }else{
            embed.addField("Player "+ turn +"'s turn", String.valueOf(boardString),true);
        }
        if(turn == 1){
            embed.setColor(0xc72a2a);
        }else{
            embed.setColor(0xccc547);
        }
        event.getMessage().editMessageEmbeds(embed.build()).queue();
        embed.clear();
    }

    public void displayBoard(MessageReceivedEvent event){
        String boardString = getBoardString();
        embed.setTitle("Connect4");
        embed.setColor(0xc72a2a);
        embed.addField("Player "+ turn +"'s turn", String.valueOf(boardString),true);
        event.getChannel().sendMessageEmbeds(embed.build()).queue(message -> {
            message.addReaction("1️⃣").queue();
            message.addReaction("2️⃣").queue();
            message.addReaction("3️⃣").queue();
            message.addReaction("4️⃣").queue();
            message.addReaction("5️⃣").queue();
            message.addReaction("6️⃣").queue();
            message.addReaction("7️⃣").queue();
        });
        embed.clear();
    }

    public boolean checkWin(){
        // Iterates through rows then columns
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                //Stores the current location
                int location = board[row][col];
                // Skips empty locations
                if (location == 0){ continue;}

                //Horizontal
                if (col + 3 < board[0].length && location == board[row][col+1] && location == board[row][col+2] && location == board[row][col+3]){
                    return true;
                }
                if (row + 3 < board.length) {
                    // Vertical
                    if (location == board[row+1][col] && location == board[row+2][col] && location == board[row+3][col]){
                        return true;
                    }
                    // Diagonal leaning right
                    if (col + 3 < board[0].length && location == board[row+1][col+1] && location == board[row+2][col+2] && location == board[row+3][col+3]){
                        return true;
                    }
                    // Diagonal leaning left
                    if (col - 3 >= 0 && location == board[row+1][col-1] && location == board[row+2][col-2] && location == board[row+3][col-3]){
                        return true;
                    }
                }
            }
        }

        if(turn == 1){turn++;}
        else{turn--;}

        return false;
    }
}
