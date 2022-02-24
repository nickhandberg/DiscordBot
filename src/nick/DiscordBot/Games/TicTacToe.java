package nick.DiscordBot.Games;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nick.DiscordBot.DiscordBot;

import java.util.Arrays;
import java.util.Objects;

public class TicTacToe extends ListenerAdapter {
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
        if (args[0].equalsIgnoreCase(DiscordBot.prefix + "tictactoe")) {
            turn = 1;
            board = new int[3][3];
            for (int[] ints : board) {
                Arrays.fill(ints, 0);
            }
            displayBoard(event);
        }
    }

    public void onMessageReactionAdd(MessageReactionAddEvent event){
        String[] reactionArray = {"↖","⬆","↗","⬅","⏹","➡","↙","⬇","↘"};
        int[][] choice = {  {0,0},{0,1},{0,2},
                            {1,0},{1,1},{1,2},
                            {2,0},{2,1},{2,2}   };
        for(int i=0; i<reactionArray.length; i++){
            if(event.getReactionEmote().getName().equals(reactionArray[i]) && !Objects.requireNonNull(event.getMember()).getUser().isBot()){
                placePiece(choice[i]);
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }
    }

    public void placePiece(int[] choice){
        if(board[choice[0]][choice[1]] == 0){
            board[choice[0]][choice[1]] = turn;
            updateBoard(checkWin());
        }
    }

    public String getBoardString(){
        String[] icons = {"⚫","❌","🟡"};
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
        embed.setTitle("TicTacToe");
        System.out.println("hit");
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
        embed.setTitle("TicTacToe");
        embed.setColor(0xc72a2a);
        embed.addField("Player "+ turn +"'s turn", String.valueOf(boardString),true);
        event.getChannel().sendMessageEmbeds(embed.build()).queue(message -> {
            message.addReaction("↖").queue();
            message.addReaction("⬆").queue();
            message.addReaction("↗").queue();
            message.addReaction("⬅").queue();
            message.addReaction("⏹").queue();
            message.addReaction("➡").queue();
            message.addReaction("↙").queue();
            message.addReaction("⬇").queue();
            message.addReaction("↘").queue();
        });
        embed.clear();
    }

    public boolean checkWin(){
        //Horizontal
        for(int i =0; i<board.length; i++){
            if(board[i][0] == turn && board[i][1] == turn && board[i][2] == turn){
                return true;
            }
        }

        //Vertical
        for(int i =0; i<board[0].length; i++){
            if(board[0][i] == turn && board[1][i] == turn && board[2][i] == turn){
                return true;
            }
        }

        //Diagonals
        if(board[1][1] == turn){
            if(board[0][0] == turn && board[2][2] == turn){
                return true;
            }
            if(board[2][0] == turn && board[0][2] == turn){
                return true;
            }
        }



        if(turn == 1){turn++;}
        else{turn--;}

        return false;
    }
}
