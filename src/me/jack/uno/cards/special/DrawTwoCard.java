package me.jack.uno.cards.special;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.players.Player;

public class DrawTwoCard extends AbstractSpecialCard implements ColoredCard {

    private final CardColor color;

    public DrawTwoCard(CardColor color){
        this.color = color;
    }

    @Override
    public boolean canPlay() {
        return UnoGame.getColor() == color;
    }

    @Override
    public void process() {
        Player player = UnoGame.getCurrentPlayer().getNext();
        for(int i = 0; i < 2; i++){
            player.addCard(UnoGame.drawFromDeck());
        }
    }

    @Override
    public CardColor getColor() {
        return color;
    }
}
