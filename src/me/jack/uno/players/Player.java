package me.jack.uno.players;

import me.jack.uno.cards.AbstractCard;

import java.util.ArrayList;

public class Player {

    private Player next;
    private Player previous;

    private final boolean ai;

    private final ArrayList<AbstractCard> cards = new ArrayList<>();

    public Player(boolean ai){
        this.ai = ai;
    }

    public void setNext(Player next){
        this.next = next;
    }

    public Player getNext(){
        return next;
    }

    public void setPrevious(Player previous){
        this.previous = previous;
    }

    public Player getPrevious(){
        return previous;
    }

    public boolean isAI(){
        return ai;
    }

    public void addCard(AbstractCard card){
        cards.add(card);
    }

    public ArrayList<AbstractCard> getCards(){
        return cards;
    }
}
