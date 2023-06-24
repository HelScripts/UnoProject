package me.jack.uno.cards.special;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.players.PlayerList;

public class ReverseCard extends AbstractSpecialCard implements ColoredCard {

    private final CardColor color;

    public ReverseCard(CardColor color){
        this.color = color;
    }

    @Override
    public void process() {
        PlayerList.reverse();
    }

    @Override
    public boolean canPlay() {
        return UnoGame.getColor() == color;
    }

    @Override
    public CardColor getColor() {
        return color;
    }
}
