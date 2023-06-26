package me.jack.uno.cards;

import me.jack.uno.UnoGame;

public abstract class AbstractCard {
    public abstract boolean canPlay();

    public final void play(){
        UnoGame.playCard(this);
    }

    public abstract String toString();

}
