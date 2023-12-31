package me.jack.uno.cards.special;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.cards.types.TypedCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.data.CardType;
import me.jack.uno.players.Player;
import me.jack.uno.players.PlayerList;

public class DrawTwoCard extends AbstractSpecialCard implements ColoredCard, TypedCard {

    private final CardColor color;

    public DrawTwoCard(CardColor color) {
        this.color = color;
    }

    @Override
    public boolean canPlay() {
        return UnoGame.getColor() == color;
    }

    @Override
    public String toString() {
        return color + " DRAW TWO";
    }

    @Override
    public void process() {
        Player player = PlayerList.nextPlayer();
        for(int i = 0; i < 2; i++) {
            player.addCard(UnoGame.drawFromDeck());
        }
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.DRAW_TWO;
    }
}
