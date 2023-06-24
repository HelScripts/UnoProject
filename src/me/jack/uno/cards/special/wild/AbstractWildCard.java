package me.jack.uno.cards.special.wild;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.AbstractCard;
import me.jack.uno.cards.special.AbstractSpecialCard;
import me.jack.uno.cards.types.ActionableCard;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.players.Player;
import me.jack.uno.players.PlayerList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractWildCard extends AbstractSpecialCard implements ActionableCard {

    private static final Random random = new Random();
    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public abstract void process();

    public void processPlayer(){
        //TODO: Implement
    }
    public void processAI(){
        Player player = PlayerList.getCurrentPlayer();
        Map<CardColor, Integer> counts = new HashMap<>();

        for(AbstractCard card : player.getCards()){
            if(card instanceof ColoredCard coloredCard){
                int count = counts.getOrDefault(coloredCard.getColor(), 0);
                counts.put(coloredCard.getColor(), ++count);
            }
        }

        Map.Entry<CardColor, Integer> highest = null;
        for(Map.Entry<CardColor, Integer> entry : counts.entrySet()){
            if(highest == null){
                highest = entry;
            }else{
                if(highest.getValue() < entry.getValue()){
                    highest = entry;
                }
            }
        }
        //No Colored Cards
        if(highest == null){
            UnoGame.setColor(CardColor.values()[random.nextInt(CardColor.values().length)]);
        }else{
            // Set as most common card color we have
            UnoGame.setColor(highest.getKey());
        }
    }

}
