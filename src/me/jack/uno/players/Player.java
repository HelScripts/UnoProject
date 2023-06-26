package me.jack.uno.players;

import me.jack.uno.cards.AbstractCard;

import java.util.ArrayList;

public class Player {

    private Player next;
    private Player previous;

    private final boolean ai;

    private final ArrayList<AbstractCard> cards = new ArrayList<>();

    private final String name;

    private static final String[] botNames = {
            "BOT Stephen",
            "BOT Wolf",
            "BOT Heartbeat",
            "BOT Russ",
            "BOT King",
            "BOT Energy",
            "BOT Zombie",
            "BOT Light"
    };

    public Player(){
        this.ai = false;
        this.name = "You";
    }
    public Player(boolean ai, int id){
        this.ai = ai;
        this.name = botNames[id];
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

    public void removeCard(AbstractCard card){
        cards.remove(card);
    }

    public ArrayList<AbstractCard> getCards(){
        return cards;
    }

    @Override
    public String toString(){
        return name;
    }
}
