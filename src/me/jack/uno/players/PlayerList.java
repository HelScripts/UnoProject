package me.jack.uno.players;

public class PlayerList {

    static Player currentPlayer;
    static boolean reversed = false;

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }


    /**
     *
     * Creates and links all Players of the upcoming game
     * @param botCount Amount of AI players in the game
     * @return If setup was successful
     */
    public static boolean setupPlayers(int botCount) {

        if(botCount < 1 || botCount > 8) return false;

        Player[] bots = new Player[botCount];

        //Setup local player
        currentPlayer = new Player();

        //Initialize bot array
        for(int i = 0; i < botCount; i++) {
            bots[i] = new Player(true, i);
        }

        Player lastPlayer = currentPlayer;

        //Link players to each other
        for(Player player : bots) {
            lastPlayer.setNext(player);
            player.setPrevious(lastPlayer);
            lastPlayer = player;
        }

        //Link head and tail for seamless looping
        currentPlayer.setPrevious(lastPlayer);
        lastPlayer.setNext(currentPlayer);

        return true;
    }

    /**
     *
     * Sets current player to the appropriate next player based on reversed game order
     *
     */
    public static void processNextPlayer() {
        if(reversed) {
            currentPlayer = currentPlayer.getPrevious();
        }else {
            currentPlayer = currentPlayer.getNext();
        }
    }

    /**
     *
     * Used for Draw Two and Draw Four cards to get the appropriate victim
     * @return Next player based on reversed game order
     */
    public static Player nextPlayer(){
        return reversed ? currentPlayer.getPrevious() : currentPlayer.getNext();
    }

    /**
     *
     * Inverse the reversed state of the game
     *
     */
    public static void reverse(){
        reversed = !reversed;
    }


}
