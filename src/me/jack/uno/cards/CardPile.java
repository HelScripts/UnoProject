package me.jack.uno.cards;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardPile {

    Stack<AbstractCard> pile = new Stack<>();

    public AbstractCard getTop() {
        return pile.peek();
    }

    public void shuffle() {
        Collections.shuffle(pile);
    }

    public void add(AbstractCard card) {
        pile.push(card);
    }

    public AbstractCard draw() {
        return pile.pop();
    }

    public int getSize() {
        return pile.size();
    }

    public void addAll(AbstractCard... cards) {
        pile.addAll(List.of(cards));
    }

    public AbstractCard[] getCards() {
        return pile.toArray(new AbstractCard[0]);
    }

    public void clear() {
        pile.clear();
    }
}
