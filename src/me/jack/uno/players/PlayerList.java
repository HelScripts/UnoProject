package me.jack.uno.players;

public class PlayerList {

    static Player currentPlayer;
    static boolean reversed = false;

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

    public static boolean addPlayers(int playerCount) {

        if(playerCount < 1 || playerCount > 8) return false;

        Player[] players = new Player[playerCount];
        currentPlayer = new Player();

        for(int i = 0; i < playerCount; i++){
            players[i] = new Player(true, i);
        }

        Player lastPlayer = currentPlayer;

        for(Player player : players){
            lastPlayer.setNext(player);
            player.setPrevious(lastPlayer);
            lastPlayer = player;
        }

        currentPlayer.setPrevious(lastPlayer);
        lastPlayer.setNext(currentPlayer);

        return true;
    }

    public static void processNextPlayer(){
        if(reversed){
            currentPlayer = currentPlayer.getPrevious();
        }else{
            currentPlayer = currentPlayer.getNext();
        }
    }

    public static Player nextPlayer(){
        return reversed ? currentPlayer.getPrevious() : currentPlayer.getNext();
    }

    public static void reverse(){
        reversed = !reversed;
    }


}
