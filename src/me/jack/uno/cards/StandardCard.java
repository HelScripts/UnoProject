package me.jack.uno.cards;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.cards.types.NumberedCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.data.CardNumber;

public class StandardCard extends AbstractCard implements ColoredCard, NumberedCard {

    private final CardColor color;
    private final CardNumber number;

    public StandardCard(CardNumber number, CardColor color){
        this.number = number;
        this.color = color;
    }

    @Override
    public boolean canPlay() {
        if(UnoGame.getColor() == color) return true;

        AbstractCard lastPlayed = UnoGame.getLastPlayed();
        if(lastPlayed instanceof NumberedCard numberedCard){
            return numberedCard.getNumber() == number;
        }

        return false;
    }

    @Override
    public CardNumber getNumber() {
        return number;
    }

    @Override
    public CardColor getColor() {
        return color;
    }
}
