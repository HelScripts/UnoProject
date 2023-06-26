package me.jack.uno.cards.special.wild;

import me.jack.uno.UnoGame;
import me.jack.uno.players.Player;

public class DrawFourCard extends AbstractWildCard {

    @Override
    public void process() {
        Player player = UnoGame.getCurrentPlayer().getNext();
        for(int i = 0; i < 4; i++){
            player.addCard(UnoGame.drawFromDeck());
        }
    }

    @Override
    public String toString() {
        return "DRAW FOUR";
    }
}
