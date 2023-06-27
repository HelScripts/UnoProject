package me.jack.uno.cards;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.cards.types.TypedCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.data.CardType;

public class StandardCard extends AbstractCard implements ColoredCard, TypedCard {

    private final CardColor color;
    private final CardType type;

    public StandardCard(CardType type, CardColor color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public boolean canPlay() {
        if(UnoGame.getColor() == color) return true;

        AbstractCard lastPlayed = UnoGame.getLastPlayed();
        if(lastPlayed instanceof TypedCard typedCard) {
            return typedCard.getType() == type;
        }

        return false;
    }

    @Override
    public String toString() {
        return color + " " + type;
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public CardColor getColor() {
        return color;
    }
}
