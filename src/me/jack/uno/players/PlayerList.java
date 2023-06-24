package me.jack.uno.players;

public class PlayerList {

    static Player currentPlayer;
    static boolean reversed = false;

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

    public static boolean addPlayers(Player... players) {

        currentPlayer = null;
        if(players.length < 2) return false;

        Player lastPlayer = null;
        for(Player player : players){
            if(lastPlayer == null){
                currentPlayer = player;
            }else{
                lastPlayer.setNext(player);
                player.setPrevious(lastPlayer);
            }
            lastPlayer = currentPlayer;
        }

        currentPlayer.setPrevious(lastPlayer);
        lastPlayer.setNext(currentPlayer);

        return true;
    }

    public static void nextPlayer(){
        if(reversed){
            currentPlayer = currentPlayer.getNext();
        }else{
            currentPlayer = currentPlayer.getPrevious();
        }
    }

    public static void reverse(){
        reversed = !reversed;
    }


}
