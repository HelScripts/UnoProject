package me.jack.uno.cards.special;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.cards.types.TypedCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.data.CardType;
import me.jack.uno.players.PlayerList;

public class SkipCard extends AbstractSpecialCard implements ColoredCard, TypedCard {

    private final CardColor color;

    public SkipCard(CardColor color){
        this.color = color;
    }

    @Override
    public boolean canPlay() {
        return UnoGame.getColor() == color;
    }

    @Override
    public String toString() {
        return color + " SKIP";
    }

    @Override
    public void process() {
        PlayerList.processNextPlayer();
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.SKIP;
    }
}
